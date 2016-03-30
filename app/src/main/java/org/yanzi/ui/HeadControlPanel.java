package org.yanzi.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.constant.Config;
/**
 * 这个是主界面顶部标题栏的逻辑实现
 * */
public class HeadControlPanel extends RelativeLayout {

	private Context mContext;
	private TextView mMidleTitle;
	private TextView mRightTitle;
	private ImageView imageView;
	private static final float middle_title_size = 20f; 
	private static final float right_title_size = 17f;
	private static final int default_background_color = Color.rgb(161,60,64);

	
	public HeadControlPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		mMidleTitle = (TextView)findViewById(R.id.midle_title);
		mRightTitle = (TextView)findViewById(R.id.right_title);
		imageView= (ImageView) findViewById(R.id.head_image);
		//setBackgroundColor(default_background_color);
	}
	/**
	 * 这个是用来初始化顶部标题栏的方法，默认进入程序之后标题栏之后是第一个界面
	 * */
	public void initHeadPanel(){
		if(mMidleTitle != null){
			setMiddleTitle(Config.FRAGMENT_FLAG_MESSAGE);
		}
	}

	/**
	 * 用来设置头控件的标题
	 * @param s
	 */
	public void setMiddleTitle(String s){
		mMidleTitle.setText(s);
		mMidleTitle.setTextSize(middle_title_size);
	}

	/**
	 * 用来设置右控件的标题
	 * @param s
	 */
	public void setRightTitle(String s){
		mRightTitle.setText(s);
		mRightTitle.setTextSize(middle_title_size);
	}
	/**
	 * 用来设置右控件的标题并返回TextView
	 * @param s
	 */
	public TextView setRightTitleForReturn(String s){
		mRightTitle.setText(s);
		mRightTitle.setTextSize(middle_title_size);
		return mRightTitle;
	}

	/**
	 * 用来返回一个imageview
	 * @return
	 */
	public ImageView getMiddleImage(){

		return imageView;
	}
	

}
