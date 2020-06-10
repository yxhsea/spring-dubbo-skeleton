
package com.skeleton.foundation.processor.execute;


import com.skeleton.foundation.processor.config.ProcessorGraph;

import java.util.List;

/**
 * 过程管理器
 **/
public interface IProcessManager {
	
    public void setExecutor(IProcessExecutor executor);

    public IProcessExecutor getExecutor();

    public void executeProcesses(String processId, String... args) throws Exception;

    public void registerProcess(ProcessorGraph processorGraph);

    public List<ProcessorGraph> getProcesses();
    
}