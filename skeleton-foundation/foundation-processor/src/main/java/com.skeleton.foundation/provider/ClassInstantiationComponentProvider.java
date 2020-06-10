package com.skeleton.foundation.provider;

/**
 * 类实例化组件提供器
 **/
public class ClassInstantiationComponentProvider implements IComponentProvider {
    @Override
    public Object getComponent(String componentId) {
        try {
            Class<?> clz = Class.forName(componentId, true, this.getClass().getClassLoader());
            if(clz!=null){
                return clz.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
