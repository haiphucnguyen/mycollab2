/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

/**
 *
 * @author haiphucnguyen
 */
public class DbLoggingAppender extends AppenderSkeleton {

    @Override
    protected void append(LoggingEvent event) {
        if (this.layout == null) {
            errorHandler.error("No layout for appender " + name,
                    null, ErrorCode.MISSING_LAYOUT);
            return;
        }

        StringBuilder message = new StringBuilder(this.layout.format(event));
        
        if (layout.ignoresThrowable()) {
            String[] messages = event.getThrowableStrRep();
            if (messages != null) {
                for (int j = 0; j < messages.length; ++j) {
                    message.append(messages[j], 0, messages[j].length());
                    message.append('\n');
                }
            }
        }
        
        System.out.println("MESSAGE: " + message);
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
