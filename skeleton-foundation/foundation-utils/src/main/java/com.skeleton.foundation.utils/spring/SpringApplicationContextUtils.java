package com.skeleton.foundation.utils.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring context 工具
 **/
public class SpringApplicationContextUtils {

    private static ApplicationContext applicationContext;
    private static String contextId;
    private static Object lockObj = new Object();

    public static String getContextId() {
        return contextId;
    }

    public static void setContextId(String contextId) {
        SpringApplicationContextUtils.contextId = contextId;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationContextUtils.applicationContext = applicationContext;
    }

    public static synchronized ApplicationContext getApplicationContext() {
        if(applicationContext!=null) {
            return applicationContext;
        }else{
            //synchronized (lockObj) {
                applicationContext = new ClassPathXmlApplicationContext(contextId == null ? "worker.xml" : contextId);
                return applicationContext;
            //}
        }
    }

}
