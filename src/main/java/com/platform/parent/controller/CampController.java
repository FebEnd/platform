package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.config.PriceConfig;
import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.mybatis.bean.CampAttend;
import com.platform.parent.mybatis.bean.Tag;
import com.platform.parent.mybatis.bean.Teacher;
import com.platform.parent.mybatis.service.CampAttendService;
import com.platform.parent.mybatis.service.CampService;
import com.platform.parent.mybatis.service.TeacherService;
import com.platform.parent.request.camp.ApplyCampRequest;
import com.platform.parent.response.camp.CampCollection;
import com.platform.parent.response.camp.CampWithTeacher;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.SuccessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @GetMapping(value = "/getPrice")
    public @ResponseBody Object getPrice(@RequestParam("star") String star) {
        JSONObject result = new JSONObject();
        result.put("status","0");
        result.put("start", priceConfig.getSprint().get("start"));
        result.put("end", priceConfig.getSprint().get("end"));
        result.put("rate", priceConfig.getSprint().get("rate"));
        result.put("price", priceConfig.getOrdinary().get(star+""));
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
                    return SuccessCode.APPLY_CAMP_SUCCESSFULLY;
                } else {
                    return ErrorCode.APPLY_CAMP_FAILED;
                }
            } else {
                //添加失败
                return ErrorCode.APPLY_CAMP_FAILED;
            }
        } else {
            //不是认证教师
            return ErrorCode.NOT_TEACHER_ERROR;
        }
    }

    @GetMapping(value = "/getDetail")
    public @ResponseBody Object getDetail(@RequestParam("id") String id) {
        JSONObject result = new JSONObject();
        result.put("status", "0");
        result.put("id","1");
        result.put("type","0");
        result.put("favor","2000");
        result.put("collection","500");
        result.put("member","167");
        result.put("comment","倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。");
        result.put("description","倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。");
        Map<String, BigDecimal> price = new HashMap<>();
        price.put("three",new BigDecimal(600));
        price.put("six",new BigDecimal(1000));
        price.put("twelve",new BigDecimal(2000));
        result.put("price",price);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag().name("学霸家长"));
        tags.add(new Tag().name("奥数获奖"));
        tags.add(new Tag().name("门萨会员"));
        Teacher teacher = new Teacher().id(1).star(5).tags(tags).description("上外附小三年级家长");
        result.put("teacher",teacher);
        return result;
    }

    @GetMapping("/listCampCollection")
    public @ResponseBody Object listCampCollection(@RequestParam("id") String id) {
        JSONObject result = new JSONObject();
        result.put("status", "0");
        List<CampCollection> collection = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher().id(1).description("上外附小三年级家长").star(4));
        CampCollection camp = new CampCollection().id(1).price(new BigDecimal(200.00)).comment("倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。")
                .title("导师  BlueFirefly66 V").subtitle("上外附小三年级家长").teachers(teachers);
        CampCollection camp1 = new CampCollection().id(1).price(new BigDecimal(200.00)).comment("倡导高效快乐的学习方法，注重自主学习习惯的培养，侠客岛成员。")
                .title("导师  BlueFirefly66 V").subtitle("上外附小三年级家长").teachers(teachers);
        collection.add(camp);
        collection.add(camp1);
        result.put("collection", collection);
        return result;
    }

    @PutMapping(value = "/update")
    public @ResponseBody Object updateCamp(@RequestBody Camp camp, @RequestParam("teacherId") String teacherId) {
        Camp query = campService.queryCampById(camp.getId());
        if (query != null) {
            int status = campService.update(camp);
            if (status >0) {
                return SuccessCode.UPDATE_SUCCESSFULLY;
            } else {
                return ErrorCode.UPDATE_FAILED;
            }
        } else {
            return ErrorCode.NO_SUCH_CAMP;
        }
    }

    @PostMapping(value = "/delete")
    public @ResponseBody Object deleteCampsByIds(@RequestParam("ids") String ids) {
        logger.info("ids:\t\t" + ids);
        int status = campService.deleteByIds(ids.split(","));
        if (status > 0) {
            return SuccessCode.DELETE_SUCCESSFULLY;
        } else {
            return ErrorCode.DELETE_FAILED;
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody Object listAllCamps() {
        logger.info("get all camps");
        List<CampWithTeacher> camps = campService.findAllCampsWithTeacher();
        if (camps != null && camps.size() >0) {
            JSONObject result = new JSONObject();
            result.put("status", 0);
            result.put("camps", camps);
            return result;
        } else if (camps.size() == 0){
            return ErrorCode.NO_SUCH_CAMP;
        } else {
            return ErrorCode.QUERY_CAMP_FAILED;
        }
    }
}
