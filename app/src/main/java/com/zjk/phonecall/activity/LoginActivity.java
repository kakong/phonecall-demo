package com.zjk.phonecall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zjk.phonecall.R;
import com.zjk.phonecall.entity.UserEntity;
import com.zjk.phonecall.provider.DBhandle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongjiakang on 16/3/10.
 */
public class LoginActivity extends Activity {
    //声明按钮
    private Button registerBtn,loginBtn;
    private EditText userNameEt,userPswEt;
    private CheckBox rememberPassword,autoLogin;
//    private Switch rememberPassword,autoLogin;
    private SharedPreferences spf;
    private String userNameValue,passwordVale;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mContext = this.getBaseContext();
        //初始化控件
        userNameEt = (EditText)findViewById(R.id.userName_et);
        userPswEt = (EditText)findViewById(R.id.password_et);
        registerBtn = (Button)findViewById(R.id.register_bt);
        loginBtn = (Button)findViewById(R.id.login_bt);
        rememberPassword = (CheckBox)findViewById(R.id.rememberPassword_cb);
        autoLogin = (CheckBox)findViewById(R.id.autoLogin_cb);
//        rememberPassword = (Switch)findViewById(R.id.remember_sw);
//        autoLogin = (Switch)findViewById(R.id.autologin_sw);

        spf = getSharedPreferences("userInfo",0);
        String name = spf.getString("USER_NAME","");
        String psw = spf.getString("PASSWORD","");

        boolean choseRememberPassword = spf.getBoolean("rememberPassword", false);
        boolean choseAutoLogin = spf.getBoolean("autoLogin",false);

        if(choseRememberPassword){
            userNameEt.setText(name);
            userPswEt.setText(psw);
            rememberPassword.setChecked(true);
        }
        if(choseAutoLogin){
            autoLogin.setChecked(true);
        }
        Intent intent = this.getIntent();
        Boolean cancel = intent.getBooleanExtra("CancelAutoLogin",choseAutoLogin);
        if(cancel){
            login();
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                }
            }
        );
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void login(){

        userNameValue = userNameEt.getText().toString();
        passwordVale = userPswEt.getText().toString();
        SharedPreferences.Editor editor = spf.edit();

        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        DBhandle dBhandle = new DBhandle();
        userEntities = dBhandle.getUser(mContext);

        for(int j = 0;j < userEntities.size();j++){
        if (userNameValue.equals(userEntities.get(j).getName()) && passwordVale.equals(userEntities.get(j).getPassword())) {
//            Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();

            //保存用户名和密码
            editor.putString("USER_NAME", userNameValue);
            editor.putString("PASSWORD", passwordVale);

            //是否记住密码
            if (rememberPassword.isChecked()) {
                editor.putBoolean("rememberPassword", true);
//                        editor.putBoolean("autoLogin",true);
            } else {
                editor.putBoolean("rememberPassword", false);
            }

            //是否自动登录
            if (autoLogin.isChecked()) {
                editor.putBoolean("autoLogin", true);
            } else {
                editor.putBoolean("autoLogin", false);
            }

            editor.commit();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("putname",userNameValue);
            startActivity(intent);
            LoginActivity.this.finish();
        }
        }
    }
}
