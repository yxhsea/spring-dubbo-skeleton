package com.skeleton.foundation.processor;

import com.skeleton.foundation.collector.ICollector;
import com.skeleton.foundation.mq.IMessageConsumer;
import com.skeleton.foundation.mq.MessageTopic;

import java.util.List;

public class MessageReceiveProducer extends BaseProcessor {

    private IMessageConsumer messageConsumer;
    private MessageTopic messageTopic;
    private String topicName;
    private int batchSize;
    private Class messageClass;
    private String env;

    public MessageReceiveProducer() {
        this.batchSize = 10;
    }

    public IMessageConsumer getMessageConsumer() {
        return messageConsumer;
    }

    public void setMessageConsumer(IMessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public Class getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(Class messageClass) {
        this.messageClass = messageClass;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
        this.messageTopic = new MessageTopic();
        this.messageTopic.setTopicName(this.topicName);
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    @Override
    protected void produce(IProcessInput input, IProcessOutput output) throws Exception {
        List list = this.messageConsumer.receive(this.messageTopic, this.messageClass, this.batchSize);
        ICollector collector = output.getCollector();
        if (list != null && list.size() > 0) {
            Object[] objs = this.handleMessageList(list);
            for (Object obj : objs) {
                collector.collect(obj);
            }
        } else {

        }
    }

    protected Object[] handleMessageList(List list) {
        return list.toArray();
    }

}
