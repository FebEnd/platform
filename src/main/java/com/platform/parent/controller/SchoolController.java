package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.NewsConfig;
import com.platform.parent.mybatis.bean.Location;
import com.platform.parent.mybatis.bean.Order;
import com.platform.parent.mybatis.bean.School;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.response.school.CampWithTitle;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import com.platform.parent.util.SuccessCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/school")
public class SchoolController {

    private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);
    static final int SPECIAL = 1, ORG = 2;

    @Autowired
    SchoolService schoolService;
    @Autowired
    LocationService locationService;
    @Autowired
    CampService campService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    NewsConfig newsConfig;

    @RequestMapping(value = "/getDiscovery", method = RequestMethod.GET)
    @ResponseBody
    public Object getDiscovery(@RequestParam(value = "city", defaultValue = "上海市") String city) {
        Map<String, String> params = new HashMap<>();
        params.put("city", city);
        List<Location> locations = this.locationService.findLocationByParams(params);
        if (locations == null || locations.size() <= 0) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CITY);
        }
        List<School> schools = this.schoolService.findHotSchool(city);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("message", "成功");
        result.put("status", 200);
        if (schools != null && schools.size()>0) {
            data.put("schools", schools);
        } else {
            data.put("schools", null);
        }
        List<CampWithTitle> specials = this.campService.findCampByTypeWithTitle(SPECIAL, city);
        if (specials != null && specials.size() >0) {
            data.put("specials", specials);
        } else {
            data.put("specials", null);
        }
        List<CampWithTitle> orgs = this.campService.findCampByTypeWithTitle(ORG, city);
        if (orgs != null && orgs.size()>0) {
            data.put("orgs", orgs);
        } else {
            data.put("orgs", null);
        }
        List<Order> orders = this.orderService.findCompleteOrderForCamp();
        ArrayList<String> news = new ArrayList<>();
        if (orders != null && orders.size()>0) {
            for (Order order : orders) {
                User user = this.userService.queryUserById(order.getUserId());
                List<User> teacher = this.userService.findUserByCampId(order.getType());
                String s = user.getNickname() + " " + teacher.get(0).getNickname() ;
                news.add(s);
            }
            generateRandomNews(news);
            System.out.println("here");
            data.put("news", news);
        } else {
            generateRandomNews(news);
            data.put("news", news);
        }
        result.put("data", data);
        return result;

    }

    private void generateRandomNews(ArrayList<String> news) {
        if (news.size() >= 20) return ;
        int i = 20 - news.size();
        for (int j = 0; j < i; j++) {
            int random = new Random().nextInt(20);
            String s = newsConfig.getName().get(random);
            System.out.println(s);
            news.add(s);
        }
    }


    @GetMapping(value = "/search")
    public @ResponseBody
    Object searchSchool(@RequestParam("name") String name, @RequestParam("city") String city) {
        if (StringUtil.isNull(name)|| StringUtil.isNull(city)) {
            return EnumUtil.errorToJson(ErrorCode.KEYWORD_IS_NULL);
        } else {
            List<School> schools = schoolService.findSchoolFuzzy(name, city);
        /*List<School> result = new ArrayList<>();
        School school = new School().id(1).fullName("上海世外小学").alias("世外小学").locationId(1);
        School school1 = new School().id(2).fullName("上海世界小学").alias("世界小学").locationId(2);
        result.add(school);
        result.add(school1);*/
            if (schools != null && schools.size() > 0) {
                return combine(schools);
            } else if (schools.size() == 0) {
                return EnumUtil.errorToJson(ErrorCode.NO_SUCH_SCHOOL);
            } else {
                return EnumUtil.errorToJson(ErrorCode.QUERY_SCHOOL_FAILED);
            }
        }
    }

    private Object combine(List<School> schools) {
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        data.put("schools", schools);
        result.put("data",data);
        return result;
    }

    @GetMapping(value = "/getHot")
    public @ResponseBody Object getHotSchools(@RequestParam("city") String city) {
        //todo 根据city查询相应城市的热门搜索前九
        if (StringUtil.isNull(city)) {
            return EnumUtil.errorToJson(ErrorCode.KEYWORD_IS_NULL);
        }
        Map<String, String> params = new HashMap<>();
        params.put("city", city);
        List<Location> locations = this.locationService.findLocationByParams(params);
        if (locations == null || locations.size() <= 0) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CITY);
        }
        List<School> schools = this.schoolService.findHotSchool(city);
        if (schools != null && schools.size()>0) {
            return combine(schools);
        } else {
            return EnumUtil.errorToJson(ErrorCode.QUERY_SCHOOL_FAILED);
        }
    }


    @PostMapping(value = "/add")
    public @ResponseBody
    Object addSchool(@RequestBody School school, @RequestBody Location location) {
        int status = schoolService.add(school, location);
        if (status > 0) {
            return EnumUtil.succToJson(SuccessCode.ADD_SUCCESSFULLY);
        } else {
            return EnumUtil.errorToJson(ErrorCode.ADD_FAILED);
        }
    }

    @PutMapping(value = "/update")
    public @ResponseBody
    Object updateSchool(@RequestBody School school) {
        int status = schoolService.updateSchool(school);
        if (status > 0) {
            return EnumUtil.succToJson(SuccessCode.UPDATE_SUCCESSFULLY);
        } else {
            return EnumUtil.errorToJson(ErrorCode.UPDATE_FAILED);
        }
    }

    @PostMapping(value = "/delete")
    public @ResponseBody
    Object deleteSchool(@RequestParam("ids") String ids) {
        logger.info("ids:\t\t" + ids);
        int status = schoolService.deleteByIds(ids.split(","));
        if (status > 0) {
            return EnumUtil.succToJson(SuccessCode.DELETE_SUCCESSFULLY);
        } else {
            return EnumUtil.errorToJson(ErrorCode.DELETE_FAILED);
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody Object listSchools() {
        logger.info("list all schools");
        List<School> schools = schoolService.findAllSchools();
        if (schools!= null && schools.size()>0) {
            return combine(schools);
        } else {
            return EnumUtil.errorToJson(ErrorCode.QUERY_SCHOOL_FAILED);
        }
    }

}
