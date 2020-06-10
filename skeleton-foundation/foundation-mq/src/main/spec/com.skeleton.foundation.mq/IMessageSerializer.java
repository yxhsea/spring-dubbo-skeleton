package com.skeleton.foundation.mq;

import java.io.IOException;

/**
 * 消息序列化器
 **/
public interface IMessageSerializer {
    /**
     * 序列化
     * @param object
     * @return
     * @throws IOException
     */
    byte[] serialize(Object object) throws IOException;

    /**
     * 反序列化
     * @param data
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] data, Class<T> type) throws IOException;
}
