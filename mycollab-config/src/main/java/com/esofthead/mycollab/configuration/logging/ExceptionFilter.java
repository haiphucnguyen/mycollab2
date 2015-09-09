package com.esofthead.mycollab.configuration.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class ExceptionFilter extends Filter<ILoggingEvent> {
    private static Class[] blacklistClss;

    static {
        try {
            blacklistClss = new Class[]{Class.forName("org.apache.jackrabbit.core.cluster.ClusterException")};
        } catch (Exception e) {
        }
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        final IThrowableProxy throwableProxy = event.getThrowableProxy();
        if (throwableProxy == null) {
            return FilterReply.NEUTRAL;
        }

        if (!(throwableProxy instanceof ThrowableProxy)) {
            return FilterReply.NEUTRAL;
        }

        final ThrowableProxy throwableProxyImpl = (ThrowableProxy) throwableProxy;
        final Throwable throwable = throwableProxyImpl.getThrowable();
        for (Class exceptCls : blacklistClss) {
            if (exceptCls.isInstance(throwable)) {
                return FilterReply.DENY;
            }
        }


        return FilterReply.NEUTRAL;
    }
}
