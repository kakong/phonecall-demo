package com.zjk.phonecall.provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by zhongjiakang on 16/3/2.
 */
public class ContactDetailProvider extends ContactProvider{
    private  DBHelper dbHelper;
    private static final UriMatcher sUriMatcher;
    private  static final int CONTACTDETAIL = 1;
    private  static final int CONTACTDETAIL_ID = 2;

    // 查询列集合
    private static HashMap<String, String> tblProjectionMap;
    static {
        // Uri配合工具类
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ContactDetail.AUTHORITY, "contactdetails", CONTACTDETAIL);
        sUriMatcher.addURI(ContactDetail.AUTHORITY, "contactdetails/#", CONTACTDETAIL_ID);
        // 实例化查询列集合
        tblProjectionMap = new HashMap<String, String>();
        // 添加查询列
        tblProjectionMap.put(ContactDetail._ID, ContactDetail._ID);
        tblProjectionMap.put(ContactDetail.USERID, ContactDetail.USERID);
        tblProjectionMap.put(ContactDetail.USERNAME, ContactDetail.USERNAME);
        tblProjectionMap.put(ContactDetail.COUSTOMERID, ContactDetail.COUSTOMERID);
        tblProjectionMap.put(ContactDetail.COUSTOMERNAME, ContactDetail.COUSTOMERNAME);
        tblProjectionMap.put(ContactDetail.TYPE, ContactDetail.TYPE);
        tblProjectionMap.put(ContactDetail.DURATION, ContactDetail.DURATION);
        tblProjectionMap.put(ContactDetail.DATE, ContactDetail.DATE);
        tblProjectionMap.put(ContactDetail.PHONENUMBER, ContactDetail.PHONENUMBER);
    }

    // 创建时候调用
    public boolean onCreate() {
        // 实例化数据库帮助类
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri,String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)) {
            // 查询所有
            case CONTACTDETAIL:
                qb.setTables(DBHelper.TABLES_CONTACTDETAIL);
                qb.setProjectionMap(tblProjectionMap);
                break;
            // 根据ID查询
            case CONTACTDETAIL_ID:
                qb.setTables(DBHelper.TABLES_CONTACTDETAIL);
                qb.setProjectionMap(tblProjectionMap);
                qb.appendWhere(ContactDetail._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Uri错误！ " + uri);
        }

        // 使用默认排序
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = ContactDetail.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        // 获得数据库实例
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // 返回游标集合
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // 获取数据库实例
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 插入数据，返回行ID
        long rowId = db.insert(DBHelper.TABLES_CONTACTDETAIL,null, values);
        // 如果插入成功返回uri
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(ContactDetail.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //获得数据库实例
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLES_CONTACTDETAIL, null, null);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
