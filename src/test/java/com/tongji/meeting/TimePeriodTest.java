package com.tongji.meeting;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TimePeriodTest {

    @Test
    public void compareTo() throws ParseException {
        String dateString1 = "2019-04-30 15:59:10";
        String dateString2 = "2019-05-01 15:59:10";
        String dateString3 = "2019-04-25 15:59:10";
        String dateString4 = "2019-04-27 15:59:10";
        String dateString5 = "2019-04-30 15:00:10";
        String dateString6 = "2019-04-30 12:59:10";
        String dateString7 = "2019-04-03 15:59:10";
        String dateString8 = "2019-04-01 15:59:10";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 =format.parse(dateString1);
        Date date2 =format.parse(dateString2);
        Date date3 =format.parse(dateString3);
        Date date4 =format.parse(dateString4);
        Date date5 =format.parse(dateString5);
        Date date6 =format.parse(dateString6);
        Date date7 =format.parse(dateString1);
        Date date8 =format.parse(dateString7);

        TimePeriod a = new TimePeriod(date1,date2);
        TimePeriod b = new TimePeriod(date2,date2);
        TimePeriod c = new TimePeriod(date3,date2);
        TimePeriod d = new TimePeriod(date4,date2);
        TimePeriod e = new TimePeriod(date5,date2);
        TimePeriod f = new TimePeriod(date6,date2);

        List<TimePeriod> listss = new ArrayList<>();
        listss.add(a);
        listss.add(b);
        listss.add(c);
        listss.add(d);
        listss.add(e);
        listss.add(f);


        Collections.sort(listss);



    }
}