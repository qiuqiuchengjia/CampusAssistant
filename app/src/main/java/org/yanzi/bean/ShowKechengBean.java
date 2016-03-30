package org.yanzi.bean;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.Utils.ToastTools;
import org.yanzi.activity.ActivityCourseDeatil;
import org.yanzi.activity.ActivityKebiao;
import org.yanzi.constant.Config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  这个类用来将数据向界面的转换
 */
public class ShowKechengBean {
    private KebiaoBean kebiao;
    private ActivityKebiao context;
    private List<TextView>  saveTextView;
    public ShowKechengBean(KebiaoBean kebiaoBean, ActivityKebiao activityKebiao){
        this.kebiao = kebiaoBean;
        this.context=activityKebiao;
        saveTextView =new ArrayList<TextView>();
    }

    /**
     * 这个是用来获取当前周数的课程
     * @param list
     */
    public void initKecheng(List<Course> list) {

        Log.d("ShowKechengBean", Config.getCachedWeekSpace(context));
        //设置标题
        setTextview(Integer.parseInt(Config.getCachedWeekSpace(context)),Config.THIS_WEEK_TEXTVIEW);
        if (list != null) {
            initKechengWithWeek(list, Config.getCachedWeekSpace(context));
        }

    }

    public  void setTextview(int position,String string){
        Config.cacheWeekShowWeek(context,position+"");
        switch (position){
            case 1:context.setTextViewTitle(Config.KEBIAO_WEEK_1 + string);
                break;
            case 2:context.setTextViewTitle(Config.KEBIAO_WEEK_2 + string);
                break;
            case 3:context.setTextViewTitle(Config.KEBIAO_WEEK_3 + string);
                break;
            case 4:context.setTextViewTitle(Config.KEBIAO_WEEK_4 + string);
                break;
            case 5:context.setTextViewTitle(Config.KEBIAO_WEEK_5+string);
                break;
            case 6:context.setTextViewTitle(Config.KEBIAO_WEEK_6+string);
                break;
            case 7:context.setTextViewTitle(Config.KEBIAO_WEEK_7+string);
                break;
            case 8:context.setTextViewTitle(Config.KEBIAO_WEEK_8+string);
                break;
            case 9:context.setTextViewTitle(Config.KEBIAO_WEEK_9+string);
                break;
            case 10:context.setTextViewTitle(Config.KEBIAO_WEEK_10+string);
                break;
            case 11:context.setTextViewTitle(Config.KEBIAO_WEEK_11+string);
                break;
            case 12:context.setTextViewTitle(Config.KEBIAO_WEEK_12+string);
                break;
            case 13:context.setTextViewTitle(Config.KEBIAO_WEEK_13+string);
                break;
            case 14:context.setTextViewTitle(Config.KEBIAO_WEEK_14+string);
                break;
            case 15:context.setTextViewTitle(Config.KEBIAO_WEEK_15+string);
                break;
            case 16:context.setTextViewTitle(Config.KEBIAO_WEEK_16+string);
                break;
            case 17:context.setTextViewTitle(Config.KEBIAO_WEEK_17+string);
                break;
            case 18:context.setTextViewTitle(Config.KEBIAO_WEEK_18+string);
                break;
            case 19:context.setTextViewTitle(Config.KEBIAO_WEEK_19+string);
                break;
            case 20:context.setTextViewTitle(Config.KEBIAO_WEEK_20+string);
                break;
            case 21:context.setTextViewTitle(Config.KEBIAO_WEEK_21+string);
                break;
            case 22:context.setTextViewTitle(Config.KEBIAO_WEEK_22+string);
                break;
            case 23:context.setTextViewTitle(Config.KEBIAO_WEEK_23+string);
                break;
            case 24:context.setTextViewTitle(Config.KEBIAO_WEEK_24+string);
                break;
            case 25:context.setTextViewTitle(Config.KEBIAO_WEEK_25+string);
                break;

        }
    }

    /**
     * 用来展现课程
     * @param list 课程的集合
     * @param string 第几周的课程
     */
    public void initKechengWithWeek(List<Course> list,String string) {
        if(list!=null){
            Iterator it = list.iterator();

            while(it.hasNext()){
                boolean falg = true;
                final Course course = (Course) it.next();
                String [] week = course.getWeek();
                String [] time = course.getClassTime();
                for(int i=0 ;i<week.length ;i++) {
                    if (week[i] != null ){
                        if(week[i].equals(string)) {
                            falg=false;
                            //这个是课程是星期几加上课程开始是第几节，只能从2-19,颜色现在只能是1-7
                            switch (Integer.parseInt(course.getDayOfWeek())+Integer.parseInt(time[0])){
                                case 2:
                                    setCourseColor(course,time,1);
                                    break;
                                case 3:
                                    setCourseColor(course,time,2);
                                    break;
                                case 4:
                                    setCourseColor(course,time,3);
                                    break;
                                case 5:
                                    setCourseColor(course,time,4);
                                    break;
                                case 6:
                                    setCourseColor(course,time,5);
                                    break;
                                case 7:
                                    setCourseColor(course,time,6);
                                    break;
                                case 8:
                                    setCourseColor(course,time,7);
                                    break;
                                case 9:
                                    setCourseColor(course,time,1);
                                    break;
                                case 10:
                                    setCourseColor(course,time,2);
                                    break;
                                case 11:
                                    setCourseColor(course,time,3);
                                    break;
                                case 12:
                                    setCourseColor(course,time,4);
                                    break;
                                case 13:
                                    setCourseColor(course,time,5);
                                    break;
                                case 14:
                                    setCourseColor(course,time,6);
                                    break;
                                case 15:
                                    setCourseColor(course,time,7);
                                    break;
                                case 16:
                                    setCourseColor(course,time,1);
                                    break;
                                case 17:
                                    setCourseColor(course,time,2);
                                    break;
                                case 18:
                                    setCourseColor(course,time,3);
                                    break;
                                case 19:
                                    setCourseColor(course,time,4);
                                    break;

                            }

                        }
                    }

                }
                if(falg){
                    falg=false;
                    setGrayCourseColor(course,time,0);
                }

            }
        }else{
            //这里是网络获取课程表失败之后调用缓存来显示课程表
            List<Course> courseList = (List<Course>) SaveAndGetObject.readObject(context, Config.SAVE_KECHENG_OBJECT_KEY);
            if(courseList!=null){
              initKecheng(courseList);
            }else{
                new ToastTools(context, R.string.show_course_fail);
                context.initFail();
            }

        }

    }

    /**
     * 这个方法是将颜色设置给课程
     * @param course 课程
     * @param time
     * @param color 颜色，现在只能有1-7选择
     */
    private void setCourseColor(final Course course, String[] time, int color) {
        TextView textView = kebiao.showCourse(course.getName() + "@" + course.getPlace(),
                Integer.parseInt(course.getDayOfWeek()),
                Integer.parseInt(time[0]), time.length, color);

        saveTextView.add(textView);
        //将字体的大小设置为16
        textView.setTextSize(18);
        //为每个课程设置点击事件，点击进去可以看到课程的详细信息
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityCourseDeatil.class);
                intent.putExtra("Course", course);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 这个方法是将颜色设置给课程
     * @param course 课程
     * @param time
     * @param color 颜色，现在只能有1-7选择
     */
    private void setGrayCourseColor(final Course course, String[] time, int color) {
        TextView textView = kebiao.showCourse(course.getName() + "@" + course.getPlace(),
                Integer.parseInt(course.getDayOfWeek()),
                Integer.parseInt(time[0]), time.length, color);

        saveTextView.add(textView);
        //将字体的大小设置为16
        textView.setTextSize(18);
        textView.setTextColor(R.color.TextTransparent);
        //为每个课程设置点击事件，点击进去可以看到课程的详细信息
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityCourseDeatil.class);
                intent.putExtra("Course", course);
                context.startActivity(intent);
            }
        });
    }


}
