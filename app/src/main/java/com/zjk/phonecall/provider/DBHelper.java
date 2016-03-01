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
    public static final  String TABLES_CONTACT ="contact";
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
        db.execSQL("CREATE TABLE"+TABLES_CONTACT+"("
                + Contact._ID + " INTEGER PRIMARY KEY,"
                + Contact.NAME + "TEXT,"
                + Contact.PHONENUMBER + "TEXT,"
                + Contact.TYPE + "INTEGER"
                + Contact.DATE + "TEXT,"
                + Contact.DURATION + "INTEGER"
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
                + ContactDetail.COUSTOMERNAME + "TEXT"
                + ContactDetail.PHONENUMBER + "TEXT,"
                + ContactDetail.TYPE + "INTEGER"
                + ContactDetail.DATE + "TEXT,"
                + ContactDetail.DURATION + "INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS customer");
        db.execSQL("DROP TABLE IF EXISTS contactdetail");

        onCreate(db);
    }
}
