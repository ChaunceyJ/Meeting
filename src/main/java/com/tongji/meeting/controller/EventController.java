package com.tongji.meeting.controller;

import com.tongji.meeting.model.Event;
import com.tongji.meeting.model.EventDetail;
import com.tongji.meeting.model.UserDetail;
import com.tongji.meeting.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Api("事件模块")
@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    //检查有权创建事件
    //在calendar里

    //再次检查创建权限
    @ApiOperation(value = "创建新事件", notes="某用户在某日历中创建新事件，加入组员在别地方")
    @RequestMapping(value = "/addNewEvent" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity addNewEvent(
            @RequestParam(value = "title")
                    String title,
            @RequestParam(value = "content")
                    String content,
            @RequestParam(value = "priority")
                    int priority,
            @RequestParam(value = "startTime")
                    Timestamp startTime,
            @RequestParam(value = "endTime")
                    Timestamp endTime,
            @RequestParam(value = "calendarId")
                    int calendarId,
            @RequestParam(value = "userId")
                    int userId
    ){
        EventDetail eventDetail = new EventDetail();
        eventDetail.setStartTime(startTime);
        eventDetail.setTitle(title);
        eventDetail.setEndTime(endTime);
        eventDetail.setContent(content);
        Event event = new Event();
        event.setCalendarId(calendarId);
        event.setPriority(priority);
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userId);

        eventService.addNewEvent(eventDetail, event, userDetail);
        return ResponseEntity.ok("ok");
    }

    //在修改页面前先调用一次权限查询
    @ApiOperation(value = "查看修改权限", notes="查看用户是否有可修改某事件权限，返回权限级别")
    @RequestMapping(value = "/checkPermission" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity checkPermission(
            @RequestParam(value = "detailId") int detailId,
            @RequestParam(value = "userId") int userId
    ){
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userId);
        userDetail.setDetailId(detailId);
        //"owner" or "read"
        return ResponseEntity.ok(eventService.checkPermission(userDetail));
    }

    //需要再次核实权限
    @ApiOperation(value = "修改事件", notes="修改事件时间、标题、内容、优先级、移动日历，可能只有部分")
    @RequestMapping(value = "/modifyEvent" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity modifyEvent(
            @RequestParam(value = "detailId") int detailId,
            @RequestParam(value = "eventId") int eventId,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "priority", required = false) int priority,
            @RequestParam(value = "startTime", required = false) Timestamp startTime,
            @RequestParam(value = "endTime", required = false) Timestamp endTime,
            @RequestParam(value = "calendarId", required = false) int calendarId,
            @RequestParam(value = "userId") int userId
    ){
        EventDetail eventDetail = new EventDetail();
        eventDetail.setDetailId(detailId);
        eventDetail.setStartTime(startTime);
        eventDetail.setTitle(title);
        eventDetail.setEndTime(endTime);
        eventDetail.setContent(content);
        Event event = new Event();
        event.setDetailId(detailId);
        event.setEventId(eventId);
        event.setPriority(priority);
        event.setCalendarId(calendarId);
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userId);
        userDetail.setDetailId(detailId);
        return ResponseEntity.ok(eventService.modifyEvent(eventDetail, event, userDetail));
    }

    @ApiOperation(value = "删除事件", notes="含：owner删除、reader删除")
    @RequestMapping(value = "/deleteEvent" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity deleteEvent(
            @RequestParam(value = "userId") int userId
    ){
        return ResponseEntity.ok("Success!");
    }
    //        HashMap result = new HashMap<String, String>();

////        result.put("skey", skey);
////        return ResponseEntity.ok(result);

    @ApiOperation(value = "显示某用户日历事件", notes="会得到用户对各个事件的权限说明")
    @RequestMapping(value = "/showFirstScreen" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity showFirstScreen(
            @RequestParam(value = "userId") int userId
    ){
        return ResponseEntity.ok("Success!");
    }

    @ApiOperation(value = "发送共享事件邀请", notes="")
    @RequestMapping(value = "/shareEvent" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity shareEvent(
            @RequestParam(value = "userId") int userId
    ){
        return ResponseEntity.ok("Success!");
    }

    @ApiOperation(value = "接受共享事件邀请", notes="")
    @RequestMapping(value = "/acceptEvent" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity acceptEvent(
            @RequestParam(value = "userId") int userId
    ){
        return ResponseEntity.ok("Success!");
    }
}
