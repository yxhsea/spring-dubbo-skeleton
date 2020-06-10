package com.skeleton.foundation.flow.interceptor;

import com.skeleton.foundation.flow.IActionInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeLoggerInterceptor implements IFlowInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public void intercept(IActionInvocation invocation) throws Exception {
        long c = System.currentTimeMillis();
        invocation.invoke();

        logger.debug(String.format("Action [%s] execute invocation [%s] ms", invocation.getAction().getClass()
                .getSimpleName(), System.currentTimeMillis() - c));
    }
}
