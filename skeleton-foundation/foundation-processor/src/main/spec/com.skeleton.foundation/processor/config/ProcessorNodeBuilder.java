
package com.skeleton.foundation.processor.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * builder
 **/
public class ProcessorNodeBuilder {

    public static ProcessorNodeBuilder createWithId(String nodeId){
        ProcessorNodeBuilder nb = new ProcessorNodeBuilder();
        nb.withId(nodeId);
        return nb;
    }

    private ProcessorNode node;
    private List<ProcessorLinker> linkers;

    public ProcessorNodeBuilder() {
        this.node = new ProcessorNode();
        this.linkers = new ArrayList<ProcessorLinker>();
    }

    public ProcessorNodeBuilder withId(String nodeId) {
        this.node.setId(nodeId);
        return this;
    }

    public ProcessorNodeBuilder withComponentId(String componentId) {
        this.node.setComponentId(componentId);
        return this;
    }

    public ProcessorNodeBuilder withParallelism(int parallelism) {
        this.node.setParallelism(parallelism);
        return this;
    }

    public ProcessorNodeBuilder withOutputType(Class outputType){
        this.node.setOutputType(outputType);
        return this;
    }

    public ProcessorNodeBuilder from(String fromNodeId, String linkType, Map<String, String> linkParameters) {
        ProcessorLinker linker = new ProcessorLinker();
        linker.setFromNodeId(fromNodeId);
        linker.setLinkType(linkType);
        linker.setLinkParameters(linkParameters);
        this.linkers.add(linker);
        return this;
    }

    public ProcessorNodeBuilder from(String fromNodeId, String linkType) {
        ProcessorLinker linker = new ProcessorLinker();
        linker.setFromNodeId(fromNodeId);
        linker.setLinkType(linkType);
        this.linkers.add(linker);
        return this;
    }

    public ProcessorNodeBuilder from(String fromNodeId) {
        ProcessorLinker linker = new ProcessorLinker();
        linker.setFromNodeId(fromNodeId);
        this.linkers.add(linker);
        return this;
    }

    public ProcessorNode getProcessorNode() {
        return this.node;
    }

    public List<ProcessorLinker> getProcessorLinkers() {
        List<ProcessorLinker> ls = new ArrayList<ProcessorLinker>(this.linkers.size());
        for (ProcessorLinker k : this.linkers) {
            k.setToNodeId(this.node.getId());
            ls.add(k);
        }
        return ls;
    }


}
