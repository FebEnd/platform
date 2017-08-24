package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.config.PriceConfig;
import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.mybatis.bean.CampAttend;
import com.platform.parent.mybatis.bean.Teacher;
import com.platform.parent.mybatis.service.CampAttendService;
import com.platform.parent.mybatis.service.CampService;
import com.platform.parent.mybatis.service.TeacherService;
import com.platform.parent.request.camp.ApplyCampRequest;
import com.platform.parent.response.camp.CampWithTeacher;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.SuccessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        result.put("price", priceConfig.getOrdinary().get(star));
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
