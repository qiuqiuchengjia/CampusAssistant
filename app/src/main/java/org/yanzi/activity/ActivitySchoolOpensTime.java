package org.yanzi.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fragmentproject.R;

import org.yanzi.Adapter.RegisterSchoolOpensTimeAdapter;
import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.Utils.GetNowTimeTools;
import org.yanzi.Utils.ProgressDialogTools;
import org.yanzi.constant.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个用来选择入学的时间
 */
public class ActivitySchoolOpensTime extends Activity {
    private ListView listview;
    String province,city,school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_school_opens_time);
        //用来初始化头控件
        listview = (ListView)findViewById(R.id.opens_listview);
        new ActivityHeadInitTools(ActivitySchoolOpensTime.this,Config.HEAD_SCHOOL_OPEN_TIME,4);
        DestoryActivityTools.addDestoryActivity(ActivitySchoolOpensTime.this,4+"");
        Bundle extras = ActivitySchoolOpensTime.this.getIntent().getExtras();
        province = extras.getString("province");
        city = extras.getString("city");
        school = extras.getString("school");
        initOpensTime();
    }

    /**
     * 用来初始化适配器的
     */
    private void initOpensTime() {
        //显示进度框
        final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                (ActivitySchoolOpensTime.this, R.string.login_dialog_register_get_school);

        List<String> listTime = new ArrayList<String>();
        int year = GetNowTimeTools.getYear();
        int month = GetNowTimeTools.getMonth();
        int i=15;
        if(month>=8){
            for(int j=0 ; j<i ;j++){
                listTime.add(j,year+"");
                year--;
            }
        }else{
            year=year-1;
            for(int j=0 ; j<i ;j++){
                listTime.add(j,year+"");
                year--;
            }

        }
        RegisterSchoolOpensTimeAdapter adapter = new RegisterSchoolOpensTimeAdapter(ActivitySchoolOpensTime.this,
                listTime,province,city,school);
        listview.setAdapter(adapter);
    }




}
