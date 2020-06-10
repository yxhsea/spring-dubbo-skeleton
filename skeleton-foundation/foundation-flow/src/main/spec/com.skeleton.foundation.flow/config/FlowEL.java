package com.skeleton.foundation.flow.config;

import java.util.List;

/**
 * 用于映射flow json
 **/
public class FlowEL {

    private String id;
    private String description;
    private List<GroupEL> groups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GroupEL> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupEL> groups) {
        this.groups = groups;
    }
}
