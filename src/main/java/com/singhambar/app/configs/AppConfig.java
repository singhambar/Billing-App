/**
 * 
 */
package com.singhambar.app.configs;

import java.util.logging.Logger;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public class AppConfig extends ResourceConfig{

	public AppConfig() {
		
		Logger logger = Logger.getLogger(this.getClass().getName());
		
		packages("com.singhambar.rests");
		
		register(AuthenticationFilter.class);
		logger.info("\n ambar\n");
		BeanFactory.getBean("entityManagerFactory");
	}
}
