package com.skeleton.foundation.assist.filter.dubbo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultRpcStubContext implements IRpcStubContext {
    /**
     * traceId 调用链路唯一ID
     */
    private String traceId;
    /**
     * 调用开始时间
     */
    private long start;
    /**
     * 消费者IP
     */
    private String consumerIp;
    /**
     * 调用/被调用接口名称
     */
    private String interfaceName;
    /**
     * 调用/被调用方法名称
     */
    private String methodName;
    /**
     * 调用参数
     */
    private Object[] args;
    /**
     * 进程间传递的信息
     */
    private Map<String, String> attachments;
    /**
     * 异常
     */
    private Throwable throwable;
    /**
     * 返回信息
     */
    private Object value;

    public String getTraceId() {
        return traceId;
    }

    public long getStart() {
        return start;
    }

    public String getConsumerIp() {
        return consumerIp;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public Object getValue() {
        return value;
    }

    public static final class Builder {
        private String traceId;
        private long start;
        private String consumerIp;
        private String interfaceName;
        private String methodName;
        private Object[] args;
        private Map<String, String> attachments;
        private Throwable throwable;
        private Object value;

        public Builder withTraceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder withStart(long start) {
            this.start = start;
            return this;
        }

        public Builder withConsumerIp(String consumerIp) {
            this.consumerIp = consumerIp;
            return this;
        }

        public Builder withInterfaceName(String interfaceName) {
            this.interfaceName = interfaceName;
            return this;
        }

        public Builder withMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder withArgs(Object[] args) {
            this.args = args;
            return this;
        }

        public Builder withAttachments(Map<String, String> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder addAttachment(String key, String val) {
            if (this.attachments == null) {
                this.attachments = new ConcurrentHashMap<>();
            }
            this.attachments.put(key, val);
            return this;
        }

        public Builder withThrowable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        public Builder withValue(Object value) {
            this.value = value;
            return this;
        }

        public DefaultRpcStubContext build() {
            DefaultRpcStubContext defaultRpcStubContext = new DefaultRpcStubContext();
            defaultRpcStubContext.traceId = this.traceId;
            defaultRpcStubContext.value = this.value;
            defaultRpcStubContext.start = this.start;
            defaultRpcStubContext.consumerIp = this.consumerIp;
            defaultRpcStubContext.methodName = this.methodName;
            defaultRpcStubContext.interfaceName = this.interfaceName;
            defaultRpcStubContext.throwable = this.throwable;
            defaultRpcStubContext.args = this.args;
            defaultRpcStubContext.attachments = this.attachments;
            return defaultRpcStubContext;
        }
    }
}
