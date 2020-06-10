package com.skeleton.foundation.flow;

import com.skeleton.foundation.flow.interceptor.IFlowInterceptor;

/**
 * 用于组织flow
 **/
public interface IFlowGroup extends IFlowAction, IFlowDispatcher{
    /**
     * 注册拦截器
     * @param interceptor
     * @param pri 优先级
     */
    void registerInterceptor(IFlowInterceptor interceptor, int pri);

    /**
     * 注销拦截器
     * @param interceptor
     */
    void unregisterInterceptor(IFlowInterceptor interceptor);
}
