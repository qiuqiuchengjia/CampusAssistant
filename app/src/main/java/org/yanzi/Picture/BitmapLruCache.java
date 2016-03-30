package org.yanzi.Picture;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 *  这里是使用LruCache 类缓存一张bitmap
 */
public class BitmapLruCache {
    private LruCache<String, Bitmap> mMemoryCache;
    public BitmapLruCache(){
        //获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        //使用最大可用内存值1/8作为缓存大小
        int cacheSize = maxMemory/8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //重写此方法来衡量每张图片的大小，默认返回图片数量
                return bitmap.getByteCount()/1024;
            }
        };
    }

    /**
     * 将bitmap加入到缓存中
     * @param key
     * @param bitmap
     */
    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if(getBitmapFromMemCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }

    }

    /**
     * 将bitmap从缓存中读取出来
     * @param key
     * @return
     */
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
