package com.skeleton.foundation.processor;

/**
 */
public class ProcessInput implements IProcessInput {

    private static final long serialVersionUID = 5893597342303795109L;
    private Object inputObject;

    public void setInputObject(Object inputObject) {
        this.inputObject = inputObject;
    }

    @Override
    public Object getInputObject() {
        return this.inputObject;
    }

    @Override
    public boolean hasInputObject() {
        return this.inputObject != null;
    }
}
