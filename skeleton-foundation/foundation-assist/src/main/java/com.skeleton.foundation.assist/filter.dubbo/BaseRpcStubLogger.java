package com.skeleton.foundation.assist.filter.dubbo;


public abstract class BaseRpcStubLogger implements IRpcStubLogger {

    @Override
    public void preLog(IRpcStubContext context) {
        if (context == null || !(context instanceof DefaultRpcStubContext)) {
            throw new RuntimeException("BaseRpcStubLogger - 非法context");
        }
        DefaultRpcStubContext defaultRpcStubContext = (DefaultRpcStubContext) context;
        this.processPreLog(defaultRpcStubContext);
    }

    @Override
    public void postLog(IRpcStubContext context) {
        if (context == null || !(context instanceof DefaultRpcStubContext)) {
            throw new RuntimeException("BaseRpcStubLogger - 非法context");
        }
        DefaultRpcStubContext defaultRpcStubContext = (DefaultRpcStubContext) context;
        this.processPostLog(defaultRpcStubContext);
    }

    /**
     * 前置日志处理
     *
     * @param defaultRpcStubContext
     */
    protected abstract void processPreLog(DefaultRpcStubContext defaultRpcStubContext);

    /**
     * 后置日志处理
     *
     * @param defaultRpcStubContext
     */
    protected abstract void processPostLog(DefaultRpcStubContext defaultRpcStubContext);
}
