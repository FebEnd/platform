package com.platform.parent.controller;

import com.alibaba.fastjson.JSONObject;
import com.platform.parent.easemob.api.impl.EasemobChatGroup;
import com.platform.parent.mybatis.bean.*;
import com.platform.parent.mybatis.service.*;
import com.platform.parent.request.order.CancelOrderReq;
import com.platform.parent.request.order.CompleteOrderReq;
import com.platform.parent.request.order.CreateOrderReq;
import com.platform.parent.util.EnumUtil;
import com.platform.parent.util.ErrorCode;
import io.swagger.client.model.UserName;
import io.swagger.client.model.UserNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * Created by tqyao.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    final static EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
    final static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    CampService campService;
    @Autowired
    CampAttendService campAttendService;
    @Autowired
    MemberService memberService;

//    @PostMapping(value = "/createOrder")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object createOrder(CreateOrderReq req) {
        User user = this.userService.queryUserById(req.getUserId());
        if (user == null) {
            logger.error("Find user for id:{} error, no such user", req.getUserId());
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Order order = new Order();
        order.amount(req.getAmount()).userId(req.getUserId()).type(req.getType()).created(new Timestamp(System.currentTimeMillis()))
                .confirm(true).coupons(req.getCouponIds()).duration(req.getDuration());
        int i = this.orderService.add(order);
        if (i <= 0) {
            logger.error("Create order failed. Detail: userId:{}, type:{}, coupons:{}, duration:{}, amount:{}",
                    order.getUserId(), order.getType(), order.getCoupons(), order.getDuration(), order.getAmount());
            return EnumUtil.errorToJson(ErrorCode.CREATE_ORDER_FAILED);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status", 200);
        result.put("message", "成功");
        data.put("paymentId", order.getId());
        data.put("create", order.getCreated());
        data.put("userId", order.getUserId());
        data.put("payed", order.getPayed());
        data.put("coupons", order.getCoupons());
        data.put("confirm", order.isConfirm());
        result.put("data",data);
        return result;
    }

//    @PostMapping(value = "/completeOrder")
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    @ResponseBody
    public Object completePayment(CompleteOrderReq req) {
        User user = this.userService.queryUserById(req.getUserId());
        if (user == null) {
            logger.error("Find user for id:{} error, no such user", req.getUserId());
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Order order = this.orderService.findOrderById(req.getOrderId());
        if (order == null) {
            logger.error("Find order for id:{} error, no such order", req.getOrderId());
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_ORDER);
        }
        if (!order.isConfirm()) {
            //订单已取消
            logger.error("Order for id:{} is already canceled, failed to complete.", req.getOrderId());
            return EnumUtil.errorToJson(ErrorCode.ORDER_ALREADY_CANCELED);
        }
        order.payed(new Timestamp(System.currentTimeMillis()));
        int i = this.orderService.update(order);
        if (i <= 0) {
            logger.error("Order for id:{} complete failed caused by sql error", order.getId());
            return EnumUtil.errorToJson(ErrorCode.COMPLETE_ORDER_FAILED);
        }
        if (order.getType() == -1) {
            //购买vip
            Member member = this.memberService.findMemberById(req.getUserId());
            if (member == null) {
                member = new Member().id(user.getId()).vip(new Timestamp(3600*1000*24*order.getDuration()));
                int j = this.memberService.add(member);
                if (j <= 0) {
                    logger.error("Purchase Vip failed for userId:{}, orderId:{} caused by sql update error", order.getUserId(),order.getId());
                    return EnumUtil.errorToJson(ErrorCode.PURCHASE_VIP_FAILED);
                }
            } else {

            }
        } else {
            Camp camp = this.campService.queryCampById(order.getType());
            if (camp == null) {
                logger.error("Add user to camp id:{} failed caused by no such camp", order.getType());
                return EnumUtil.errorToJson(ErrorCode.NO_SUCH_CAMP);
            }
            String groupId = camp.getGroupId();
            UserNames userNames = new UserNames();
            UserName userList = new UserName();
            userList.add(user.getPhone());
            userNames.usernames(userList);
            System.out.println(groupId);
            Object response = easemobChatGroup.addBatchUsersToChatGroup(groupId, userNames);
            if (response == null) {
                logger.error("Add user to easemob chat group failed, groupId:{}, userId:{}", groupId, user.getId());
                return EnumUtil.errorToJson(ErrorCode.ATTEND_CAMP_FAILED);
            }
            CampAttend campAttend = new CampAttend().userId(order.getUserId()).role(0).campId(order.getType());
            int j = this.campAttendService.add(campAttend);
            if (j <= 0) {
                logger.error("Add user to camp_attend table failed, campId:{}, userId:{}", order.getType(), user.getId());
                return EnumUtil.errorToJson(ErrorCode.ATTEND_CAMP_FAILED);
            }
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status",200);
        result.put("message","成功");
        result.put("data",data);
        return result;
    }

//    @PostMapping(value = "/cancelOrder")
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object cancelOrder(CancelOrderReq req) {
        User user = this.userService.queryUserById(req.getUserId());
        if (user == null) {
            logger.error("Find user for id:{} error, no such user", req.getUserId());
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_USER);
        }
        Order order = this.orderService.findOrderById(req.getOrderId());
        if (order == null) {
            logger.error("Find order for id:{} error, no such order", req.getOrderId());
            return EnumUtil.errorToJson(ErrorCode.NO_SUCH_ORDER);
        }
        order.confirm(false);
        int i = this.orderService.cancelOrder(order);
        if (i <= 0) {
            logger.error("Cancel order failed for id:{} , update databased error", req.getOrderId());
            return EnumUtil.errorToJson(ErrorCode.CANCEL_ORDER_FAILED);
        }
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("status",200);
        result.put("message","成功");
        result.put("data",data);
        return result;

    }

}
