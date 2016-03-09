package com.zjk.phonecall.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zjk.phonecall.entity.CoustomerEntity;
import com.zjk.phonecall.entity.RecordEntity;
import com.zjk.phonecall.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongjiakang on 16/3/3.
 */
public class DBhandle {
    public List<RecordEntity> recordlist;
    public List<UserEntity> userlist;
    public List<CoustomerEntity> coustomerlist;

    public void insertCoustomer(Context mContext, List<CoustomerEntity> clist) {
        DBHelper dbh = new DBHelper(mContext);
        SQLiteDatabase sqldbInsert = dbh.getWritableDatabase();
        for (int i = 0; i < clist.size(); i++) {
            ContentValues values = new ContentValues();
            CoustomerEntity coustomerEntity = clist.get(i);
            values.put(Coustomers.NAME, coustomerEntity.name);
            values.put(Coustomers.NUMBER, coustomerEntity.phoneNumber);
            values.put(Coustomers.SEX, coustomerEntity.sex);
            sqldbInsert.insert(DBHelper.TABLES_CUSTOMER, null, values);
        }
    }
    public void insertListContact(Context context,List<RecordEntity> recordlist){
        DBHelper dbh = new DBHelper(context);
        SQLiteDatabase sqldbInsert = dbh.getWritableDatabase();
        sqldbInsert.execSQL("delete from contacts ");
        for (int i = 0; i < recordlist.size(); i++) {
            String name = "未知";
           RecordEntity recordEntity =  recordlist.get(i);
            ContentValues values = new ContentValues();
            if(recordEntity.name!=null){
                name = recordEntity.name;
            }else {
                values.put(Contacts.NAME,name);
                values.put(Contacts.PHONENUMBER,recordEntity.number);
                values.put(Contacts.TYPE,recordEntity.type);
                values.put(Contacts.DATE, recordEntity.lDate);
                values.put(Contacts.DURATION,recordEntity.duration);
                sqldbInsert.insert(DBHelper.TABLES_CONTACT, null, values);
            }
        }
    }
    public void insertNumberDate(Context mContext,String num,String Date){
        String number = "number";
        String date = "date";
        String table = "callnumberdate";
        DBHelper dbh = new DBHelper(mContext);
        SQLiteDatabase sqldbInsert = dbh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(number, num);
        values.put(date,Date);
        sqldbInsert.insert(table,null,values);
    }

    public List<CoustomerEntity> getCoustomer(Context mcontext, String sql, String[] term) {
        coustomerlist = new ArrayList<CoustomerEntity>();
        DBHelper dbh = new DBHelper(mcontext);
        SQLiteDatabase db = dbh.getReadableDatabase();
        // String str = "select * from contants";
        Cursor cursor = db.rawQuery(sql, term);
        int i = cursor.getCount();
        while (cursor.moveToNext()) {
            CoustomerEntity coustomerEntity = new CoustomerEntity();
            coustomerEntity.name = cursor.getString(cursor.getColumnIndex(Coustomers.NAME));
            coustomerEntity.phoneNumber = cursor.getString(cursor.getColumnIndex(Coustomers.NUMBER));
            coustomerEntity.sex = cursor.getString(cursor.getColumnIndex(Coustomers.SEX));
            coustomerlist.add(coustomerEntity);
        }
        return coustomerlist;
    }

    public void handle(Context mcontext, String sql) {
        DBHelper dbh = new DBHelper(mcontext);
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertContact(Context mContext, RecordEntity re) {
        String name = "未知";
        if (re.name != null) {
            name = re.name;
        } else {
        }
        int _new = re._new;
        ContentValues values = new ContentValues();
        values.put(Contacts.NAME, name);
        values.put(Contacts.PHONENUMBER, re.number);
        values.put(Contacts.TYPE, re.type);
        values.put(Contacts.DATE, re.lDate);
        values.put(Contacts.DURATION, re.duration);
        DBHelper dbh = new DBHelper(mContext);
        SQLiteDatabase sqldbInsert = dbh.getWritableDatabase();
        sqldbInsert.insert(DBHelper.TABLES_CONTACT, null, values);
    }


    //从sqlite获取contact数据
    public List<RecordEntity> getRecord(Context ontext, String sql, String[] term) {
        recordlist = new ArrayList<RecordEntity>();
        DBHelper dbh = new DBHelper(ontext);
        SQLiteDatabase db = dbh.getReadableDatabase();
        // String str = "select * from contants";
        Cursor cursor = db.rawQuery(sql, term);
        int i = cursor.getCount();
        while (cursor.moveToNext()) {
            RecordEntity recordEntity = new RecordEntity();
            recordEntity.name = cursor.getString(cursor.getColumnIndex(ContactDetail.COUSTOMERNAME));
            recordEntity.number = cursor.getString(cursor.getColumnIndex(ContactDetail.PHONENUMBER));
            recordEntity.lDate = cursor.getString(cursor.getColumnIndex(ContactDetail.DATE));
            recordEntity.duration = cursor.getInt(cursor.getColumnIndex(ContactDetail.DURATION));
            recordEntity.type = cursor.getInt(cursor.getColumnIndex(ContactDetail.TYPE));
            recordlist.add(recordEntity);
        }
        return recordlist;
    }
}
