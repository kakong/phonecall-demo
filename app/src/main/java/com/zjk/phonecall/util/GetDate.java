package com.zjk.phonecall.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zhongjiakang on 16/3/1.
 */
public class GetDate {

    public String getdate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dated = dateFormat.format(calendar.getTime());
        return dated;
    }
}
