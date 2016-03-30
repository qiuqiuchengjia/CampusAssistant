package org.yanzi.IO;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 这个类用来对硬盘中和SD卡中的文件进行创建和读取
 */
public class IOOperateFile {
    private Context context;
    public IOOperateFile(Context context){
        this.context = context;
    }

    /**
     * 将文件存储到SD卡中，成功则返回true
     * @param fileName 文件名字
     * @param content 文件内容
     * @return 文件是否存入SD卡中
     */
    public boolean saveSDCard(String path ,String fileName ,String content){

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return false;
        }
        File f = new File(Environment.getExternalStorageDirectory()+path ,fileName);
        IOFile ioFile= new IOFile();
        if(ioFile.folderIsExistCreate(Environment.getExternalStorageDirectory()+path)){
            if (f.exists()) {
                f.delete();
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 从SD卡中读取指定文件
     * @param filename 文件名字
     * @return 文件的内容
     */
    public String readSDCard(String path,String filename){
        FileInputStream fis = null;
        String string=null;
        IOFile ioFile = new IOFile();
        if(ioFile.SDCardfileIsExist(path,filename)){
            File file = new File(Environment.getExternalStorageDirectory().toString()+path,filename);
            try {
                fis = new FileInputStream(file);
                int len=0;
                byte [] buffer = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while((len=fis.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                }
                string = new String(baos.toByteArray());
                return string;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return string;
    }
    /**
     * 用来读取手机硬盘中的文件
     * @param filename 文件名
     * @return 文件中的内容
     */
    public String readDisk(String filename){

        FileInputStream fileInputStream = null;
        String string=null;
        try {
            fileInputStream = context.openFileInput(filename);
            int len =0;
            byte [] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((len = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer,0,len);
            }
            string = new String(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return string;
    }
    /**
     * 用来保存文件到手机硬盘中,（/data/data/package name/files）
     * @param fileName  文件名
     * @param content 文件内容
     */
    public void saveDisk(String fileName,String content){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream =context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 指定路径存入文件到手机硬盘
     * @param path
     * @param fileName
     * @param context
     * @param content
     * @return
     */
    public boolean saveToDisk(String path ,String fileName ,Context context,String content){

        File f = new File(context.getCacheDir().getAbsolutePath()+path ,fileName);
        IOFile ioFile= new IOFile();
        if(ioFile.folderIsExistCreate(context.getCacheDir().getAbsolutePath()+path)){
            if (f.exists()) {
                f.delete();
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 指定路径读取东西从手机硬盘
     * @param path
     * @param context
     * @param filename
     * @return
     */
    public String readToDisk(String path,String filename,Context context){
        FileInputStream fis = null;
        String string=null;
        IOFile ioFile = new IOFile();
        if(ioFile.DiskfileIsExist(path,filename,context)){
            File file = new File(context.getCacheDir().getAbsolutePath()+path,filename);
            try {
                fis = new FileInputStream(file);
                int len=0;
                byte [] buffer = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while((len=fis.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                }
                string = new String(baos.toByteArray());
                return string;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return string;
    }

}
