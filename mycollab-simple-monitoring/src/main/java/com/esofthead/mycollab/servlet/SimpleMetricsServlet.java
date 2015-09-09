package com.esofthead.mycollab.servlet;

import com.codahale.metrics.servlets.MetricsServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@WebServlet(name = "Metric Servlets", urlPatterns = "/metrics", asyncSupported = true, loadOnStartup = 0, initParams =
        {@WebInitParam(name = "show-jvm-metrics", value = "true")})
public class SimpleMetricsServlet extends MetricsServlet {
}
