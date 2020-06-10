package com.skeleton.foundation.flow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DefaultFlow implements IFlow {

    private Map<String, FlowGroupWrapper> wrappers = new HashMap<String, FlowGroupWrapper>();
    private IFlowGroup[] groups = new IFlowGroup[0];

    private Object lockObj = new Object();

    @Override
    public IFlowGroup findGroup(String groupId) {
        if (this.wrappers.containsKey(groupId)) {
            FlowGroupWrapper wrapper = this.wrappers.get(groupId);
            return wrapper.group;
        }
        return null;
    }

    @Override
    public FlowGroupWrapper findGroupInfo(String groupId) {
        return this.wrappers.get(groupId);
    }

    @Override
    public IFlowGroup[] getGroups() {
        return this.groups;
    }


    @Override
    public void registerGroup(String groupId, IFlowGroup group, int pri) {
        this.wrappers.put(groupId, new FlowGroupWrapper(group, groupId, pri));
        this.resort();
    }

    private void resort() {
        synchronized (lockObj) {
            FlowGroupWrapper[] wps = this.wrappers.values().toArray(new FlowGroupWrapper[this.wrappers.size()]);
            Arrays.sort(wps, new Comparator<FlowGroupWrapper>() {
                @Override
                public int compare(FlowGroupWrapper o1, FlowGroupWrapper o2) {
                    return o1.pri - o2.pri;
                }
            });
            this.groups = new IFlowGroup[wps.length];
            for (int i = 0, n = wps.length; i < n; i++) {
                this.groups[i] = wps[i].group;
            }
        }
    }

    @Override
    public void unregisterGroup(String groupId) {
        if (this.wrappers.containsKey(groupId)) {
            this.wrappers.remove(groupId);
            this.resort();
        }
    }

    @Override
    public void execute(IFlowContext context) throws Exception {
        for (IFlowGroup group : this.groups) {
            group.execute(context);
        }
    }
}
