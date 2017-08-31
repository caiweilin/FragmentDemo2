package com.example.fragmentdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.adapter.DemoAdapter;
import com.example.fragmentdemo.utils.ToastUtil;

import cn.bmob.v3.Bmob;

public class DingdanActivity extends Activity {

	/**
	 * 根据自己创建的应用输入相应的Application ID
	 */
	private static final String APP_ID = "bf0edfac459b271bb489de81fd4b5f47";
	private ListView dataOperateLv;
	private DemoAdapter demoAdapter;
	private String[] mContentStrings = {"去下单","修改订单","删除订单","查询订单"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);

		//初始化Bmob
		Bmob.initialize(this, APP_ID);
		
		initView();
		
	}
	
	
	private void initView(){
		dataOperateLv = (ListView) this.findViewById(R.id.id_data_operate_lv);
		dataOperateLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					startActivity(AddActivity.class);
					break;
					case 1:
						ToastUtil.toast(DingdanActivity.this, "未开发");
						break;
					case 2:
						ToastUtil.toast(DingdanActivity.this, "未开发");
						break;
					case 3:
						ToastUtil.toast(DingdanActivity.this, "未开发");
						break;
					default:
						break;
				}
			}
		});
		
		demoAdapter = new DemoAdapter(this, mContentStrings);
		dataOperateLv.setAdapter(demoAdapter);
		
	}
	
	
	private void startActivity(Class<?> clz){
		Intent intent = new Intent();
		intent.setClass(this, clz);
		startActivity(intent);
	}

}
