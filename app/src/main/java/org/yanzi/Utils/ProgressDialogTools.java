package org.yanzi.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fragmentproject.R;

/**
 * 这个是用来提示进度的progressdialog
 */
public class ProgressDialogTools {

    /**
     * 自定义的progressDialog
     * @param context 上下文
     * @param msg 加载数据时显示的信息
     * @return Dialog
     */
    public static Dialog createLoadingDialog(Context context, int msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        //加载loading_dialog.xml
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view

        // loading_dialog.xml中的LinearLayout
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        // loading_dialog.xml中的TextView
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息（如：登录中，请稍候...）

        // loading_dialog.xml中的ImageView
        ImageView imageView = (ImageView) v.findViewById(R.id.img);
        // 加载动画load_animation.xml
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        // 使用ImageView显示动画
        spinner.start();

        // 创建自定义样式loading_dialog
        Dialog loadingDialog = new Dialog(context, R.style.Custom_Progress);
        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        // 设置布局
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
        return loadingDialog;

    }
}