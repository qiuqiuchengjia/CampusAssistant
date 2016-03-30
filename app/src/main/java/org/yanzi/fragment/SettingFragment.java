package org.yanzi.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentproject.R;

import org.yanzi.Picture.BitmapTools;
import org.yanzi.Picture.BitmapZoom;
import org.yanzi.Utils.GetWidthAndHeightTool;
import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.activity.ActivityLogin;
import org.yanzi.activity.MainActivity;
import org.yanzi.bean.User;
import org.yanzi.constant.Config;
import org.yanzi.ui.AlertDialog;

public class SettingFragment extends BaseFragment {
	private Activity mActivity ;
	private TextView tv_quit,tv_number,tv_username,tv_sign;
	private ImageView user_touxiang;
	private RelativeLayout user_layout;
	private LinearLayout user_number_layout,layout_quit;
	private User user;

	/**
	 * 用来初始化控件并增加处理
	 * @param settingLayout
	 */
	private void initView(View settingLayout) {
		tv_quit = (TextView) settingLayout.findViewById(R.id.setting_quit);
		tv_number= (TextView) settingLayout.findViewById(R.id.user_number);
		tv_username= (TextView) settingLayout.findViewById(R.id.setting_name);
		tv_sign = (TextView) settingLayout.findViewById(R.id.settint_sign);
		user_touxiang = (ImageView) settingLayout.findViewById(R.id.setting_picture);
		user_layout= (RelativeLayout) settingLayout.findViewById(R.id.layout_user_touxiang);
		//为退出设置点击事件，将所有的用户数据清空
		user_number_layout= (LinearLayout) settingLayout.findViewById(R.id.my_number);
		//为我的账号设置点击事件
		user_number_layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mActivity,"我的账号",Toast.LENGTH_SHORT).show();

			}
		});
		//设置头像
		if(Config.getCachedUsernametouxiang(mActivity)!=null){
			Bitmap bitmap = BitmapTools.StringToBitmap(Config.getCachedUsernametouxiang(mActivity));
			int width = GetWidthAndHeightTool.getScreenWidth(mActivity)/9;//这个是定义显示的头像为我们屏幕宽度的九分之一
					user_touxiang.setImageBitmap(BitmapZoom.bitmapZoomBySize(bitmap, width, width));//显示头像
		}
		//为头像和真实名字设置点击事件
		user_layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mActivity,"我是头像",Toast.LENGTH_SHORT).show();
			}
		});

		//把自己的账号设置进去
		if(Config.getCachedUsername(mActivity)!=null){
			tv_number.setText(Config.getCachedUsername(mActivity));
		}


		user = (User) SaveAndGetObject.readObject(mActivity, Config.SAVE_USER_OBJECT_KEY);
		//判断真实名字是否存在
		if(user.getName().equals("")) {
			if(Config.getCachedUsername(mActivity)!=null){
				tv_username.setText(Config.getCachedUsername(mActivity));
			}
		}else{
			tv_username.setText(user.getName());
		}



		//为退出账号设置点击事件
		tv_quit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//这里是一个弹出窗口，用来提醒用户是否退出账号
				final AlertDialog ad=new AlertDialog(mActivity);
				ad.setTitle(Config.OK_TO_QUIT_TITLE);
				ad.setMessage(Config.OK_TO_QUIT_CONTENT);
				//确认退出
				ad.setPositiveButton(Config.BTN_OK_TO_QUIT, new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ad.dismiss();

				//下面就是将很多的缓存清空
				Config.cacheToken(mActivity, null);//将token清空
				Config.cacheStudentNumber(mActivity, null);//将学号清空
				//Config.cacheUsername(mActivity, null);//将用户名清空
				Config.cacheThisWeek(mActivity, null);//将周数清空
				Config.cacheSchoolYear(mActivity, null);//将学年清空
				Config.cacheSemester(mActivity, null);//将学期清空
				//Config.cacheUsernametouxiang(mActivity, null);//将头像的bitmap字符串清空
				//这里将user对象清空在SharedPreferences中
				SaveAndGetObject.saveObject(mActivity, Config.SAVE_USER_OBJECT_KEY, null);
				//这里将课程集合对象清空在SharedPreferences中
				SaveAndGetObject.saveObject(mActivity, Config.SAVE_KECHENG_OBJECT_KEY, null);

				//跳转到登录界面
				Intent intent = new Intent(mActivity, ActivityLogin.class);
				startActivity(intent);
				mActivity.finish();

					}
				});
				//退出取消
				ad.setNegativeButton(Config.BTN_CANCEL_TO_QUIT, new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ad.dismiss();

					}
				});


			}
		});

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.setting_layout,
				container, false);
		//用来初始化控件
		initView(settingLayout);
		return settingLayout;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (MainActivity) activity;
	}

	@Override
	public void onResume() {
		super.onResume();
		MainActivity.currFragTag = Config.FRAGMENT_FLAG_SETTING;
		
	}



}
