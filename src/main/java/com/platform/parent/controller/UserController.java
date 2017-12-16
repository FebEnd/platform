package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.easemob.api.IMUserAPI;
import com.platform.parent.easemob.api.impl.EasemobIMUser;
import com.platform.parent.mybatis.bean.*;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.request.user.ApplyAuthReq;
import com.platform.parent.request.user.CompleteInfoReq;
import com.platform.parent.response.user.UserInfoResponse;
import com.platform.parent.util.*;
import io.swagger.client.model.Nickname;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/user")
//@PreAuthorize(value = "hasAnyRole('USER', 'ADMIN')")
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);
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
    @Autowired
    SchoolService schoolService;

    private static final IMUserAPI imUser = new EasemobIMUser();

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateInfo(@RequestParam(required = false, value = "nickname") String nickname,
                             @RequestParam(required = false, value = "avatar") boolean avatar,
                             @RequestParam(value = "userId") String _userId,
                             @RequestParam(required = false, value = "file")MultipartFile file) {
//        System.out.println(avatar);
//        System.out.println(nickname);
//        System.out.println(_userId);
//        if (file == null) System.out.println("no file");
        if ((StringUtil.isNull(nickname)&& (!avatar|| file == null)) || !StringUtil.isNumber(_userId.trim())) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId.trim());
        User user = this.userService.queryUserById(userId);
        if (user == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        if (avatar) {
            String name = user.getPhone() + "_avatar";
            String _filename = file.getOriginalFilename();
            String type = _filename.substring(_filename.lastIndexOf('.'));
            String filename = name + type;
            String path = "C:/platform/avatar/" + filename;
            String url = "http://101.132.71.121:8080/platform/avatar/" + filename;
            Path path1 = Paths.get(path);
            try {
                Files.deleteIfExists(path1);
                Files.copy(file.getInputStream(),path1);
                user.avatar(url);
            } catch (IOException e) {
                logger.error("Copy file {} error, failed.", path1);
                e.printStackTrace();
                return EnumUtil.errorToJson(ErrorCode.COPY_FILE_ERROR);
            }
//            System.out.println(path);
        }
        if (!StringUtil.isNull(nickname)) {
            user.nickname(nickname);
        }
        int i = this.userService.updateAOrN(user);
        if (i <= 0) return EnumUtil.errorToJson(ErrorCode.UPDATE_FAILED);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        result.put("data",data);
        return result;
    }

    @RequestMapping(value = "/completeInfo", method = RequestMethod.POST)
    public @ResponseBody
    Object completeInfo(CompleteInfoReq req) {
//        User user = new User().id(userDetail.getId()).phone(phone).nickname(nickname);
        System.out.println(req.getId());
        System.out.println(req.getChildBirth());
        System.out.println(req.getPhone());
        User user = this.userService.queryUserByIdWithDetail(req.getId());
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        user.phone(req.getPhone());
        user.nickname(req.getNickname());
        UserDetail detail = user.getDetail();
        detail.id(req.getId()).childBirth(DateUtil.sqlDateStringFormat(req.getChildBirth())).childGender(req.getChildGender()).childGrade(req.getChildGrade())
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

    @RequestMapping(value = "/applyAuth", method = RequestMethod.POST)
    public @ResponseBody
    Object applyAuth(ApplyAuthReq req) {
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

//    @PostMapping(value = "/applyTeacher")
    @RequestMapping(value = "/applyTeacher", method = RequestMethod.POST)
    public @ResponseBody
    Object applyTeacher(@RequestParam("id") String id) {
        id = id.trim();
        if (StringUtil.isNumber(id)) {
            long userId = Long.valueOf(id);
            return _applyTeacher(userId);
        } else {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
    }

    private Object _applyTeacher(long userId) {
        User user = this.userService.queryUserByIdWithDetail(userId);
        if (user != null) {
            Teacher teacher = this.teacherService.findTeacherById(userId);
            if (teacher != null) {
                JSONObject result = new JSONObject();
                result.put("status", 200);
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
                Teacher teacher1 = new Teacher().id(userId).account("has not set").star(1).status(1);
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

    @RequestMapping(value = "/getDetailInCamp", method = RequestMethod.GET)
    @ResponseBody
    public Object getDetailInCamp(@RequestParam("userId") String _userId, @RequestParam("targetId") String _targetId) {
        if (!(StringUtil.isNumber(_userId.trim()) && StringUtil.isNumber(_targetId.trim()))) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId.trim());
        long targetId = Long.valueOf(_targetId.trim());
        User user = this.userService.findUserInfoById(targetId);
        User u = this.userService.queryUserById(userId);
        if (user == null || u == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (userId == targetId) {
            UserInfoResponse response = new UserInfoResponse();
            response.setUserId(user.getId());
            response.setChildBirth(user.getDetail().getChildBirth());
            response.setChildGender(user.getDetail().getChildGender());
            response.setChildGrade(user.getDetail().getChildGrade());
            response.setChildSchool(user.getDetail().getChildSchool());
            response.setNickname(user.getNickname());
            response.setPhone(user.getPhone());
            response.setAvatar(user.getAvatar());
            response.setSchoolAlias(user.getDetail().getSchool());
            data.put("userInfo", response);
        } else {
            UserInfoResponse response = new UserInfoResponse();
            response.setUserId(user.getId());
            response.setChildBirth(user.getDetail().getChildBirth());
            response.setChildGender(user.getDetail().getChildGender());
            response.setChildGrade(user.getDetail().getChildGrade());
            response.setChildSchool(user.getDetail().getChildSchool());
            response.setNickname(user.getNickname());
//            response.setPhone(user.getPhone());
            response.setSchoolAlias(user.getDetail().getSchool());
            response.setAvatar(user.getAvatar());
            data.put("userInfo", response);
        }
        result.put("status", 200);
        result.put("message", "成功");
        result.put("data", data);
        return result;
    }


//    @PostMapping(value = "/getDetail")
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public @ResponseBody
    Object getDetail(@RequestParam("id") String id) {
        id = id.trim();
        if (!StringUtil.isNumber(id)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(id);
        User user = this.userService.queryUserByIdWithDetail(userId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        if (user != null) {
            result.put("status", 200);
            result.put("message", "成功");
            data.put("id", user.getId());
            data.put("nickname", user.getNickname());
            data.put("childGrade", user.getDetail().getChildGrade());
            data.put("auth", user.getAuth());
            data.put("avatar", user.getAvatar());
        } else {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Camp camp = this.campService.findCampByTeacherId(userId);
        if (camp == null) {
//            data.put("application", "未申请");
            data.put("application", 0);
        } else {
//            0 初始, 1 上线, 2 开课, 3 下线
            switch (camp.getStatus()) {
                case 0:
//                    data.put("application", "已申请，审核中");
                    data.put("application",1);
                    break;
                case 1:
                case 2:
//                    data.put("application", "已通过");
                    data.put("application",2);
                    break;
                case 3:
                default:
//                    data.put("application", "未通过");
                    data.put("application",3);
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
        Teacher teacher = this.teacherService.findTeacherById(userId);
        if (teacher != null) {
            data.put("teacher", teacher.getStatus());
        } else {
            data.put("teacher", 0);
        }
        School school = this.schoolService.findSchoolById(user.getDetail().getChildSchool());
        /*if (school == null) {
            return EnumUtil.errorToJson(ErrorCode.QUERY_SCHOOL_FAILED);
        }*/
        data.put("childSchool", school);
        result.put("data", data);
        return result;
    }

    //todo 后台的各种操作
}
