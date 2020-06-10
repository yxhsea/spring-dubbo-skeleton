package com.skeleton.foundation.flow;

/**
 * flow调度器，用于调度flow unit
 **/
public interface IFlowDispatcher extends IFlowUnit{

    /**
     * 获取flow unit
     * @param context
     * @return
     * @throws Exception
     */
    IFlowUnit getFlowUnit(IFlowContext context) throws Exception;
}
