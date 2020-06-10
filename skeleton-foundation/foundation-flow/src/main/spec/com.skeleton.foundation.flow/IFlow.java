package com.skeleton.foundation.flow;

public interface IFlow extends IFlowAction {
    /**
     * 获得当前逻辑的全部组
     * @return
     */
    IFlowGroup[] getGroups();

    /**
     * 获得指定的逻辑组
     * @param groupId
     * @return
     */
    IFlowGroup findGroup(String groupId);

    /**
     * 获得指定的逻辑组信息
     * @param groupId
     * @return
     */
    FlowGroupWrapper findGroupInfo(String groupId);

    /**
     * 注册逻辑组
     */
    void registerGroup(String groupId, IFlowGroup group, int pri);

    /**
     * 注销逻辑组
     * @param groupId
     */
    void unregisterGroup(String groupId);
}
