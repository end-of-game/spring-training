package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LoggingBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object o, String arg1) throws BeansException {
		if (o instanceof Loggable) {
			Loggable loggable = (Loggable) o;
			Logger log = LoggerFactory.getLogger(loggable.getClass());
			loggable.setLogger(log);
		}
		return o;
	}

	@Override
	public Object postProcessBeforeInitialization(Object o, String arg1) throws BeansException {
		return o;
	}

}
