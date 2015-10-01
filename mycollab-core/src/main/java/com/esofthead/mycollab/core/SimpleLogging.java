package com.esofthead.mycollab.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class SimpleLogging {
    private static Logger LOG = LoggerFactory.getLogger(SimpleLogging.class);

    public static void error(String message) {
        LOG.error(message);
    }
}
