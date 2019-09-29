/**
 * 
 */
package com.singhambar.app.utilities;

import static com.singhambar.app.utilities.AppConstants.GLOBAL_LOGGER;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public class AppLogger {

	private static final Logger LOGGER = Logger.getLogger(GLOBAL_LOGGER);

	static {
		try {
//			PropertyConfigurator.configure("log4j.properties");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public static Logger getLogger(Object o) {
		return Logger.getLogger(o.getClass().getName());
	}
	
	public static Logger getLogger(String str) {
		return Logger.getLogger(str);
	}
	
	public static void info(String message) {
		LOGGER.info(message);
	}
	
	public static void debug(String message) {
		LOGGER.debug(message);
	}
	
	public static void error(String message) {
		LOGGER.error(message);
	}
	
	public static void fatal(String message) {
		LOGGER.fatal(message);
	}
}
