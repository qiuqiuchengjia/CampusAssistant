package org.yanzi.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmentproject.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import org.apache.http.Header;
import org.yanzi.IO.IOFile;
import org.yanzi.IO.IOOperateFile;
import org.yanzi.Utils.AgainToGetDateTools;
import org.yanzi.Utils.DateTools;
import org.yanzi.Utils.GetNowTimeTools;
import org.yanzi.Utils.GetWidthAndHeightTool;
import org.yanzi.Utils.ProgressDialogTools;
import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.Utils.ToastTools;
import org.yanzi.bean.Course;
import org.yanzi.bean.KebiaoBean;
import org.yanzi.bean.KechengDataAnalysis;
import org.yanzi.bean.ShowKechengBean;
import org.yanzi.bean.User;
import org.yanzi.constant.Config;
import org.yanzi.ui.HeadControlPanel;
import org.yanzi.ui.PopupKebiaoMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 这个是课程表的activity
 */
public class ActivityKebiao extends Activity{

    private User user;
    private Bundle extras;

    /** 屏幕宽度 **/
    protected int screenWidth;
    /** 课程格子平均宽度 **/
    protected int aveWidth;
    private int gridHeight;
    /** 课程表body部分布局 */
    public static RelativeLayout course_table_layout;
    private RelativeLayout sum_relative_layout;
    private HeadControlPanel headPanel;
    /** 第一个无内容的格子 */
    protected TextView empty;
    /** 星期一的格子 */
    protected TextView monColum;
    /** 星期二的格子 */
    protected TextView tueColum;
    /** 星期三的格子 */
    protected TextView wedColum;
    /** 星期四的格子 */
    protected TextView thrusColum;
    /** 星期五的格子 */
    protected TextView friColum;
    /** 星期六的格子 */
    protected TextView satColum;
    /** 星期日的格子 */
    protected TextView sunColum;
    private  TextView tv_setting,tv_title;
    private ImageView iv_back;
    private KebiaoBean kebiaoBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kebiao);
        extras = ActivityKebiao.this.getIntent().getExtras();

        initStudentNumber();

    }



    /**
     * 用断学号和密码是否存储，如果没有就弹窗
     */
    private void initStudentNumber() {

        user = (User) SaveAndGetObject.readObject(ActivityKebiao.this, Config.SAVE_USER_OBJECT_KEY);
        if(user.getStudentnumber()==null||user.getStudentnumberpassword()==null){
            //这里的代码待实现，这里是当学号和密码没有存储在数据库和缓存中的时候
            //弹出一个弹窗然后要用户输入学号和密码
        }else{
           //这里用来初始化控件
            initTable();
            initKebiaoBackground(Config.KEY_KEBIAO_BACKGROUND_PATH + "/background.png", ActivityKebiao.this);
            getKecheng();
            initHeadView();

        }
    }

    /**
     * 这个方法用来连接网络获取课程
     */
    private void getKecheng(){
        AsyncHttpClient client = new AsyncHttpClient();
        //显示进度框
        final Dialog dialogs = new ProgressDialogTools().createLoadingDialog
                (ActivityKebiao.this, R.string.download_kebiao);
        RequestParams params = new RequestParams();
        params.put(Config.CONNECTION_KEY, Config.NET_KEBIAO_GET_COURSE);
        params.put(Config.KEY_TOKEN, Config.getCachedToken(ActivityKebiao.this));
        dialogs.show();
        client.post(Config.KEBIAO_GET_COURSE_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String content = new String(bytes);
                //这里将得到的json数据进行解析
                List<Course> list = new KechengDataAnalysis(ActivityKebiao.this, content).initData();
                initDate();
                //这个函数用来将数据向界面的转换
                kebiaoBean = new KebiaoBean(ActivityKebiao.this,
                        course_table_layout);
                new ShowKechengBean(kebiaoBean, ActivityKebiao.this).initKecheng(list);
                Log.d("ActivityKebiao", "成功");

                //这里将得到的json字符串存进文件中
                if (Config.getCachedUsername(ActivityKebiao.this) != null) {
                    if (Config.getCachedSchoolYear(ActivityKebiao.this) != null && Config.getCachedSemester(ActivityKebiao.this) != null) {
                        IOOperateFile ioo = new IOOperateFile(ActivityKebiao.this);
                        //将课程存入SD卡中
                        boolean sdStatue = ioo.saveSDCard(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                                Config.getCachedSchoolYear(ActivityKebiao.this) + Config.getCachedSemester(ActivityKebiao.this), content);
                        //将课表的年份和学期存入SD卡中
                        boolean yearStatue = ioo.saveSDCard(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                                Config.FILE_YEAR_SEMESTER, Config.getCachedSchoolYear(ActivityKebiao.this) +
                                        Config.getCachedSemester(ActivityKebiao.this));

                        //将课表的年份和学期存入硬盘中
                        if (!yearStatue) {
                            //如果SD不存在的话就将文件存入手机硬盘中
                            ioo.saveToDisk(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                                    Config.FILE_YEAR_SEMESTER,
                                    ActivityKebiao.this, Config.getCachedSchoolYear(ActivityKebiao.this) +
                                            Config.getCachedSemester(ActivityKebiao.this));
                        }

                        //将课程存入硬盘中
                        if (!sdStatue) {
                            //如果SD不存在的话就将文件存入手机硬盘中
                            ioo.saveToDisk(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                                    Config.getCachedSchoolYear(ActivityKebiao.this) + Config.getCachedSemester(ActivityKebiao.this),
                                    ActivityKebiao.this, content);
                        }
                    }
                }
                dialogs.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                dialogs.dismiss();
                failToShow();
                Log.d("ActivityKebiao", "失败");
            }
        });
    }


            /**
             * 这个是用来初始化头控件
             */
    private void initHeadView() {
        headPanel= (HeadControlPanel) findViewById(R.id.head_panel);

        if(headPanel!=null){
            tv_setting.setText(Config.HEAD_COURSE_SETTING);
            iv_back.setImageResource(R.mipmap.ic_navigation_back_normal);
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv_back.setImageResource(R.mipmap.ic_navigation_back_pressed);
                    finish();

                }
            });
            tv_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ActivityKebiao", "我是设置");
                }
            });
        }

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> arrayList = new ArrayList<String>();
                String [] strings= new String[]{Config.KEBIAO_WEEK_1, Config.KEBIAO_WEEK_2, Config.KEBIAO_WEEK_3
                        , Config.KEBIAO_WEEK_4, Config.KEBIAO_WEEK_5, Config.KEBIAO_WEEK_6, Config.KEBIAO_WEEK_7
                        , Config.KEBIAO_WEEK_8, Config.KEBIAO_WEEK_9, Config.KEBIAO_WEEK_10, Config.KEBIAO_WEEK_11
                        , Config.KEBIAO_WEEK_12, Config.KEBIAO_WEEK_13, Config.KEBIAO_WEEK_14, Config.KEBIAO_WEEK_15
                        , Config.KEBIAO_WEEK_16, Config.KEBIAO_WEEK_17, Config.KEBIAO_WEEK_18, Config.KEBIAO_WEEK_19
                        , Config.KEBIAO_WEEK_20, Config.KEBIAO_WEEK_21, Config.KEBIAO_WEEK_22, Config.KEBIAO_WEEK_23
                        , Config.KEBIAO_WEEK_24, Config.KEBIAO_WEEK_25};
                for(String s:strings){
                    arrayList.add(s);
                }
                PopupKebiaoMenu po = new PopupKebiaoMenu(ActivityKebiao.this, arrayList,course_table_layout);
                Log.d("ActivityKebiao", "arrayList.size():" + arrayList.size());

                if(Config.getCachedWeekSpace(ActivityKebiao.this)!=null){
                    int thisweek = Integer.parseInt(Config.getCachedWeekSpace(ActivityKebiao.this));
                    switch (thisweek){
                        case 1:po.setTargt(thisweek,Config.KEBIAO_WEEK_1+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 2:po.setTargt(thisweek,Config.KEBIAO_WEEK_2+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 3:po.setTargt(thisweek,Config.KEBIAO_WEEK_3+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 4:po.setTargt(thisweek,Config.KEBIAO_WEEK_4+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 5:po.setTargt(thisweek,Config.KEBIAO_WEEK_5+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 6:po.setTargt(thisweek,Config.KEBIAO_WEEK_6+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 7:po.setTargt(thisweek,Config.KEBIAO_WEEK_7+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 8:po.setTargt(thisweek,Config.KEBIAO_WEEK_8+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 9:po.setTargt(thisweek,Config.KEBIAO_WEEK_9+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 10:po.setTargt(thisweek,Config.KEBIAO_WEEK_10+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 11:po.setTargt(thisweek,Config.KEBIAO_WEEK_11+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 12:po.setTargt(thisweek,Config.KEBIAO_WEEK_12+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 13:po.setTargt(thisweek,Config.KEBIAO_WEEK_13+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 14:po.setTargt(thisweek,Config.KEBIAO_WEEK_14+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 15:po.setTargt(thisweek,Config.KEBIAO_WEEK_15+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 16:po.setTargt(thisweek,Config.KEBIAO_WEEK_16+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 17:po.setTargt(thisweek,Config.KEBIAO_WEEK_17+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 18:po.setTargt(thisweek,Config.KEBIAO_WEEK_18+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 19:po.setTargt(thisweek,Config.KEBIAO_WEEK_19+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 20:po.setTargt(thisweek,Config.KEBIAO_WEEK_20+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 21:po.setTargt(thisweek,Config.KEBIAO_WEEK_21+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 22:po.setTargt(thisweek,Config.KEBIAO_WEEK_22+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 23:po.setTargt(thisweek,Config.KEBIAO_WEEK_23+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 24:po.setTargt(thisweek,Config.KEBIAO_WEEK_24+Config.THIS_WEEK_TEXTVIEW);
                            break;
                        case 25:po.setTargt(thisweek,Config.KEBIAO_WEEK_25+Config.THIS_WEEK_TEXTVIEW);
                            break;
                    }
                }

                po.showAsDropDown(v);

            }
        });
    }

    /**
     * 用来设置头控件标题的内容
     * @param string 需要显示的内容
     */
    public TextView setTextViewTitle(String string){
        tv_title.setText(string);
        return tv_title;

    }

    /**
     * 用来将日期信息初始化进入表头，和将周数缓存进入SharedPreferences中
     */
    private void initDate(){
        String start = Config.getCachedThisWeek(ActivityKebiao.this);
        String year =Config.getCachedSchoolYear(ActivityKebiao.this);
        String semester = Config.getCachedSemester(ActivityKebiao.this);
        int timeInt = 0;
        int yearInt = 0;
        if(start!=null&&year!=null&&semester!=null){
            String [] years =year.split("-");
            if(semester.equals("上")){
                if(years[0]!=null) {
                     timeInt = Integer.parseInt(start);
                     yearInt = Integer.parseInt(years[0]);
                    Config.cacheWeekKechengYear(ActivityKebiao.this,yearInt + "");
                    //当当前的年份等于课程的年份
                    if(GetNowTimeTools.getYear()==yearInt){
                        //将周数间隔放进缓存中
                        Config.cacheWeekSpace(ActivityKebiao.this,(GetNowTimeTools.getWeekOfYear()-timeInt+1)+"");
                    }else{
                        Config.cacheWeekSpace(ActivityKebiao.this,(1)+"");
                    }


                }
            }
            if(semester.equals("下")){
                if(years[1]!=null) {
                     timeInt = Integer.parseInt(start);
                     yearInt = Integer.parseInt(years[1]);
                    Config.cacheWeekKechengYear(ActivityKebiao.this,yearInt + "");
                    //当当前的年份等于课程的年份
                    if(GetNowTimeTools.getYear()==yearInt){
                        //将周数间隔放进缓存中
                        Config.cacheWeekSpace(ActivityKebiao.this,(GetNowTimeTools.getWeekOfYear()-timeInt+1)+"");
                    }else{
                        Config.cacheWeekSpace(ActivityKebiao.this,(1)+"");
                    }
                }

            }

        }

        ShowTargtWeekDate(Integer.parseInt(Config.getCachedWeekSpace(ActivityKebiao.this)));
    }

    /**
     * 展现指定周数的日期
     * @param targtWeek 第几周
     */
    public  void ShowTargtWeekDate(int targtWeek){
            int yearInt= Integer.parseInt(Config.getCachedKechengYear(ActivityKebiao.this));
            int timeInt = Integer.parseInt(Config.getCachedThisWeek(ActivityKebiao.this))+targtWeek-1;
            //用来初始化日期头
            monColum.setText(formatData(initDay(yearInt,  timeInt,0) + "\n" + Config.DATE_WEEK_MONDAY));
            tueColum.setText(formatData(initDay(yearInt,  timeInt, 1))+ "\n" + Config.DATE_WEEK_TUESDAY);
            wedColum.setText(formatData(initDay(yearInt,   timeInt, 2))+ "\n" + Config.DATE_WEEK_WEDNESDAY);
            thrusColum.setText(formatData(initDay(yearInt,   timeInt, 3))+ "\n" + Config.DATE_WEEK_THURSDAY);
            friColum.setText(formatData(initDay(yearInt,   timeInt, 4))+ "\n" + Config.DATE_WEEK_FRIDAY);
            satColum.setText(formatData(initDay(yearInt,   timeInt, 5))+ "\n" + Config.DATE_WEEK_STAURDAY);
            sunColum.setText(formatData(initDay(yearInt,   timeInt, 6))+ "\n" + Config.DATE_WEEK_SUNDAY);


            Calendar calendarFirst = new DateTools().getWeekFirst(yearInt, timeInt, 0);
            Date dateFirst = calendarFirst.getTime();
            SimpleDateFormat  formatMonth = new SimpleDateFormat("MM");
            if(formatMonth.format(dateFirst)!=null){
                if(formatMonth.format(dateFirst).equals("01")){
                    empty.setText("\n"+Config.DATE_ONE_MONTH);
                }else if(formatMonth.format(dateFirst).equals("02")){
                    empty.setText("\n"+Config.DATE_TWO_MONTH);

                }else if(formatMonth.format(dateFirst).equals("03")){
                    empty.setText("\n"+Config.DATE_THREE_MONTH);

                }else if(formatMonth.format(dateFirst).equals("04")){
                    empty.setText("\n"+Config.DATE_FOUR_MONTH);

                }else if(formatMonth.format(dateFirst).equals("05")){
                    empty.setText("\n"+Config.DATE_FIVE_MONTH);

                }else if(formatMonth.format(dateFirst).equals("06")){
                    empty.setText("\n"+Config.DATE_SIX_MONTH);

                }else if(formatMonth.format(dateFirst).equals("07")){
                    empty.setText("\n"+Config.DATE_SEVEN_MONTH);

                }else if(formatMonth.format(dateFirst).equals("08")){
                    empty.setText("\n"+Config.DATE_EIGHT_MONTH);

                }else if(formatMonth.format(dateFirst).equals("09")){
                    empty.setText("\n"+Config.DATE_NINE_MONTH);

                }else if(formatMonth.format(dateFirst).equals("10")){
                    empty.setText("\n"+Config.DATE_TEN_MONTH);

                }else if(formatMonth.format(dateFirst).equals("11")){
                    empty.setText("\n"+Config.DATE_ELEVEN_MONTH);

                }else if(formatMonth.format(dateFirst).equals("12")){
                    empty.setText("\n"+Config.DATE_TWELVE_MONTH);

                }
            }



    }

    /**
     * 初始化日期的方法
     * @param yearInt 年份
     * @param weekLength 开学周离
     * @param day 星期几
     * @return 所对应的日期
     */
    private String initDay(int yearInt, int weekLength,int day) {
            Calendar calendar = new DateTools().getWeekFirst(yearInt, weekLength, day);
            Date date = calendar.getTime();
            SimpleDateFormat formatDay = new SimpleDateFormat("dd");
            return formatDay.format(date);
    }

    /**
     * 用来格式化日期格式
     * @param string 需要格式化的数据
     * @return 格式化之后的日期
     */
    private String formatData(String string) {
        String str =null;
        if(string.equals("01")){
            str="1";
        }else if(string.equals("02")){
            str="2";
        }else if(string.equals("03")){
            str="3";
        }else if(string.equals("04")){
            str="4";
        }else if(string.equals("05")){
            str="5";
        }else if(string.equals("06")){
            str="6";
        }else if(string.equals("07")){
            str="7";
        }else if(string.equals("08")){
            str="8";
        }else if(string.equals("09")){
            str="9";
        }else{
            str = string;
        }
        return str;
    }

    /**
     * 这个是用来初始化课表表格
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initTable() {
        //获得列头的控件
        empty = (TextView) findViewById(R.id.test_empty);
        monColum = (TextView)findViewById(R.id.test_monday_course);
        tueColum = (TextView) findViewById(R.id.test_tuesday_course);
        wedColum = (TextView) findViewById(R.id.test_wednesday_course);
        thrusColum = (TextView) findViewById(R.id.test_thursday_course);
        friColum = (TextView) findViewById(R.id.test_friday_course);
        satColum  = (TextView) findViewById(R.id.test_saturday_course);
        sunColum = (TextView) findViewById(R.id.test_sunday_course);
        course_table_layout = (RelativeLayout) ActivityKebiao.this.findViewById(R.id.test_course_rl);
        sum_relative_layout = (RelativeLayout) ActivityKebiao.this.findViewById(R.id.sum_relative_layout);
        iv_back= (ImageView) findViewById(R.id.kebiao_head_image);
        tv_setting= (TextView) findViewById(R.id.kebiao_right_title);
        tv_title= (TextView) findViewById(R.id.kebiao_midle_title);



        //屏幕宽度
        int width = GetWidthAndHeightTool.getScreenWidth(ActivityKebiao.this);
        //平均宽度
        int aveWidth = width / 8;
        //第一个空白格子设置为25宽
        empty.setWidth(width-(aveWidth+4)*7);
        monColum.setWidth(aveWidth +4);
        tueColum.setWidth(aveWidth +4);
        wedColum.setWidth(aveWidth +4);
        thrusColum.setWidth(aveWidth+4);
        friColum.setWidth(aveWidth +4);
        satColum.setWidth(aveWidth +4);
        sunColum.setWidth(aveWidth+4);

        this.screenWidth = width;
        this.aveWidth = aveWidth;
        int height = GetWidthAndHeightTool.getScreenHeight(ActivityKebiao.this)-50;
        gridHeight = height / 12;

        //设置课表界面
        //动态生成12 * maxCourseNum个textview
        for(int i = 1; i <= 12; i ++) {

            for (int j = 1; j <= 8; j++) {

                TextView tx = new TextView(ActivityKebiao.this);
                tx.setId((i - 1) * 8 + j);
                if(j==1){
                    tx.setBackground(ActivityKebiao.this.
                            getResources().getDrawable(R.drawable.course_text_view_bg));
                }
                //相对布局参数
                RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                        aveWidth +4,
                        gridHeight);
                //文字对齐方式
                tx.setGravity(Gravity.CENTER);
                //字体样式
                tx.setTextAppearance(ActivityKebiao.this, R.style.courseTableText);
                //如果是第一列，需要设置课的序号（1 到 12）
                if (j == 1) {
                    tx.setText(String.valueOf(i));
                    rp.width = width-(aveWidth +4)*7;
                    //设置他们的相对位置
                    if (i == 1)
                        rp.addRule(RelativeLayout.BELOW, empty.getId());
                    else
                        rp.addRule(RelativeLayout.BELOW, (i - 1) * 8);
                } else {
                    rp.addRule(RelativeLayout.RIGHT_OF, (i - 1) * 8 + j - 1);
                    rp.addRule(RelativeLayout.ALIGN_TOP, (i - 1) * 8 + j - 1);
                    tx.setText("");
                }

                tx.setLayoutParams(rp);
                course_table_layout.addView(tx);

            }

        }

        }



    /**
     * 用来给课表设置背景
     * @param path 这个是从SD卡设置背景的路径
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initKebiaoBackground(String path,Context context) {
        int width = GetWidthAndHeightTool.getScreenWidth(ActivityKebiao.this);
        int height = GetWidthAndHeightTool.getScreenHeight(ActivityKebiao.this);

        IOFile ioFile = new IOFile();
        //这个是SD卡中存在这个文件,就将这个bitmap设置为背景
        if(ioFile.FileIsExistSD(path)){
            //设置适合本手机分辨率的位图
            ImageSize imageSize = new ImageSize(width,height);
            //将bitmap设置为课表背景
            Bitmap bitmap = MainActivity.imageLoader.loadImageSync("file://" + Environment.getExternalStorageDirectory()+path,
                    imageSize, MainActivity.options);
            Drawable drawable = new BitmapDrawable(bitmap);
            sum_relative_layout.setBackground(drawable);

        }else{
            //如果手机硬盘中存在这个文件就将这个bitmap设置为背景
            if(ioFile.FileIsExistCache(path,context)){
                //设置适合本手机分辨率的位图
                ImageSize imageSize = new ImageSize(width,height);
                //将bitmap设置为课表背景
                Bitmap bitmap = MainActivity.imageLoader.loadImageSync("file://" + context.getCacheDir().getAbsolutePath()+path,
                        imageSize, MainActivity.options);
                Drawable drawable = new BitmapDrawable(bitmap);
                sum_relative_layout.setBackground(drawable);
            }else{//如果SD卡和手机硬盘都没有这个文件，就设置默认的bitmap
                Drawable drawable = getResources().getDrawable(R.mipmap.default_kebiao_background);
                sum_relative_layout.setBackground(drawable);
            }
        }
    }

    /**
     * 这个是网络连接失败之后出现的刷新按钮
     */
    public void initFail() {

        AgainToGetDateTools.getButton(ActivityKebiao.this, R.id.activity_register_select_city_layout).
                setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCreate(extras);
                    }
                });
    }


    /**
     * 用于网络获取课程失败之后的处理方法
     */
    public void failToShow(){
        List<Course> courseList = (List<Course>) SaveAndGetObject.readObject(ActivityKebiao.this, Config.SAVE_KECHENG_OBJECT_KEY);
        if(courseList!=null){
            //将日期信息初始化进入表头中
            initDate();
            new ShowKechengBean(new KebiaoBean(ActivityKebiao.this,
                    course_table_layout),ActivityKebiao.this).initKecheng(courseList);
        }else{
            IOOperateFile ioo = new IOOperateFile(ActivityKebiao.this);
            IOFile ioFile = new IOFile() ;
            if(Config.getCachedUsername(ActivityKebiao.this)!=null){
                //SD卡
                String string =ioo.readSDCard(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                        Config.FILE_YEAR_SEMESTER);
                if(string!=null){

                    boolean fi = ioFile.SDCardfileIsExist(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this), string);
                    if(fi){
                        String strContent =  ioo.readSDCard(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                                string);
                        List<Course> li =  new KechengDataAnalysis(ActivityKebiao.this,strContent).initData();
                        //将日期信息初始化进入表头中
                        initDate();
                        //这个函数用来将数据向界面的转换
                        new ShowKechengBean(new KebiaoBean(ActivityKebiao.this,
                                course_table_layout),ActivityKebiao.this).initKecheng(li);


                    }else{
                        new ToastTools(ActivityKebiao.this, R.string.get_kebiao_fail);
                        //这里是添加一个布局
                        initFail();
                    }
                }else{
                    //硬盘
                    String str =ioo.readToDisk(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this),
                            Config.FILE_YEAR_SEMESTER, ActivityKebiao.this);
                    if(str!=null){
                        boolean fi = ioFile.DiskfileIsExist(Config.SAVE_COURSE_TO_FILE_PATH + "/" + Config.getCachedUsername(ActivityKebiao.this), str
                                , ActivityKebiao.this);
                        if(fi){
                            String con = ioo.readToDisk(Config.SAVE_COURSE_TO_FILE_PATH + "/"+Config.getCachedUsername(ActivityKebiao.this),str,
                                    ActivityKebiao.this);

                            List<Course> li =  new KechengDataAnalysis(ActivityKebiao.this,con).initData();
                            //将日期信息初始化进入表头中
                            initDate();
                            //这个函数用来将数据向界面的转换
                            new ShowKechengBean(new KebiaoBean(ActivityKebiao.this,
                                    course_table_layout),ActivityKebiao.this).initKecheng(li);

                        }else{
                            new ToastTools(ActivityKebiao.this, R.string.get_kebiao_fail);
                            //这里是添加一个布局
                            initFail();
                        }

                    }else{
                        new ToastTools(ActivityKebiao.this, R.string.get_kebiao_fail);
                        //这里是添加一个布局
                        initFail();
                    }
                }

            }else{
                new ToastTools(ActivityKebiao.this, R.string.get_kebiao_fail);
                //这里是添加一个布局
                initFail();
            }


        }


    }


}
