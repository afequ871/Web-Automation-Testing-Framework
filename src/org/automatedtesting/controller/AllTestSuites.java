package org.automatedtesting.controller;

import java.util.HashMap;
//import java.util.logging.Level;
import java.util.logging.LogManager;



import org.automatedtesting.suites.*;
import org.automatedtesting.utils.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import ch.qos.logback.classic.Level;

public final class AllTestSuites {
	private static Logger logger ;
	
	public static void main(String[] args) throws InterruptedException {
		AllTestSuites mainClass = new AllTestSuites();	
		// Optionally remove existing handlers attached to j.u.l root logger
		SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)
		SLF4JBridgeHandler.install();
		LogManager.getLogManager().getLogger("").setLevel(java.util.logging.Level.FINEST);		
		
		logger = LoggerFactory.getLogger(mainClass.getClass());
		LogbackFileUtils.start(mainClass.getClass());
		
		logger.info("--------- Web Automation Test Suites ----------");
		printResults(new LoginLogout().LoginLogout());
		Thread.sleep(5000);
		printResults(new Profile().RunProfile());
		
		LogbackFileUtils.stop();
	}
	
	public static void printResults(HashMap result){
		logger.info(result.get("testName") + "\t" + result.get("totalTime")  + "\t" + result.get("result"));
	}
}
