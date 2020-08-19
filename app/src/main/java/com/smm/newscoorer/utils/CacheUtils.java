package com.smm.newscoorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 缓存json
 * @author wangdh
 *
 */
public class CacheUtils {
    /**
     * 获取json缓存数据
     * @param context
     * @param key 
     * @return
     */
    public static String readCache(Context context, String key) {
        SharedPreferences sp= context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
    /**
     * 写入缓存
     * @param context
     * @param key : 接口的url
     * @param value ： json字符串
     */
    public static void saveCache(Context context, String key, String value) {
        SharedPreferences sp= context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }
    
}
