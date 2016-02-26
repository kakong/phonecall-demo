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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewAdapter.ViewHolder> {

    private Context mContext;
    private List<RecordEntity> mRecordList;
    public int listlenght=15;
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

    public void onBindViewHolder(final ContactViewAdapter.ViewHolder holders, int i) {
       // final View view = holder.mView;
        //ViewModel item = items.get(position);
        // ViewHolder holders = (ViewHolder) holder;
        getlianxixinxi();
        holders.position = 0;
        RecordEntity recordEntity = mRecordList.get(i);
        if(recordEntity.getName()==null) {
            holders.name.setText("未知");
        }
        else {
            holders.name.setText(recordEntity.getName().toString());
        }
        holders.phonenumber.setText(recordEntity.getNumber().toString());

        holders.duration.setText(""+ recordEntity.getDuration()+"秒");

        holders.lDate.setText("" + recordEntity.getlDate());
        switch (recordEntity.getType()){
            case  0:holders.type.setText("打进");break;
            case  1:holders.type.setText("打出");break;
            case  2:holders.type.setText("未接");break;
            case  3:holders.type.setText("响铃");break;
            default:holders.type.setText("");
        }



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
                    CallLog.Calls.CONTENT_URI, null, null, null,
                    CallLog.Calls.DATE + " desc");
            if (cursor == null){

            }

            mRecordList = new ArrayList<RecordEntity>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(int i=0;i<listlenght;i++) {
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
                    record.duration = cursor.getLong(cursor
                            .getColumnIndex(CallLog.Calls.DURATION));
                    record._new = cursor.getInt(cursor
                            .getColumnIndex(CallLog.Calls.NEW));
                    mRecordList.add(record);
                }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public TextView name,phonenumber,duration,lDate,type;
        public int position;
        public ViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.hadcontact_coustomer_name);
            phonenumber = (TextView)view.findViewById(R.id.hadcontact_coustomer_num);
            duration = (TextView)view.findViewById(R.id.hadcontact_coustomer_duration);
            lDate = (TextView)view.findViewById(R.id.hadcontact_coustomer_time);
            type =(TextView)view.findViewById(R.id.hadcontact_coustomer_type);
            //mRecordList.get(0);

        }
    }
}