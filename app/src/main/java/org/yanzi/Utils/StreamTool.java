package org.yanzi.Utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 将输入流转换承byte数组返回
 */
public class StreamTool {

    public static  byte[] read(InputStream in) throws Exception{
        ByteArrayOutputStream out_byte = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len=0;
        while((len = in.read(buff))!= -1){

    //写到内存中  字节流

            out_byte.write( buff, 0 , len);

        }

        out_byte.close();

    // 把内存数据返回

        return  out_byte.toByteArray();
    }
}
