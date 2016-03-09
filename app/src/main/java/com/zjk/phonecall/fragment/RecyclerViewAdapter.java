package com.zjk.phonecall.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zjk.phonecall.R;
import com.zjk.phonecall.entity.CoustomerEntity;
import com.zjk.phonecall.entity.RecordEntity;
import com.zjk.phonecall.provider.DBhandle;
import com.zjk.phonecall.util.GetDate;
import com.zjk.phonecall.util.GetRecord;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public int listcount = 15;
    private Context mContext;
    final int state1 = 0;
    public int step = 0;
    public int getcoumtersstep = 0;
    public int b=0;
    public List<RecordEntity> mRecordList;
    public List<CoustomerEntity> mCoustomerList, getmCoustomerList;
    private boolean bRingingPhoneState = false;
    // public List<string>
    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        String sql = " delete from customers;";
        String sql3 = "delete from contacts;";
        //输入查询条件语句
        String sql2 = " select * from customers order by _id desc";
        //输入查询的参数
        // 如sql=（select * from contants where phonenumber = ?）,term = {"13631224465"}
        String[] term = null;
        getmCoustomerList = new DBhandle().getCoustomer(mContext, sql2, term);
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_coustomer, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;
        final String coustomer_phonenumber;
        if (getmCoustomerList.size() > 0) {
            CoustomerEntity coustomerEntity = getmCoustomerList.get(position);
            coustomer_phonenumber = coustomerEntity.getPhoneNumber().toString();
            holder.phonenum_tv.setText(coustomer_phonenumber);
            holder.coustomer_sex.setText(coustomerEntity.getSex().toString());
            holder.coustomer_name.setText(coustomerEntity.getName().toString());
            // holder.nu
            holder.callimgbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    b=0;
                    //获取电话号码
                    //意图对象 1.意图的动作用指明交给的那一个应用 2.传输数据
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + coustomer_phonenumber));
                    //把意图传播给操作系统
                    mContext.startActivity(intent);
                    String date;
                    GetDate getDate = new GetDate();
                    date = getDate.getdate();
                    insterNum(coustomer_phonenumber, date);
                    // MainActivity
//                    TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//                    exPhoneCallListener ex = new exPhoneCallListener();
//                    telephonyManager.listen(ex, PhoneStateListener.LISTEN_CALL_STATE);
                }
            });

        }
    }
    public void insterNum(String number,String date){
        DBhandle dbhandle = new DBhandle();
        dbhandle.insertNumberDate(mContext,number,date);
    }
    public class exPhoneCallListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
//          b=0;
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE ://待机
                    b++;
//                    Toast.makeText(mContext,""+b, Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK ://挂断
                    b++;
//                    Toast.makeText(mContext,""+b, Toast.LENGTH_LONG).show();
                    break;
                case TelephonyManager.CALL_STATE_RINGING ://来电话
                    break;
                default:
                    break;
            }
//            Toast.makeText(mContext,""+b, Toast.LENGTH_LONG).show();
            if(b==3){
                inster();
            }
        }
    }

    public void inster() {
        GetRecord getrecord = new GetRecord();
        getrecord.setCount(2);
        mRecordList = getrecord.getRe(mContext);
        DBhandle dbhandle = new DBhandle();
        dbhandle.insertContact(mContext, mRecordList.get(0));

//        String sql ="delete from people \n" +
//                "where peopleName in (select peopleName from people group by peopleName having count(peopleName) > 1) \n" +
//                "and peopleId not in (select min(peopleId) from people group by peopleName having count(peopleName)>1) ";
//        dbhandle.handle(mContext,sql);
    }

    @Override
    public int getItemCount() {
        if (getmCoustomerList.size() < listcount)
            return getmCoustomerList.size();
        else return listcount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton callimgbtn;
        public TextView phonenum_tv, phonenum_state, coustomer_sex, coustomer_name;
        public View mView;
        public ViewHolder(View view) {
            super(view);
            callimgbtn = (ImageButton) view.findViewById(R.id.card_coustomer_call_imgbtn);
            phonenum_tv = (TextView) view.findViewById(R.id.card_coustomer_tel_tv);
            phonenum_state = (TextView) view.findViewById(R.id.card_coustomer_contact_status);
            coustomer_sex = (TextView) view.findViewById(R.id.card_coustomer_sex);
            coustomer_name = (TextView) view.findViewById(R.id.card_coustomer_name_tv);
        }
    }
}
