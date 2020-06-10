package com.skeleton.foundation.flow.interceptor;

import com.skeleton.foundation.flow.IActionInvocation;

/**
 * flow 拦截器
 **/
public interface IFlowInterceptor {
    /**
     * 拦截
     * @param invocation
     * @throws Exception
     */
    void intercept(IActionInvocation invocation) throws Exception;
}
