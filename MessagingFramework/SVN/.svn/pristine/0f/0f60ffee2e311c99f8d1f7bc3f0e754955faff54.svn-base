package com.siemens.cms.message.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContainerFactory implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(SpringContainerFactory.class);
	private static ApplicationContext applicationContext;
	
	public SpringContainerFactory() {
		logger.debug("SpringContainerFactory initialized");
    }

	@Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        SpringContainerFactory.applicationContext = applicationContext;
        logger.debug("SpringContainerFactory - applicationContext set");
    }

    /**
     * @return the applicationContext
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public static <T> T getBean(final Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
