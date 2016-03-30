package org.yanzi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.constant.Config;

/**
 * 用来展示用户的注册信息
 */
public class ActivityRegisterShowInfo extends Activity{
    String province,city,school,time,college,career;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_show_info);

        //用来初始化头控件
        new ActivityHeadInitTools(ActivityRegisterShowInfo.this,Config.HEAD_REGISTER_SHOW_INFO,7);
        DestoryActivityTools.addDestoryActivity(ActivityRegisterShowInfo.this,7+"");
        Bundle extras = ActivityRegisterShowInfo.this.getIntent().getExtras();
        province = extras.getString("province");
        city = extras.getString("city");
        school = extras.getString("school");
        time = extras.getString("time");
        college = extras.getString("college");
        career = extras.getString("career");
        init();
    }

    /**
     * 用来初始化其他控件
     */
    private void init() {
        TextView mSchool =(TextView)findViewById(R.id.show_info_school);
        TextView mCollege =(TextView)findViewById(R.id.show_info_college);
        TextView mCareer=(TextView)findViewById(R.id.show_info_career);
        TextView mTime =(TextView)findViewById(R.id.show_info_time);
        Button button = (Button)findViewById(R.id.ok_button);
        mSchool.setText(school);
        mCollege.setText(college);
        mCareer.setText(career);
        mTime.setText(time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegisterShowInfo.this, AcitvityRegisterFillUserInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("province", province);
                bundle.putString("city", city);
                bundle.putString("school",school);
                bundle.putString("time",time);
                bundle.putString("college",college);
                bundle.putString("career",career);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }




}
