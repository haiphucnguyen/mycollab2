package com.esofthead.mycollab.servlet;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class HealthcheckServletContextListener extends HealthCheckServlet.ContextListener {

    @Autowired
    private HealthCheckRegistry healthCheckRegistry;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(event.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        super.contextInitialized(event);
    }

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        return healthCheckRegistry;
    }
}
