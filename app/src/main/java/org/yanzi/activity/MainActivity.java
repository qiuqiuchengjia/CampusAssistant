package org.yanzi.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;

import com.example.fragmentproject.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.ta.common.TAStringUtils;

import org.yanzi.constant.Config;
import org.yanzi.fragment.BaseFragment;
import org.yanzi.ui.BottomControlPanel;
import org.yanzi.ui.BottomControlPanel.BottomPanelCallback;
import org.yanzi.ui.HeadControlPanel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity implements BottomPanelCallback {
	BottomControlPanel bottomPanel = null;
	HeadControlPanel headPanel = null;
	public static ImageLoader imageLoader;
	public static DisplayImageOptions  options;

	private FragmentManager fragmentManager = null;
	private FragmentTransaction fragmentTransaction = null;
	public static String currFragTag = "";

	//这个是imageLoader
	public static ImageLoaderConfiguration config;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//判断缓存的token是否为空，如果不为空就不需要登录

		if(!TAStringUtils.isEmpty(Config.getCachedToken(MainActivity.this))){
         initUI();
		fragmentManager = getFragmentManager();
		setDefaultFirstFragment(Config.FRAGMENT_FLAG_MESSAGE);//这个是设置默认进入的是第一个fragment

		}else{
			startActivity(new Intent(MainActivity.this, ActivityLogin.class));
			finish();
		}

	}

	/**
	 * 这个是activity重启开始
	 */
	@Override
	protected void onResume() {
		super.onResume();

		//在这里创建imageLoader
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.mipmap.default_kebiao_background) // 在ImageView加载过程中显示图片
				.showImageForEmptyUri(R.mipmap.default_kebiao_background) // image连接地址为空时
				.showImageOnFail(R.mipmap.default_kebiao_background) // image加载失败
				.cacheInMemory(true) // 加载图片时会在内存中加载缓存
				.cacheOnDisc(true) // 加载图片时会在磁盘中加载缓存
				.build();

		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(MainActivity.this));
	}

	/**
	 * 这个是当程序停止的时候调用的方法，将currFragTag清空
	 * */
	@Override
	protected void onStop() {
		super.onStop();
		currFragTag = "";
		//将imageLoader销毁,并且将缓存清除
		imageLoader.clearMemoryCache();
		imageLoader.clearDiskCache();
		imageLoader.destroy();
	}


	/**
	 * 将一个输入流转换成字符串
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	public static String changeInputeStream(InputStream inputStream,String encode) {
		//通常叫做内存流，写在内存中的
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if(inputStream != null){
			try {
				while((len = inputStream.read(data))!=-1){
					//data.toString();
					outputStream.write(data, 0, len);
				}
				//result是在服务器端设置的doPost函数中的
				result = new String(outputStream.toByteArray(),encode);
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * 这个方法用来加载UI
	 */
	private void initUI(){
		//这个是用来初始化底部的选择项目列表
		bottomPanel = (BottomControlPanel)findViewById(R.id.bottom_layout);
		if(bottomPanel != null){
			bottomPanel.initBottomPanel();
			bottomPanel.setBottomCallback(this);
		}
		headPanel = (HeadControlPanel)findViewById(R.id.head_layout);
		if(headPanel != null){
			headPanel.initHeadPanel();
		}
	}
	/**处理BottomControlPanel的回调
     * @see org.yanzi.ui.BottomControlPanel.BottomPanelCallback#onBottomPanelClick(int)
     */
	@Override
	public void onBottomPanelClick(int itemId) {
		// TODO Auto-generated method stub
		String tag = "";
		if((itemId & Config.BTN_FLAG_MESSAGE) != 0){
			tag = Config.FRAGMENT_FLAG_MESSAGE;
		}else if((itemId & Config.BTN_FLAG_CONTACTS) != 0){
			tag = Config.FRAGMENT_FLAG_CONTACTS;
		}else if((itemId & Config.BTN_FLAG_NEWS) != 0){
			tag = Config.FRAGMENT_FLAG_NEWS;
		}else if((itemId & Config.BTN_FLAG_SETTING) != 0){
			tag = Config.FRAGMENT_FLAG_SETTING;
		}
		setTabSelection(tag); //切换Fragment
		headPanel.setMiddleTitle(tag);//切换标题
	}
/**
 * 这个是用来设置默认的第一个fragment界面
 * */
	private void setDefaultFirstFragment(String tag){
		setTabSelection(tag);
		bottomPanel.defaultBtnChecked();
	}
/**
 * 这个方法是用来提交fragment事务，并将currFragTag设置为当前的fragment，而且将事务清空
 * */
	private void commitTransactions(String tag){
		if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
			fragmentTransaction.commit();
			currFragTag = tag;
			fragmentTransaction = null;
		}
	}
/**
 * 这个方法用来确保一个fragment的事物已经开启，
 * 如果没有开启就开启然后返回一个FragmentTransaction
 *
 * */
	private FragmentTransaction ensureTransaction( ){
		if(fragmentTransaction == null){
			fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

		}
		return fragmentTransaction;

	}
/**
 * 这个方法用来依附一个fragment，如果没有添加fragment就添加fragment
 * */
	private void attachFragment(int layout, Fragment f, String tag){
		if(f != null){
			if(f.isDetached()){
				ensureTransaction();
				fragmentTransaction.attach(f);

			}else if(!f.isAdded()){
				ensureTransaction();
				fragmentTransaction.add(layout, f, tag);
			}
		}
	}
/**
 * 这个方法是通过当前的tag获得fragment
 * */
	private Fragment getFragment(String tag){

		Fragment f = fragmentManager.findFragmentByTag(tag);

		if(f == null){
			f = BaseFragment.newInstance(getApplicationContext(), tag);
		}
		return f;

	}
	/**
	 * 这个方法用来将一个fragment清理掉
	 * */
	private void detachFragment(Fragment f){

		if(f != null && !f.isDetached()){
			ensureTransaction();
			fragmentTransaction.detach(f);
		}
	}
	/**切换fragment
	 * @param tag
	 */
	private  void switchFragment(String tag){
		if(TextUtils.equals(tag, currFragTag)){
			return;
		}
		//把上一个fragment detach掉
		if(currFragTag != null && !currFragTag.equals("")){
			detachFragment(getFragment(currFragTag));
		}
		attachFragment(R.id.fragment_content, getFragment(tag), tag);
		commitTransactions(tag);//这个用来提交事务
	}

	/**设置选中的Tag
	 * @param tag
	 */
	public  void setTabSelection(String tag) {
		// 开启一个Fragment事务
		fragmentTransaction = fragmentManager.beginTransaction();
/*       if(TextUtils.equals(tag, Config.FRAGMENT_FLAG_MESSAGE)){
           if (messageFragment == null) {
                messageFragment = new DongtaiFragment();
            }

        }else if(TextUtils.equals(tag, Config.FRAGMENT_FLAG_CONTACTS)){
            if (contactsFragment == null) {
                contactsFragment = new ContentFragment();
            }

        }else if(TextUtils.equals(tag, Config.FRAGMENT_FLAG_NEWS)){
            if (newsFragment == null) {
                newsFragment = new FindFragment();
            }

        }else if(TextUtils.equals(tag,Config.FRAGMENT_FLAG_SETTING)){
            if (settingFragment == null) {
                settingFragment = new SettingFragment();
            }
        }else if(TextUtils.equals(tag, Config.FRAGMENT_FLAG_SIMPLE)){
            if (simpleFragment == null) {
                simpleFragment = new SimpleFragment();
            }

        }*/
		switchFragment(tag);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	}

}