package org.yanzi.IO;

import android.content.Context;
import android.os.Environment;

/**
 * 这个类专门用来选择和判断存储路径
 */
public class IOStoragePathSelect {
    private Context context;
    public IOStoragePathSelect(Context context){
        this.context=context;
    }

    /**
     * 用来获取手机存储空间还有多少字节
     * @return 剩余的字节数
     */
    public long getCacheDirFreeSpace(){
        return context.getCacheDir().getFreeSpace();
    }

    /**
     * 用来获取SD卡的剩余存储空间
     * @return 剩余的字节数
     */
    public long getSDCardFreeSpace(){
        return Environment.getExternalStorageDirectory().getFreeSpace();
    }
    /**
     * 用来判断SD卡是否存在，如果存在则返回SD的根路径
     * 如果不存在，返回手机存储路径( /data/data/com.example.fragmentproject/cache )
     * @return 存储路径
     */
    public String priorSDCard(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
                    return Environment.getExternalStorageDirectory().toString();
        }else{
            return  context.getCacheDir().getAbsolutePath();
        }

    }

    /**
     * 用来判断SD卡是否存在
     * @return
     */
    public static boolean SDCardExist(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            return true;
        }else{
            return false;
        }
    }




}
