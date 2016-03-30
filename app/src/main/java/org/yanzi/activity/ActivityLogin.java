package org.yanzi.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmentproject.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;
import com.ta.common.TAStringUtils;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.yanzi.IO.IOOperateFile;
import org.yanzi.IO.IOSaveBitmapToFolder;
import org.yanzi.Picture.BitmapTools;
import org.yanzi.Utils.DestoryActivityTools;
import org.yanzi.Utils.GetNowTimeTools;
import org.yanzi.Utils.IsPhoneNumberTools;
import org.yanzi.Utils.MD5Tool;
import org.yanzi.Utils.ProgressDialogTools;
import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.Utils.ToastTools;
import org.yanzi.bean.User;
import org.yanzi.constant.Config;
import org.yanzi.constant.ImageViewSizeControl;

/**
 * 这个是登录界面
 */
public class ActivityLogin extends Activity {
    private ImageView iv_Picture;
    private EditText et_Username,et_Password;
    private Button btn_login,btn_register;
    private TextView tv_forgetPwd;
    private    String tokenStr;
    private boolean flag=false;
    private  Bitmap bitmap =null,valueBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_login);
        DestoryActivityTools.addDestoryActivity(ActivityLogin.this,0+"");
        initAnim();
        initControl();
    }

    /**
     * 这个是获取用户头像的方法
     */
    private void getTouxiang(){
        final AsyncHttpClient client;
        if(IsPhoneNumberTools.isPhoneNumber(et_Username.getText().toString().trim())||
                TAStringUtils.isEmail(et_Username.getText().toString().trim())){
            client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put(Config.CONNECTION_KEY, Config.LOGIN_CONNECTION_KEY);
            params.put(Config.LOGIN_GET_USER_PICTURE, et_Username.getText().toString().trim());
            client.post(Config.LOGIN_GET_USER_PICTURE_URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String string = new String(bytes);

                    //这里获取到了用户头像的URL
                    try {
                        JSONObject jsonObject = new JSONObject(string);
                        //这里代表用户没有设置头像
                        if(jsonObject.getString(Config.LOGIN_GET_USER_TOUXIANG_KEY).equals
                                (Config.LOGIN_GET_USER_TOUXIANG_STATUE)){
                            //这个是用户没有头像
                            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.login_default_picture);
                            Bitmap newBitmap = ImageViewSizeControl.LoginImageViewLessen(bitmap, ActivityLogin.this);
                            iv_Picture.setImageBitmap(newBitmap);
                            flag = true;//将flag变成true，代表已经从服务器获取到头像了

                            //这里是将图片转换成数组然后缓存到SharedPreferences中
                            Config.cacheUsernametouxiang(ActivityLogin.this,
                                    Base64.encodeToString(BitmapTools.bitmapToByteArray(bitmap), Base64.DEFAULT));
                            valueBitmap = bitmap;

                        }else{

                            //这里用来获取用户头像
                            client.post(jsonObject.getString(Config.LOGIN_GET_USER_TOUXIANG_KEY)
                                    , null, new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                    BitmapFactory factory = new BitmapFactory();
                                    bitmap = factory.decodeByteArray(bytes, 0, bytes.length);
                                    Bitmap newBitmap = ImageViewSizeControl.LoginImageViewLessen(bitmap, ActivityLogin.this);
                                    iv_Picture.setImageBitmap(newBitmap);
                                    flag = true;//将flag变成true，代表已经从服务器获取到头像了

                                    //这里是将图片转换成数组然后缓存到SharedPreferences中
                                    Config.cacheUsernametouxiang(ActivityLogin.this,
                                            Base64.encodeToString(BitmapTools.bitmapToByteArray(bitmap), Base64.DEFAULT));
                                    valueBitmap = bitmap;

                                }

                                @Override
                                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {


                                }
                            });

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                }
            });
        }else{
            //这里是用户名不是合法的格式
            Bitmap  bit = BitmapFactory.decodeResource(getResources(), R.mipmap.login_default_picture);
            Bitmap newBitmap = ImageViewSizeControl.LoginImageViewLessen(bit, ActivityLogin.this);
            iv_Picture.setImageBitmap(newBitmap);
        }
    }
    /**
     *
     * 这个函数用来初始化登录界面的控件
     */
    private void initControl() {

        iv_Picture = (ImageView) findViewById(R.id.login_picture);
        et_Username= (EditText) findViewById(R.id.account);
        et_Password= (EditText) findViewById(R.id.password);
        btn_login= (Button) findViewById(R.id.login);
        btn_register= (Button) findViewById(R.id.register);
        tv_forgetPwd= (TextView) findViewById(R.id.tv_forget_password);

        //判断当前SharedPreferences中是否有用户名，有的话就显示在上面
        if(Config.getCachedUsername(ActivityLogin.this)!=null){
            et_Username.setText(Config.getCachedUsername(ActivityLogin.this));
        }

        //判断当前的SharedPreferences中是否有头像，有的话就显示
        if(Config.getCachedUsernametouxiang(ActivityLogin.this)!=null){

            Bitmap newBitmap = ImageViewSizeControl.LoginImageViewLessen(
                    BitmapTools.StringToBitmap(Config.getCachedUsernametouxiang(ActivityLogin.this)), ActivityLogin.this);
            iv_Picture.setImageBitmap(newBitmap);

        }

        //这个是当用户输入用户名的时候我去获取用户的头像
        et_Username.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getTouxiang();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                getTouxiang();
            }
        });
        if(!flag){
            et_Password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    getTouxiang();
                }
            });
        }


        //这个是登录按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //显示进度框
                final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                        (ActivityLogin.this, R.string.login_dialog_tishi);
                String strUser = et_Username.getText().toString().trim();
                final String strPwd = et_Password.getText().toString().trim();

                    //判断密码是不是大于6位数
                    if(strPwd.toCharArray().length >= 6){

                        if (!TAStringUtils.isEmpty(strUser) && !TAStringUtils.isEmpty(strPwd) ) {

                            if (TAStringUtils.isEmail(strUser) || IsPhoneNumberTools.isPhoneNumber(strUser)) {
                                //判断头像是否下载成功
                                if(flag) {

                                    AsyncHttpClient client = new AsyncHttpClient();
                                    RequestParams params = new RequestParams();
                                    params.put(Config.CONNECTION_KEY, Config.LOGIN_CONNECTION_KEY);
                                    params.put(Config.USERNAME, strUser);
                                    params.put(Config.LOGIN_PASSWORD_MD5_KEY, MD5Tool.md5(strPwd));
                                    tokenStr = MD5Tool.md5(strUser + GetNowTimeTools.getFormat());
                                    params.put(Config.KEY_TOKEN, tokenStr);
                                    dialogs.show();
                                    client.post(Config.LOGIN_URL, params, new AsyncHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                            String content = new String(bytes);
                                            try {
                                                JSONObject jsonObject = new JSONObject(content);

                                                String name = jsonObject.getString(Config.USERNAME);
                                                String passwd = jsonObject.getString(Config.LOGIN_PASSWORD_MD5_KEY);
                                                String tokens = jsonObject.getString(Config.KEY_LOGIN_TOKENSTATUS);
                                                String kebiaotokenstatus = jsonObject.getString(Config.KEBIAO_LOGIN_TOKEN_STATUES);
                                                if (!name.equals("0")) {
                                                    //这里的是有这个用户
                                                    if (!passwd.equals("0")) {

                                                        if (!tokens.equals("0")) {

                                                            if (!kebiaotokenstatus.equals("0")) {
                                                                User user = new User();
                                                                user.setId(jsonObject.getInt(Config.USER_ID));
                                                                user.setUsername(jsonObject.getString(Config.USERNAME));
                                                                Config.cacheUsername(ActivityLogin.this, jsonObject.getString(Config.USERNAME));
                                                                user.setSchool(jsonObject.getString(Config.KEY_SCHOOL));
                                                                user.setPassword(strPwd);
                                                                user.setToken(tokenStr);
                                                                Config.cacheToken(ActivityLogin.this, tokenStr);
                                                                user.setTouxiang(jsonObject.getString(Config.KEY_USER_TOUXIANG));
                                                                user.setStartTime(jsonObject.getString(Config.KEY_START_TIME));
                                                                user.setBanji(jsonObject.getString(Config.KEY_BANJI));
                                                                user.setSex(jsonObject.getInt(Config.KEY_USER_SEX));
                                                                user.setPhonenumber(jsonObject.getString(Config.KEY_USER_PHONE_NUMBER));
                                                                user.setCollege(jsonObject.getString(Config.KEY_COLLEGE));
                                                                user.setAge(jsonObject.getInt(Config.KEY_USER_AGE));
                                                                user.setEmail(jsonObject.getString(Config.KEY_USER_EMAIL));
                                                                user.setCity(jsonObject.getString(Config.KEY_USER_CITY));
                                                                user.setActivetime(jsonObject.getInt(Config.KEY_USER_ACTIE_TIME));
                                                                user.setName(jsonObject.getString(Config.KEY_USER_TURE_NAME));
                                                                user.setProvince(jsonObject.getString(Config.KEY_USER_PROVINCE));
                                                                user.setCareer(jsonObject.getString(Config.KEY_USER_CAREER));
                                                                user.setStudentnumber(jsonObject.getString(Config.STUDENT_NUMBER));
                                                                user.setStudentnumberpassword(jsonObject.getString(Config.STUDENT_NUMBER_PASSWORD));
                                                                //这里将user对象保存在SharedPreferences中
                                                                SaveAndGetObject.saveObject(ActivityLogin.this, Config.SAVE_USER_OBJECT_KEY, user);
                                                                //将用户名保存到文件中，用来获取最后一次登录的用户
                                                                saveUserNameToFile();

                                                                //用来将头像保存到文件中去
                                                                saveUserTouxiangToFile(jsonObject.getString(Config.KEY_USER_TOUXIANG));
                                                                valueBitmap = null;
                                                                //将标记置为false,下次继续使用
                                                                flag=false;
                                                                //这里还可以将头像存入到文件中，待会实现这个功能

                                                                dialogs.dismiss();
                                                                //将bitmap回收
                                                                BitmapTools.recoverBitmap(bitmap);
                                                                startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                                                                finish();


                                                            } else {
                                                                new ToastTools(ActivityLogin.this, R.string.login_fail_intent);
                                                                //  Toast.makeText(ActivityLogin.this,R.string.login_fail,Toast.LENGTH_SHORT).show();
                                                                dialogs.dismiss();
                                                            }

                                                        } else {
                                                            new ToastTools(ActivityLogin.this, R.string.login_fail);
                                                            //  Toast.makeText(ActivityLogin.this,R.string.login_fail,Toast.LENGTH_SHORT).show();
                                                            dialogs.dismiss();
                                                        }


                                                    } else {
                                                        new ToastTools(ActivityLogin.this, R.string.password_fail);
                                                        // Toast.makeText(ActivityLogin.this, R.string.password_fail, Toast.LENGTH_SHORT).show();
                                                        dialogs.dismiss();
                                                    }

                                                } else {
                                                    //没有这个用户
                                                    new ToastTools(ActivityLogin.this, R.string.user_not_exist);
                                                    //Toast.makeText(ActivityLogin.this,R.string.user_not_exist,Toast.LENGTH_SHORT).show();
                                                    dialogs.dismiss();
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                dialogs.dismiss();
                                                Log.d("ActivityLogin", "try");
                                                new ToastTools(ActivityLogin.this, R.string.login_fail);
                                                // Toast.makeText(ActivityLogin.this,R.string.login_fail,Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                            dialogs.dismiss();
                                            new ToastTools(ActivityLogin.this, R.string.login_fail);
                                            //  Toast.makeText(ActivityLogin.this,R.string.login_fail,Toast.LENGTH_SHORT).show();
                                        }

                                    });
                                }else{
                                    new ToastTools(ActivityLogin.this, R.string.login_fail);
                                }

                            } else {
                                new ToastTools(ActivityLogin.this, R.string.username_form_error);
                                //  Toast.makeText(ActivityLogin.this, R.string.username_form_error, Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            new ToastTools(ActivityLogin.this, R.string.username_or_password_empty);
                            //   Toast.makeText(ActivityLogin.this, R.string.username_or_password_empty, Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        new ToastTools(ActivityLogin.this, R.string.username_or_password_length_error);
                    }


            }
        });
        //这个是注册按钮的点击事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册界面
                startActivity(new Intent(ActivityLogin.this,ActivityRegisterSelectProvince.class));
            }
        });
        tv_forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ddd", "忘记密码");
            }
        });

    }



    /**
     * 用来将用户名和头像存入文件中
     */
    private void saveUserNameToFile() {
        IOOperateFile ioo = new IOOperateFile(ActivityLogin.this);
        //将用户名存入文件中
        if(Config.getCachedUsername(ActivityLogin.this)!=null){
            boolean sd = ioo.saveSDCard(Config.KEY_USER_USER_PATH,Config.FILE_USERNAME,Config.getCachedUsername(ActivityLogin.this));
            if(!sd){
               boolean disk =  ioo.saveToDisk(Config.KEY_USER_USER_PATH,Config.FILE_USERNAME,ActivityLogin.this,
                        Config.getCachedUsername(ActivityLogin.this));
            }
        }

    }

    /**
     * 用来将用户头像存入文件中
     * @param touxiang
     */
    private void saveUserTouxiangToFile(String touxiang){
        //将头像存入文件中
        if(Config.getCachedUsername(ActivityLogin.this)!=null) {

            if (valueBitmap != null) {
                new IOSaveBitmapToFolder().saveHead(valueBitmap, ActivityLogin.this, Config.getCachedUsername(ActivityLogin.this));

            } else {
                if (touxiang != null) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.post(touxiang, null, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {

                            BitmapFactory factory = new BitmapFactory();
                            valueBitmap = factory.decodeByteArray(bytes, 0, bytes.length);

                            if (valueBitmap != null) {
                                //这里是将图片转换成数组然后缓存到SharedPreferences中
                                Config.cacheUsernametouxiang(ActivityLogin.this,
                                        Base64.encodeToString(BitmapTools.bitmapToByteArray(valueBitmap), Base64.DEFAULT));

                                if (Config.getCachedUsername(ActivityLogin.this) != null) {
                                    //这里是将头像缓存进入文件中
                                    new IOSaveBitmapToFolder().saveHead(valueBitmap, ActivityLogin.this, Config.getCachedUsername(ActivityLogin.this));
                                }

                            }

                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                        }
                    });


                }

            }
        }

    }


    /**
     * 这个函数用于初始化登录界面输入框的动画效果
     */
    private void initAnim() {
        RelativeLayout rl_user=(RelativeLayout) findViewById(R.id.rl_user);
        Animation anim= AnimationUtils.loadAnimation(this, R.anim.login_anim);
        anim.setFillAfter(true);
        rl_user.startAnimation(anim);
    }
}
