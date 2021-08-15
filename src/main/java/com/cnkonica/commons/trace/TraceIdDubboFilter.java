package com.cnkonica.commons.trace;

import com.alibaba.dubbo.rpc.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.UUID;

public class TraceIdDubboFilter  implements Filter {
    private static final String TRACE_ID_KEY = "traceId";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (RpcContext.getContext().isProviderSide()) {
            final String traceId = Optional.ofNullable(invocation.getAttachments())
                    .map(x -> x.get(TRACE_ID_KEY))
                    .orElseGet(() -> RpcContext.getContext().getAttachment(TRACE_ID_KEY));
            if (StringUtils.isNotBlank(traceId)) {
                TraceIdcontext.set(traceId);
            } else {
                TraceIdcontext.set(UUID.randomUUID().toString().replace("-",""));
            }
            try {
                return invoker.invoke(invocation);
            }finally {
                TraceIdcontext.remove();
            }


        } else if (RpcContext.getContext().isConsumerSide()) {
            final String traceId = TraceIdcontext.get();
            if (StringUtils.isNotEmpty(traceId) && StringUtils.isEmpty(RpcContext.getContext().getAttachment(TRACE_ID_KEY))) {
                RpcContext.getContext().setAttachment(TRACE_ID_KEY, traceId);
            }
            return invoker.invoke(invocation);

        } else {
            return invoker.invoke(invocation);
        }
    }
}
