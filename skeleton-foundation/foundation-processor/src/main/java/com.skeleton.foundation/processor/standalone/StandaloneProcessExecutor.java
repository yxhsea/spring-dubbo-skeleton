package com.skeleton.foundation.processor.standalone;

import com.skeleton.foundation.processor.config.ProcessorGraph;
import com.skeleton.foundation.processor.execute.IProcessExecutor;
import com.skeleton.foundation.provider.IComponentProvider;
/**
 * Standalone模式ProcessExecutor
 **/
public class StandaloneProcessExecutor implements IProcessExecutor {

    private IComponentProvider componentProvider;

    public StandaloneProcessExecutor(IComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    public void execute(ProcessorGraph processGraph, String... args) throws Exception {
        StandaloneProcessActuator actuator = new StandaloneProcessActuator(this.componentProvider);
        actuator.setMaxPending(100000);
        actuator.initialize(processGraph, args);
        actuator.launch();
    }
}
