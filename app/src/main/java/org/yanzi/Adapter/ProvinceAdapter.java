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

import org.yanzi.activity.ActivityRegisterCity;

/**
 * 这个是注册界面关于省份的listview的适配器
 */
public class ProvinceAdapter extends BaseAdapter{

    private Context context;
    private String[] strings = new String []{"北京市","天津市","重庆市","上海市","河北省","山西省",
    "辽宁省","吉林省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省",
    "湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","黑龙江省","广西壮族自治区",
    "内蒙古自治区","宁夏回族自治区","新疆维吾尔自治区","西藏自治区"};

    public ProvinceAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final  ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_register_province_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_name);
            onclick(strings[position], viewHolder);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
            onclick(strings[position],viewHolder);
        }
        return convertView;
    }

    /**
     * 这个用来设置点击事件
     * @param
     * @param
     */
    private void onclick(final String string, ViewHolder viewHolder) {
        viewHolder.textView.setText(string);
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityRegisterCity.class);
                Bundle bundle = new Bundle();
                bundle.putString("province", string);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private static class ViewHolder{
          TextView textView;

    }
}
