package com.esofthead.mycollab.servlet;

import com.codahale.metrics.servlets.HealthCheckServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@WebServlet(name = "Healthcheck Servlets", urlPatterns = "/healthcheck", asyncSupported = true, loadOnStartup = 0,
        initParams = {@WebInitParam(name = "show-jvm-metrics", value = "true")})
public class SimpleHealthcheckServlet extends HealthCheckServlet {
}
