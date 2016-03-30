package org.yanzi.constant;

import android.content.Context;
import android.graphics.Bitmap;

import org.yanzi.Picture.BitmapZoom;
import org.yanzi.Utils.GetWidthAndHeightTool;

/**
 *  这个类是专门用来控制imageview的大小
 */
public class ImageViewSizeControl {
    /**
     * 这个是用来控制登录界面的imageview的大小
     * @param bitmap 需要适配的bitmap
     * @param context 上下文
     * @return 视频好的bitmap
     */
    public static Bitmap LoginImageViewLessen(Bitmap bitmap,Context context){
        int width = GetWidthAndHeightTool.getScreenWidth(context)/7;//这个是定义显示的头像为我们屏幕宽度的九分之一
        Bitmap newBit = BitmapZoom.bitmapZoomBySize(bitmap, width, width);
        return newBit;

    }
}
