package com.skeleton.foundation.flow;

import com.skeleton.foundation.flow.interceptor.IFlowInterceptor;

/**
 * 拦截器包装器
 **/
public class FlowInterceptorWrapper {
    public IFlowInterceptor interceptor;
    public int pri;

    public FlowInterceptorWrapper(IFlowInterceptor interceptor, int pri) {
        super();
        this.interceptor = interceptor;
        this.pri = pri;
    }
}
