/**  
 * All rights Reserved 杭州海量
 * @Title:  LogUtils.java   
 * @Package com.itsea.platform.ocr.util   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: 姜辉晖     
 * @date:   2018年7月20日 下午3:59:07   
 * @version V1.0     
 */
package com.tor.project.utils;

import cn.com.itsea.util.TimeOfSystem;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TimeZone;

@Slf4j
public class LogUtils {
	public static String getTrace(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}

    /**
     * 获取一个唯一标识码
     * @return
     */
    public static String getLogKey(){
        return new TimeOfSystem(System.currentTimeMillis()).getMiscFormatString(TimeZone.getDefault());
    }
}
