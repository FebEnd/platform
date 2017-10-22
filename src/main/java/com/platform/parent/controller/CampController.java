package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.config.MaxMemberConfig;
import com.platform.parent.config.PriceConfig;
import com.platform.parent.mybatis.bean.*;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.request.camp.AddFavorReq;
import com.platform.parent.request.camp.ApplyCampRequest;
import com.platform.parent.request.camp.PassCampReq;
import com.platform.parent.response.camp.CampList;
import com.platform.parent.response.camp.CampWithGroupId;
import com.platform.parent.response.camp.CampWithTeacher;
import com.platform.parent.response.camp.TopicWithGroupId;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import com.platform.parent.util.SuccessCode;
import io.swagger.client.model.Group;
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
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    CampCollectionService campCollectionService;
    @Autowired
    TopicService topicService;
    @Autowired
    SchoolService schoolService;


    @GetMapping(value = "/getDetail")
    public @ResponseBody Object getDetail(@RequestParam("campId") String _campId, @RequestParam(required = false, value = "userId") String _userId) {
        if (!StringUtil.isNumber(_campId)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long campId = Long.valueOf(_campId);
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
        List<CampCollection> l = this.campCollectionService.findCampCollectionsByCampId(campId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        if (!StringUtil.isNull(_userId)) {
            long userId = Long.valueOf(_userId.trim());
            CampCollection collection = this.campCollectionService.queryCampCollectionByUserIdAndCampId(userId,campId);
            data.put("collected", (collection == null)? false : true);
        }
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
        data.put("title", camp.getTitle());
        data.put("subtitle", camp.getSubtitle());
        data.put("teachers", teachers);
        data.put("tags", tags);
        data.put("collection",(l==null)? 0 : l.size());
        result.put("data", data);
        return result;
    }

//    @GetMapping("/listCampCollection")
    @RequestMapping(value = "/listCampCollection", method = RequestMethod.GET)
    public @ResponseBody Object listCampCollection(@RequestParam("userId") String _userId) {
        if (!StringUtil.isNumber(_userId)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        List<CampList> collection = this.campService.findCampList(userId);
        if (collection == null) {
            return EnumUtil.errorToJson(ErrorCode.QUERY_COLLECTION_FAILED);
        }
//        checkCollected(collection, userId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("collection", collection);
        result.put("status", 200);
        result.put("message", "成功");
        result.put("data", data);
        return result;
    }

    private List<CampList> checkCollected(List<CampList> list, long userId) {
        for (CampList campList : list) {
            CampCollection collection = this.campCollectionService.queryCampCollectionByUserIdAndCampId(userId, campList.getId());
            campList.setCollected((collection == null)? false : true);
        }
        return list;
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
            JSONObject result = new JSONObject();
            JSONObject data = new JSONObject();
            result.put("data", data);
            result.put("status",200);
            result.put("message","成功");
            return result;
        } else {
            return EnumUtil.errorToJson(ErrorCode.ADD_FAVOR_FAILED);
        }
    }

    @RequestMapping(value = "/modifyCollection", method = RequestMethod.POST)
    @ResponseBody
    public Object addCollection(@RequestParam("userId") String _userId, @RequestParam("campId") String _campId, @RequestParam("add") boolean add) {
        if (!(StringUtil.isNumber(_userId) && StringUtil.isNumber(_campId))) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        long campId = Long.valueOf(_campId);
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Camp camp = this.campService.queryCampById(campId);
        if (camp == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("campId", campId);
        data.put("userId", userId);
        result.put("data", data);
        result.put("status",200);
        result.put("message","成功");
        if (add) {
            com.platform.parent.mybatis.bean.CampCollection _collection = this.campCollectionService.queryCampCollectionByUserIdAndCampId(userId,campId);
            if (_collection != null) {
                return EnumUtil.errorToJson(ErrorCode.ALREADY_IN_COLLECTION);
            }
            com.platform.parent.mybatis.bean.CampCollection collection = new com.platform.parent.mybatis.bean.CampCollection().userId(userId).campId(campId);
            int i = this.campCollectionService.add(collection);
            if (i <= 0) {
                return EnumUtil.errorToJson(ErrorCode.ADD_COLLECTION_FAILED);
            }
        } else {
            com.platform.parent.mybatis.bean.CampCollection collection = this.campCollectionService.queryCampCollectionByUserIdAndCampId(userId,campId);
            if (collection == null) return result;
            String[] ids = {collection.getId()+""};
            int i = this.campCollectionService.deleteByIds(ids);
            if (i <= 0) return EnumUtil.errorToJson(ErrorCode.DELETE_FAILED);
        }
        return result;
    }

    @RequestMapping(value = "/cancelCollection", method = RequestMethod.POST)
    @ResponseBody
    public Object cancelCollection(@RequestParam("userId") String _userId, @RequestParam("campId") String _campId) {
        if (!(StringUtil.isNumber(_userId) && StringUtil.isNumber(_campId))) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        long campId = Long.valueOf(_campId);
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Camp camp = this.campService.queryCampById(campId);
        if (camp == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status",200);
        result.put("message", "成功");
        result.put("data",data);
        com.platform.parent.mybatis.bean.CampCollection collection = this.campCollectionService.queryCampCollectionByUserIdAndCampId(userId,campId);
        if (collection == null) return result;
        String[] ids = {collection.getId()+""};
        int i = this.campCollectionService.deleteByIds(ids);
        if (i <= 0) return EnumUtil.errorToJson(ErrorCode.DELETE_FAILED);
        return result;
    }

    @RequestMapping(value = "/searchCamp", method = RequestMethod.GET)
    @ResponseBody
    public Object searchCamp(@RequestParam("schoolId") String _schoolId, @RequestParam(required = false, value = "userId") String _userId) {
        if (!StringUtil.isNumber(_schoolId.trim())) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long schoolId = Long.valueOf(_schoolId);
        School school = this.schoolService.findSchoolById(schoolId);
        if (school == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_SCHOOL);
        List<CampList> campLists = this.campService.findCampListBySchoolId(schoolId);
        if (!StringUtil.isNull(_userId)) {
            long userId = Long.valueOf(_userId);
            checkCollected(campLists, userId);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        data.put("camps", campLists);
        result.put("data",data);
        return result;
    }

    @RequestMapping(value = "/getEssence", method = RequestMethod.GET)
    @ResponseBody
    public Object getEssence(@RequestParam("campId") String _campId) {

    //todo haven't implemented
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        return result;
    }

    @RequestMapping(value = "/listActive", method = RequestMethod.GET)
    @ResponseBody
    public Object listActive(@RequestParam("userId") String _userId) {
        //todo 查询userId 用户已参加的且status=2的训练营，返回groupId和campId
        if (!StringUtil.isNumber(_userId)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        List<CampWithGroupId> camps = this.campService.findCampsActiveByUserId(userId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("camps", camps);
        result.put("status",200);
        result.put("data", data);
        result.put("message", "成功");
        return result;
    }

    @RequestMapping(value = "/listTopicAccessible", method = RequestMethod.GET)
    @ResponseBody
    public Object listTopicAccessible(@RequestParam("userId") String _userId) {
        //todo 查询userId用户已参与的话题且private=0或者owner=user.phone的，返回groupId
        if (!StringUtil.isNumber(_userId)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId.trim());
        User user = this.userService.queryUserById(userId);
        if (user == null) {
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        List<TopicWithGroupId> topics = new ArrayList<>();
        List<Topic> _topics = this.topicService.findTopicAccessible(userId);
        if (_topics == null) {
            data.put("topics", null);
        } else {
            for (Topic topic : _topics) {
                TopicWithGroupId t = new TopicWithGroupId();
                t.setTopicId(topic.getId());
                t.setGroupId(topic.getGroupId());
                t.setName(topic.getName());
                t.setUpdated(topic.getUpdated());
                topics.add(t);
            }
            data.put("topics", topics);
        }
        result.put("status", 200);
        result.put("message", "成功");
        result.put("data",data);
        return result;
    }

    @RequestMapping(value = "/passApply", method = RequestMethod.POST)
    @ResponseBody
    public Object passApply(PassCampReq req) {
        //todo 根据传入参数，获取申请该训练营的导师，并根据导师信息生成title，subtitle，favor=0，status=1
        //todo 存入数据库，并创建环信群聊，name=导师nickname+的训练营
        User user = this.userService.queryUserByIdWithDetail(req.getUserId());
        if (user == null) {
            logger.error("Find user for id:{} failed", req.getUserId());
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Teacher teacher = this.teacherService.findTeacherById(req.getUserId());
        if (teacher == null || teacher.getStatus()!=2) {
            logger.error("User of id:{} is not a teacher yet", req.getUserId());
            return EnumUtil.errorToJson(ErrorCode.NOT_TEACHER_ERROR);
        }
        Camp camp = new Camp();
        camp.comment(req.getComment()).description(req.getDescription()).favor(100).maxLimit(req.getMaxLimit())
                .type(req.getType()).title(req.getTitle()).subtitle(req.getSubtitle()).status(1).minLimit(req.getMinLimit())
                .price0(req.getPrice0()).price1(req.getPrice1()).price2(req.getPrice2());
        int i = this.campService.add(camp);
        if (i <= 0) {
            logger.error("Add new Camp failed for teacher id:{}", req.getUserId());
            return EnumUtil.errorToJson(ErrorCode.ADD_FAILED);
        }
        CampAttend campAttend = new CampAttend();
        campAttend.campId(camp.getId()).userId(user.getId());
        i = this.campAttendService.add(campAttend);
        if (i <= 0) {
            logger.error("Add new camp attend failed for camp id:{}", camp.getId());
            return EnumUtil.errorToJson(ErrorCode.ADD_FAILED);
        }
        Group group = new Group().owner(user.getPhone())._public(true).approval(false).desc("test chat camp").groupname("test group name");
        String cResult = (String)ChatController.easemobChatGroup.createChatGroup(group);
        if (cResult==null) {
            logger.error(cResult);
            return EnumUtil.errorToJson(ErrorCode.ADD_FAILED);
        }
        JSONObject parser = JSONObject.parseObject(cResult);
        JSONObject d = parser.getJSONObject("data");
        camp.groupId(d.getString("groupid"));
        this.campService.update(camp);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status",200);
        result.put("message", "成功");
        data.put("groupId", camp.getGroupId());
        data.put("campId", camp.getId());
        result.put("data", data);
        return result;
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
            result.put("status", 200);
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

    @GetMapping(value = "/getPrice")
    public @ResponseBody Object getPrice(@RequestParam("id") String id) {
        id = id.trim();
        if (!StringUtil.isNumber(id)) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(id);
        Teacher teacher = this.teacherService.findTeacherById(userId);
        if (teacher == null) {
            return EnumUtil.errorToJson(ErrorCode.NOT_TEACHER_ERROR);
        }
        if (teacher.getStatus() != 2) {
            return EnumUtil.errorToJson(ErrorCode.NOT_TEACHER_ERROR);
        }
        JSONObject result = new JSONObject();
        result.put("status",200);
        result.put("message","成功");
        JSONObject data = new JSONObject();
        data.put("start", priceConfig.getSprint().get("start"));
        data.put("end", priceConfig.getSprint().get("end"));
        data.put("rate", priceConfig.getSprint().get("rate"));
        data.put("price", priceConfig.getOrdinary().get(teacher.getStar()));
        result.put("data", data);
        logger.info("Get price config for star:\t" + teacher.getStar());
        return result;
    }
    //申请开班
    @PostMapping(value = "/apply")
    public @ResponseBody Object apply(ApplyCampRequest request){
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
}
