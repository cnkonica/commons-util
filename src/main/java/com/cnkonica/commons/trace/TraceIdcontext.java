package com.cnkonica.commons.trace;

import java.util.concurrent.Callable;

public class TraceIdcontext {
    private static final ThreadLocal<String> TRACE_ID_HOLDER = new ThreadLocal<>();
    public static String get(){
        return TRACE_ID_HOLDER.get();
    }

    public static void set(final String traceId) {
        TRACE_ID_HOLDER.set(traceId);

    }
    public static void remove(){
        TRACE_ID_HOLDER.remove();

    }

    public static Runnable transfer(final Runnable action) {
        final String traceId = TraceIdcontext.get();
        return ()->{
            TraceIdcontext.set(traceId);
            try {
                action.run();
            } finally {

                TraceIdcontext.remove();
            }
        };
    }

    public static <T> Callable<T> transfer(final Callable<T> func) {
        final String traceId = TraceIdcontext.get();
        return ()->{
            TraceIdcontext.set(traceId);
            try {
                return func.call();
            }finally {
                TraceIdcontext.remove();
            }
        };
    }
}
