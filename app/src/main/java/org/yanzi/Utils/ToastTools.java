package org.yanzi.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 这个是专门用来生成Toast的工具
 */
public class ToastTools {
    private int string;
    private Context context;
    private int i;

    /**
     * 这个是两个参数的，代表的是短时间
     * @param string
     * @param context
     */
    public ToastTools( Context context, int string) {
        this.string = string;
        this.context = context;
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }

    /**
     * 如果是三个参数的，就代表长时间的显示
     * @param string
     * @param context
     * @param i
     */
    public ToastTools( Context context,int string,int i) {
        this.string = string;
        this.context = context;
        Toast.makeText(context,string,Toast.LENGTH_LONG).show();
    }


}
