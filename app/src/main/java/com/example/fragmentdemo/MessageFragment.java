package com.example.fragmentdemo;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


	public class MessageFragment extends Fragment {

	private View mView;
	private ViewPager mViewPaper;
	private List<ImageView> images;
	private List<View> dots;
	private int currentItem;
		private ImageButton meishi1;
		private ImageButton lengyin;
		private ImageButton hangbao;
		private ImageButton mianshi;
		private ImageButton longxia;
		private ImageButton zaocan;
		private ImageButton xiaoye;
		private ImageButton suiguo;
	//记录上一次点的位置
	private int oldPosition = 0;
	//存放图片的id
	private int[] imageIds = new int[]{
			R.mipmap.food1,
			R.mipmap.food2,
			R.mipmap.food3,
			R.mipmap.food4,
			R.mipmap.food5
	};
	//存放图片的标题
	private String[] titles = new String[]{
			"轮播1",
			"轮播2",
			"轮播3",
			"轮播4",
			"轮播5"
	};
	private TextView title;
	private ViewPagerAdapter adapter;
	private ScheduledExecutorService scheduledExecutorService;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mView=inflater.inflate(R.layout.buju, null);
		setView();
		meishi1=(ImageButton)mView.findViewById(R.id.meishi1);
		lengyin=(ImageButton)mView.findViewById(R.id.lengyin);
		hangbao=(ImageButton)mView.findViewById(R.id.hangbao);
		mianshi=(ImageButton)mView.findViewById(R.id.mianshi);
		longxia=(ImageButton)mView.findViewById(R.id.longxia);
		zaocan=(ImageButton)mView.findViewById(R.id.zaocan);
		xiaoye=(ImageButton)mView.findViewById(R.id.xiaoye);
		suiguo=(ImageButton)mView.findViewById(R.id.suiguo);
		return mView;
	}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);


			meishi1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent("com.example.fragmentdemo.ACTION_START");
					startActivity(intent);
				}
			});
			lengyin.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了饮料", 0).show();
				}
		});
			hangbao.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了汉堡", 0).show();
				}
			});
			mianshi.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了面食", 0).show();
				}
			});
			longxia.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了龙虾", 0).show();
				}
			});
			zaocan.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了早餐", 0).show();
				}
			});
			xiaoye.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了宵夜", 0).show();
				}
			});
			suiguo.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "你点击了水果", 0).show();
				}
			});
		}
		private void setView(){
		mViewPaper = (ViewPager)mView.findViewById(R.id.vp);

		//显示的图片
		images = new ArrayList<ImageView>();
		for(int i = 0; i < imageIds.length; i++){
			ImageView imageView = new ImageView(getActivity());
			imageView.setBackgroundResource(imageIds[i]);
			images.add(imageView);
		}
		//显示的小点
		dots = new ArrayList<View>();
		dots.add(mView.findViewById(R.id.dot_0));
		dots.add(mView.findViewById(R.id.dot_1));
		dots.add(mView.findViewById(R.id.dot_2));
		dots.add(mView.findViewById(R.id.dot_3));
		dots.add(mView.findViewById(R.id.dot_4));

		title = (TextView) mView.findViewById(R.id.title);
		title.setText(titles[0]);

		adapter = new ViewPagerAdapter();
		mViewPaper.setAdapter(adapter);

		mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


			@Override
			public void onPageSelected(int position) {
				title.setText(titles[position]);
				dots.get(position).setBackgroundResource(R.mipmap.dot_yes);
				dots.get(oldPosition).setBackgroundResource(R.mipmap.dot_yes);

				oldPosition = position;
				currentItem = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	/*定义的适配器*/
	public class ViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			// TODO Auto-generated method stub
//          super.destroyItem(container, position, object);
//          view.removeView(view.getChildAt(position));
//          view.removeViewAt(position);
			view.removeView(images.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			// TODO Auto-generated method stub
			view.addView(images.get(position));
			return images.get(position);
		}

	}

	/**
	 * 利用线程池定时执行动画轮播
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(
				new ViewPageTask(),
				2,
				2,
				TimeUnit.SECONDS);
	}


	/**
	 * 图片轮播任务
	 * @author liuyazhuang
	 *
	 */
	private class ViewPageTask implements Runnable{

		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageIds.length;
			mHandler.sendEmptyMessage(0);
		}
	}

	/**
	 * 接收子线程传递过来的数据
	 */
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			mViewPaper.setCurrentItem(currentItem);
		};
	};
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(scheduledExecutorService != null){
			scheduledExecutorService.shutdown();
			scheduledExecutorService = null;
		}
	}
}