
package com.skeleton.foundation.processor;

import com.skeleton.foundation.processor.config.ProcessorGraph;

/**
 * 用于执行processor graph
 **/
public interface IProcessActuator {

    public void initialize(ProcessorGraph graph, String... args);

    public void launch() throws Exception;

    public void shutdown() throws Exception;

    public boolean isLaunched();

}
