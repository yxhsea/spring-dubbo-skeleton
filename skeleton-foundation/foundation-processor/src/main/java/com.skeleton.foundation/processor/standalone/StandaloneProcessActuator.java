package com.skeleton.foundation.processor.standalone;

import com.skeleton.foundation.collector.ICollector;
import com.skeleton.foundation.processor.IProcessActuator;
import com.skeleton.foundation.processor.config.*;
import com.skeleton.foundation.provider.IComponentProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Standalone模式ProcessActuator
 **/
public class StandaloneProcessActuator implements IProcessActuator, ICollector, Runnable {

    private boolean launched;
    private ProcessorGraph processorGraph;
    private String[] processorArgs;
    private IComponentProvider componentProvider;

    private ConcurrentLinkedQueue<ProcessTask> tasks;
    private ExecutorService executorService;

    private int maxPending;
    private int maxParallelism;
    private Map<String, StandaloneProcessorAgent> agentMap;
    private AtomicBoolean stopped;

    public IComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public int getMaxPending() {
        return maxPending;
    }

    public void setMaxPending(int maxPending) {
        this.maxPending = maxPending;
    }

    public int getMaxParallelism() {
        return maxParallelism;
    }

    public void setMaxParallelism(int maxParallelism) {
        this.maxParallelism = maxParallelism;
    }

    public StandaloneProcessActuator(IComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
        this.maxParallelism = 50;
        this.maxPending = 10000;
        this.stopped = new AtomicBoolean(false);
        this.agentMap = new ConcurrentHashMap<String, StandaloneProcessorAgent>();
    }

    @Override
    public void initialize(ProcessorGraph graph, String... args) {
        this.processorGraph = graph;
        this.processorArgs = args;
        this.launched = false;

        this.tasks = new ConcurrentLinkedQueue<ProcessTask>();
        this.executorService = new ThreadPoolExecutor(10,10,
                200L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(10));
    }

    @Override
    public void launch() throws Exception {
        this.launched = false;
        if (this.processorGraph == null) {
            return;
        }
        this.agentMap.clear();
        for (ProcessorNode node : this.processorGraph.getNodes()) {
            boolean isProducer = this.isProducerNode(node.getId(), this.processorGraph);
            List<ProcessorLinker> toLinkers = this.getNodeToLinkers(node.getId(), this.processorGraph);
            StandaloneProcessorAgent processorAgent = new StandaloneProcessorAgent(node, toLinkers, isProducer, this,
                    this, this.componentProvider);
            this.agentMap.put(node.getId(), processorAgent);
        }

        //init
        for (StandaloneProcessorAgent agent : this.agentMap.values()) {
            agent.init();
        }

        //start
        for (StandaloneProcessorAgent agent : this.agentMap.values()) {
            agent.start();
        }

        //
        this.executorService.execute(this);

        this.launched = true;

    }

    @Override
    public void shutdown() throws Exception {
        this.stopped.set(true);
        for (StandaloneProcessorAgent agent : this.agentMap.values()) {
            try {
                agent.shutdown();
            } catch (Exception ex) {
            }
        }
        this.executorService.shutdownNow();
    }

    @Override
    public void collect(Object object) {
        if (object instanceof ProcessTask) {
            ProcessTask taskObject = (ProcessTask) object;
            this.tasks.offer(taskObject);
        }
    }

    private List<ProcessorLinker> getNodeToLinkers(String nodeId, ProcessorGraph graph) {
        List<ProcessorLinker> links = new ArrayList<ProcessorLinker>();
        for (ProcessorLinker link : graph.getLinkers()) {
            if (nodeId.equals(link.getFromNodeId())) {
                links.add(link);
            }
        }
        return links;
    }

    private boolean isProducerNode(String nodeId, ProcessorGraph graph) {
        for (ProcessorLinker linker : graph.getLinkers()) {
            if (nodeId.equals(linker.getToNodeId())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isLaunched() {
        return this.launched;
    }

    public boolean isInBusy() {
        boolean ret = this.tasks.size() > this.maxPending;
        return ret;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!this.stopped.get()) {
            try {
                boolean hasTaskProcessed = false;
                ProcessTask processTask = this.tasks.poll();
                if (processTask != null) {
                    StandaloneProcessorAgent agent = this.agentMap.get(processTask.getToNodeId());
                    if (agent != null) {
                        agent.appendTask(processTask);
                        hasTaskProcessed = true;
                    }
                }
                if (!hasTaskProcessed) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        ProcessorGraph graph = ProcessorGraphBuilder.createWithId("transformer")
//                .addNode(ProcessorNodeBuilder.createWithId("input")
//                        .withComponentId("org.hippoproject.framework.marvin.foundation.processor.standalone" +
//                                ".DemoProducer")
//                        .withParallelism(1)
//                )
////                .addNode(ProcessorNodeBuilder.createWithId("ouput")
////                        .withComponentId("org.hippoproject.framework.marvin.foundation.processor.standalone
//// .DemoProcessor")
////                        .withParallelism(5)
////                        .from("input")
////                )
//                .build();
//
//
//        StandaloneProcessActuator actuator = new StandaloneProcessActuator(new ClassInstantiationComponentProvider());
//        actuator.setMaxPending(100000);
//        actuator.initialize(graph);
//        actuator.launch();
//
//        Thread.currentThread().join();
//    }
}
