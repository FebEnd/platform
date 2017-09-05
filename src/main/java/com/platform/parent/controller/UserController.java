package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.easemob.api.IMUserAPI;
import com.platform.parent.easemob.api.impl.EasemobIMUser;
import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.mybatis.bean.Teacher;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.bean.UserDetail;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.request.user.ApplyAuthReq;
import com.platform.parent.request.user.CompleteInfoReq;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import com.platform.parent.util.SuccessCode;
import io.swagger.client.model.Nickname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/user")
//@PreAuthorize(value = "hasAnyRole('USER', 'ADMIN')")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CampService campService;
    @Autowired
    CampCollectionService collectionService;
    @Autowired
    UserCouponService couponService;
    @Autowired
    TeacherService teacherService;
    private static final IMUserAPI imUser = new EasemobIMUser();

    @PutMapping(value = "/completeInfo")
    public @ResponseBody
    Object completeInfo(CompleteInfoReq req) {
//        User user = new User().id(userDetail.getId()).phone(phone).nickname(nickname);
        /*System.out.println(req.getId());
        System.out.println(req.getChildBirth());*/
        User user = this.userService.queryUserByIdWithDetail(req.getId());
        user.phone(req.getPhone());
        user.nickname(req.getNickname());
        UserDetail detail = user.getDetail();
        detail.id(req.getId()).childBirth(req.getChildBirth()).childGender(req.getChildGender()).childGrade(req.getChildGrade())
                .childSchool(req.getChildSchool()).city(req.getCity()).liveDistrict(req.getLiveDistrict()).targetDistrict(req.getTargetDistrict());
        int i = this.userService.update(user, detail);
        if (i > 0) {
            String username = req.getPhone();
            Nickname nickname = new Nickname().nickname(req.getNickname());
            imUser.modifyIMUserNickNameWithAdminToken(username, nickname);
            return EnumUtil.succToJson(SuccessCode.COMPLETE_INFO_SUCCESSFULLY);
//            return new MsgResponse("0", "完善资料成功");
        } else {
            return EnumUtil.errorToJson(ErrorCode.COMPLETE_INFO_FAILED);
//            return new MsgResponse("400", "完善资料失败");
        }
        /*int i = userService.update(user);
        if (i != 0) {
            return new MsgResponse("0", "完善资料成功");
        } else {
            return new MsgResponse("203","完善资料失败");
        }*/
    }

    @PostMapping(value = "/applyAuth")
    public @ResponseBody
    Object applyAuth(@RequestBody ApplyAuthReq req) {
        User user = this.userService.queryUserByIdWithDetail(req.getId());
        if (user != null) {
            user.auth(1);
            UserDetail detail = user.getDetail();
            detail.childGrade(req.getChildGrade()).childGender(req.getChildGender()).childSchool(req.getChildSchool());
            int i = this.userService.update(user, detail);
            if (i > 0) {
                return _applyTeacher(req.getId());
            } else {
                return EnumUtil.errorToJson(ErrorCode.APPLY_AUTH_FAILED);
            }
        } else {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
    }

    @PostMapping(value = "/applyTeacher")
    public @ResponseBody
    Object applyTeacher(@RequestParam("id") String id) {
        id = id.trim();
        if (StringUtil.isNumber(id)) {
            long userId = Long.valueOf(id);
            return _applyTeacher(userId);
        } else {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_USER_ID);
        }
    }

    private Object _applyTeacher(long userId) {
        User user = this.userService.queryUserByIdWithDetail(userId);
        if (user != null) {
            Teacher teacher = this.teacherService.findTeacherById(userId);
            if (teacher != null) {
                JSONObject result = new JSONObject();
                result.put("status", 0);
                switch (teacher.getStatus()) {
                    case 1://未审核
                        result.put("code", "ALREADY_APPLIED");
                        result.put("message", "已申请请耐心等待审核");
                        return result;
                    case 2://已通过
                        result.put("code", "ALREADY_PASSED");
                        result.put("message", "已通过审核");
                        return result;
                    case 3://未通过
                        result.put("code","FAILED_TO_PASS");
                        result.put("message","未通过审核");
                        return result;
                    default://未申请
                        teacher.status(1);
                        int i = this.teacherService.update(teacher);
                        if (i > 0) {
                            return EnumUtil.succToJson(SuccessCode.APPLY_TEACHER_SUCCESSFULLY);
                        } else {
                            return EnumUtil.errorToJson(ErrorCode.APPLY_TEACHER_FAILED);
                        }
                }
            } else {
                Teacher teacher1 = new Teacher().id(userId).account("has not set").star(1);
                int i = this.teacherService.add(teacher1);
                if (i > 0) {
                    return EnumUtil.succToJson(SuccessCode.APPLY_TEACHER_SUCCESSFULLY);
                } else {
                    return EnumUtil.errorToJson(ErrorCode.APPLY_TEACHER_FAILED);
                }
            }
        } else {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
    }


    @PostMapping(value = "/getDetail")
    public @ResponseBody
    Object getDetail(@RequestParam("id") String id) {
        id = id.trim();
        if (!StringUtil.isNumber(id)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_USER_ID);
        }
        long userId = Long.valueOf(id);
        User user = this.userService.queryUserByIdWithDetail(userId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (user != null) {
            result.put("status", 0);
            result.put("message", "获取成功");
            data.put("id", id);
            data.put("nickname", user.getNickname());
            data.put("childGrade", user.getDetail().getChildGrade());
        } else {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Camp camp = this.campService.findCampByTeacherId(userId);
        if (camp != null) {
            data.put("application", "未申请");
        } else {
//            0 初始, 1 上线, 2 开课, 3 下线
            switch (camp.getStatus()) {
                case 0:
                    data.put("application", "已申请，审核中");
                    break;
                case 1:
                case 2:
                    data.put("application", "已通过");
                    break;
                case 3:
                default:
                    data.put("application", "未通过");
                    break;
            }
        }
        long collection = this.collectionService.queryCountByUserId(userId);
        data.put("collection", collection);
        /*List<CampCollection> collections = this.collectionService.findCampCollectionsByUserId(userId);
        if (collections != null) {
            data.put("collection", collections.size());
        } else {
            data.put("collection", 0);
        }*/
        int count = this.couponService.findCountUsableByUserId(userId);
        data.put("coupon", count);
        result.put("data", data);
        return result;
    }
}
