package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventDetail;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.soap.Detail;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class EventDetailDaoTest {

    @Test
    public void insertEventDetail() {
//        SqlSession sqlSession = getSessionFactory().openSession();
//        EventDetailDao detailMapper = sqlSession.getMapper(EventDetailDao.class);
//        EventDetail eventDetail = new EventDetail();
//        eventDetail.setContent("sfsdf");
//        //eventDetail.setDetailId(13);
//        eventDetail.setStartTime(new Timestamp(System.currentTimeMillis()));
//        eventDetail.setEndTime(new Timestamp(System.currentTimeMillis()));
//        eventDetail.setRepeat(false);
//        eventDetail.setTitle("sdfdsf");
//
//        int result = detailMapper.insertEventDetail(eventDetail);
//        //Assert.assertNotNull(result);
    }

    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "mybatis/mybatis-config.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}