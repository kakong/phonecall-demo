package com.zjk.phonecall.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by zhongjiakang on 16/3/1.
 */
public final class Contact implements BaseColumns {
    public static  final  String AUTHORITY = "com.zjk.phonecall.provider.CONTACT";
    public static  final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contact");
    public static String DEFAULT_SORT_ORDER = "id DESC";
    public static  final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONENUMBER = "phonenumber";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String DURATION = "duration";
}
