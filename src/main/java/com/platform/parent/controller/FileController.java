package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.mybatis.bean.Camp;
import com.platform.parent.mybatis.bean.CampAttend;
import com.platform.parent.mybatis.bean.FileRecord;
import com.platform.parent.mybatis.bean.User;
import com.platform.parent.mybatis.service.CampAttendService;
import com.platform.parent.mybatis.service.CampService;
import com.platform.parent.mybatis.service.FileRecordService;
import com.platform.parent.mybatis.service.UserService;
import com.platform.parent.request.file.FileUploadReq;
import com.platform.parent.response.file.FileResponse;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import com.platform.parent.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by tqyao.
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {
    static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    FileRecordService fileRecordService;
    @Autowired
    CampService campService;
    @Autowired
    UserService userService;
    @Autowired
    CampAttendService campAttendService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@Valid FileUploadReq fileUploadReq) {
        User user = this.userService.queryUserById(fileUploadReq.getOwnerId());
        if (user == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        if (fileUploadReq.getCampId() != -1) {
            Camp camp = this.campService.queryCampById(fileUploadReq.getCampId());
            if (camp == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
        }
        FileRecord fileRecord = new FileRecord();
        fileRecord.setCampId(fileUploadReq.getCampId());
        fileRecord.setCreated(new Timestamp(System.currentTimeMillis()));
        fileRecord.setUpdated(new Timestamp(System.currentTimeMillis()));
        fileRecord.setFilename(fileUploadReq.getFilename());
        fileRecord.setOwnerId(fileUploadReq.getOwnerId());
        fileRecord.setSize(fileUploadReq.getSize());
        fileRecord.setUrl(fileUploadReq.getUrl());
        int i = this.fileRecordService.add(fileRecord);
        if (i <= 0) return EnumUtil.errorToJson(ErrorCode.ADD_FAILED);
        FileResponse file = this.fileRecordService.findFileRecordById(fileRecord.getId());
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        data.put("file", file);
        result.put("data", data);

        return result;
    }

    @RequestMapping(value = "/getPrivate", method = RequestMethod.GET)
    @ResponseBody
    public Object getPrivate(@RequestParam("userId") String _userId, @RequestParam("campId") String _campId) {
        if (!StringUtil.isNumber(_userId.trim()) || !StringUtil.isNumber(_campId.trim())) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        long campId = Long.valueOf(_campId);
        User user = this.userService.queryUserById(userId);
        if (user == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        if (campId != -1) {
            Camp camp = this.campService.queryCampById(campId);
            if (camp == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
            List<CampAttend> attend = this.campAttendService.findCampAttendByUserIdAndCampId(userId,campId);
            if (attend == null || attend.size() <= 0) return EnumUtil.errorToJson(ErrorCode.NO_AUTH_ERROR);
        }
        return getFiles(campId);
    }

    private Object getFiles(long campId) {
        List<FileResponse> files = this.fileRecordService.findFileRecordsByCampId(campId);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("files", files);
        result.put("status", 200);
        result.put("message", "成功");
        result.put("data", data);
        return result;
    }

    @RequestMapping(value = "/getPublic", method = RequestMethod.GET)
    @ResponseBody
    public Object getPublic(@RequestParam("userId") String _userId) {
        if (!StringUtil.isNumber(_userId.trim())) {
            return EnumUtil.errorToJson(ErrorCode.ILLEGAL_REQUEST_PARAM);
        }
        long userId = Long.valueOf(_userId);
        User user = this.userService.queryUserById(userId);
        if (user == null) return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        return getFiles(-1);
    }

}
