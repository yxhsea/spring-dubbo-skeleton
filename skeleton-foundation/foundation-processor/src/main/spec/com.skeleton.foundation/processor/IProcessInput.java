/**  
 * Copyright © 2016 ETERNITY. All rights reserved.
 * @Title: IProcessInput.java
 * @Prject: wisdomplanet-foundation
 * @Package: org.wisdomplanet.foundation.processor
 * @Description: TODO
 * @author: A.Z  
 * @date: 2016-12-05
 * @version: V1.0  
 */
package com.skeleton.foundation.processor;

import java.io.Serializable;

/**
 * 包含处理器所需要物料
 **/
public interface IProcessInput extends Serializable {

    Object getInputObject();

    boolean hasInputObject();

}
