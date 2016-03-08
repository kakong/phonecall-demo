package com.zjk.phonecall.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhongjiakang on 16/2/29.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "phoneCall.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLES_USER = "users";
    public static final String TABLES_CUSTOMER = "customers";
    public static final String TABLES_CONTACTDETAIL = "contactdetails";
    public static final String TABLES_CONTACT = "contacts";
    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i(TAG, "DATABASE_VERSION=" + DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate(SQLiteDatabase db)");
        String str = "CREATE TABLE " + TABLES_USER + " ("
                + Users._ID + " INTEGER PRIMARY KEY,"
                + Users.NAME + " TEXT,"
                + Users.PSW + " TEXT"
                + ");";
        db.execSQL(str);
        String strc = "CREATE TABLE " + TABLES_CONTACT + " ("
                + Contacts._ID + " INTEGER PRIMARY KEY,"
                + Contacts.NAME + " TEXT,"
                + Contacts.PHONENUMBER + " TEXT,"
                + Contacts.TYPE + " INTEGER,"
                + Contacts.DURATION + " INTEGER,"
                + Contacts.DATE + " TEXT"
                + ");";
        db.execSQL(strc);
        db.execSQL("CREATE TABLE " + TABLES_CUSTOMER + " ("
                + Coustomers._ID + " INTEGER PRIMARY KEY,"
                + Coustomers.NAME + " TEXT,"
                + Coustomers.SEX + " TEXT,"
                + Coustomers.NUMBER + " TEXT"
                + ");");
        db.execSQL("CREATE TABLE " + TABLES_CONTACTDETAIL + " ("
                + ContactDetail._ID + " INTEGER PRIMARY KEY,"
                + ContactDetail.USERID + " INTEGER,"
                + ContactDetail.COUSTOMERID + " INTEGER,"
                + ContactDetail.USERNAME + " TEXT,"
                + ContactDetail.COUSTOMERNAME + " TEXT,"
                + ContactDetail.PHONENUMBER + " TEXT,"
                + ContactDetail.TYPE + " INTEGER,"
                + ContactDetail.DATE + " TEXT,"
                + ContactDetail.DURATION + " INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS customer");
        db.execSQL("DROP TABLE IF EXISTS contactdetail");
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}
