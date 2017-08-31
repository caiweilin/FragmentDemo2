package com.example.fragmentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class SecondActivity extends Activity{
    /**
     * 用户名
     */
    private EditText sname;

    /**
     * 密码
     */
    private EditText spassword;
    private EditText mname,mpassword;
    private Button mlogin,mregi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        Bmob.initialize(this, "614b7327837cd173e62ebd23c50ec16b");
        mname=(EditText) findViewById(R.id.sname);
        mpassword=(EditText) findViewById(R.id.spassword);
        mlogin=(Button) findViewById(R.id.login);
        mregi=(Button) findViewById(R.id.regi);
    }
    private void initViews() {
        sname = (EditText) findViewById(R.id.sname);
        spassword = (EditText) findViewById(R.id.spassword);
        findViewById(R.id.login).setOnClickListener(mOnClickListener);
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {


        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.login://登录
                    String userName = sname.getText().toString();
                    String userPwd = spassword.getText().toString();
                    UserManage.getInstance().saveUserInfo(SecondActivity.this, userName, userPwd);
                    Toast.makeText(SecondActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondActivity.this, ThridActivity.class);//跳转到主页
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };
    //注册点击
    public void dregi(View view){
        Intent intentlogin = new Intent();
        intentlogin.setClass(SecondActivity.this,MainActivity.class);
        startActivity(intentlogin);

    }
    //登录点击
    public void dlogin(View view){
        String name=mname.getText().toString();
        String password=mpassword.getText().toString();
        if(name.equals("")||password.equals("")){
            Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        BmobQuery<user> query=new BmobQuery<user>();
        query.addWhereEqualTo("name", name);
        query.addWhereEqualTo("password", password);
        query.findObjects(new FindListener<user>() {

            @Override
            public void done(List<user> arg0, BmobException e) {
                // TODO Auto-generated method stub
                if(e==null){
                    String gname=arg0.get(0).getName().toString();
                    String gpassword=arg0.get(0).getPassword().toString();
                    String name=mname.getText().toString();
                    String password=mpassword.getText().toString();
                    Toast.makeText(SecondActivity.this, gname, Toast.LENGTH_LONG).show();
                    if(gname.equals(name)&&gpassword.equals(password))
                    {
                        Intent seccess = new Intent();
                        seccess.setClass(SecondActivity.this, ThridActivity.class);
                        startActivity(seccess);
                    }

                }
                else{
                    Toast.makeText(SecondActivity.this, "帐号或密码有误", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


}