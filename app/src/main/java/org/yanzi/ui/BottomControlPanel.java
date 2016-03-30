package org.yanzi.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.fragmentproject.R;

import org.yanzi.constant.Config;

import java.util.ArrayList;
import java.util.List;
/**
 * 这个是底部控制栏的逻辑实现
 * */
public class BottomControlPanel extends RelativeLayout implements View.OnClickListener {
	private Context mContext;
	private ImageText mMsgBtn = null;
	private ImageText mContactsBtn = null;
	private ImageText mNewsBtn = null;
	private ImageText mSettingBtn = null;
	private int DEFALUT_BACKGROUND_COLOR = Color.rgb(249, 249, 249);
	private BottomPanelCallback mBottomCallback = null;
	private List<ImageText> viewList = new ArrayList<ImageText>();
/**
 *  定义的一个接口，当主界面选择底部选择栏中的一项之后会返回一个回调
 * */
	public interface BottomPanelCallback{
		 void onBottomPanelClick(int itemId);
	}
	public BottomControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	/**
	 *用来将底部控制栏的每一个条目初始化并将每一个条目的对象加入viewList集合中
	 * */
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		mMsgBtn = (ImageText)findViewById(R.id.btn_message);
		mContactsBtn = (ImageText)findViewById(R.id.btn_contacts);
		mNewsBtn = (ImageText)findViewById(R.id.btn_news);
		mSettingBtn = (ImageText)findViewById(R.id.btn_setting);
		setBackgroundColor(DEFALUT_BACKGROUND_COLOR);
		viewList.add(mMsgBtn);
		viewList.add(mContactsBtn);
		viewList.add(mNewsBtn);
		viewList.add(mSettingBtn);

	}
	/**
	 * 用来初始化底部控制栏的文字和图片
	 * */
	public void initBottomPanel(){
		if(mMsgBtn != null){
			mMsgBtn.setImage(R.mipmap.message_unselected);
			mMsgBtn.setText(Config.FRAGMENT_FLAG_MESSAGE);
		}
		if(mContactsBtn != null){
			mContactsBtn.setImage(R.mipmap.contacts_unselected);
			mContactsBtn.setText(Config.FRAGMENT_FLAG_CONTACTS);
		}
		if(mNewsBtn != null){
			mNewsBtn.setImage(R.mipmap.news_unselected);
			mNewsBtn.setText(Config.FRAGMENT_FLAG_NEWS);
		}
		if(mSettingBtn != null){
			mSettingBtn.setImage(R.mipmap.setting_unselected);
			mSettingBtn.setText(Config.FRAGMENT_FLAG_SETTING);
		}
		setBtnListener();

	}

	/**
	 * 这个是用来为底部控制栏定义点击事件
	 */
	private void setBtnListener(){
		int num = this.getChildCount();
		for(int i = 0; i < num; i++){
			View v = getChildAt(i);
			if(v != null){
				v.setOnClickListener(this);
			}
		}
	}
	/**
	 * 这个方法用来设置底部控制栏的回调函数
	 * */
	public void setBottomCallback(BottomPanelCallback bottomCallback){
		mBottomCallback = bottomCallback;
	}
	/**
	 * 这个方法用来监听底部控制栏条目的点击事件，并将结果当作回调函数的返回值
	 * */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		initBottomPanel();
		int index = -1;
		switch(v.getId()){
			case R.id.btn_message:
				index = Config.BTN_FLAG_MESSAGE;
				mMsgBtn.setChecked(Config.BTN_FLAG_MESSAGE);
				break;
			case R.id.btn_contacts:
				index = Config.BTN_FLAG_CONTACTS;
				mContactsBtn.setChecked(Config.BTN_FLAG_CONTACTS);
				break;
			case R.id.btn_news:
				index = Config.BTN_FLAG_NEWS;
				mNewsBtn.setChecked(Config.BTN_FLAG_NEWS);
				break;
			case R.id.btn_setting:
				index = Config.BTN_FLAG_SETTING;
				mSettingBtn.setChecked(Config.BTN_FLAG_SETTING);
				break;
			default:break;
		}
		if(mBottomCallback != null){
			mBottomCallback.onBottomPanelClick(index);
		}
	}
	/**
	 *用来设置默认的底部控制栏选中的是第一个条目
	 * */
	public void defaultBtnChecked(){
		if(mMsgBtn != null){
			mMsgBtn.setChecked(Config.BTN_FLAG_MESSAGE);
		}
	}
	/**
	 * 用来设置底部控制栏每一个条目的布局
	 * */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		layoutItems(left, top, right, bottom);
	}
	/**最左边和最右边的view由母布局的padding进行控制位置。这里需对第2、3个view的位置重新设置
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	private void layoutItems(int left, int top, int right, int bottom){
		int n = getChildCount();
		if(n == 0){
			return;
		}
		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();
		int width = right - left;
		int height = bottom - top;
		int allViewWidth = 0;
		for(int i = 0; i< n; i++){
			View v = getChildAt(i);
			allViewWidth += v.getWidth();
		}
		int blankWidth = (width - allViewWidth - paddingLeft - paddingRight) / (n - 1);

		LayoutParams params1 = (LayoutParams) viewList.get(1).getLayoutParams();
		params1.leftMargin = blankWidth;
		viewList.get(1).setLayoutParams(params1);
		LayoutParams params2 = (LayoutParams) viewList.get(2).getLayoutParams();
		params2.leftMargin = blankWidth;
		viewList.get(2).setLayoutParams(params2);
	}



}