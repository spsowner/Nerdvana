package com.sps;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class ControllerHelper {
	static File getWebRoot(HttpSession session) {
		ServletContext cxt = session.getServletContext();
		return new File(cxt.getRealPath("/"));
	}
	
	static String toJson(String key, String value) {
		return "{\"" + key + "\":\"" + value + "\"}";
	}
	
	static String toErrorJson(String errMsg) {
		return toJson("error", errMsg);
	}
}
