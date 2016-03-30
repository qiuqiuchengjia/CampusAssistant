package org.yanzi.IO;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.ta.util.cache.DiskLruCache;

import org.yanzi.Utils.MD5Tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 这个类用来保存和读取图片到硬盘缓存中
 */
public class IODiskReadSave {
    DiskLruCache mDiskLruCache = null;
    private Context context;

    /**
     * 这个是读取储存在手机硬盘中的图片
     * @param context
     * @param folder
     */
    public IODiskReadSave(Context context, String folder) {
        this.context = context;

        try {

            File cacheDir = getDiskCacheDir(context, folder);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除缓存中的某个文件夹
     * @param folderName
     * @return
     */
    public boolean delete(String folderName) {
        IOFile iofile = new IOFile();
        if (iofile.folderIsExist(context.getExternalCacheDir().getPath() + File.separator + folderName)) {
            //这个是判断SD下有这个文件夹
            File file = new File(context.getExternalCacheDir().getPath() + File.separator + folderName);
            boolean delete = iofile.delete(file);
            return delete;
        }
        if(iofile.folderIsExist(context.getCacheDir().getPath()+File.separator + folderName)){
            //这个是系统内存中有这个文件夹
            File file = new File(context.getCacheDir().getPath()+File.separator + folderName);
            boolean delete = iofile.delete(file);
            return delete;
        }
        return false;
    }


    /**
     * 这个用来保存图片
     * @param bitmap
     * @param key
     * @return
     */
    public Boolean saveBitmap(Bitmap bitmap,String key){
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {

            DiskLruCache.Editor editor = mDiskLruCache.edit(MD5Tool.md5(key));
            if(editor!=null){
                OutputStream outputStream = editor.newOutputStream(0);
                if(downloadUrlToStream(bitmap,outputStream)){
                    editor.commit();
                    return true;
                }else {
                    editor.abort();
                    return false;
                }
            }
            mDiskLruCache.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 这个函数用来读取图片
     * @param key
     * @return
     */
    public Bitmap readBitmap(String key){

        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(MD5Tool.md5(key));
            if(snapshot!=null){
                InputStream is = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * 这个用来移除缓存
     * @param key
     * @return
     */
    public boolean remove(String key){
        try {
            mDiskLruCache.remove(MD5Tool.md5(key));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean downloadUrlToStream(Bitmap bitmap, OutputStream outputStream) {
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            in = new BufferedInputStream(is, 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路径，
     * 否则就调用getCacheDir()方法来获取缓存路径。前者获取到的就是
     * /sdcard/Android/data/<application package>/cache 这个路径，
     * 而后者获取到的是 /data/data/<application package>/cache 这个路径
     * @param
     * @param
     * @return
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 这个方法用来获取应用程序的版本号
     * 每当版本号改变，缓存路径下存储的所有数据都会被清除掉，
     * 因为DiskLruCache认为当应用程序有版本更新的时候，所有的数据都应该从网上重新获取。
     * @param context
     * @return
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
