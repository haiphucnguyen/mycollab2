package com.mycollab.servlet;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.configuration.ApplicationConfiguration;
import com.mycollab.configuration.IDeploymentMode;
import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.i18n.LocalizationHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@WebServlet(urlPatterns = "/error", name = "appExceptionHandlerServlet")
public class AppExceptionServletHandler extends GenericHttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AppExceptionServletHandler.class);

    @Autowired
    private Configuration templateEngine;

    @Autowired
    private IDeploymentMode deploymentMode;

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private ServerConfiguration serverConfiguration;

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer status_code = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (request.getHeader("User-Agent") == null) {
            return;
        }

        try {
            if ((status_code != null && status_code == 404) || ("404".equals(request.getParameter("param")))) {
                responsePage404(response);
            } else {
                // Analyze the servlet exception
                Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
                responsePage500(response, throwable);
            }

        } catch (Exception e) {
            LOG.error("Error in servlet", e);
        }
    }

    private void responsePage404(HttpServletResponse response) throws IOException, TemplateException {
        Map<String, Object> context = new HashMap<>();
        Map<String, String> defaultUrls = applicationConfiguration.defaultUrls();
        defaultUrls.put("cdn_url", serverConfiguration.getCdnUrl());
        defaultUrls.put("app_url", serverConfiguration.getAppUrl());
        context.put("defaultUrls", defaultUrls);

        StringWriter writer = new StringWriter();
        Template template = templateEngine.getTemplate("page404.ftl", response.getLocale());
        template.process(context, writer);

        String html = writer.toString();
        PrintWriter out = response.getWriter();
        out.println(html);
    }

    private void responsePage500(HttpServletResponse response, Throwable throwable) throws IOException, TemplateException {
        if (throwable != null) {
            DataIntegrityViolationException integrityViolationException = getExceptionType(throwable,
                    DataIntegrityViolationException.class);
            if (integrityViolationException != null) {
                response.getWriter().println(LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale(),
                        GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE));
                LOG.error("Exception in MyCollab", throwable);
                return;
            }
            DataAccessException exception = getExceptionType(throwable, DataAccessException.class);
            if (exception != null) {
                response.getWriter().println(String.format("<h1>%s</h1>", LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale(),
                        ErrorI18nEnum.ERROR_ESTABLISH_DATABASE_CONNECTION)));
                return;
            }
            LOG.error("Exception in mycollab", throwable);
        }

        Map<String, Object> context = new HashMap<>();
        Map<String, String> defaultUrls = applicationConfiguration.defaultUrls();

        defaultUrls.put("cdn_url", serverConfiguration.getCdnUrl());
        defaultUrls.put("app_url", serverConfiguration.getAppUrl());
        context.put("defaultUrls", defaultUrls);

        StringWriter writer = new StringWriter();
        Template template = templateEngine.getTemplate("page500.ftl", response.getLocale());
        template.process(context, writer);

        String html = writer.toString();
        PrintWriter out = response.getWriter();
        out.println(html);
    }

    private static <T> T getExceptionType(Throwable e, Class<T> exceptionType) {
        if (exceptionType.isAssignableFrom(e.getClass())) {
            return (T) e;
        } else if (e.getCause() != null) {
            return getExceptionType(e.getCause(), exceptionType);
        } else {
            return null;
        }
    }
}
