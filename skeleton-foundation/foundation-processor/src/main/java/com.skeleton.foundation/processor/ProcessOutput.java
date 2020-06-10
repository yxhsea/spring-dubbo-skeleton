package com.skeleton.foundation.processor;

import com.skeleton.foundation.collector.ICollector;

public class ProcessOutput implements IProcessOutput {

    private static final long serialVersionUID = 6357723790550296351L;
    private ICollector collector;

    public void setCollector(ICollector collector) {
        this.collector = collector;
    }

    @Override
    public ICollector getCollector() {
        return this.collector;
    }
}
