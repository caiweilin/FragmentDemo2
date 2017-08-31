package com.example.fragmentdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.bean.UserBean;
import com.example.fragmentdemo.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class DeleteActivity extends Activity {

	private Button deleteBtn, batchdeleteBtn,querydeleteBtn;
	
	private List<BmobObject> queryBeans = new ArrayList<BmobObject>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_delete);
		
		initView();

	}

	private void initView() {
		deleteBtn = (Button) this.findViewById(R.id.id_delete_btn);
		querydeleteBtn = (Button) this.findViewById(R.id.id_query_delete_btn);
		batchdeleteBtn = (Button) this.findViewById(R.id.id_batch_delete_btn);

		deleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				delete();
			}
		});

		batchdeleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				batchDelete();
			}
		});

	}

	/**
	 * 批量删除
	 */
	protected void batchDelete() {
		
		if(queryBeans.size() == 0){
			ToastUtil.toast(this, "数据不存在,请先插入");
		}
		
		new BmobObject().delete(new UpdateListener() {

			@Override
			public void done(BmobException e) {
				if(e==null){
					ToastUtil.toast(DeleteActivity.this, "批量删除成功");
				}else{
					ToastUtil.toast(DeleteActivity.this,"批量删除失败");
				}
			}

		});

	}

	/**
	 * 删除
	 */
	protected void delete() {
		if(queryBeans.size() == 0){
			ToastUtil.toast(this, "无数据，请先插入！");
			return;
		}
		UserBean userBean = (UserBean)queryBeans.get(0);
		
		/**
		 * 根据指定的objectId修改元素
		 */
		userBean.delete(new UpdateListener() {

			@Override
			public void done(BmobException e) {
				if(e==null){
					ToastUtil.toast(DeleteActivity.this, "删除成功");
				}else{
					ToastUtil.toast(DeleteActivity.this, "删除失败");
				}
			}

		});

		
		/**
		 * 默认删除Bmob服务器第一行元素
		 */
//		userBean.delete(this , new deleteListener() {
//			
//			@Override
//			public void onSuccess() {
//				// TODO Auto-generated method stub
//				ToastUtil.toast(deleteActivity.this, "删除成功");
//			}
//			
//			@Override
//			public void onFailure(int arg0, String arg1) {
//				// TODO Auto-generated method stub
//				ToastUtil.toast(deleteActivity.this, arg1 + "删除失败");
//			}
//		});
	}
	
	/**
	 * 修改
	 */
	/**protected void query() {

		BmobQuery<UserBean> query = new BmobQuery<>();

		query.findObjects(this, new FindListener<UserBean>() {

			@Override
			public void onError(int arg0, String arg1) {
				ToastUtil.toast(DeleteActivity.this, arg1 + "查询失败");

			}

			@Override
			public void onSuccess(List<UserBean> userBeanList) {
				// TODO Auto-generated method stub
				ToastUtil.toast(DeleteActivity.this, showMsg(userBeanList));
				convertUserToObject(userBeanList);

			}
		});
	}*/

	/**
	 * 弹出提示
	 * @param userBmobObjects
	 * @return
	 */
	private String showMsg(List<UserBean> userBmobObjects){
		String msg = "";
		if(userBmobObjects.size() == 0){
			msg = "数据不存在!";
		}
		for(UserBean userBean:userBmobObjects){
			msg += userBean.getObjectId() + "," + userBean.getUserId() + "," + userBean.getUserName() + "," 
					+ userBean.getPassword() + "\n";
		}
		return msg;
	}
	
	/**
	 * 将子类集合转换为基类BmobObject集合
	 * @param userBeanList
	 * @return
	 */
	private List<BmobObject> convertUserToObject(List<UserBean> userBeanList){
		queryBeans.clear();
		for(UserBean userBean: userBeanList){
			queryBeans.add(userBean);
		}
		
		return queryBeans;
	}

}
