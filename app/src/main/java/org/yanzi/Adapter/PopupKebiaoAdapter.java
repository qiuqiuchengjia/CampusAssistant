package org.yanzi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.Utils.SaveAndGetObject;
import org.yanzi.activity.ActivityKebiao;
import org.yanzi.bean.Course;
import org.yanzi.bean.KebiaoBean;
import org.yanzi.bean.ShowKechengBean;
import org.yanzi.constant.Config;
import org.yanzi.ui.PopupKebiaoMenu;

import java.util.List;

/**
 *
 */
public class PopupKebiaoAdapter extends BaseAdapter {
    private  ActivityKebiao context;
    private List<String> itemList;
    private RelativeLayout course_table_layout;
    public PopupKebiaoAdapter(ActivityKebiao context, List<String> itemList, RelativeLayout course_table_layout) {
        this.context=context;
        this.itemList =itemList;
        this.course_table_layout=course_table_layout;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.pomenu_kebiao_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.groupItem = (Button) convertView.findViewById(R.id.pop_item_button);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.groupItem.setText(itemList.get(position));
        onclick(position, holder);
        int index = position+1;
        convertView.setBackgroundResource(R.drawable.kebiao_menu_button_selector);
        holder.groupItem.setTextColor(Config.COLOR_KEBIAO_BULE_TEXT);
        if(index==Integer.parseInt(Config.getCachedShowWeek(context))) {
            convertView.setBackgroundColor(Config.COLOR_KEBIAO_BULT_BACKGROUND);
            holder.groupItem.setTextColor(Config.COLOR_KEBIAO_WHITE_TEXT);
        }
        return convertView;
    }
    /**
     * 这个用来设置点击事件
     * @param position
     * @param viewHolder
     */
    private void onclick(final int position, final ViewHolder viewHolder) {

        viewHolder.groupItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里将课程集合对象获取从SharedPreferences中
                List<Course> courses = (List<Course>) SaveAndGetObject.readObject(context, Config.SAVE_KECHENG_OBJECT_KEY);
                ActivityKebiao activityKebiao = new ActivityKebiao();
                int year = Integer.parseInt(Config.getCachedKechengYear(context));
                int week = Integer.parseInt(Config.getCachedWeekSpace(context)) +
                        Integer.parseInt(Config.getCachedThisWeek(context));
                context.ShowTargtWeekDate(position + 1);
                KebiaoBean kebiaoBean = new KebiaoBean(context, course_table_layout);
                course_table_layout.removeAllViews();
                context.initTable();
                new ShowKechengBean(kebiaoBean, context).initKechengWithWeek(courses, position + 1 + "");
                Config.cacheWeekShowWeek(context, position + 1 + "");
                PopupKebiaoMenu.popupWindow.dismiss();


                if ((position + 1) == Integer.parseInt(Config.getCachedWeekSpace(context))) {
                    TextView textView = setTextview(position, Config.THIS_WEEK_TEXTVIEW);
                    if (textView != null) {
                        //设置颜色为白色
                        textView.setTextColor(0xFFFFFFFF);
                    }
                } else {
                    TextView textView = setTextview(position, Config.NO_THIS_WEEK_TEXTVIEW);
                    if (textView != null) {
                        //设置颜色为蓝色
                        textView.setTextColor(0xCC0079FF);
                    }

                }

            }
        });


    }
    public TextView setTextview(int position, String string){
        TextView textView=null;
        switch (position+1){
            case 1: textView=context.setTextViewTitle(Config.KEBIAO_WEEK_1+string);
                break;
            case 2:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_2+string);
                break;
            case 3:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_3+string);
                break;
            case 4:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_4+string);
                break;
            case 5:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_5+string);
                break;
            case 6:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_6+string);
                break;
            case 7:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_7+string);
                break;
            case 8:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_8+string);
                break;
            case 9:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_9+string);
                break;
            case 10:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_10+string);
                break;
            case 11:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_11+string);
                break;
            case 12:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_12+string);
                break;
            case 13:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_13+string);
                break;
            case 14:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_14+string);
                break;
            case 15:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_15+string);
                break;
            case 16:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_16+string);
                break;
            case 17:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_17+string);
                break;
            case 18:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_18+string);
                break;
            case 19:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_19+string);
                break;
            case 20:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_20+string);
                break;
            case 21:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_21+string);
                break;
            case 22:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_22+string);
                break;
            case 23:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_23+string);
                break;
            case 24:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_24+string);
                break;
            case 25:textView=context.setTextViewTitle(Config.KEBIAO_WEEK_25+string);
                break;

        }
        return textView;
    }
    private final class ViewHolder {
        Button groupItem;
    }
}
