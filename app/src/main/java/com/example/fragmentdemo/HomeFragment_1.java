package com.example.fragmentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment_1 extends ListFragment {

	private ListView list;
	private SimpleAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home_1, container, false);
		list = (ListView) view.findViewById(android.R.id.list);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] listItem = { "小鸡快跑", "辉煌美食", "潮汕美食", "肯德基", "华莱士" };
		int[] iconItem = { R.drawable.xjkl, R.drawable.hhms2,
				R.drawable.mtiao, R.drawable.hbb,
				R.drawable.hls };
		adapter = new SimpleAdapter(getActivity(), getData(listItem, iconItem),
				R.layout.me_function_item, new String[] { "name", "icon" },
				new int[] { R.id.functionName, R.id.functionIcon });
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		int data=position;
		Intent intent=new Intent(getActivity(),DingdanActivity.class);
		intent.putExtra("nun",data);
		startActivity(intent);

	}

	private List<? extends Map<String, ?>> getData(String[] strs, int[] icon) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 5; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", strs[i]);
			String a=strs[i];
			map.put("icon", icon[i]);
			list.add(map);

		}

		return list;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

}
