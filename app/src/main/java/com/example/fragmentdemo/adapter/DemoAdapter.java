package com.example.fragmentdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fragmentdemo.R;

public class DemoAdapter extends BaseAdapter {

	private String[] mContents;
	private LayoutInflater mInflater;
	
	public DemoAdapter(Context context,String[] mConteStrings){
		this.mContents = mConteStrings;
		this.mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContents.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mContents[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.activity_operate_item, null);
			viewHolder.operateTv = (TextView) convertView.findViewById(R.id.id_operate_tv);
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.operateTv.setText(mContents[position]);
		return convertView;
	}
	
	public class ViewHolder{
		TextView operateTv;
	}
	

}
