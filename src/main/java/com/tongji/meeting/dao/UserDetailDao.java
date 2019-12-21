package com.tongji.meeting.dao;

import com.tongji.meeting.model.UserDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailDao {
    void create(UserDetail userDetail);

    void delete(UserDetail userDetail);

    UserDetail retrieveByPK(int userId, int detailId);
}
