package com.skeleton.foundation.mq;

/**
 * 消息生产者
 **/
public interface IMessageProducer {
    /**
     * 设置序列化器
     * @param serializer
     */
    void setSerializer(IMessageSerializer serializer);

    /**
     * 发送消息
     * @param topic
     * @param messages
     * @throws Exception
     */
    void send(MessageTopic topic, Object... messages) throws Exception;
}
