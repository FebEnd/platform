package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.mybatis.bean.Location;
import com.platform.parent.mybatis.bean.School;
import com.platform.parent.mybatis.bean.SchoolAlias;
import com.platform.parent.mybatis.service.LocationService;
import com.platform.parent.mybatis.service.SchoolService;
import com.platform.parent.response.school.SearchResponse;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import com.platform.parent.util.SuccessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/school")
public class SchoolController {

    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    SchoolService schoolService;
    @Autowired
    LocationService locationService;


    @GetMapping(value = "/search")
    public @ResponseBody
    Object searchSchool(@RequestParam("name") String name) {
        if (StringUtil.isNull(name)) {
            return ErrorCode.KEYWORD_IS_NULL;
        } else {
            List<School> result = schoolService.findSchoolFuzzy(name);
        /*List<School> result = new ArrayList<>();
        School school = new School().id(1).fullName("上海世外小学").alias("世外小学").locationId(1);
        School school1 = new School().id(2).fullName("上海世界小学").alias("世界小学").locationId(2);
        result.add(school);
        result.add(school1);*/
            if (result != null && result.size() > 0) {
                return new SearchResponse("0", result);
            } else if (result.size() == 0) {
                return ErrorCode.NO_SUCH_SCHOOL;
            } else {
                return ErrorCode.QUERY_SCHOOL_FAILED;
            }
        }

    }

    @GetMapping(value = "/getHot")
    public @ResponseBody Object getHotSchools(@RequestParam("city") String city) {
        //todo 根据city查询相应城市的热门搜索前九
        JSONObject result = new JSONObject();
        result.put("status","0");
        List<School> schools = new ArrayList<>();
        List<SchoolAlias> alias = new ArrayList<>();
        alias.add(new SchoolAlias().id(1).schoolId(1).alias("世外小学"));
        School school = new School().id(1).heat(5).locationId(1).fullName("上海市世外小学").alias(alias);
        schools.add(school);
        result.put("schools", schools);
        return result;
    }

    @PostMapping(value = "/add")
    public @ResponseBody
    Object addSchool(@RequestBody School school, @RequestBody Location location) {
        int status = schoolService.add(school, location);
        if (status > 0) {
            return SuccessCode.ADD_SUCCESSFULLY;
        } else {
            return ErrorCode.ADD_FAILED;
        }
    }

    @PutMapping(value = "/update")
    public @ResponseBody
    Object updateSchool(@RequestBody School school) {
        int status = schoolService.updateSchool(school);
        if (status > 0) {
            return SuccessCode.UPDATE_SUCCESSFULLY;
        } else {
            return ErrorCode.UPDATE_FAILED;
        }
    }

    @PostMapping(value = "/delete")
    public @ResponseBody
    Object deleteSchool(@RequestParam("ids") String ids) {
        logger.info("ids:\t\t" + ids);
        int status = schoolService.deleteByIds(ids.split(","));
        if (status > 0) {
            return SuccessCode.DELETE_SUCCESSFULLY;
        } else {
            return ErrorCode.DELETE_FAILED;
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody Object listSchools() {
        logger.info("list all schools");
        List<School> schools = schoolService.findAllSchools();
        if (schools!= null && schools.size()>0) {
            return new SearchResponse("0", schools);
        } else {
            return ErrorCode.QUERY_SCHOOL_FAILED;
        }
    }

}
