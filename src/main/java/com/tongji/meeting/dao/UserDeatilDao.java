package com.tongji.meeting.dao;

import com.tongji.meeting.model.UserDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDeatilDao {
    void create(UserDetail userDetail);
}
