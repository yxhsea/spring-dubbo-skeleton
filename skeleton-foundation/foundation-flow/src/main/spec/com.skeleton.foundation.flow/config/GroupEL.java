package com.skeleton.foundation.flow.config;

import java.util.List;

/**
 * 用于映射group json
 **/
public class GroupEL {
    private String id;
    private int pri;
    private String refFlowId;
    private String refGroupId;
    private String nodeComponentId;
    private List<InterceptorEL> interceptors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPri() {
        return pri;
    }

    public void setPri(int pri) {
        this.pri = pri;
    }

    public String getRefFlowId() {
        return refFlowId;
    }

    public void setRefFlowId(String refFlowId) {
        this.refFlowId = refFlowId;
    }

    public String getRefGroupId() {
        return refGroupId;
    }

    public void setRefGroupId(String refGroupId) {
        this.refGroupId = refGroupId;
    }

    public String getNodeComponentId() {
        return nodeComponentId;
    }

    public void setNodeComponentId(String nodeComponentId) {
        this.nodeComponentId = nodeComponentId;
    }

    public List<InterceptorEL> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<InterceptorEL> interceptors) {
        this.interceptors = interceptors;
    }
}
