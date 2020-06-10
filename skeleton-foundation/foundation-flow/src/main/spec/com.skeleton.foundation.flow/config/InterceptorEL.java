package com.skeleton.foundation.flow.config;

/**
 * 用于映射Interceptor json
 **/
public class InterceptorEL {
    private int pri;
    private String componentId;

    public int getPri() {
        return pri;
    }

    public void setPri(int pri) {
        this.pri = pri;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
