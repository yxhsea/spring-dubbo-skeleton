package com.skeleton.foundation.flow;

import com.skeleton.foundation.flow.config.FlowConfigEL;
import com.skeleton.foundation.flow.config.FlowEL;
import com.skeleton.foundation.flow.config.GroupEL;
import com.skeleton.foundation.flow.config.InterceptorEL;
import com.skeleton.foundation.flow.interceptor.IFlowInterceptor;
import com.skeleton.foundation.flow.provider.IComponentProvider;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultFlowManager implements IFlowManager {
    private Map<String, IFlow> flow = new HashMap<String, IFlow>();
    private List<Resource> configResources = new ArrayList<Resource>();
    private ObjectMapper objectMapper;
    private IComponentProvider componentProvider;

    public DefaultFlowManager() {
    }

    public DefaultFlowManager(List<Resource> configResources) {
        this.configResources = configResources;
    }

    public void __init__() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (this.configResources != null) {
            for (Resource resource : this.configResources) {
                try {
                    this.loadConfigFromResource(resource);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setComponentProvider(IComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    private void loadConfigFromResource(Resource resource) throws Exception {
        if (resource == null || !resource.exists() || !resource.isReadable()) {
            return;
        }
        InputStream stream = resource.getInputStream();
        FlowConfigEL configEL = this.objectMapper.readValue(stream, FlowConfigEL.class);
        if (configEL == null || configEL.getFlow() == null || configEL.getFlow().size() == 0) {
            return;
        }

        for (FlowEL flow : configEL.getFlow()) {
            IFlow obj = new DefaultFlow();
            if (flow.getGroups() != null) {
                for (GroupEL group : flow.getGroups()) {
                    if (group.getRefFlowId() != null && group.getRefGroupId() != null) {
                        //引用现有组
                        IFlowGroup groupObj = this.findGroup(group.getRefFlowId(), group.getRefGroupId());
                        if (groupObj == null) {
                            throw new RuntimeException("Ref flow group{flowId=" + group.getRefFlowId() + ", " +
                                    "groupId=" + group.getRefGroupId() + "} does not found.");
                        }
                        obj.registerGroup(group.getId(), groupObj, group.getPri());
                    } else {
                        //新建组
                        DefaultFlowGroup groupObj = new DefaultFlowGroup();
                        IFlowUnit unitObj = null;
                        if (this.componentProvider != null) {
                            unitObj = (IFlowUnit) this.componentProvider.getComponent(group.getNodeComponentId());
                        }
                        if (unitObj != null) {
                            groupObj.setUnit(unitObj);
                        } else {
                            throw new RuntimeException("Flow{id=" + flow.getId() + "} unit{comp=" + group
                                    .getNodeComponentId() + "} does not found.");
                        }

                        if (group.getInterceptors() != null) {
                            for (InterceptorEL interceptor : group.getInterceptors()) {
                                IFlowInterceptor interceptorObj = null;
                                if (this.componentProvider != null) {
                                    interceptorObj = (IFlowInterceptor) this.componentProvider.getComponent(interceptor
                                            .getComponentId());
                                }
                                if (interceptorObj != null) {
                                    groupObj.registerInterceptor(interceptorObj, interceptor.getPri());
                                } else {
                                    throw new RuntimeException("Flow{id=" + flow.getId() + "} interceptor{comp=" +
                                            interceptor.getComponentId() + "} does not found.");
                                }
                            }
                        }
                        obj.registerGroup(group.getId(), groupObj, group.getPri());
                    }
                }
            }
            this.registerGraph(flow.getId(), obj);
        }
    }

    @Override
    public IFlow findFlow(String flowId) {
        return this.flow.get(flowId);
    }

    @Override
    public IFlowGroup findGroup(String flowId, String groupId) {
        IFlow graph = this.findFlow(flowId);
        if (graph != null) {
            return graph.findGroup(groupId);
        }
        return null;
    }


    @Override
    public void registerGraph(String flowId, IFlow flow) {
        if (flowId != null && flow != null) {
            this.flow.put(flowId, flow);
        }
    }

    @Override
    public void unregisterFlow(String graphId) {
        if (graphId != null) {
            this.flow.remove(graphId);
        }
    }

    @Override
    public void execute(String flowId, IFlowContext context) throws Exception {
        IFlow graph = this.flow.get(flowId);
        if (graph != null) {
            graph.execute(context);
        } else {
            throw new NullPointerException("Graph{Id=" + flowId + "} Not Found.");
        }
    }
}
