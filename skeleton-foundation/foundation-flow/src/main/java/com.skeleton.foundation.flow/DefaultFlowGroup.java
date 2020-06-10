package com.skeleton.foundation.flow;

import com.skeleton.foundation.flow.interceptor.IFlowInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DefaultFlowGroup implements IFlowGroup {
    private List<FlowInterceptorWrapper> interceptorWrappers = new ArrayList<FlowInterceptorWrapper>();
    private IFlowInterceptor[] interceptors = new IFlowInterceptor[0];
    private IFlowUnit unit;

    private Object lockObj = new Object();


    private void resort() {
        synchronized (lockObj) {
            Collections.sort(this.interceptorWrappers, new Comparator<FlowInterceptorWrapper>() {
                @Override
                public int compare(FlowInterceptorWrapper o1, FlowInterceptorWrapper o2) {
                    return o1.pri - o2.pri;
                }
            });
            interceptors = new IFlowInterceptor[this.interceptorWrappers.size()];
            for (int i = 0, n = this.interceptorWrappers.size(); i < n; i++) {
                this.interceptors[i] = this.interceptorWrappers.get(i).interceptor;
            }
        }
    }

    @Override
    public void registerInterceptor(IFlowInterceptor interceptor, int pri) {
        if (interceptor != null) {
            FlowInterceptorWrapper wrapper = this.findWrapper(interceptor);
            if (wrapper == null) {
                this.interceptorWrappers.add(new FlowInterceptorWrapper(interceptor, pri));
                this.resort();
            }
        }
    }

    @Override
    public void unregisterInterceptor(IFlowInterceptor interceptor) {
        if (interceptor != null) {
            for (FlowInterceptorWrapper wrapper : this.interceptorWrappers) {
                if (interceptor.equals(wrapper.interceptor)) {
                    this.interceptorWrappers.remove(wrapper);
                    this.resort();
                    break;
                }
            }
        }
    }

    private FlowInterceptorWrapper findWrapper(IFlowInterceptor interceptor) {
        FlowInterceptorWrapper giw = null;
        for (FlowInterceptorWrapper wrapper : this.interceptorWrappers) {
            if (interceptor.equals(wrapper.interceptor)) {
                giw = wrapper;
                break;
            }
        }
        return giw;
    }

    @Override
    public void execute(IFlowContext context) throws Exception {
        IFlowUnit nd = this.getFlowUnit(context);
        IFlowAction action = null;
        if (nd instanceof IFlowAction) {
            action = (IFlowAction) nd;
        }
        //创建拦截器调用代理
        IActionInvocation invocation = new ActionInvocation(action, this.interceptors, this, context);
        //调用
        invocation.invoke();
    }


    @Override
    public IFlowUnit getFlowUnit(IFlowContext context) throws Exception {
        IFlowUnit nd = null;
        if (this.unit instanceof IFlowGroup) {
            nd = this.unit;
        } else if (this.unit instanceof IFlowDispatcher) {
            IFlowDispatcher dispatcher = (IFlowDispatcher) this.unit;
            nd = dispatcher.getFlowUnit(context);
        } else {
            nd = this.unit;
        }
        return nd;
    }

    public IFlowUnit getUnit() {
        return unit;
    }

    public void setUnit(IFlowUnit unit) {
        this.unit = unit;
    }
}
