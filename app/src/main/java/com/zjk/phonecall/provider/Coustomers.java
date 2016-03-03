package com.zjk.phonecall.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by zhongjiakang on 16/2/29.
 */
public final class Coustomers implements BaseColumns{
    public static final String AUTHORITY = "com.zjk.phonecall.provider.CoustomerProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/coustomers");
    public static final String DEFAULT_SORT_ORDER = "id DESC";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String SEX = "sex";
}
