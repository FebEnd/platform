package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.config.PriceConfig;
import com.platform.parent.mybatis.bean.*;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.request.camp.AddFavorReq;
import com.platform.parent.request.camp.ApplyCampRequest;
import com.platform.parent.response.camp.CampList;
import com.platform.parent.response.camp.CampWithTeacher;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import com.platform.parent.util.SuccessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/camp")
public class CampController {

    private static final Logger logger = LoggerFactory.getLogger(CampController.class);
    @Autowired
    MaxMemberConfig memberConfig;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CampService campService;
    @Autowired
    CampAttendService campAttendService;
    @Autowired
    PriceConfig priceConfig;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    CampCollectionService campCollectionService;

    @GetMapping(value = "/getPrice")
    public @ResponseBody Object getPrice(@RequestParam("star") String star) {
        if (!StringUtil.isNumber(star)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        } else {
            int s = Integer.valueOf(star);
            if (s < 1 && s > 5) {
                return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
            }
        }
        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("start", priceConfig.getSprint().get("start"));
        data.put("end", priceConfig.getSprint().get("end"));
        data.put("rate", priceConfig.getSprint().get("rate"));
        data.put("price", priceConfig.getOrdinary().get(star+""));
        result.put("data", data);
        logger.info("Get price config for star:\t" + star);
        return result;
    }
    //申请开班
    @PostMapping(value = "/apply")
    public @ResponseBody Object apply(@RequestBody ApplyCampRequest request){
        Teacher teacher = teacherService.findTeacherById(request.getUserId());
        if (teacher != null) {
            //已经是认证教师
            int star = teacher.getStar();
            Camp camp = new Camp().status(0).maxLimit(memberConfig.getMaxMember(star)).minLimit(0).price0(request.getPrice0())
                    .price1(request.getPrice1()).price2(request.getPrice2());
            int status = campService.add(camp);
            if (status >0) {
                //添加成功
                CampAttend campAttend = new CampAttend().campId(camp.getId()).role(2).userId(request.getUserId()).expiration(null);
                int index = campAttendService.add(campAttend);
                if (index >0) {
                    return EnumUtil.succToJson(SuccessCode.APPLY_CAMP_SUCCESSFULLY);
                } else {
                    return EnumUtil.errorToJson(ErrorCode.APPLY_CAMP_FAILED);
                }
            } else {
                //添加失败
                return EnumUtil.errorToJson(ErrorCode.APPLY_CAMP_FAILED);
            }
        } else {
            //不是认证教师
            return EnumUtil.errorToJson(ErrorCode.NOT_TEACHER_ERROR);
        }
    }

    @GetMapping(value = "/getDetail")
    public @ResponseBody Object getDetail(@RequestParam("id") String id) {
        if (!StringUtil.isNumber(id)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long campId = Long.valueOf(id);
        Camp camp = this.campService.queryCampById(campId);
        if (camp == null) {
            return EnumUtil.errorToJson(ErrorCode.QUERY_CAMP_FAILED);
        }
        long member = this.campAttendService.countCampAttendByCampId(campId);
        if (member == 0l) {
            return EnumUtil.errorToJson(ErrorCode.QUERY_MEMBER_FAILED);
        }
        List<Teacher> teachers = this.teacherService.findTeachersByCampId(campId);
        if (teachers == null || teachers.size() < 1) {
            return EnumUtil.errorToJson(ErrorCode.QUERY_TEACHER_FAILED);
        }
        List<Tag> tags = this.tagService.findTagsByCampId(campId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", "0");
        result.put("message", "成功");
        data.put("id", camp.getId());
        data.put("type", camp.getType());
        data.put("favor", camp.getFavor());
        data.put("member",member);
        data.put("comment",camp.getComment());
        data.put("description", camp.getDescription());
        Map<String, BigDecimal> price = new HashMap<>();
        price.put("three",camp.getPrice0());
        price.put("six", camp.getPrice1());
        price.put("twelve",camp.getPrice2());
        data.put("price", price);
        data.put("max", camp.getMaxLimit());
        data.put("min", camp.getMinLimit());
        data.put("teachers", teachers);
        data.put("tags", tags);
        result.put("data", data);
        return result;
        /*result.put("id","1");
        result.put("type","0");
        result.put("favor","2000");
        result.put("collection","500");
        result.put("member","167");
        result.put("comment","倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。");
        result.put("description","倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。");
        result.put("price",price);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag().name("学霸家长"));
        tags.add(new Tag().name("奥数获奖"));
        tags.add(new Tag().name("门萨会员"));
        Teacher teacher = new Teacher().id(1).star(5).tags(tags).description("上外附小三年级家长");
        result.put("teacher",teacher);
        return result;*/
    }

    @GetMapping("/listCampCollection")
    public @ResponseBody Object listCampCollection(@RequestParam("id") String id) {
        if (StringUtil.isNumber(id)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(id);
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        List<CampList> collection = this.campService.findCampList(userId);
        if (collection == null) {
            return EnumUtil.errorToJson(ErrorCode.QUERY_COLLECTION_FAILED);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("collection", collection);
        result.put("status", 0);
        result.put("message", "成功");
        result.put("data", data);
        /*result.put("status", "0");
        List<CampCollection> collection = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher().id(1).description("上外附小三年级家长").star(4));
        CampCollection camp = new CampCollection().id(1).price(new BigDecimal(200.00)).comment("倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。")
                .title("导师  BlueFirefly66 V").subtitle("上外附小三年级家长").teachers(teachers);
        CampCollection camp1 = new CampCollection().id(1).price(new BigDecimal(200.00)).comment("倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。")
                .title("导师  BlueFirefly66 V").subtitle("上外附小三年级家长").teachers(teachers);
        collection.add(camp);
        collection.add(camp1);
        result.put("collection", collection);*/
        return result;
    }

    @PostMapping(value = "/addFavor")
    @ResponseBody
    public Object addFavor(AddFavorReq req) {
        Camp camp = this.campService.queryCampById(req.getCampId());
        if (camp == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
        }
        User user = this.userService.queryUserById(req.getUserId());
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        int i = this.campService.addFavor(req.getCampId());
        if (i > 0) {
            return EnumUtil.succToJson(SuccessCode.ADD_FAVOR_SUCCESSFULLY);
        } else {
            return EnumUtil.errorToJson(ErrorCode.ADD_FAVOR_FAILED);
        }
    }

    @PutMapping(value = "/update")
    public @ResponseBody Object updateCamp(@RequestBody Camp camp, @RequestParam("teacherId") String teacherId) {
        Camp query = campService.queryCampById(camp.getId());
        if (query != null) {
            int status = campService.update(camp);
            if (status >0) {
                return EnumUtil.succToJson(SuccessCode.UPDATE_SUCCESSFULLY);
            } else {
                return EnumUtil.errorToJson(ErrorCode.UPDATE_FAILED);
            }
        } else {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
        }
    }

    @PostMapping(value = "/delete")
    public @ResponseBody Object deleteCampsByIds(@RequestParam("ids") String ids) {
        logger.info("ids:\t\t" + ids);
        int status = campService.deleteByIds(ids.split(","));
        if (status > 0) {
            return EnumUtil.succToJson(SuccessCode.DELETE_SUCCESSFULLY);
        } else {
            return EnumUtil.errorToJson(ErrorCode.DELETE_FAILED);
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody Object listAllCamps() {
        logger.info("get all camps");
        List<CampWithTeacher> camps = campService.findAllCampsWithTeacher();
        if (camps != null && camps.size() >0) {
            JSONObject result = new JSONObject();
            JSONObject data = new JSONObject();
            result.put("status", 0);
            result.put("message", "成功");
            data.put("camps", camps);
            result.put("data", data);
            return result;
        } else if (camps.size() == 0){
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
        } else {
            return EnumUtil.errorToJson(ErrorCode.QUERY_CAMP_FAILED);
        }
    }
}
