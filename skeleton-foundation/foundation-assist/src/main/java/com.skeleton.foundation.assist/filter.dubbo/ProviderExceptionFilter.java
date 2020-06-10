package com.skeleton.foundation.assist.filter.dubbo;

import com.skeleton.foundation.assist.handler.DefaultExceptionHandler;
import com.skeleton.foundation.assist.support.ResultInvoker;
import com.skeleton.foundation.model.dto.AbstractResult;
import com.skeleton.foundation.model.dto.ResultDTO;
import com.skeleton.foundation.model.error.ErrorCodes;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = CommonConstants.PROVIDER)
public class ProviderExceptionFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(ProviderExceptionFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        try {
            result = invoker.invoke(invocation);
        } catch (Throwable e) {
            logger.error("服务端异常", e);
            result = new ResultInvoker<AbstractResult>(invoker, DefaultExceptionHandler.handleException(e)).invoke(invocation);
            return result;
        }
        if (result == null) {
            result = new ResultInvoker<ResultDTO>(invoker, ResultDTO.failure(
                    ErrorCodes.build(ErrorCodes.RPC_ERROR, "系统繁忙，请稍后重试"))).invoke(invocation);
        } else if (result.getException() != null) {
            logger.error("服务端异常", result.getException());
            result.setValue(DefaultExceptionHandler.handleException(result.getException()));
            result.setException(null);
        }
        return result;
    }

}
