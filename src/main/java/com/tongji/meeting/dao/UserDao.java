package com.tongji.meeting.dao;

import com.tongji.meeting.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(UserDomain record);

    void deleteUserById(@Param("userid") int userid);

    void updateUser(UserDomain userDomain);

    List<UserDomain> selectUsers();

    List<UserDomain> selectUsersByOpenid(@Param("openid") String openid);

}
