package com.zjk.phonecall.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zhongjiakang on 16/2/29.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="phoneCall.db";
    private static final  int DATABASE_VERSION =1;
    public static final String TABLES_USER = "user";
    public static final String TABLES_CUSTOMER = "customer";
    public static final  String TABLES_CONTACTDETAIL ="contactdetail";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE"+TABLES_USER+"("
                + Users._ID + " INTEGER PRIMARY KEY,"
                + Users.NAME + "TEXT,"
                + Users.PSW + "TEXT"
                + ");");
        db.execSQL("CREATE TABLE"+TABLES_CUSTOMER+"("
                + Coustomers._ID + " INTEGER PRIMARY KEY,"
                + Coustomers.NAME + "TEXT,"
                + Coustomers.SEX + "TEXT"
                + Coustomers.NUMBER + "TEXT"
                + ");");
        db.execSQL("CREATE TABLE"+TABLES_CONTACTDETAIL+"("
                + ContactDetail._ID + " INTEGER PRIMARY KEY,"
                + ContactDetail.USERID + "INTEGER,"
                + ContactDetail.COUSTOMERID + "INTEGER"
                + ContactDetail.USERNAME + "TEXT,"
                + ContactDetail.C + "TEXT"
                + ContactDetail.NAME + "TEXT,"
                + ContactDetail.PSW + "TEXT"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
