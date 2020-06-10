package com.skeleton.foundation.flow;

public interface IActionInvocation {
    /**
     * 调用逻辑
     * @throws Exception
     */
    public void invoke() throws Exception;
    /**
     * 获得拦截的动作
     * @return
     */
    public IFlowAction getAction();

    /**
     * 获得逻辑上下文
     * @return
     */
    public IFlowContext getContext();
}
