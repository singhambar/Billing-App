/**
 * 
 */
package com.singhambar.app.configs;

import org.glassfish.jersey.server.ResourceConfig;

import com.singhambar.app.security.AuthenticationFilter;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public class AppConfig extends ResourceConfig {

	public AppConfig() {
		packages("com.singhambar.rests");
		register(AuthenticationFilter.class);
		BeanFactory.loadApplicationContext();
	}
}
