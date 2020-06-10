package com.skeleton.foundation.utils.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 */
public class ThreadPoolUtil {

    /**
     * 有界队列
     */
    private static LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(1000);

    /**
     * 创建指定线程数量的线程池
     *
     * @param threadCount
     * @return
     */
    public static ThreadPoolExecutor createThreadPoolByCount(int threadCount) {
        return new ThreadPoolExecutor(threadCount, threadCount,
                60L, TimeUnit.SECONDS, blockingQueue, new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
