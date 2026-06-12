package org.example.utils;

import org.example.model.StandardReport;

/**
 * ThreadLocalUtils
 *
 * @author Yang QingBo
 * @date 2026-06-12 15:18
 * @description
 */

public class ThreadLocalUtils {
	
	private static ThreadLocal<StandardReport> reportThreadLocal = new ThreadLocal<>();
	
	public static void set(StandardReport report) {
		reportThreadLocal.set(report);
	}
	
	public static StandardReport get() {
		return reportThreadLocal.get();
	}
	
	public static void remove() {
		reportThreadLocal.remove();
	}
}
