package com.mycollab.module.billing.servlet;

import com.mycollab.configuration.ApplicationConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.spring.AppContextUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
class PageGeneratorUtil {
    static void responseUserNotExistPage(HttpServletResponse response, String username, String loginURL) throws IOException, TemplateException {
        Map<String, Object> context = new HashMap<>();
        context.put("loginURL", loginURL);
        context.put("username", username);
        ApplicationConfiguration applicationConfiguration = AppContextUtil.getSpringBean(ApplicationConfiguration.class);
        Map<String, String> defaultUrls = applicationConfiguration.defaultUrls();
        defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
        defaultUrls.put("app_url", SiteConfiguration.getAppUrl());
        context.put("defaultUrls", defaultUrls);

        StringWriter writer = new StringWriter();
        Configuration templateEngine = AppContextUtil.getSpringBean(Configuration.class);
        Template template = templateEngine.getTemplate("pageUserNotExist.ftl", Locale.US);
        template.process(context, writer);

        String html = writer.toString();
        PrintWriter out = response.getWriter();
        out.println(html);
    }
}
