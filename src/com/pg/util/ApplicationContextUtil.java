package com.pg.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;
	private static final Logger log = Logger.getLogger(ApplicationContextUtil.class);
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		// TODO Auto-generated method stub
		
		context = ctx;
		
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}
	
	public static <T> T getMapper(Class<T> c)
	{
		return context.getBean(c);
	}

	

	public static void main(String[] args) {

		log.info("Enter the main()....");
		log.debug("Enter the main()....");
		log.warn("Enter the main()....");
		log.info("Enter the main()....");
		log.error("error");
		System.out.println("this is a log4j test.");
		log.info("log end.");

	}

}
