package com.hujiang.designsupportlibrarydemo;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewAdapter.ViewHolder> {

    private Context mContext;
    private List<RecordEntity> mRecordList;
    public ContactViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ContactViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hadcontact_coustomer, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    public void onBindViewHolder(final ContactViewAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;
        getlianxixinxi();
        TextView name = (TextView)view.findViewById(R.id.hadcontact_coustomer_name);
        TextView phonenumber = (TextView)view.findViewById(R.id.hadcontact_coustomer_num);
        TextView duration = (TextView)view.findViewById(R.id.hadcontact_coustomer_duration);
        TextView lDate = (TextView)view.findViewById(R.id.hadcontact_coustomer_time);
        //mRecordList.get(0);
        if(mRecordList.get(0).getName()==null) {
            name.setText("未知");
        }
        else {
            name.setText(mRecordList.get(0).getName().toString());
        }
        phonenumber.setText(mRecordList.get(0).getNumber().toString());

        duration.setText(""+mRecordList.get(0).getDuration());

        lDate.setText(""+mRecordList.get(0).getlDate());

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
//                animator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
//                    }
//                });
//                animator.start();
//            }
//        });

    }
    public void getlianxixinxi(){
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(
                    // CallLog.Calls.CONTENT_URI, Columns, null,
                    // null,CallLog.Calls.DATE+" desc");
                    CallLog.Calls.CONTENT_URI, null, null, null,
                    CallLog.Calls.DATE + " desc");
            if (cursor == null){

            }

             mRecordList = new ArrayList<RecordEntity>();
            while (cursor.moveToNext()) {
                RecordEntity record = new RecordEntity();
                record.name = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.CACHED_NAME));
                record.number = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.NUMBER));
                record.type = cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.TYPE));
                record.lDate = cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DATE));
                record.duration = cursor.getLong(cursor
                        .getColumnIndex(CallLog.Calls.DURATION));
                record._new = cursor.getInt(cursor
                        .getColumnIndex(CallLog.Calls.NEW));
//                lastnumber += record.toString();
//                record_tv.setText(lastnumber);
                mRecordList.add(record);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
