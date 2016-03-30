package org.yanzi.Utils;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 这个是专门用来销毁activity的工具类
 */
public class DestoryActivityTools {
    public  static Map<String,Activity> destoryMap = new HashMap<String,Activity>();
    public DestoryActivityTools() {
    }

    /**
     * 清空队列
     */
    public static  void removeDestoryActivityAll(){
        destoryMap.clear();
    }
    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(Activity activity,String activityName) {
        destoryMap.put(activityName, activity);

    }
    /**
     * 减少activity从销毁队列
     * @param activityName
     */
    public static  void reduceDestoryActivity(String activityName){
        destoryMap.remove(activityName);
    }

    /**
     *销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet=destoryMap.keySet();
        for (String key:keySet){
            destoryMap.get(key).finish();
        }
    }

}
