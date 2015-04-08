package com.esofthead.mycollab.servlet;

import com.esofthead.mycollab.configuration.SharingOptions;
import com.esofthead.mycollab.core.utils.FileUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class UpgradeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        VelocityContext context = new VelocityContext();
        Reader reader = FileUtils.getReader("templates/page/WaitingUpgrade.mt");

        SharingOptions sharingOptions = SharingOptions
                .getDefaultSharingOptions();
        Map<String, String> defaultUrls = new HashMap<>();
        defaultUrls.put("cdn_url", "/assets/images/email/");
        defaultUrls.put("app_url", "/");
        defaultUrls.put("facebook_url", sharingOptions.getFacebookUrl());
        defaultUrls.put("google_url", sharingOptions.getGoogleplusUrl());
        defaultUrls.put("linkedin_url", sharingOptions.getLinkedinUrl());
        defaultUrls.put("twitter_url", sharingOptions.getTwitterUrl());

        context.put("defaultUrls", defaultUrls);

        StringWriter writer = new StringWriter();
        VelocityEngine voEngine = new VelocityEngine();

        voEngine.evaluate(context, writer, "log task", reader);

        PrintWriter out = response.getWriter();
        out.print(writer.toString());
    }
}
