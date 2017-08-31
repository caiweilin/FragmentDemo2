package com.example.fragmentdemo;



import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentdemo.ui.DingdanActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class ThridActivity extends AppCompatActivity implements OnClickListener {
	private MessageFragment messageFragment;
	private ContactsFragment contactsFragment;
	private NewsFragment newsFragment;
	private SettingFragment settingFragment;
	private View messageLayout;
	private View contactsLayout;
	private View newsLayout;
	private View settingLayout;
	private ImageView messageImage;
	private ImageView contactsImage;
	private ImageView newsImage;
	private ImageView settingImage;
	private TextView messageText;
	private TextView contactsText;
	private TextView newsText;
	private TextView settingText;
	private FragmentManager fragmentManager;
	private int currentPage = 0;
	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("neo", "onCreate");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_thrid);
		ImageButton tianjia=(ImageButton)findViewById(R.id.tianjia);
		tianjia.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ThridActivity.this,DingdanActivity.class);
				startActivity(intent);
			}
		});
		Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
		setSupportActionBar(toolbar);
		if (null != savedInstanceState) {
			currentPage = savedInstanceState.getInt("neo");
		}
		// 初始化布局元素
		initViews();
		fragmentManager =getSupportFragmentManager();
		//在FragmentManager里面根据Tag去找相应的fragment. 用户屏幕发生旋转，重新调用onCreate方法。否则会发生重叠
		messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("message");
		contactsFragment = (ContactsFragment) fragmentManager.findFragmentByTag("contacts");
		newsFragment = (NewsFragment) fragmentManager.findFragmentByTag("news");
		settingFragment = (SettingFragment) fragmentManager.findFragmentByTag("setting");
		// 第一次启动时选中第0个tab
		setTabSelection(currentPage);
		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		messageLayout = findViewById(R.id.message_layout);
		contactsLayout = findViewById(R.id.contacts_layout);
		newsLayout = findViewById(R.id.news_layout);
		settingLayout = findViewById(R.id.setting_layout);
		messageImage = (ImageView) findViewById(R.id.message_image);
		contactsImage = (ImageView) findViewById(R.id.contacts_image);
		newsImage = (ImageView) findViewById(R.id.news_image);
		settingImage = (ImageView) findViewById(R.id.setting_image);
		messageText = (TextView) findViewById(R.id.message_text);
		contactsText = (TextView) findViewById(R.id.contacts_text);
		newsText = (TextView) findViewById(R.id.news_text);
		settingText = (TextView) findViewById(R.id.setting_text);
		messageLayout.setOnClickListener(this);
		contactsLayout.setOnClickListener(this);
		newsLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.message_layout:
				// 当点击了消息tab时，选中第1个tab
				setTabSelection(0);
				currentPage = 0;
				break;
			case R.id.contacts_layout:
				// 当点击了联系人tab时，选中第2个tab
				setTabSelection(1);
				currentPage = 1;
				break;
			case R.id.news_layout:
				// 当点击了动态tab时，选中第3个tab
				setTabSelection(2);
				currentPage = 2;
				break;
			case R.id.setting_layout:
				setTabSelection(3);
				currentPage = 3;
				break;
			default:
				break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 *
	 * @param index 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		//Log.i("neo", "消息为空？ "+messageFragment+"--联系人为空？ "+contactsFragment+"-- 动态为空？ "+newsFragment+"-- 设置为空？ "+settingFragment);
		switch (index) {
			case 0:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				messageImage.setImageResource(R.drawable.message_selected);
				messageText.setTextColor(Color.WHITE);
				//messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("message");
				if (messageFragment == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					messageFragment = new MessageFragment();
					transaction.add(R.id.content, messageFragment, "message");
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(messageFragment);
				}

				break;
			case 1:
				// 当点击了联系人tab时，改变控件的图片和文字颜色
				contactsImage.setImageResource(R.drawable.contacts_selected);
				contactsText.setTextColor(Color.WHITE);
				//	contactsFragment = (ContactsFragment) fragmentManager.findFragmentByTag("contacts");
				if (contactsFragment == null) {
					// 如果ContactsFragment为空，则创建一个并添加到界面上
					contactsFragment = new ContactsFragment();
					transaction.add(R.id.content, contactsFragment, "contacts");
				} else {
					// 如果ContactsFragment不为空，则直接将它显示出来
					transaction.show(contactsFragment);
				}
				break;
			case 2:
				// 当点击了动态tab时，改变控件的图片和文字颜色
				newsImage.setImageResource(R.drawable.news_selected);
				newsText.setTextColor(Color.WHITE);
				//	newsFragment = (NewsFragment) fragmentManager.findFragmentByTag("news");
				if (newsFragment == null) {
					// 如果NewsFragment为空，则创建一个并添加到界面上
					newsFragment = new NewsFragment();
					transaction.add(R.id.content, newsFragment, "news");
				} else {
					// 如果NewsFragment不为空，则直接将它显示出来
					transaction.show(newsFragment);
				}
				break;
			case 3:
			default:
				// 当点击了设置tab时，改变控件的图片和文字颜色
				settingImage.setImageResource(R.drawable.setting_selected);
				settingText.setTextColor(Color.WHITE);
				//	settingFragment = (SettingFragment) fragmentManager.findFragmentByTag("setting");
				if (settingFragment == null) {
					// 如果SettingFragment为空，则创建一个并添加到界面上
					settingFragment = new SettingFragment();
					transaction.add(R.id.content, settingFragment, "setting");
				} else {
					// 如果SettingFragment不为空，则直接将它显示出来
					transaction.show(settingFragment);
				}
				break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		messageImage.setImageResource(R.drawable.message_unselected);
		messageText.setTextColor(Color.parseColor("#82858b"));
		contactsImage.setImageResource(R.drawable.contacts_unselected);
		contactsText.setTextColor(Color.parseColor("#82858b"));
		newsImage.setImageResource(R.drawable.news_unselected);
		newsText.setTextColor(Color.parseColor("#82858b"));
		settingImage.setImageResource(R.drawable.setting_unselected);
		settingText.setTextColor(Color.parseColor("#82858b"));
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 *
	 * @param transaction 用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {

		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (contactsFragment != null) {
			transaction.hide(contactsFragment);
		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("neo", currentPage);
		super.onSaveInstanceState(outState);
		Log.i("neo", "onSaveInstanceState");
	}

	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	public Action getIndexApiAction() {
		Thing object = new Thing.Builder()
				.setName("Thrid Page") // TODO: Define a title for the content shown.
				// TODO: Make sure this auto-generated URL is correct.
				.setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
				.build();
		return new Action.Builder(Action.TYPE_VIEW)
				.setObject(object)
				.setActionStatus(Action.STATUS_TYPE_COMPLETED)
				.build();
	}

	@Override
	public void onStart() {
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		AppIndex.AppIndexApi.start(client, getIndexApiAction());
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		AppIndex.AppIndexApi.end(client, getIndexApiAction());
		client.disconnect();
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.toolbar_collection:
				Toast.makeText(this,"你选择了选项1",Toast.LENGTH_SHORT).show();
				break;
			case R.id.toolbar_share:
				Toast.makeText(this,"你选择了选项2",Toast.LENGTH_SHORT).show();
				break;
			case R.id.toolbar_fontsize:
				Toast.makeText(this,"你选择了选项3",Toast.LENGTH_SHORT).show();
				break;
			default:
		}
		return true;
	}
}