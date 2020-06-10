package com.skeleton.foundation.mq;

import java.util.List;

/**
 * 消息消费者
 **/
public interface IMessageConsumer {
    /**
     * 设置序列化器
     * @param serializer
     */
    void setSerializer(IMessageSerializer serializer);

    /**
     * 接收消息
     * @param topic
     * @param messageType
     * @param size
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> List<T> receive(MessageTopic topic, Class<T> messageType, int size) throws Exception;
}
