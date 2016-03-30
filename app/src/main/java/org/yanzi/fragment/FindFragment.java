package org.yanzi.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragmentproject.R;

import org.yanzi.activity.ActivityKebiao;
import org.yanzi.activity.MainActivity;
import org.yanzi.constant.Config;

public class FindFragment extends BaseFragment {
	private Activity mActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (MainActivity) activity;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsLayout = inflater.inflate(R.layout.find_layout, container,
				false);

		initFindClick(newsLayout);
		return newsLayout;
	}

	/**
	 * 这个函数用来初始化find页面的各个LinearLayout的点击事件
	 * @param newsLayout
	 */
	private void initFindClick(View newsLayout) {
		final View kechengbiao = newsLayout.findViewById(R.id.find_kechengbiao);
		kechengbiao.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
                Intent intent = new Intent(mActivity, ActivityKebiao.class);
                startActivity(intent);
			}
		});

		View news = newsLayout.findViewById(R.id.find_news);
		news.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(mActivity, "我是新闻", Toast.LENGTH_SHORT).show();
			}
		});
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.currFragTag = Config.FRAGMENT_FLAG_NEWS;
	}
	

}
