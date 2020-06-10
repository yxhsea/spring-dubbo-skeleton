package com.skeleton.foundation.assist.filter.dubbo;

import com.alibaba.fastjson.JSON;
import com.skeleton.foundation.utils.LoggerUtil;
import com.skeleton.foundation.utils.date.DateRelatedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProviderStubLogger extends BaseRpcStubLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderStubLogger.class);

    @Override
    protected void processPreLog(DefaultRpcStubContext defaultRpcStubContext) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n=====================进入服务====================");
        builder.append("\n时间:");
        builder.append(DateRelatedUtils.convertMillisecondToFullDateString(defaultRpcStubContext.getStart()));
        builder.append(" 消费端地址:");
        builder.append(defaultRpcStubContext.getConsumerIp());
        builder.append("\n服务:");
        builder.append(defaultRpcStubContext.getInterfaceName());
        builder.append(".");
        builder.append(defaultRpcStubContext.getMethodName());
        // builder.append("(");
        // builder.append(JSON.toJSONString(defaultRpcStubContext.getArgs()));
        // builder.append(")");
        builder.append("\n===============================================");
        LoggerUtil.info(LOGGER,builder.toString());
    }

    @Override
    protected void processPostLog(DefaultRpcStubContext defaultRpcStubContext) {
        long end = System.currentTimeMillis();
        if (defaultRpcStubContext.getThrowable() != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n=====================结束服务====================");
            builder.append("\n时间:");
            builder.append(DateRelatedUtils.convertMillisecondToFullDateString(end));
            builder.append(" 消费端地址:");
            builder.append(defaultRpcStubContext.getConsumerIp());
            builder.append("\n服务:");
            builder.append(defaultRpcStubContext.getInterfaceName());
            builder.append(".");
            builder.append(defaultRpcStubContext.getMethodName());
            // builder.append("(");
            // builder.append(JSON.toJSONString(defaultRpcStubContext.getArgs()));
            // builder.append(")");
            builder.append("\n异常:\n"+ JSON.toJSONString(defaultRpcStubContext.getThrowable()));
            builder.append("\n耗时:");
            builder.append(String.valueOf(end-defaultRpcStubContext.getStart()));
            builder.append("毫秒");
            builder.append("\n==============================================");
            LoggerUtil.error(LOGGER,builder.toString());
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("\n=====================结束服务====================");
            builder.append("\n时间:");
            builder.append(DateRelatedUtils.convertMillisecondToFullDateString(end));
            builder.append(" 消费端地址:");
            builder.append(defaultRpcStubContext.getConsumerIp());
            builder.append("\n服务:");
            builder.append(defaultRpcStubContext.getInterfaceName());
            builder.append(".");
            builder.append(defaultRpcStubContext.getMethodName());
            // builder.append("(");
            // builder.append(JSON.toJSONString(defaultRpcStubContext.getArgs()));
            // builder.append(")");

            // builder.append("\n返回:\n"+JSON.toJSONString(defaultRpcStubContext.getValue()));
            builder.append("\n耗时:");
            builder.append(String.valueOf(end-defaultRpcStubContext.getStart()));
            builder.append("\n毫秒");
                builder.append("\n==============================================");
            LoggerUtil.info(LOGGER,builder.toString());
        }
    }
}
