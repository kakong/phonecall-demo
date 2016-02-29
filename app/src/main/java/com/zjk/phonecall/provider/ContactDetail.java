package com.zjk.phonecall.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by zhongjiakang on 16/2/29.
 */
public final class ContactDetail implements BaseColumns {
    public static final String AUTHORITY = "com.zjk.phonecall.provider.CONTACTDETAIL";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contactdetail");
    public static final String DEFAULT_SORT_ORDER = "id DESC";
    public static final String ID = "id";
    public static final String USERID = "userId";
    public static final String COUSTOMERID = "coustomerId";
    public static final String USERNAME = "userName";
    public static final String COUSTOMERNAME = "coustomerName";
    public static final String PHONENUMBER = "phonenumber";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String DURATION = "duration";

}
