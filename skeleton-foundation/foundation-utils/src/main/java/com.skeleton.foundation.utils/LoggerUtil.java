package com.skeleton.foundation.utils;

import org.slf4j.Logger;

public class LoggerUtil {

    public static final String LEVEL_DEBUG = "debug";
    public static final String LEVEL_INFO = "info";
    public static final String LEVEL_WARNING = "waring";
    public static final String LEVEL_ERROR = "error";

    public static void log(Logger logger, String level, String msg, Object... objs){
        if (logger == null) {
            throw new NullPointerException("logger cannot be null.");
        }
        if (level.equals(LEVEL_DEBUG)){
            if (logger.isDebugEnabled()) {
                logger.debug(msg,objs);
            }
        }else if (level.equals(LEVEL_INFO)) {
            logger.info(msg,objs);
        } else if (level.equals(LEVEL_WARNING)) {
            logger.warn(msg,objs);
        } else if (level.equals(LEVEL_ERROR)) {
            logger.error(msg,objs);
        }
    }

    public static void debug(Logger logger, String msg, Object... objs){
        log(logger,LEVEL_DEBUG,msg,objs);
    }
    public static void info(Logger logger, String msg, Object... objs){
        log(logger,LEVEL_INFO,msg,objs);
    }
    public static void warning(Logger logger, String msg, Object... objs){
        log(logger,LEVEL_WARNING,msg,objs);
    }
    public static void error(Logger logger, String msg, Object... objs){
        log(logger,LEVEL_ERROR,msg,objs);
    }
}
