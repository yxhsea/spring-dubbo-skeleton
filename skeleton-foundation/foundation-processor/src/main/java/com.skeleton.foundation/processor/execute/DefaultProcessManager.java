package com.skeleton.foundation.processor.execute;

import com.skeleton.foundation.processor.config.ProcessorGraph;

import java.util.ArrayList;
import java.util.List;

public class DefaultProcessManager implements IProcessManager {

    private IProcessExecutor processExecutor;
    private List<ProcessorGraph> processGraphs;

    public DefaultProcessManager() {
        this.processGraphs = new ArrayList<ProcessorGraph>();
    }

    @Override
    public void setExecutor(IProcessExecutor executor) {
        this.processExecutor = executor;
    }

    @Override
    public IProcessExecutor getExecutor() {
        return this.processExecutor;
    }

    @Override
    public void executeProcesses(String processId, String... args) throws Exception {
        if (processId == null) {
            throw new Exception("There is no process with Id[" + processId + "]");
        }
        ProcessorGraph graph = null;
        for (ProcessorGraph g : this.processGraphs) {
            if (processId.equals(g.getGraphId())) {
                graph = g;
                break;
            }
        }
        if (graph == null) {
            throw new Exception("There is no process with Id[" + processId + "]");
        }
        this.processExecutor.execute(graph, args);
    }

    @Override
    public void registerProcess(ProcessorGraph processorGraph) {
        if (processorGraph != null) {
            this.processGraphs.add(processorGraph);
        }
    }

    @Override
    public List<ProcessorGraph> getProcesses() {
        return this.processGraphs;
    }
}
