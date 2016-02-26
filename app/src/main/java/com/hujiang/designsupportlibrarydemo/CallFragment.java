package com.hujiang.designsupportlibrarydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zhongjiakang on 16/2/24.
 */
public class CallFragment extends Fragment implements View.OnClickListener {
    String mTitle;
    View view;
    private EditText phoneNumber_et;
    private TextInputLayout call_textinput;
    private Button bt,bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,btMi,btJing,btCall,btDelete,btnCount;
    private String number ="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     //   view = inflater.inflate(R.layout.fm_call;
        view = inflater.inflate(R.layout.call_fragment,container,false);
        call_textinput = (TextInputLayout)view.findViewById(R.id.call_textinput);
        call_textinput.setHint("请输入电话号码");
        phoneNumber_et = call_textinput.getEditText();
//        phoneNumber_et.setText("0");
//        phoneNumber_et.requestFocus();
//        phoneNumberTextView = call_textInput.getEditText();
       // phoneNumberTextView.setText("");
        bt = (Button)view.findViewById(R.id.button0);
        bt.setOnClickListener(this);
        bt1 = (Button)view.findViewById(R.id.button1);
        bt1.setOnClickListener(this);
        bt2 = (Button)view.findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        bt3 = (Button)view.findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        bt4 = (Button)view.findViewById(R.id.button4);
        bt4.setOnClickListener(this);
        bt5 = (Button)view.findViewById(R.id.button5);
        bt5.setOnClickListener(this);
        bt6 = (Button)view.findViewById(R.id.button6);
        bt6.setOnClickListener(this);
        bt7 = (Button)view.findViewById(R.id.button7);
        bt7.setOnClickListener(this);
        bt8 = (Button)view.findViewById(R.id.button8);
        bt8.setOnClickListener(this);
        bt9 = (Button)view.findViewById(R.id.button9);
        bt9.setOnClickListener(this);
        btMi = (Button)view.findViewById(R.id.buttonMi);
        btMi.setOnClickListener(this);
        btJing = (Button)view.findViewById(R.id.buttonJing);
        btJing.setOnClickListener(this);
        btDelete = (Button)view.findViewById(R.id.buttonDelete);
        btDelete.setOnClickListener(this);
        btnCount = (Button)view.findViewById(R.id.buttonCount);
        btnCount.setOnClickListener(this);
        btCall = (Button)view.findViewById(R.id.buttonCall);
        btCall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //获取电话号码
                String mobile = phoneNumber_et.getText().toString();
                if(!mobile.equals("")) {
                    //意图对象 1.意图的动作用指明交给的那一个应用 2.传输数据
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                    //把意图传播给操作系统
                    startActivity(intent);
                }
//                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//                telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
            }
        });
        return view;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button0:number = number+0;
                break;
            case R.id.button1:number = number+1;
                break;
            case R.id.button2:number = number+2;
                break;
            case R.id.button3:number = number+3;
                break;
            case R.id.button4:number = number+4;
                break;
            case R.id.button5:number = number+5;
                break;
            case R.id.button6:number = number+6;
                break;
            case R.id.button7:number = number+7;
                break;
            case R.id.button8:number = number+8;
                break;
            case R.id.button9:number = number+9;
                break;
            case R.id.buttonMi:number = number+"*";
                break;
            case R.id.buttonJing:number = number+"#";
                break;
            case R.id.buttonDelete:
                if(number.length()>0){
                    number = number.substring(0,number.length()-1);
                    break;
                }else{
                    break;
                }
            case  R.id.buttonCount:
                break;
        }
        phoneNumber_et.setText(number);
        phoneNumber_et.setSelection(number.length());
    }

}
