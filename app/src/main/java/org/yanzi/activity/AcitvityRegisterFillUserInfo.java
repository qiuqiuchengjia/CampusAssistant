package org.yanzi.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentproject.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.ta.common.TAStringUtils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.Utils.GetNowTimeTools;
import org.yanzi.Utils.IsPhoneNumberTools;
import org.yanzi.Utils.MD5Tool;
import org.yanzi.Utils.ProgressDialogTools;
import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.Utils.ToastTools;
import org.yanzi.bean.User;
import org.yanzi.constant.Config;

/**
 * 这个类用来在注册的时候填写用户的信息
 */
public class AcitvityRegisterFillUserInfo extends Activity {
    private Bundle extras;
    private  TextView tv_code;
    private  TextView tv_username,tv_user_code,tv_user_password,tv_student_number,tv_student_number_password;
    private String province,city,school,time,career,college,code;
    private  Button button_register;
    private   String user_name,user_code,user_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fill_user_info);
        extras = AcitvityRegisterFillUserInfo.this.getIntent().getExtras();
        DestoryActivityTools.addDestoryActivity(AcitvityRegisterFillUserInfo.this,8+"");
        initView();
        initCode();
        province = extras.getString("province");
        city = extras.getString("city");
        school = extras.getString("school");
        time = extras.getString("time");
        career = extras.getString("career");
        college = extras.getString("college");
    }

    /**
     * 实现业务逻辑的函数
     */
    private void initLogic() {

        user_name = tv_username.getText().toString().trim();
         user_code =tv_user_code.getText().toString().trim().toUpperCase();
         user_password =tv_user_password.getText().toString().trim();

        //判断用户名是否为空
        if(!TAStringUtils.isEmpty(user_name)){
            //判断用户名格式是否正确
            if(TAStringUtils.isEmail(user_name) ||
                    IsPhoneNumberTools.isPhoneNumber(user_name)){

                //判断验证码是否为空
                if(!TAStringUtils.isEmpty(user_code)){
                    //判断验证码是否正确
                    if(user_code.equals(code.toUpperCase())){
                        //判断密码长度是不是大于6
                             if(user_password.toCharArray().length>=6){
                                 //判断登录密码是否为空
                                 if(!TAStringUtils.isEmpty(user_password)){

                                     //到这里表示用户填写的信息是合法的，然后就可以连接服务器将数据写入数据库了
                                     initData();

                                 }else{
                                     new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_user_password_isEmpty);
                                 }
                             }else{
                                 new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.username_or_password_length_error);
                             }



                    }else{
                        new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_user_code_fail);
                    }


                }else{
                    new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_user_code_isEmpty);
                }



            }else{
                new ToastTools(AcitvityRegisterFillUserInfo.this,R.string.username_form_error);
            }

        }else{
            new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_user_name_isEmpty);
        }

    }


    /**
     * 用来连接服务器将注册信息写入到数据库中
     */
    private void initData() {
        //显示进度框
        final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                (AcitvityRegisterFillUserInfo.this, R.string.dialog_register_to_register);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        final String token = MD5Tool.md5(user_name + GetNowTimeTools.getFormat());
        params.put(Config.CONNECTION_KEY, Config.REGISTER_FILL_TO_SUBMIT);
        params.put(Config.REGISTER_SUBMIT_USER_NAME, user_name);
        params.put(Config.REGISTER_SUBMIT_PASSWORD_MD5, MD5Tool.md5(user_password));
        params.put(Config.REGISTER_SUBMIT_SCHOOL,school);
        params.put(Config.REGISTER_SUBMIT_COLLEGE,college);
        params.put(Config.REGISTER_SUBMIT_CAREER,career);
        params.put(Config.REGISTER_SUBMIT_OPEN_TIME,time);
        params.put(Config.REGISTER_SUBMIT_PROVINCE,province);
        params.put(Config.KEY_TOKEN,token);
        params.put(Config.REGISTER_SUBMIT_CITY,city);
        dialogs.show();
        client.post(Config.REGISTER_SUBMIT_USER_INFO, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                try {
                    JSONObject json = new JSONObject(s);
                    //这个用户名可以被注册
                    if(json.getString(Config.REGISTER_SUBMIT_USERNAME_STATUS).equals("1")) {
                        String submitsuatus =json.getString(Config.REGISTER_SUBMIT_STATUS);
                            if(submitsuatus.equals("1")) {
                                //注册成功
                                Config.cacheToken(AcitvityRegisterFillUserInfo.this, token);
                                Config.cacheUsername(AcitvityRegisterFillUserInfo.this, user_name);
                                User user = new User();
                                user.setUsername(user_name);
                                user.setPassword(user_password);
                                user.setProvince(province);
                                user.setSchool(school);
                                user.setCollege(college);
                                user.setCareer(career);
                                user.setCity(city);
                                user.setStartTime(time);
                                user.setToken(token);

                                //这里将user对象保存在SharedPreferences中
                                SaveAndGetObject.saveObject(AcitvityRegisterFillUserInfo.this, Config.SAVE_USER_OBJECT_KEY, user);
                                startActivity(new Intent(AcitvityRegisterFillUserInfo.this, MainActivity.class));
                                finishAllActivity();
                                dialogs.dismiss();
                                //将之前所有的activity销毁，然后跳转到主界面
                                for(int b=0;b<DestoryActivityTools.destoryMap.size();b++){
                                    DestoryActivityTools.destoryActivity((b+1)+"");
                                    DestoryActivityTools.removeDestoryActivityAll();
                                }

                            }else{
                                //注册信息没有写入数据库
                                new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_submit_fail);
                                dialogs.dismiss();
                            }

                    }else{
                        //这个用户名已经被注册
                        new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_username_exist);
                        dialogs.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_submit_fail);
                    dialogs.dismiss();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_submit_fail);
                dialogs.dismiss();
            }
        });

    }

    /**
     * 用来结束掉以前所有的activity
     */
    private void finishAllActivity() {
         for(int i =0;i<DestoryActivityTools.destoryMap.size();i++){
             DestoryActivityTools.destoryActivity(i+"");
         }

    }

    /**
     * 这个用来获取验证码
     */
    private void initCode() {

        tv_code = (TextView)findViewById(R.id.textview_fill_code);
        //显示进度框
        final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                (AcitvityRegisterFillUserInfo.this, R.string.dialog_register_fill_username_get_code);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put(Config.CONNECTION_KEY,Config.REGISTER_FILL_USERNAME_GET_CODE_KEY);
        dialogs.show();
        client.post(Config.REGISTER_FILL_GET_CODE_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                try {
                    JSONObject json = new JSONObject(s);
                    code =json.getString(Config.REGISTER_FILL_GET_CODE);
                    String status = json.getString(Config.REGISTER_FILL_GET_CODE_STATUS);
                    if(status.equals("1")){
                        tv_code.setText(code);
                        dialogs.dismiss();
                    }else{
                        new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_get_code_fail);
                        dialogs.dismiss();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_get_code_fail);
                    dialogs.dismiss();
                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                new ToastTools(AcitvityRegisterFillUserInfo.this, R.string.register_fill_get_code_fail);
                dialogs.dismiss();
            }
        });


    }

    /**
     * 这个函数用来初始化控件
     */
    private void initView() {

        //用来初始化头控件
        new ActivityHeadInitTools(AcitvityRegisterFillUserInfo.this,Config.HEAD_FILL_USER_INFO,8);

        Button button_code = (Button)findViewById(R.id.button_fill_code);

        tv_username = (TextView) findViewById(R.id.edittext_username);
         tv_user_code = (TextView) findViewById(R.id.edittext_code);
         tv_user_password = (TextView) findViewById(R.id.edittext_password);

        button_register = (Button)findViewById(R.id.register);
        button_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCode();
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 initLogic();
            }
        });

    }
}
