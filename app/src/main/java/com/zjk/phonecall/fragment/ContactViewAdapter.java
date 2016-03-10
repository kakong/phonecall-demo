package com.zjk.phonecall.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjk.phonecall.R;
import com.zjk.phonecall.entity.RecordEntity;
import com.zjk.phonecall.provider.DBhandle;
import com.zjk.phonecall.util.GetRecord;

import java.util.List;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewAdapter.ViewHolder> {

    private Context mContext;
    public List<RecordEntity> mRecordList,lRecordList;
    public int listcount = 15;

    public ContactViewAdapter(Context mContext) {
        this.mContext = mContext;
        // 如sql=（select * from contants where phonenumber = ?）,term = {"13631224465"}
        //输入查询条件语句
        String sql = " select * from contactdetails ";
        //输入查询的参数
        String[] term = null;
        GetRecord getRecord = new GetRecord();
        getRecord.setCount(20);
        DBhandle dBhandle = new DBhandle();
        dBhandle.insertListContact(mContext, getRecord.getRe(mContext));
        String sql2 = "INSERT into contactdetails (coustomername,phonenumber,type,duration,date) select c.name,c.phonenumber,c.type,c.duration,c.date from contacts c,callnumberdate a where   -strftime('%f',datetime(c.date))-strftime('%f',datetime(a.date))<2 and strftime('%f',datetime(c.date))-strftime('%f',datetime(a.date))>-2 and \n" +
                "c.phonenumber = a.number;";
        dBhandle.handle(mContext, sql2);
        String sql3 = " delete from contactdetails where date in (select date from contactdetails group by date having count(*)>1) and _id not in (select max(_id) from contactdetails group by date having count(*)>1) ";
        dBhandle.handle(mContext,sql3);
        mRecordList = dBhandle.getRecord(mContext, sql, term);
    }

    @Override
    public ContactViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hadcontact_coustomer, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    public void onBindViewHolder(final ContactViewAdapter.ViewHolder holders, int i) {
        if (mRecordList.size() > 0) {
            RecordEntity recordEntity = mRecordList.get(i);
            if (recordEntity.getName() == null) {
                holders.name.setText("未知");
            } else {
                holders.name.setText(recordEntity.getName().toString());
            }
            holders.phonenumber.setText(recordEntity.getNumber().toString());

            holders.duration.setText("" + recordEntity.getDuration() + "秒");

            holders.lDate.setText("" + recordEntity.getlDate());
            switch (mRecordList.get(i).getType()) {
                case 0:
                    holders.type.setText("打进");
                    break;
                case 1:
                    holders.type.setText("打出");
                    break;
                case 2:
                    holders.type.setText("未接");
                    break;
                case 3:
                    holders.type.setText("响铃");
                    break;
                default:
                    holders.type.setText("");
            }
        }
    }


    @Override
    public int getItemCount() {
        if (mRecordList.size() < listcount)
            return mRecordList.size();
        else return listcount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phonenumber, duration, lDate, type;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.hadcontact_coustomer_name);
            phonenumber = (TextView) view.findViewById(R.id.hadcontact_coustomer_num);
            duration = (TextView) view.findViewById(R.id.hadcontact_coustomer_duration);
            lDate = (TextView) view.findViewById(R.id.hadcontact_coustomer_time);
            type = (TextView) view.findViewById(R.id.hadcontact_coustomer_type);
        }
    }
}