package com.skeleton.foundation.provider;

import java.io.Serializable;

/**
 * 对象提供器
 **/
public interface IComponentProvider extends Serializable {
    /**
     * 获取组件对象
     * @param componentId
     * @return
     */
    Object getComponent(String componentId);
}
