package com.skeleton.foundation.flow;

public interface IFlowAction extends IFlowUnit {
    /**
     * 执行
     *
     * @param context
     * @throws Exception
     */
    void execute(IFlowContext context) throws Exception;
}
