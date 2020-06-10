package com.skeleton.foundation.provider;

import com.skeleton.foundation.utils.spring.SpringApplicationContextUtils;

/**
 * spring 对象提供器
 **/
public class SpringComponentProvider implements IComponentProvider {
    @Override
    public Object getComponent(String componentId) {
        if (SpringApplicationContextUtils.getApplicationContext() == null){
            throw new RuntimeException("SpringApplicationContextUtils 没有找到需要的 context");
        }
        Object bean = SpringApplicationContextUtils.getApplicationContext().getBean(componentId);
        return bean;
    }
}
