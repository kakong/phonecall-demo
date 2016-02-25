package com.hujiang.designsupportlibrarydemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationZ", 20, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
                    }
                });
                animator.start();
            }
        });
        final ImageButton callimgbtn = (ImageButton)view.findViewById(R.id.card_coustomer_call_imgbtn);
        final TextView phonenum_tv = (TextView)view.findViewById(R.id.card_coustomer_tel_tv);
        final TextView phonenum_state = (TextView)view.findViewById(R.id.card_coustomer_contact_status);
        final String coustomer_phonenumber = phonenum_tv.getText().toString();
        callimgbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //获取电话号码

//                String mobilenumber;
                //意图对象 1.意图的动作用指明交给的那一个应用 2.传输数据
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + coustomer_phonenumber));
                //把意图传播给操作系统
                mContext.startActivity(intent);
                phonenum_state.setText("已联系");
               // TelephonyManager telephonyManager = (TelephonyManager)MainActivity.getSystemService(Context.TELEPHONY_SERVICE);
               // telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
            }
        });
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
