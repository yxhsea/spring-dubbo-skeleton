package com.skeleton.foundation.assist.filter.dubbo;

/**
 * Rpc调用记录器
 **/
public interface IRpcStubLogger {
    /**
     * 前置记录
     * @param context
     */
    void preLog(IRpcStubContext context);

    /**
     * 后置记录
     * @param context
     */
    void postLog(IRpcStubContext context);
}
