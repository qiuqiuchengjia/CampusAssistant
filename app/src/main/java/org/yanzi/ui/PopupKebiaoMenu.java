package org.yanzi.ui;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.fragmentproject.R;

import org.yanzi.Adapter.PopupKebiaoAdapter;
import org.yanzi.Utils.GetWidthAndHeightTool;
import org.yanzi.activity.ActivityKebiao;
import org.yanzi.constant.Config;

import java.util.List;

/**
 * 这个类是课表界面下拉选择周数的菜单类
 */
public class PopupKebiaoMenu {
    private ActivityKebiao context;
    public  static PopupWindow popupWindow ;
    private ListView listView;
    private PopupKebiaoAdapter adapter;
    private  List<String> itemList;
    public PopupKebiaoMenu(ActivityKebiao context, List<String> itemList, RelativeLayout course_table_layout ) {
        this.context = context;
        this.itemList = itemList;

        View view = LayoutInflater.from(context).inflate(R.layout.popup_kebiao_menu, null);

        //设置 listview
        listView = (ListView)view.findViewById(R.id.popup_list);
        Button dropThisWeek = (Button) view.findViewById(R.id.drop_button);

        adapter = new PopupKebiaoAdapter(context, itemList,course_table_layout);
        listView.setAdapter(adapter);

        //这里是设置listview滑动到指定位置
        if(Config.getCachedShowWeek(context)!=null){
            int thisWeek = Integer.parseInt(Config.getCachedShowWeek(context));
            if((thisWeek-3)<=0){

            }else{
                listView.setSelection(thisWeek - 3);
            }
        }
        popupWindow = new PopupWindow(view, GetWidthAndHeightTool.getScreenWidth(context)/4,
                GetWidthAndHeightTool.getScreenHeight(context)/4);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        dropThisWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PopupKebiaoMenu", "修改当前周");
            }
        });
    }


    /**
     * 添加一个item到指定的位置
     * @param item
     * @param i
     */
    public void setTargt(int i,String item){
        itemList.remove(i - 1);
        itemList.add(i - 1, item);
    }

    /**
     * 下拉式 弹出 pop菜单 parent 右下角
     * @param parent
     */
    public void showAsDropDown(View parent) {
        popupWindow.showAtLocation(parent, 0, GetWidthAndHeightTool.getScreenWidth(context) / 2
                - GetWidthAndHeightTool.getScreenWidth(context) / 8, 85);

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        //刷新状态
        popupWindow.update();
    }

    /**
     * 隐藏菜单
     */
    public void dismiss() {
        popupWindow.dismiss();
    }


}
