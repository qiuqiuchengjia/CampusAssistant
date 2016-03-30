package org.yanzi.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.Utils.ActivityHeadInitTools;
import org.yanzi.bean.Course;

/**
 * 这个是用来展示详细的课程信息的类
 */
public class ActivityCourseDeatil extends Activity {
    private  Course course;
    private TextView courseName,courseClass,courseTeacher,courseTime,courseWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_deatil);
        course = (Course) getIntent().getSerializableExtra("Course");
        initView();
        initData();
    }
    /**
     * 用来初始化界面
     */
    private void initView() {
        courseName = (TextView) findViewById(R.id.textview_course_name);
        courseClass = (TextView) findViewById(R.id.textview_class_place);
        courseTeacher = (TextView) findViewById(R.id.textview_teacher);
        courseTime = (TextView) findViewById(R.id.textview_time);
        courseWeek = (TextView) findViewById(R.id.textview_week);


    }
    /**
     * 用来将数据适配进空间中，显示在界面
     */
    private void initData() {


        //显示周数
        if (course.getFormatWeek() != null && course.getFormatWeek().length > 0) {
            String[] week = course.getFormatWeek();
            String string = "";
            for (int i = 0; i < week.length; i++) {
                string = string + "　" + week[i];
            }
            courseWeek.setText(string + "周");
        }
        //显示节数
        if (course.getClassTime() != null && course.getDayOfWeek() != null) {
            String string = "";
            String[] time = course.getClassTime();
            if (course.getDayOfWeek().equals("a")) {
                string = "周一";
            } else if (course.getDayOfWeek().equals("aa")) {
                string = "周二";
            } else if (course.getDayOfWeek().equals("aaa")) {
                string = "周三";
            } else if (course.getDayOfWeek().equals("4")) {
                string = "周四";
            } else if (course.getDayOfWeek().equals("5")) {
                string = "周五";
            } else if (course.getDayOfWeek().equals("6")) {
                string = "周六";
            } else if (course.getDayOfWeek().equals("7")) {
                string = "周七";
            }
            if (time[0] != null && time[1] != null) {
                courseTime.setText(string + "　" + time[0] + "-" + time[1] + "节");
                string = "";
            }

        }
        //显示老师
        if (course.getTeacher() != null) {
            courseTeacher.setText(course.getTeacher());
        }
        //显示教室
        if (course.getPlace() != null) {
            courseClass.setText(course.getPlace());
        }
        //显示课程名称
        if (course.getName() != null) {
            //这个用来初始化头控件
            new ActivityHeadInitTools(ActivityCourseDeatil.this, course.getName());
            courseName.setText(course.getName());
        }



      /*  //这里是将头像显示出来
        String strBitmap = Config.getCachedUsernametouxiang(ActivityCourseDeatil.this);
        if(strBitmap!=null){
            //判断当前的SharedPreferences中是否有头像，有的话就显示
                imageView.setImageBitmap(
                        BitmapTools.StringToBitmap(Config.getCachedUsernametouxiang(ActivityCourseDeatil.this)));
         }else{
            if(Config.getCachedUsername(ActivityCourseDeatil.this)!=null){
                IOOperateFile io = new IOOperateFile(ActivityCourseDeatil.this);
               String  sd= io.readSDCard(Config.KEY_USER_HEAD_PATH,Config.getCachedUsername(ActivityCourseDeatil.this));
                if(sd!=null){
                    imageView.setImageBitmap(
                            BitmapTools.StringToBitmap(sd));
                }else{
                    String disk = io.readToDisk(Config.KEY_USER_HEAD_PATH,Config.getCachedUsername
                            (ActivityCourseDeatil.this),ActivityCourseDeatil.this);
                    if(disk!=null){
                        imageView.setImageBitmap(BitmapTools.StringToBitmap(disk));
                    }

                }
            }
        }*/

    }




}
