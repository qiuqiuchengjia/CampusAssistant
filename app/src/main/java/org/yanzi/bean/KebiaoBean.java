package org.yanzi.bean;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.Utils.GetWidthAndHeightTool;

/**
 * 这个课表的业务类，用来处理数据库中的数据向界面的转换
 */
public class KebiaoBean {

    final int[] background;
    private Activity activity;
    private RelativeLayout course_table_layout;
    private int aveWidth,gridHeight;

    public KebiaoBean(Activity activity, RelativeLayout course_table_layout){
        this.activity=activity;
        this.course_table_layout=course_table_layout;
        this.aveWidth= GetWidthAndHeightTool.getScreenWidth(activity)/8;
        this.gridHeight=(GetWidthAndHeightTool.getScreenHeight(activity)-50)/12;

//五种颜色的背景
        background = new int[]{R.drawable.course_info_gary,R.drawable.course_info_blue, R.drawable.course_info_green,
                R.drawable.course_info_red,
                R.drawable.course_info_yellow,
                R.drawable.course_info_color2,R.drawable.course_info_color3,R.drawable.course_info_color4};

    }



    /**
     * 这个函数用来显示课表的信息
     * @param classInfo  课程信息
     * @param week  星期几
     * @param lesson 从第几节课开始
     * @param classlength  课程的长度
     * @param color 课程显示的颜色
     */
    public  TextView showCourse(String classInfo,int week,int lesson,int classlength,int color){

        // 添加课程信息
        TextView courseInfo = new TextView(activity);
        courseInfo.setClickable(true);
        courseInfo.setText(classInfo);
        //该textview的高度根据其节数的跨度来设置
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                aveWidth+4,
                (gridHeight - 5) * classlength );

        //textview的位置由课程开始节数和上课的时间（day of week）确定
        rlp.topMargin = 6 + (lesson - 1) * gridHeight;
        rlp.leftMargin = (week-1)*(aveWidth+4);
        // 偏移由这节课是星期几决定
        rlp.addRule(RelativeLayout.RIGHT_OF, 1);
        //字体剧中
        courseInfo.setGravity(Gravity.CENTER);
        // 设置一种背景
        courseInfo.setBackgroundResource(background[color]);
        courseInfo.setTextSize(12);
        courseInfo.setLayoutParams(rlp);
        courseInfo.setTextColor(Color.WHITE);
        //设置不透明度
        courseInfo.getBackground().setAlpha(222);
        course_table_layout.addView(courseInfo);
        return courseInfo;
    }



}
