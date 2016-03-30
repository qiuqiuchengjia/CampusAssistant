package org.yanzi.Utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.ui.HeadControlPanel;

/**
 *  这个类专门用来初始化activity的头信息
 */
public class ActivityHeadInitTools {
    private HeadControlPanel headPanel;
    private Activity activity;
    private String string,stringCenter;
    private int i;

    public ActivityHeadInitTools(Activity activity,String string){
        this.activity=activity;
        this.string=string;
        initView();
    }
    //这个是设置右控件的构造函数
    public ActivityHeadInitTools(String string,String stringCenter,Activity activity){
        this.activity=activity;
        this.stringCenter=stringCenter;
        this.string=string;
    }
    public ActivityHeadInitTools(Activity activity,String string,int i){
        this.activity=activity;
        this.string=string;
        this.i=i;
        initViewFollowInt();
    }


    /**
     * 这个是专门为几个注册activity写的头控件初始化函数
     */
    private void initViewFollowInt() {
        headPanel = (HeadControlPanel)activity.findViewById(R.id.head);
        if(headPanel!=null){
            headPanel.setMiddleTitle(string);
            final ImageView middleImage = headPanel.getMiddleImage();
            middleImage.setImageResource(R.mipmap.ic_navigation_back_normal);
            middleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    middleImage.setImageResource(R.mipmap.ic_navigation_back_pressed);
                    DestoryActivityTools.reduceDestoryActivity(i+"");
                    activity.finish();

                }
            });
        }
    }

    /**
     * 这个是初始化字体在中间的头控件的函数
     */
    private void initView() {
        headPanel = (HeadControlPanel)activity.findViewById(R.id.head);
        if(headPanel!=null){
            headPanel.setMiddleTitle(string);
            final ImageView middleImage = headPanel.getMiddleImage();
            middleImage.setImageResource(R.mipmap.ic_navigation_back_normal);
            middleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    middleImage.setImageResource(R.mipmap.ic_navigation_back_pressed);
                    activity.finish();

                }
            });
        }
    }

    /**
     * 这个是初始化字体在右边的头控件的函数
     */
    public TextView initRightView() {
        headPanel = (HeadControlPanel)activity.findViewById(R.id.head);
        TextView textView = null;
        if(headPanel!=null){
            textView = headPanel.setRightTitleForReturn(string);
            final ImageView middleImage = headPanel.getMiddleImage();
            middleImage.setImageResource(R.mipmap.ic_navigation_back_normal);
            middleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    middleImage.setImageResource(R.mipmap.ic_navigation_back_pressed);
                    activity.finish();

                }
            });
        }
        return textView;
    }

    /**
     * 这个是初始化字体在右边的头控件的函数
     * 而且设置返回键imageLoader清除缓存
     */
    public TextView initRightViewAndClearCache() {
        headPanel = (HeadControlPanel)activity.findViewById(R.id.head);
        TextView textView = null;
        TextView tv_title = null;
        if(headPanel!=null){
            textView = headPanel.setRightTitleForReturn(string);
            final ImageView middleImage = headPanel.getMiddleImage();
            middleImage.setImageResource(R.mipmap.ic_navigation_back_normal);
            middleImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    middleImage.setImageResource(R.mipmap.ic_navigation_back_pressed);

                    activity.finish();

                }
            });
        }
        return textView;
    }


}
