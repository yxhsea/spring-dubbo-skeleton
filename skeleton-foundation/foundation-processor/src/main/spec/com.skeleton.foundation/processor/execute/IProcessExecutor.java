
package com.skeleton.foundation.processor.execute;

import com.skeleton.foundation.processor.config.ProcessorGraph;
/**
 * 过程执行器
 **/
public interface IProcessExecutor {

	public void execute(ProcessorGraph processorGraph, String... args) throws Exception;
	
}
