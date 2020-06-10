package com.skeleton.foundation.assist.support;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.*;

public class ResultInvoker<T> implements Invoker<T> {
    private Invoker invoker;
    private final T obj;
    public  ResultInvoker(Invoker invoker,T obj) {
        this.obj = obj;
        this.invoker = invoker;
    }
    @Override
    public Class getInterface() {
        return this.invoker.getInterface();
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        return AsyncRpcResult.newDefaultAsyncResult(obj,invocation);
    }
    @Override
    public URL getUrl() {
        return  this.invoker.getUrl();
    }
    @Override
    public boolean isAvailable() {
        return  this.invoker.isAvailable();
    }
    @Override
    public void destroy() {
        this.invoker.destroy();
    }


}
