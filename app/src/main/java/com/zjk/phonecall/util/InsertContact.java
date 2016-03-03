package com.zjk.phonecall.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zjk.phonecall.entity.RecordEntity;
import com.zjk.phonecall.provider.Contacts;
import com.zjk.phonecall.provider.DBHelper;

/**
 * Created by zhongjiakang on 16/3/2.C
 */
public class InsertContact {
    public static void insertContact(RecordEntity re,Context mContext) {
        String name="未知";
        if(re.name!=null){
            name = re.name;
        }else{
        }
        String numebr = re.number;
        int type = re.type;
        String date = re.lDate;
        long duration = re.duration;
        int _new = re._new;
        ContentValues values = new ContentValues();
        values.put(Contacts.NAME, name);
        values.put(Contacts.PHONENUMBER, numebr);
        values.put(Contacts.TYPE, type);
        values.put(Contacts.DATE, date);
        values.put(Contacts.DURATION, duration);
        DBHelper dbh = new DBHelper(mContext);
        SQLiteDatabase sqldbInsert = dbh.getWritableDatabase();
        sqldbInsert.insert(DBHelper.TABLES_CONTACT,null,values);

//
//          ContentResolver cr = con.getContentResolver();
//          cr.insert(Contacts.CONTENT_URI,values);
    }
}
