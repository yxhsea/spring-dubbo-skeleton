package com.skeleton.foundation.flow;

/**
 * 包装flow group
 **/
public class FlowGroupWrapper {
    public IFlowGroup group;
    public String groupId;
    public int pri;

    public FlowGroupWrapper(IFlowGroup group, String groupId, int pri) {
        super();
        this.group = group;
        this.groupId = groupId;
        this.pri = pri;
    }
}
