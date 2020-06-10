package com.skeleton.foundation.collector;

import com.skeleton.foundation.collector.ICollector;

/**
 */
public class ProxyCollector implements ICollector {

    private ICollector delegateCollector;
    private int counter;

    public ProxyCollector(ICollector delegateCollector) {
        this.delegateCollector = delegateCollector;
        this.counter = 0;
    }

    @Override
    public void collect(Object object) {
        if(object!=null) {
            this.counter ++;
        }
        this.delegateCollector.collect(object);
    }

    public int getCollectedSize() {
        return this.counter;
    }
}
