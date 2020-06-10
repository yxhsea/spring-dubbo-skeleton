package com.skeleton.foundation.flow;

import com.skeleton.foundation.flow.provider.IComponentProvider;

/**
 * flow管理器
 **/
public interface IFlowManager {
    /**
     * 设置ComponentProvider
     *
     * @param componentProvider
     */
    void setComponentProvider(IComponentProvider componentProvider);

    /**
     * 注册
     */
    void registerGraph(String id, IFlow obj);

    /**
     * 注销
     *
     * @param graphId
     */
    void unregisterFlow(String graphId);

    /**
     * 查询
     *
     * @return
     */
    IFlow findFlow(String id);

    /**
     * 查询组
     *
     * @param groupId
     * @return
     */
    IFlowGroup findGroup(String id, String groupId);

    /**
     * 执行
     *
     * @param context
     * @throws Exception
     */
    void execute(String id, IFlowContext context) throws Exception;
}
