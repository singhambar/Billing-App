package com.singhambar.app.configs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public enum AppContext {

	INSTANCE;

	private ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:applicationConfig.xml");

	public ApplicationContext getAppContextInstance() {
		return appContext;
	}

	/**
	 * Close this application context, releasing all resources and locks that the
	 * implementation might hold. This includes destroying all cached singleton
	 * beans.
	 */
	public void close() {
		appContext = getAppContextInstance();
		if (appContext instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) appContext).close();
		}
	}

}
