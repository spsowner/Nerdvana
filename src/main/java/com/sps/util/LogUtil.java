package com.sps.util;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	public static void warn(Logger log, String msg) {
		if (log.isWarnEnabled()) {
			log.warn(msg);
		}
	}
	
	public static void info(Logger log, String msg) {
		if (log.isInfoEnabled()) {
			log.info(msg);
		}
	}
	
	public static void debug(Logger log, String msg) {
		if (log.isDebugEnabled()) {
			log.debug(msg);
		}
	}
	
	public static void trace(Logger log, String msg) {
		if (log.isTraceEnabled()) {
			log.trace(msg);
		}
	}
	
	/**
	 * -Xms init heap size
	 * -Xmx max heap size
	 * -Xss stackSize
	 * @param action
	 */
	public static void runtimeStats() {
		DecimalFormat formatter = new DecimalFormat("#,###");
		Runtime rt = Runtime.getRuntime();
		long free = rt.freeMemory();
		long total = rt.totalMemory();
		long used = total - free;
		//rt.maxMemory()
		
		String msg = new StringBuilder("memory: used [")
			.append(formatter.format(used)).append("], ")
			.append("free [").append(formatter.format(free)).append("], ")
			.append("total [" + formatter.format(total) + "]").toString();
		
		info(LoggerFactory.getLogger(LogUtil.class), msg);
	}
}
