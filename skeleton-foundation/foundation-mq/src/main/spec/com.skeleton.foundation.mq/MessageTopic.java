package com.skeleton.foundation.mq;

/**
 * topic
 **/
public class MessageTopic {
    private String topicName;

    public MessageTopic() {
    }

    public MessageTopic(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
