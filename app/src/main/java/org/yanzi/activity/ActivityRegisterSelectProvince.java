package org.yanzi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fragmentproject.R;

import org.yanzi.Adapter.ProvinceAdapter;
import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.constant.Config;

/**
 * 这个类是注册界面给用户选择省份
 */
public class ActivityRegisterSelectProvince extends Activity{
    private  ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DestoryActivityTools.addDestoryActivity(ActivityRegisterSelectProvince.this,1+"");
        setContentView(R.layout.activity_register_select_province);
        initView();

    }


    /**
     * 这个函数用来初始化控件
     */
    private void initView() {
        //用来初始化头控件
        new ActivityHeadInitTools(ActivityRegisterSelectProvince.this,Config.HEAD_REGISTER_PROVINCE,1);

        listview = (ListView)findViewById(R.id.province_listview);

        ProvinceAdapter provinceAdapter = new ProvinceAdapter(ActivityRegisterSelectProvince.this);
        listview.setAdapter(provinceAdapter);
    }


}
