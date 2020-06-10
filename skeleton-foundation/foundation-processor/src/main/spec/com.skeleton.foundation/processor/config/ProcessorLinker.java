
package com.skeleton.foundation.processor.config;

import java.io.Serializable;
import java.util.Map;

/**
 * 节点连接器
 **/
public class ProcessorLinker implements Serializable {

    private static final long serialVersionUID = 8197756220966679186L;
    private String fromNodeId;
    private String toNodeId;
    private String linkType;
    private Map<String, String> linkParameters;

    public String getFromNodeId() {
        return fromNodeId;
    }

    public void setFromNodeId(String fromNodeId) {
        this.fromNodeId = fromNodeId;
    }

    public String getToNodeId() {
        return toNodeId;
    }

    public void setToNodeId(String toNodeId) {
        this.toNodeId = toNodeId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public Map<String, String> getLinkParameters() {
        return linkParameters;
    }

    public void setLinkParameters(Map<String, String> linkParameters) {
        this.linkParameters = linkParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessorLinker that = (ProcessorLinker) o;

        if (fromNodeId != null ? !fromNodeId.equals(that.fromNodeId) : that.fromNodeId != null) return false;
        return toNodeId != null ? toNodeId.equals(that.toNodeId) : that.toNodeId == null;

    }

    @Override
    public int hashCode() {
        int result = fromNodeId != null ? fromNodeId.hashCode() : 0;
        result = 31 * result + (toNodeId != null ? toNodeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProcessorLinker{");
        sb.append("fromNodeId='").append(fromNodeId).append('\'');
        sb.append(", toNodeId='").append(toNodeId).append('\'');
        sb.append(", linkType='").append(linkType).append('\'');
        sb.append(", linkParameters=").append(linkParameters);
        sb.append('}');
        return sb.toString();
    }
}