package com.smm.newscoorer.bean;

import android.text.TextUtils;

public class BaseBean<T> {

    public String retcode;//状态码
    public T data;//额外的数据

    public boolean isSuccess(){
        return TextUtils.equals(retcode,"200");
    }
}
