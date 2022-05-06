package com.duynn.sqa1_n8_yc21_shopmanager_spring.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    protected static Logger logger;
    static {
        String filename = "log/database.log";
        System.setProperty("logFilename", filename);
        logger = LogManager.getLogger(MyLogger.class);
    }
    private static void setLogger(Class className) {
        String filename = "log/database.log";
        System.setProperty("logFilename", filename);
        logger = LogManager.getLogger(className);
    }

    public static void logInfo(Class className, String message) {
        setLogger(className);
        logger.info(message);
    }

    public static void logWarn(Class className, String message) {
        setLogger(className);
        logger.warn(message);
    }

    public static void logError(Class className, String message, Exception e) {
        setLogger(className);
        logger.error(message, e);
    }

    public static void main(String[] args) {
        MyLogger.logInfo(MyLogger.class, "Hello");
        MyLogger.logWarn(MyLogger.class, "Hello");
        MyLogger.logError(MyLogger.class, "Hello", new Exception("Hello"));
    }
}
