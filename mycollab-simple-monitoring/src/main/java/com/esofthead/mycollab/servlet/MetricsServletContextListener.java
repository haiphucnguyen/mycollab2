package com.esofthead.mycollab.servlet;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class MetricsServletContextListener extends MetricsServlet.ContextListener {
    @Autowired
    private MetricRegistry metricRegistry;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(event.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        super.contextInitialized(event);
    }

    @Override
    protected MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }
}
