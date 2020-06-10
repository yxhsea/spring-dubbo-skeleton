
package com.skeleton.foundation.processor.config;

import java.util.ArrayList;
import java.util.List;

/**
 * graph builder
 **/
public class ProcessorGraphBuilder {

    public static ProcessorGraphBuilder createWithId(String graphId) {
        ProcessorGraphBuilder gb = new ProcessorGraphBuilder(graphId);
        return gb;
    }

    private ProcessorGraph graph;
    private List<ProcessorNodeBuilder> nodeBuilders;

    public ProcessorGraphBuilder(String graphId) {
        this.graph = new ProcessorGraph();
        this.graph.setGraphId(graphId);
        this.nodeBuilders = new ArrayList<ProcessorNodeBuilder>();
    }

    public ProcessorGraphBuilder addNode(ProcessorNodeBuilder nodeBuilder) {
        this.nodeBuilders.add(nodeBuilder);
        return this;
    }

    public ProcessorGraph build() {
        ProcessorGraph g = new ProcessorGraph();

        List<ProcessorNode> nodes = new ArrayList<ProcessorNode>();
        List<ProcessorLinker> linkers = new ArrayList<ProcessorLinker>();
        for(ProcessorNodeBuilder nb : this.nodeBuilders) {
            ProcessorNode node = nb.getProcessorNode();
            List<ProcessorLinker> ls = nb.getProcessorLinkers();
            if(!nodes.contains(node)) nodes.add(node);
            for(ProcessorLinker pl : ls){
                if(!linkers.contains(pl)) linkers.add(pl);
            }
        }
        g.setGraphId(this.graph.getGraphId());
        g.setLinkers(linkers);
        g.setNodes(nodes);
        return g;
    }

    public static void main(String[] args) {
        ProcessorGraph graph = ProcessorGraphBuilder.createWithId("transformer")
                .addNode(ProcessorNodeBuilder.createWithId("input")
                        .withComponentId("comp-input")
                        .withParallelism(1)
                )
                .addNode(ProcessorNodeBuilder.createWithId("ouput")
                        .withComponentId("comp-output")
                        .withParallelism(1)
                        .from("input")
                ).build();

        System.out.println(">> " + graph);

    }

}
