package com.skeleton.foundation.assist.filter.dubbo;

import com.skeleton.foundation.model.error.ErrorCodes;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.MDC;

import java.util.UUID;

@Activate(group = CommonConstants.CONSUMER)
public class ConsumerLoggingFilter implements Filter {

	private static final String TRACE_ID = "traceId";

	private IRpcStubLogger consumerStubLogger;

	public ConsumerLoggingFilter() {
		this.consumerStubLogger = new ConsumerStubLogger();
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		long start = System.currentTimeMillis();
		Result result = null;
		String traceId = MDC.get(TRACE_ID);
		if(traceId == null) {
			traceId = UUID.randomUUID().toString().replaceAll("-", "");
		}
		//消费者，传递traceId
		RpcContext.getContext().setAttachment(TRACE_ID, traceId);
		DefaultRpcStubContext.Builder builder = new DefaultRpcStubContext.Builder();
		builder.withArgs(invocation.getArguments());
		builder.withAttachments(RpcContext.getContext().getAttachments());
		builder.withInterfaceName(invoker.getInterface().getName());
		builder.withMethodName(invocation.getMethodName());
		builder.withStart(start);
		builder.withTraceId(traceId);
		this.consumerStubLogger.preLog(builder.build());
		try {
			result = invoker.invoke(invocation);
		} catch (RpcException e) {
			builder.withThrowable(e);
			builder.withValue("远程调用异常");
			builder.addAttachment(ErrorCodes.RPC_ERROR,"远程调用异常"+e.getMessage());
			if(result != null){
				result.setException(e.getCause());
				result.setValue("远程调用异常 ");
				result.setAttachment(ErrorCodes.RPC_ERROR," 远程调用异常"+result.getException().getMessage());
			}
		}finally {
			builder.withValue(result == null ? null : result.getValue());
			this.consumerStubLogger.postLog(builder.build());
		}
		return result;
	}
}
