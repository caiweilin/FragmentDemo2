package com.example.fragmentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends Activity {
    private EditText mname,mpassword,mnumber,mverify;
    private Button mgetsms,mgi,mreturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "bf0edfac459b271bb489de81fd4b5f47");
        BmobSMS.initialize(this, "bf0edfac459b271bb489de81fd4b5f47");
        mname=(EditText)findViewById(R.id.name);
        mpassword=(EditText)findViewById(R.id.password);
        mnumber=(EditText)findViewById(R.id.callnumber);
        mverify=(EditText)findViewById(R.id.sms);
        mgetsms=(Button)findViewById(R.id.getsms);
        mgi=(Button)findViewById(R.id.send);
        mreturn=(Button)findViewById(R.id.breturn);
    }

    //点击获取验证码
    public void gsms(View view){
        String number=mnumber.getText().toString();
        if(number.length()==0)
        {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if(number.length()!=11)
        {
            Toast.makeText(this, "请输入11位有效号码", Toast.LENGTH_LONG).show();
            return;
        }
        BmobSMS.requestSMSCode(this, number, "短信模板", new RequestSMSCodeListener() {

            @Override
            public void done(Integer integer, cn.bmob.sms.exception.BmobException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    //发送成功时，让获取验证码按钮不可点击，且为灰色
                    mgetsms.setClickable(false);
                    //mgetsms.setBackgroundColor();
                    Toast.makeText(MainActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
                    new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            //Message_btn.setBackgroundResource(R.drawable.button_shape02);
                            mgetsms.setText(millisUntilFinished / 1000 + "秒");
                        }

                        @Override
                        public void onFinish() {
                            mgetsms.setClickable(true);
                            //Message_btn.setBackgroundResource(R.drawable.button_shape);
                            mgetsms.setText("重新发送");
                        }
                    }.start();
                }
                else {
                    Toast.makeText(MainActivity.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    //返回到登录页面
    public void ret(View view){

        Intent intent1 = new Intent();
        intent1.setClass(MainActivity.this, SecondActivity.class);
        MainActivity.this.startActivity(intent1);


    }

    //点击注册
    public void zhuce(View view){

        String name=mname.getText().toString();
        String password=mpassword.getText().toString();
        String number=mnumber.getText().toString();
        String verify=mverify.getText().toString();
        if(name.equals("")||password.equals(""))
        {
            Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if(name.length()<6)
        {
            Toast.makeText(this, "帐号小于6位", Toast.LENGTH_LONG).show();
            return;
        }
        if(verify.length()==0)
        {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if(number.length()==0)
        {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if(number.length()!=11)
        {
            Toast.makeText(this, "请输入11位有效号码", Toast.LENGTH_LONG).show();
            return;
        }
        BmobSMS.verifySmsCode(this, number, verify, new VerifySMSCodeListener() {

            @Override
            public void done(cn.bmob.sms.exception.BmobException e) {
                // TODO Auto-generated method stub
                if (e == null) {
                    Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //验证码正确 添加用户信息
                    String name=mname.getText().toString();
                    String password=mpassword.getText().toString();
                    user User=new user();
                    User.setName(name);
                    User.setPassword(password);
                    User.save(new SaveListener<String>() {

                        @Override
                        public void done(String arg0, BmobException arg1) {
                            // TODO Auto-generated method stub
                            if(arg1==null){
                                return;

                            }else{
                                return;
                            }
                        }

                    });

                    Intent intent2 = new Intent();
                    intent2.setClass(MainActivity.this, SecondActivity.class);
                    MainActivity.this.startActivity(intent2);
                }
                else {
                    Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}