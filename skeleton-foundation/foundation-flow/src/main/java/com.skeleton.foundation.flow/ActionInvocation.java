package com.skeleton.foundation.flow;


import com.skeleton.foundation.flow.interceptor.IFlowInterceptor;

public class ActionInvocation implements IActionInvocation{
    private IFlowContext context;
    private IFlowGroup group;
    private IFlowInterceptor[] interceptors;
    private IFlowAction action;
    private int index;
    private int length;

    public ActionInvocation(IFlowAction action, IFlowInterceptor[] interceptors, IFlowGroup group, IFlowContext
            context) {
        super();
        this.action = action;
        this.interceptors = interceptors;
        this.context = context;
        this.group = group;
        this.index = -1;
        this.length = (this.interceptors != null ? this.interceptors.length : 0);
    }

    @Override
    public void invoke() throws Exception {
        if (this.index <= (this.length - 2)) {
            this.index++;
            this.interceptors[this.index].intercept(this);
        } else {
            if (this.action != null) {
                this.action.execute(context);
            }
        }
    }

    @Override
    public IFlowAction getAction() {
        return this.action;
    }

    @Override
    public IFlowContext getContext() {
        return this.context;
    }
}
