
package com.skeleton.foundation.processor.config;

import java.io.Serializable;

/**
 * 处理器节点
 **/
public class ProcessorNode implements Serializable{

    private static final long serialVersionUID = -6680701868251850100L;
    private String id;
    private String componentId;
    private Class outputType;
    private int parallelism;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public int getParallelism() {
        return parallelism;
    }

    public void setParallelism(int parallelism) {
        this.parallelism = parallelism;
    }

    public Class getOutputType() {
        return outputType;
    }

    public void setOutputType(Class outputType) {
        this.outputType = outputType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProcessorNode that = (ProcessorNode) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProcessorNode{");
        sb.append("id='").append(id).append('\'');
        sb.append(", componentId='").append(componentId).append('\'');
        sb.append(", parallelism=").append(parallelism);
        sb.append('}');
        return sb.toString();
    }
}