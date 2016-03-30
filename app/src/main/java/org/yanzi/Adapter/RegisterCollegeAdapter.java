package org.yanzi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fragmentproject.R;

import org.yanzi.activity.ActivityRegisterSelectCareer;

import java.util.List;

/**
 * 这个是注册界面选择学院的适配器
 */
public class RegisterCollegeAdapter extends BaseAdapter{
    private Context context;
    private List<String> listcity;
    private String province,city,school,time;

    public RegisterCollegeAdapter(Context context, List<String> listcity,
                                  String province, String city, String school,String time) {
        this.context = context;
        this.city=city;
        this.listcity = listcity;
        this.province = province;
        this.school=school;
        this.time=time;
    }

    @Override
    public int getCount() {
        return listcity.size();
    }

    @Override
    public Object getItem(int position) {
        return listcity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final  ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_register_city_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            onclick(listcity.get(position), viewHolder);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
             onclick(listcity.get(position), viewHolder);
        }
        return convertView;
    }

    /**
     * 这个用来设置点击事件
     * @param string
     * @param viewHolder
     */
    private void onclick(final String string, ViewHolder viewHolder) {
        viewHolder.textView.setText(string);
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ActivityRegisterSelectCareer.class);
                Bundle bundle = new Bundle();
                bundle.putString("province", province);
                bundle.putString("city", city);
                bundle.putString("school",school);
                bundle.putString("time",time);
                bundle.putString("college",string);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private static class ViewHolder{
        TextView textView;

    }
}
