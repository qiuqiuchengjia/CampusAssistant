package org.yanzi.constant;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.yanzi.Utils.GetWidthAndHeightTool;

import java.io.File;

/**
 * application类是为了那些需要保存全局变量设计的基本类，
 * 这个类在包创建的时候就已经创建了
 */
public class ImageLoaderApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context applicationContext) {
        int width = GetWidthAndHeightTool.getScreenWidth(applicationContext);
        int  height= GetWidthAndHeightTool.getScreenHeight(applicationContext);
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), Config.KEY_IMAGE_CACHE_PATH);//自定义缓存路径
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(applicationContext)
                //.memoryCacheExtraOptions(width,height)//保存每个缓存文件的最大宽度
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // 你可以通过自己的内存缓存实现,这里是2M
                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(100 * 1024 * 1024)  // 100 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))//自定义缓存路径
                .imageDownloader(new BaseImageDownloader(applicationContext, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }


}
