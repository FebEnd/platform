package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.mybatis.bean.CampCollection;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.bean.UserDetail;
import com.platform.parent.mybatis.service.CampCollectionService;
import com.platform.parent.mybatis.service.CampService;
import com.platform.parent.mybatis.service.UserCouponService;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.request.user.ApplyAuthReq;
import com.platform.parent.request.user.CompleteInfoReq;
import com.platform.parent.response.user.MsgResponse;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.SuccessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return new MsgResponse("0", "完善资料成功");
        } else {
            return new MsgResponse("400", "完善资料失败");
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
        //todo
        return SuccessCode.APPLY_AUTH_SUCCESSFULLY;
    }

    @GetMapping(value = "/applyTeacher")
    public @ResponseBody
    Object applyTeacher(@RequestParam("id") String id) {
        //todo
        return SuccessCode.APPLY_TEACHER_SUCCESSFULLY;
    }

    @GetMapping(value = "/getDetail")
    public @ResponseBody
    Object getDetail(@RequestParam("id") String id) {
        long userId = Long.valueOf(id);
        User user = this.userService.queryUserByIdWithDetail(userId);
        JSONObject result = new JSONObject();
        if (user != null) {
            result.put("id", id);
            result.put("nickname", user.getNickname());
            result.put("childGrade", user.getDetail().getChildGrade());
        } else {
            return ErrorCode.NO_SUCH_USER;
        }
        Camp camp = this.campService.findCampByTeacherId(userId);
        if (camp != null) {
            result.put("application", "未申请");
        } else {
//            0 初始, 1 上线, 2 开课, 3 下线
            switch (camp.getStatus()) {
                case 0:
                    result.put("application", "已申请，审核中");
                    break;
                case 1:
                case 2:
                    result.put("application", "已通过");
                    break;
                case 3:
                default:
                    result.put("application", "未通过");
                    break;
            }
        }
        List<CampCollection> collections = this.collectionService.findCampCollectionsByUserId(userId);
        if (collections != null) {
            result.put("collection", collections.size());
        } else {
            result.put("collection", 0);
        }
        int count  = this.couponService.findCountUsableByUserId(userId);
        result.put("coupon", count);
        return result;
    }
}
