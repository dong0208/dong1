package com.kaisheng.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {
	Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	@Test
	public void logTest(){
		logger.trace("trace message");
		logger.debug("debug message");
		logger.info("{},{},login","jack","localhost");
		logger.warn("warn message");
		logger.error("error message");
	}
}
