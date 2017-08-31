package com.example.fragmentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DingdanActivity extends AppCompatActivity {
    String[] listItem = { "小鸡快跑", "辉煌美食", "潮汕美食", "肯德基", "华莱士" };
    int[] iconItem = { R.drawable.xjkp, R.drawable.hhms111,
            R.drawable.csms, R.drawable.kdj11,
            R.drawable.hls111 };
    private TextView text11;
    private TextView title;
    private TextView jiage;
    private TextView time;
    private TextView dizhi;
    String text1;
    String text2;
    String text3;
    String text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan2);
        ImageButton titleBack1=(ImageButton)findViewById(R.id.back3);
        titleBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        int data=intent.getIntExtra("nun",1);
        text11 = (TextView) findViewById(R.id.text11);
        title=(TextView)findViewById(R.id.title1);
        jiage=(TextView)findViewById(R.id.jiage);
        time=(TextView)findViewById(R.id.time1);
        dizhi=(TextView)findViewById(R.id.dizhi);
        title.setText(listItem[data]+"订单详细信息");

        switch (data){
            case 0:text1="原味炸鸡×1+王老吉×1";
                text2="15元";
                text3="2017.8.30 15:54";
                text4="广中医10栋电梯口";
                break;
            case 1:text1="肉末茄子×1+煎蛋×1+米饭×1";
                text2="12元";
                text3="2017.8.31 15:00";
                text4="广中医10栋电梯口";
                break;
            case 2:text1="炒牛河×1+牛肉丸×1";
                text2="20元";
                text3="2017.8.31 19:00";
                text4="广中医10栋电梯口";
                break;
            case 3:text1="美味汉堡×1+大薯条×1+中杯可乐×1";
                text2="22元";
                text3="2017.9.1 9:00";
                text4="广中医10栋电梯口";
                break;
            case 4:text1="海鲜披萨×1+美式咖啡×5";
                text2="105元";
                text3="2017.9.2 22:00";
                text4="广中医10栋电梯口";
                break;
        }
        text11.setText("所购买的商品："+text1);
        jiage.setText("总计价格："+text2);
        time.setText("下单时间："+text3);
        dizhi.setText("配送地址："+text4);
    }
}
