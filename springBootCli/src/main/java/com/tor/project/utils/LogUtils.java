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
import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TimeZone;

@Slf4j
public class LogUtils {
	public static String getTrace(Exception ex) {
		return buildErrorMessage(ex);
	}

	//构造异常堆栈信息
	private static String buildErrorMessage(Exception ex) {
		StringBuilder errorMsg = new StringBuilder();
		ExceptionUtil.getThrowableList(ex).forEach(throwable -> errorMsg.append(throwable.getClass().getSimpleName()).append(":").append(throwable.getMessage()).append(";"));
		errorMsg.append(getStackTraceString(ex));
		return errorMsg.toString();
	}

	//打印异常堆栈信息
	private static String getStackTraceString(Throwable ex){
		StringBuilder traceBuilder = new StringBuilder();
		StackTraceElement[] traceElements = ex.getStackTrace();
		if (traceElements != null && traceElements.length > 0) {
			for (StackTraceElement traceElement : traceElements) {
				traceBuilder.append("\n\tat ").append(traceElement.toString());
			}
		}
		return traceBuilder.toString();
	}

	/**
	 * 获取一个唯一标识码
	 * @return
	 */
	public static String getLogKey(){
		return new TimeOfSystem(System.currentTimeMillis()).getMiscFormatString(TimeZone.getDefault());
	}
}
