package com.sps.util;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redirects {@link System#out} and {@link System#err}
 * to our {@link Logger}.
 */
public class SystemOutLogger {
	private static final Logger LOG = LoggerFactory.getLogger(SystemOutLogger.class);
	
	public static void redirect() {
		System.setOut(new PrintStream(System.out) {
			@Override
			public void print(String s) {
				LogUtil.info(LOG, s);
			}
			
			@Override
			public void println(String s) {
				print(s);
			}
		});
		
		System.setErr(new PrintStream(System.err) {
			@Override
			public void print(String s) {
				LOG.error(s);
			}
			
			@Override
			public void println(String s) {
				print(s);
			}
		});
	}
}
