package com.tor.utils;

import cn.com.itsea.util.FormatedLogAppender;
import cn.com.itsea.util.TimeOfSystem;

import java.util.TimeZone;

public class FormatedLogUtil extends FormatedLogAppender {
    private String key;
    private boolean isSucc = true;

    public String getKey() {
        return key;
    }

    public FormatedLogUtil(Object... logs){
        this.key = getLogKey();
        this.append("logkey:" + this.key);
        for (Object log : logs) {
            this.append(log);
        }
    }

    public boolean isSucc() {
        return isSucc;
    }

    public FormatedLogUtil setSucc(boolean succ) {
        isSucc = succ;
        return this;
    }

    /**
     * 获取一个唯一标识码
     * @return
     */
    private static String getLogKey(){
        return new TimeOfSystem(System.currentTimeMillis()).getMiscFormatString(TimeZone.getDefault());
    }
}
