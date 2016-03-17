package com.zjk.phonecall.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zjk.phonecall.R;
import com.zjk.phonecall.entity.UserEntity;
import com.zjk.phonecall.provider.DBhandle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongjiakang on 16/3/17.
 */
public class RegisterActivity extends Activity {

    private Button commitBtn,returnBtn;
    private EditText userNameEt,userIdEt,PswFirEt,PswSecEt;
    private Context mContext;
    private String username,pswfirst,pswsecond;
    private int userid;
    //private CheckBox rememberPassword,autoLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mContext = this.getBaseContext();
        //初始化控件
        userNameEt = (EditText)findViewById(R.id.username_et);
        userIdEt = (EditText)findViewById(R.id.workId_et);

        PswFirEt = (EditText)findViewById(R.id.pswfirst_et);
        PswSecEt = (EditText)findViewById(R.id.pswsecond_et);

        commitBtn = (Button)findViewById(R.id.commit_bt);
        returnBtn = (Button)findViewById(R.id.return_bt);

        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Boolean sameName = false;
                List<UserEntity> userEntities = new ArrayList<UserEntity>();
                DBhandle dBhandle = new DBhandle();
                UserEntity userEntity = new UserEntity();
                username = userNameEt.getText().toString();
                userid = Integer.parseInt(userIdEt.getText().toString());
                pswfirst = PswFirEt.getText().toString();
                pswsecond = PswSecEt.getText().toString();

                userEntities = dBhandle.getUser(mContext);
                for (int i = 0;i<userEntities.size();i++){
                    if(username.equals(userEntities.get(i).getName())
                            || (userid == userEntities.get(i).getId())){
                        sameName = true;
                        Toast.makeText(RegisterActivity.this, "用户名或者工号已经被注册", Toast.LENGTH_SHORT).show();
                    }

                }

                if(pswfirst.equals(pswsecond)){
                    if(!sameName) {
                        userEntity.setId(userid);
                        userEntity.setName(username);
                        userEntity.setPassword(pswfirst);
                        // DBhandle dBhandle = new DBhandle();
                        dBhandle.commitUser(mContext, userEntity);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,"前后密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });

    returnBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           RegisterActivity.this.finish();
        }
    });
    }
}
