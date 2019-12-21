package com.tongji.meeting.controller;

import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.UserCalendar;
import com.tongji.meeting.service.CalendarService;
import com.tongji.meeting.service.UserCalendarService;
import com.tongji.meeting.util.redis.RedisUtils;
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

    @GetMapping("/disbandCalendar")
    public ResponseEntity disbandCalendar(
            @RequestParam(value = "calendarId",required = true)int calendarId
    ){
        calendarService.disbandCalendar(calendarId);
        return ResponseEntity.ok("disband calendar successfully");
    }

    @GetMapping("/quitCalendar")
    public ResponseEntity quitCalendar(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value = "calendarId",required = true)Integer calendarId
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        userCalendarService.quitCalendar(userId,calendarId);
        return ResponseEntity.ok("quit calendar successfully");
    }

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

    @GetMapping("/myCreated")
    public ResponseEntity selectCreate(
            @RequestHeader(value = "Authorization",required = true)String sKey
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(calendarService.getCreatedCalendar(userId));
    }

    @GetMapping("/myCalendar")
    public ResponseEntity selectMine(
            @RequestHeader(value = "Authorization",required = true)String sKey
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(calendarService.getMyCalendar(userId));
    }

    @GetMapping("/myParticipated")
    public ResponseEntity selectParticipate(
            @RequestHeader(value = "Authorization",required = true)String sKey
    ){
        int userId = (int)redisUtils.hget(sKey, "userid");
        return ResponseEntity.ok(calendarService.getParticipantCalendar(userId));
    }

    @GetMapping("/members")
    public ResponseEntity member(
            @RequestParam(value="calendarId",required = true )Integer calendarId
    ){
        return ResponseEntity.ok(userCalendarService.getAllMembers(calendarId));
    }
}
