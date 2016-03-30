package org.yanzi.activity;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.fragmentproject.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.yanzi.Adapter.RegisterSchoolAdapter;
import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.Utils.AgainToGetDateTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.Utils.ProgressDialogTools;
import org.yanzi.Utils.ToastTools;
import org.yanzi.constant.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是注册界面的城市选择
 */
public class ActivityRegisterSchool extends Activity {
    private ListView listview;
    String province,city;
    private    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_school);
        initView();
        DestoryActivityTools.addDestoryActivity(ActivityRegisterSchool.this,3+"");
        extras = ActivityRegisterSchool.this.getIntent().getExtras();
        province = extras.getString("province");
        city = extras.getString("city");
        initSchool();
    }


    /**
     * 用来获取学校列表
     */
    private void initSchool() {
        AsyncHttpClient client = new AsyncHttpClient();
        //显示进度框
        final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                (ActivityRegisterSchool.this, R.string.login_dialog_register_get_school);
        RequestParams params = new RequestParams();
        params.put(Config.CONNECTION_KEY, Config.REGISTER_GET_SCHOOL_KEY);
        params.put(Config.REGISTER_SEND_PROVINCE, province);
        params.put(Config.REGISTER_SEND_CITY, city);
        dialogs.show();
        client.post(Config.REGISTER_GET_SCHOOL_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String content = new String(bytes);
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    String status = jsonObject.getString(Config.REGISTER_GET_SCHOOL_STATUS);
                    if (status.equals("1")) {
                        List<String> listcity = new ArrayList<String>();
                        for (int j = 1; j < jsonObject.length(); j++) {
                            listcity.add(j - 1, jsonObject.getString(j + ""));
                        }

                        dialogs.dismiss();
                        RegisterSchoolAdapter adapter = new RegisterSchoolAdapter(ActivityRegisterSchool.this,
                                listcity, province, city);
                        listview.setAdapter(adapter);


                    } else {
                        dialogs.dismiss();
                        new ToastTools(ActivityRegisterSchool.this, R.string.get_school_list_fail);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialogs.dismiss();
                    new ToastTools(ActivityRegisterSchool.this, R.string.get_school_list_fail);
                    initFail();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                dialogs.dismiss();
                new ToastTools(ActivityRegisterSchool.this, R.string.get_school_list_fail);
                initFail();
            }
        });
    }

    /**
     * 这个是网络连接失败之后出现的刷新按钮
     */
    private void initFail() {
        AgainToGetDateTools.getButton(ActivityRegisterSchool.this,R.id.register_select_school_layout).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCreate(extras);
                    }
                });

    }


    /**
     * 这个函数用来初始化控件
     */
    private void initView() {
        //用来初始化头控件
        new ActivityHeadInitTools(ActivityRegisterSchool.this,Config.HEAD_REGISTER_SCHOOL,3);
        listview = (ListView)findViewById(R.id.school_listview);

    }
}
