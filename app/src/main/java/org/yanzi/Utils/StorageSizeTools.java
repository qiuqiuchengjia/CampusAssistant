package org.yanzi.Utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * 这个类用来获取手机的系统存储容量和SD卡存储容量
 */
public class StorageSizeTools {
    /**
     *这个是获取系统内部可用存储空间是多少
     * @return
     */
    public static long getAvailableInternalMemorySize(){
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks*blockSize;
    }

    /**
     * 这个是获取系统内部总共的存储空间
     * @return
     */
    public static long getTotalInternalMemorySize(){
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks*blockSize;
    }

    /**
     * 这个是取外部存储设备的当前是否安装上去
     * @return
     */
    public static boolean externalMemoryAvailable(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 判断SD卡是否不可移除，
     * @return  返回true的话就是可以移除，
     */
    public static boolean externalStorageRemovable(){
        return Environment.isExternalStorageRemovable();

    }
    /**
     * 这个是获取SD卡可用存储空间
     * @return
     */
    public static long getAvailableExternalMemorySize(){
        if(externalMemoryAvailable()){
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks*blockSize;
        }
        else{
            return -1;
        }
    }

    /**
     * 这个是获取SD卡总共存储空间
     * @return
     */
    public static long getTotalExternalMemorySize(){
        if(externalMemoryAvailable()){
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks*blockSize;
        }
        else{
            return -1;
        }
    }
}
