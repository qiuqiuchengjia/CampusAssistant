package org.yanzi.IO;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 这个类用来操作文件或者文件夹
 */
public class IOFile{

    /**
     * 用来判断一个手机硬盘下的文件是否存在
     * @param path 文件路径
     * @return
     */
    public boolean FileIsExistCache(String path,Context context){
        if(path==null){
            return false;
        }
        File file = new File(context.getCacheDir().getAbsolutePath()+path);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用来判断一个SD下的文件是否存在
     * @param path 文件路径
     * @return
     */
    public boolean FileIsExistSD(String path){
        if(path==null){
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString()+path);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用来判断一个文件是否存在
     * @param path 文件路径
     * @return
     */
    public boolean FileIsExist(String path){
        if(path==null){
            return false;
        }
        File file = new File(path);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 用来判断一个SD卡路径下的文件是否存在，存在返回true
     * @param Path 路径
     * @param filename 文件
     * @return
     */
    public boolean SDCardfileIsExist(String Path,String filename){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString()+Path,filename);

        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用来判断一个手机路径下的文件是否存在，存在返回true
     * @param Path 路径
     * @param filename 文件
     * @return
     */
    public boolean DiskfileIsExist(String Path,String filename,Context context){

        File file = new File(context.getCacheDir().getAbsolutePath()+Path,filename);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 这个函数用来判断一个目录下的文件夹是否存在
     * 如果存在返回true
     * @param path
     */
    public boolean folderIsExist(String path){
        File file = new File(path);
        if(file.exists()){
            return true;
        }else{
            return false;

        }
    }

    /**
     * 判断一个文件夹是否存在，如果不存在就创建并返回false
     * @param path
     * @return
     */
    public boolean folderIsExistCreate(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
            return true;
        }else{
            return false;
        }

    }
        /**
         * 这个用来删除文件或者文件夹
         * @param
         */
        public  boolean delete(File file) {

            if (file.isFile()) {
                file.delete();
                return true;
            }
            if(file.isDirectory()){
                File[] childFiles = file.listFiles();
                if (childFiles == null || childFiles.length == 0) {
                    file.delete();
                    return true;
                }

                for (int i = 0; i < childFiles.length; i++) {
                    delete(childFiles[i]);
                }
                file.delete();
                return true;
            }
            return false;
        }
}
