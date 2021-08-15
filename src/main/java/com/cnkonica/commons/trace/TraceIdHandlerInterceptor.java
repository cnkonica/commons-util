package com.cnkonica.commons.trace;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class TraceIdHandlerInterceptor extends HandlerInterceptorAdapter {
    static final String REQUEST_ID_QUERY_KEY = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = request.getParameter(REQUEST_ID_QUERY_KEY);
        if (StringUtils.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString().replace("-","");
        }
        TraceIdcontext.set(requestId);
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TraceIdcontext.remove();
    }
}
