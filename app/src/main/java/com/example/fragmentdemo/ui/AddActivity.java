package com.example.fragmentdemo.ui;

import android.app.Activity;
import android.bluetooth.le.AdvertiseData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.ThridActivity;
import com.example.fragmentdemo.bean.UserBean;
import com.example.fragmentdemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends Activity {
	private TextView textView;
	private TextView textView1;
	private Spinner spProvince;
	private Spinner spCity;
	int a,b,c;
	private String[] province= {"潮汕美食外卖","肯德基外卖"};
	private String[][] citys= {{"牛肉丸","炒米线","炒牛河","汤粉"},{"汉堡","鸡肉卷","可乐","家庭套餐"}};
	private ArrayAdapter<String> provinceAdapter;
	private ArrayAdapter<String> cityAdapter;
	private TextView tv,tv1;
	private EditText userIdEt, userNameEt, passwordEt;
	private Button addBtn, batchAddBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_add);
		textView = (TextView) findViewById(R.id.tv);
		textView1= (TextView) findViewById(R.id.tv1);
		spProvince = (Spinner) findViewById(R.id.spProvince);
		spCity = (Spinner) findViewById(R.id.spCity);

		provinceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,province);
		spProvince.setAdapter(provinceAdapter);
		cityAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
		spCity.setAdapter(cityAdapter);
		spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch(cityAdapter.getItem(position)) {
					case "牛肉丸":
						c=10;break;
					case "炒米线":
						c=12;break;
					case "炒牛河":
						c=15;break;
					case "汤粉":
						c=9;break;
					case "汉堡":
						c=13;break;
					case "鸡肉卷":
						c=11;break;
					case "可乐":
						c=8;break;
					case "家庭套餐":
						c=50;break;
				}
				textView1.setText(cityAdapter.getItem(position)+c+"元");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				//根据省的选择i来改变市的数据
				String[] city = citys[i];
				//适配器清零
				cityAdapter.clear();
				//添加市的数据
				cityAdapter.addAll(city);
				spCity.setSelection(0);
				textView.setText(provinceAdapter.getItem(i));
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
		
		initView();

	}

	private void initView() {
		tv = (TextView) this.findViewById(R.id.tv);
		tv1 = (TextView) this.findViewById(R.id.tv1);
		userNameEt = (EditText) this.findViewById(R.id.id_user_name_et);
		passwordEt = (EditText) this.findViewById(R.id.id_password_et);
		addBtn = (Button) this.findViewById(R.id.id_add_btn);
		batchAddBtn = (Button) this.findViewById(R.id.id_batch_btn);

		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				add();
			}
		});

		batchAddBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				batchAdd();
			}
		});

	}

	/**
	 * 批量增加
	 */
	protected void batchAdd() {
		List<BmobObject> userBeans = initUserBeans(5);
		new BmobObject().save(new SaveListener<String>() {
			@Override
			public void done(String objectId,BmobException e) {
				if(e==null){
					ToastUtil.toast(AddActivity.this, "增加成功");
				}else{
					ToastUtil.toast(AddActivity.this,"增加失败");
				}
			}
		});

	}

	/**
	 * 增加
	 */
	protected void add() {
		UserBean userBean = (UserBean) initUserBeans(1).get(0);
		userBean.save(new SaveListener<String>() {
			@Override
			public void done(String objectId,BmobException e) {
				if(e==null){
					ToastUtil.toast(AddActivity.this, "下单成功");
					Intent intent=new Intent(AddActivity.this, ThridActivity.class);
					startActivity(intent);
				}else{
					ToastUtil.toast(AddActivity.this, "下单失败");
				}
			}
		});
	}

	/**
	 * 初始化实体对象
	 * 
	 * @param count
	 * @return
	 */
	private List<BmobObject> initUserBeans(int count) {
		List<BmobObject> userBeans = new ArrayList<BmobObject>();
		for (int i = 0; i < count; i++) {
			UserBean userBean = new UserBean();
			userBean.setUserId(tv.getText().toString() );
			userBean.setUserName(tv1.getText().toString() );
			userBean.setPassword(passwordEt.getText().toString());

			userBeans.add(userBean);
		}
		return userBeans;
	}

}
