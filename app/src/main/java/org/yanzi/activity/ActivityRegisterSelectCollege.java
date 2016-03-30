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
import org.yanzi.Adapter.RegisterCollegeAdapter;
import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.Utils.AgainToGetDateTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.Utils.ProgressDialogTools;
import org.yanzi.Utils.ToastTools;
import org.yanzi.constant.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来个注册用户选择需要的学院
 */
public class ActivityRegisterSelectCollege extends Activity{
    private ListView listview;
    String province,city,school,time;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_college);
        initView();
        DestoryActivityTools.addDestoryActivity(ActivityRegisterSelectCollege.this,5+"");
        extras = ActivityRegisterSelectCollege.this.getIntent().getExtras();
        province = extras.getString("province");
        city = extras.getString("city");
        school = extras.getString("school");
        time = extras.getString("time");
        initOpensTime();
    }

    /**
     * 用来初始化适配器的
     */
    private void initOpensTime() {
        //显示进度框
        final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                (ActivityRegisterSelectCollege.this, R.string.login_dialog_register_get_college);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put(Config.CONNECTION_KEY,Config.REGISTER_GET_COLLEGE_KEY);
        params.put(Config.REGISTER_SEND_SCHOOL, school);
        dialogs.show();
        client.post(Config.REGISTER_GET_COLLEGE_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String string = new String(bytes);
                try {
                    JSONObject jsonObject = new JSONObject(string);
                    String status = jsonObject.getString(Config.REGISTER_GET_COLLEGE_STATUE);
                    if (status.equals("1")) {
                        List<String> listCollege = new ArrayList<String>();
                        for (int j = 1; j < jsonObject.length(); j++) {
                            listCollege.add(j - 1, jsonObject.getString(j + ""));
                        }

                        dialogs.dismiss();
                        RegisterCollegeAdapter adapter = new RegisterCollegeAdapter
                                (ActivityRegisterSelectCollege.this,
                                        listCollege, province, city, school, time);
                        listview.setAdapter(adapter);

                    } else {
                        new ToastTools(ActivityRegisterSelectCollege.this, R.string.register_get_college_fail);
                        initFail();
                    }

                    dialogs.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    dialogs.dismiss();
                    initFail();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                new ToastTools(ActivityRegisterSelectCollege.this, R.string.register_get_college_fail);
                dialogs.dismiss();
                initFail();
            }
        });


    }
    /**
     * 这个是网络连接失败之后出现的刷新按钮
     */
    private void initFail() {
        AgainToGetDateTools.getButton(ActivityRegisterSelectCollege.this,R.id.register_college_layout).
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
        new ActivityHeadInitTools(ActivityRegisterSelectCollege.this,Config.HEAD_REGISTER_COLLEGE,5);
        listview = (ListView)findViewById(R.id.register_college_listview);
    }
}
