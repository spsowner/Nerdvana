package com.sps;

import java.lang.Thread.UncaughtExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

import com.sps.util.LogUtil;

@ControllerAdvice
public class GlobalDefaultExceptionHandler implements UncaughtExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		StringBuilder msg = new StringBuilder();
		msg.append(e == null ? "{null}" : e.getMessage()).append(" for url ").append(req == null ? "{unknown}" : req.getRequestURI());
		
		if (!Strings.isNullOrEmpty(req.getQueryString())) {
			msg.append("?").append(req.getQueryString());
		}
		
		LogUtil.runtimeStats();
		
		LOG.error(msg.toString(), e);
		return ControllerHelper.toErrorJson("We could not process your request. Please try again in a few minutes.");
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOG.error(t.getName() + ": " + e.getMessage(), e);
	}
}
