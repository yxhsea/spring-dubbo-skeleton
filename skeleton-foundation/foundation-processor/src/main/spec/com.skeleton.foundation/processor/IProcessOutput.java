
package com.skeleton.foundation.processor;

import com.skeleton.foundation.collector.ICollector;

import java.io.Serializable;

/**
 * 包含处理器所需要物料
 **/
public interface IProcessOutput extends Serializable {

    ICollector getCollector();

}

