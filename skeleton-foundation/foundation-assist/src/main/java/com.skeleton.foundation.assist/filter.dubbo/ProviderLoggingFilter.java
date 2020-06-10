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

@Activate(group = CommonConstants.PROVIDER)
public class ProviderLoggingFilter implements Filter {

	private IRpcStubLogger providerStubLogger;
	
	private static final String TRACE_ID = "traceId";

	public ProviderLoggingFilter() {
		this.providerStubLogger = new ProviderStubLogger();
	}

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String ip = RpcContext.getContext().getRemoteHost();
		
		Result result = null;
		long start = System.currentTimeMillis();
		String traceId = RpcContext.getContext().getAttachment(TRACE_ID);
		if(traceId == null || traceId.trim().length() == 0) {
			traceId = UUID.randomUUID().toString();
		}
		//服务提供方，接收到traceId
		MDC.put(TRACE_ID, traceId);
		DefaultRpcStubContext.Builder builder = new DefaultRpcStubContext.Builder();
		builder.withArgs(invocation.getArguments());
		builder.withAttachments(RpcContext.getContext().getAttachments());
		builder.withInterfaceName(invoker.getInterface().getName());
		builder.withMethodName(invocation.getMethodName());
		builder.withStart(start);
		builder.withTraceId(traceId);
		builder.withConsumerIp(ip);
		this.providerStubLogger.preLog(builder.build());
		try {
			result = invoker.invoke(invocation);
		} catch(RpcException e) {
			builder.withThrowable(e);
			builder.withValue("远程调用异常");
			builder.addAttachment(ErrorCodes.RPC_ERROR,"远程调用异常"+e.getMessage());
			result.setException(e.getCause());
			result.setValue("远程调用异常 ");
			result.setAttachment(ErrorCodes.RPC_ERROR," 远程调用异常"+result.getException().getMessage());
		}finally {
			builder.withValue(result.getValue());
			this.providerStubLogger.postLog(builder.build());
		}
		return result;
	}
	
}
