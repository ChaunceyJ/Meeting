package com.tongji.meeting.controller;

import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity addUser(
            @RequestParam(value = "userName", required = true)
                    String userName,
            @RequestParam(value = "openid", required = true)
                    String openid
    ){
        UserDomain userDomain = new UserDomain();
        userDomain.setName(userName);
        userDomain.setOpenid(openid);
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping("")
    public ResponseEntity deleteUser(@RequestParam(value = "userId", required = true) Integer userId){

        userService.deleteUserById(userId);
        return ResponseEntity.ok("删除成功");
    }

    @PutMapping("")
    public ResponseEntity updateUser(
            @RequestParam(value = "userId", required = true)
                    Integer userId,
            @RequestParam(value = "userName", required = false)
                    String userName,
            @RequestParam(value = "openid", required = false)
                    String openid
    ){
        UserDomain userDomain = new UserDomain();
        userDomain.setUserid(userId);
        userDomain.setName(userName);
        userDomain.setOpenid(openid);
        userService.updateUser(userDomain);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping("")
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.selectUsers());
    }

}
