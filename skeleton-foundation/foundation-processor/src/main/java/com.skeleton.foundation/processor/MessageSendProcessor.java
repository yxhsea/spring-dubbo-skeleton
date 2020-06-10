package com.skeleton.foundation.processor;


import com.skeleton.foundation.mq.IMessageProducer;
import com.skeleton.foundation.mq.MessageTopic;

public class MessageSendProcessor extends BaseProcessor {

    private IMessageProducer messageProducer;
    private MessageTopic messageTopic;
    private String topicName;

    public IMessageProducer getMessageProducer() {
        return messageProducer;
    }

    public void setMessageProducer(IMessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
        this.messageTopic = new MessageTopic();
        this.messageTopic.setTopicName(this.topicName);
    }

    @Override
    protected boolean process(Object inputObject, IProcessInput input, IProcessOutput output) throws Exception {
        Object[] objs = this.handleObjectToMessages(inputObject);
        this.messageProducer.send(this.messageTopic, objs);
        return true;
    }

    protected Object[] handleObjectToMessages(Object inputObject) {
        return new Object[]{inputObject};
    }
}
