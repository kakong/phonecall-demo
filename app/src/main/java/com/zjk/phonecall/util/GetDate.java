package com.zjk.phonecall.util;

import java.util.Calendar;

/**
 * Created by zhongjiakang on 16/3/1.
 */
public class GetDate {

    public String getdate(){
        Calendar calendar = Calendar.getInstance();
        String dated = calendar.get(Calendar.YEAR) + "年"
                + calendar.get(Calendar.MONTH) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日"
                + calendar.get(Calendar.HOUR_OF_DAY) + "时"
                + calendar.get(Calendar.MINUTE) + "分"
                + calendar.get(Calendar.SECOND)+ "秒";
        return dated;
    }
}
