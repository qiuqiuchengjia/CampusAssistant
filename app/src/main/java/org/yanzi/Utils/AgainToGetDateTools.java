package org.yanzi.Utils;

import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fragmentproject.R;

/**
 * 这个类用来给错误或者没有网络的情况下去刷新列表的一个按钮
 */
public class AgainToGetDateTools {

    /**
     *
     * @param context
     * @param linearLayout  这个是你的activity的布局的父LinearLayout
     * @return
     */
    public static Button getButton(Activity context,int linearLayout){
        int height = GetWidthAndHeightTool.getScreenHeight(context);
        int width = GetWidthAndHeightTool.getScreenWidth(context);
        LinearLayout layout = (LinearLayout) context.findViewById(linearLayout);

        LinearLayout mlayout = new LinearLayout(context);
        mlayout.setPadding(width / 2 - 55, height / 2 - 100, 0, 0);
        Button button = new Button(context);
        button.setText(R.string.fail_get_listview_and_again);
        button.setTextColor(R.color.blue);
        button.setBackgroundResource(R.drawable.register_user_btn);
        mlayout.addView(button);
        layout.addView(mlayout);
        context.setContentView(layout);
        return button;

    }
}
