package com.singhambar.app.configs;


import com.singhambar.app.configs.AppContext;

/**
 * @author Ambar Singh
 * @email singhambar55@gmail.com
 *
 */
public class BeanFactory {

	public static Object getBean(String beanName) {
		return AppContext.INSTANCE.getAppContextInstance().getBean(beanName);
	}

	public static Object getBeanWithDefaultNull(String beanName) {
		try {
			return AppContext.INSTANCE.getAppContextInstance().getBean(beanName);
		} catch (Exception ex) {
			return null;
		}
	}

	public static Object getBean(String beanName, Object... args) {
		return AppContext.INSTANCE.getAppContextInstance().getBean(beanName, args);
	}

	public static Object getBeanWithDefaultNull(String beanName, Object... args) {
		try {
			return AppContext.INSTANCE.getAppContextInstance().getBean(beanName, args);
		} catch (Exception ex) {
			return null;
		}
	}

	public <T> T getBean(Class<T> requiredType) {
		return AppContext.INSTANCE.getAppContextInstance().getBean(requiredType);
	}

	public static <T> T getBean(String beanName, Class<T> requiredType) {
		return AppContext.INSTANCE.getAppContextInstance().getBean(beanName, requiredType);
	}

	public static <T> T getBean(String beanName, Class<T> requiredType, boolean defaultNull) {
		try {
			return AppContext.INSTANCE.getAppContextInstance().getBean(beanName, requiredType);
		} catch (Exception e) {
			if (defaultNull)
				return null;
			throw e;
		}
	}

	public static <T> T getBeanWithDefaultNull(String beanName, Class<T> requiredType) {
		try {
			return AppContext.INSTANCE.getAppContextInstance().getBean(beanName, requiredType);
		} catch (Exception e) {
			return null;
		}
	}
}
