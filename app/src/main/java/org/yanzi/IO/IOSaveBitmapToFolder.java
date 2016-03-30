package org.yanzi.IO;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import org.yanzi.constant.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 这个方法用来保存bitmap到指定文件夹
 * 对于bitmap没有任何限制
 */
public class IOSaveBitmapToFolder {

    /**
     * 这个是将bitmap保存到本地，没有进行任何的压缩处理
     * 如果这个文件已经存在，那么将会把原来那个文件删除
     *
     * @param bitmap     图片
     * @param path       这个是存储的目录
     * @param bitmapName 图片的名字
     * @return
     */
    public  boolean saveBitmap(Bitmap bitmap, String path,String bitmapName) {
        File f = new File(path ,bitmapName+".png");
        //File f = new File(path ,bitmapName);
        IOFile ioFile= new IOFile();
        if(ioFile.folderIsExistCreate(path)){
            if (f.exists()) {
                f.delete();
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 这个类专门用来存储头像到本地，，路径已经被写死
     * @param bitmap
     * @param context
     * @param headName
     * @return
     */
    public boolean saveHead(Bitmap bitmap , Context context,String headName){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            boolean b =saveBitmap(bitmap,
                       Environment.getExternalStorageDirectory().toString()+ Config.KEY_USER_HEAD_PATH, headName);
            return b;
        }else{
            boolean b =saveBitmap(bitmap, context.getCacheDir().getAbsolutePath()+Config.KEY_USER_HEAD_PATH,headName);
            return b;
        }
    }

    /**
     * 这个类专门用来存储课表背景到本地，，路径已经被写死
     * @param bitmap
     * @param context
     * @param headName
     * @return
     */
    public boolean saveKebiaoBackground(Bitmap bitmap , Context context,String headName){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            boolean b =saveBitmap(bitmap,
                    Environment.getExternalStorageDirectory().toString()+ Config.KEY_KEBIAO_BACKGROUND_PATH, headName);
            return b;
        }else{
            boolean b =saveBitmap(bitmap, context.getCacheDir().getAbsolutePath()+Config.KEY_KEBIAO_BACKGROUND_PATH,headName);
            return b;
        }
    }
}
