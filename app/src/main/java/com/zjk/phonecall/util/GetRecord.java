package com.zjk.phonecall.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import com.zjk.phonecall.entity.RecordEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhongjiakang on 16/2/24.
 */
public class GetRecord {
    public List<RecordEntity> mRecordList;
    public int count = 30;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //private Context mContext;

    public List<RecordEntity> getRe(Context mContext) {

        ContentResolver contentResolver = mContext.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(
                    CallLog.Calls.CONTENT_URI, null, null, null,
                    CallLog.Calls.DATE + " desc");
            if (cursor == null) {

            }

            mRecordList = new ArrayList<RecordEntity>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // for(int i=0;i<listlenght;i++) {
            while (cursor.moveToNext()) {
                RecordEntity record = new RecordEntity();
                record.name = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.CACHED_NAME));
                record.number = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.NUMBER));
                record.type = cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.TYPE));
                Date date = new Date(Long.parseLong(cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.DATE))));
                record.lDate = sdf.format(date);
                record.duration = (int) cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DURATION));
                record._new = cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.NEW));
                mRecordList.add(record);
                if (mRecordList.size() == count) {
                    break;
                }
            }
            //        }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mRecordList;
    }
}
