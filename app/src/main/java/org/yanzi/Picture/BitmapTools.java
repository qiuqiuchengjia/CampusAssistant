package org.yanzi.Picture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.loopj.android.http.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 *  这个是处理bitmap的工具类
 */
public class BitmapTools{

    /**
     * 获取bitmap的大小
     * @param bitmap 需要计算大小的bitmap
     * @return bitmap的大小，单位是KB
     */
    public long getBitmapSize(Bitmap bitmap){
        ByteArrayOutputStream baos  = new ByteArrayOutputStream();
        if(bitmap!=null){
            //质量压缩方法，这里100表示不要压缩,把压缩过后的数据存入baos中
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            return baos.toByteArray().length/1024;
        }else{
            return 0;
        }

    }

    /**
     * 把bitmap转换成String
     *
     * @param filePath
     * @return
     */
    public static String bitmapFormFileToString(String filePath) {

        Bitmap bm = new BitmapCompress().getSmallBitmap(filePath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();

        return android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);

    }

    /**
     * 把bitmap转换成String
     *
     * @param bm
     * @return
     */
    public static String bitmapToString(Bitmap bm) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();

        return android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);

    }
    /**
     * 添加到图库
     */
    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }


    /**
     * 将bitmap回收，用完的bitmap一定要记得回收
     * @param bitmap
     */
    public static void recoverBitmap(Bitmap bitmap){
        if(!(bitmap==null)&&!(bitmap.isRecycled())){
            bitmap.recycle();
            System.gc();

        }

    }


    /**
     * 这个方法是将bitmap转换成为byte数组,采用jpg格式
     * @param bitmap
     * @return
     */
    public static  byte[] bitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] appiction = baos.toByteArray();// 转为byte数组
        return appiction;

    }

    /**
     * 将字符串转换成bitmap，但是仅仅适用于用默认的Base64编码加密的字符串
     * @param string
     * @return
     */
    public static Bitmap StringToBitmap(String string){
        Bitmap bitmap =null;
        byte [] bitmaparray;
        bitmaparray = Base64.decode(string, Base64.DEFAULT);
        bitmap =
                BitmapFactory.decodeByteArray(bitmaparray, 0,
                        bitmaparray.length);
        return bitmap;

    }


}
