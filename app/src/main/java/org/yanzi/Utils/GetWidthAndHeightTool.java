package org.yanzi.Utils;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * 这个是一个工具方法，用来获取屏幕的宽度和高度
 */
public class GetWidthAndHeightTool {

    //这个方法用来获取屏幕的宽度
    public static int getScreenWidth(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        return display.getWidth();
    }

    //这个方法用来获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

}
