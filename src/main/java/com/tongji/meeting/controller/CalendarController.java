package com.tongji.meeting.controller;

import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.UserCalendar;
import com.tongji.meeting.service.CalendarService;
import com.tongji.meeting.service.UserCalendarService;
import com.tongji.meeting.util.redis.RedisUtils;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @Autowired
    private UserCalendarService userCalendarService;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "创建新日历", notes="")
    @GetMapping("/newCalendar")
    public ResponseEntity newCalendar(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value = "calendarName",required = true)String calendarName
    ){
        System.out.println(sKey);
        int userId = (int)redisUtils.hget(sKey, "userid");
        int newCalendarId;
        Calendar calendar=new Calendar();
        UserCalendar userCalendar=new UserCalendar();
        calendar.setCalendarName(calendarName);
        userCalendar.setUserId(userId);
        userCalendar.setDisturb(false);
        userCalendar.setRole("owner");
        userCalendar.setDetailExposed(false);
        calendarService.createCalendar(calendar);
        newCalendarId=calendar.getCalendarId();
        userCalendar.setCalendarId(newCalendarId);
        userCalendarService.createUCRelation(userCalendar);
        return ResponseEntity.ok(newCalendarId);
}

    @ApiOperation(value = "获取某日历中参与成员信息", notes="")
    @GetMapping("/participateCalendar")
    public ResponseEntity participateCalendar(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value = "calendarId",required = true)Integer calendarId
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        UserCalendar userCalendar=new UserCalendar();
        userCalendar.setUserId(userId);
        userCalendar.setCalendarId(calendarId);
        userCalendar.setDisturb(false);
        userCalendar.setRole("participant");
        userCalendar.setDetailExposed(false);
        userCalendarService.createUCRelation(userCalendar);
        return ResponseEntity.ok("participant calendar successfully");
    }

    @ApiOperation(value = "解散日历工作组", notes="")
    @GetMapping("/disbandCalendar")
    public ResponseEntity disbandCalendar(
            @RequestParam(value = "calendarId",required = true)int calendarId
    ){
        calendarService.disbandCalendar(calendarId);
        return ResponseEntity.ok("disband calendar successfully");
    }

    @ApiOperation(value = "退出日历工作组", notes="")
    @GetMapping("/quitCalendar")
    public ResponseEntity quitCalendar(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value = "calendarId",required = true)Integer calendarId
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        userCalendarService.quitCalendar(userId,calendarId);
        return ResponseEntity.ok("quit calendar successfully");
    }

    @ApiOperation(value = "设置对某日历工作组勿扰模式", notes="")
    @GetMapping("/setNoDisturb")
    public ResponseEntity setDisturb(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value="calendarId",required = true )Integer calendarId,
            @RequestParam(value="disturb",required = true)Boolean disturb
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        userCalendarService.setNoDisturb(userId,calendarId,disturb);
        return ResponseEntity.ok("set the disturb mode successfully");
    }

    @ApiOperation(value = "设置对日历工作组隐私模式", notes="")
    @GetMapping("/setDetailExposed")
    public ResponseEntity setDetailExposed(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value="calendarId",required = true )Integer calendarId,
            @RequestParam(value="detailExposed",required = true)Boolean detailExposed
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        userCalendarService.setDetailExposed(userId,calendarId,detailExposed);
        return ResponseEntity.ok("set the detail exposed successfully");
    }

    @ApiOperation(value = "获得我创建的日历工作组", notes="")
    @GetMapping("/myCreated")
    public ResponseEntity selectCreate(
            @RequestHeader(value = "Authorization",required = true)String sKey
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(calendarService.getCreatedCalendar(userId));
    }

    @ApiOperation(value = "获得我的个人日历", notes="")
    @GetMapping("/myCalendar")
    public ResponseEntity selectMine(
            @RequestHeader(value = "Authorization",required = true)String sKey
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(calendarService.getMyCalendar(userId));
    }

    @ApiOperation(value = "获得我参与的日历工作组", notes="")
    @GetMapping("/myParticipated")
    public ResponseEntity selectParticipate(
            @RequestHeader(value = "Authorization",required = true)String sKey
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(calendarService.getParticipantCalendar(userId));
    }

    @ApiOperation(value = "获得某日历工作组中所有成员", notes="")
    @GetMapping("/members")
    public ResponseEntity member(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value="calendarId",required = true )Integer calendarId
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(userCalendarService.getAllMembers(userId,calendarId));
    }

    @ApiOperation(value = "获得对日历工作组勿扰模式的设置情况", notes="")
    @GetMapping("/disturbStatus")
    public ResponseEntity getDisturbStatus(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value="calendarId",required = true )Integer calendarId
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(userCalendarService.getDisturbStatus(userId,calendarId));
    }
}
