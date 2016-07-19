package com.mycollab.ondemand.support.servlet;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.servlet.TemplateWebServletRequestHandler;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@WebServlet(name = "unsubsribeEmailPage", urlPatterns = "/unsubscribe")
public class UnsubscribeEmailPreferencePage extends TemplateWebServletRequestHandler {

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateException {
        String encryptEmail = request.getParameter("email");
        if (encryptEmail != null) {
            String email = UrlEncodeDecoder.decode(encryptEmail);
            Map<String, Object> context = new HashMap<>();
            context.put("email", email);
            String unSubscribeUrl = request.getContextPath() + "/unsubscribe/action";
            context.put("unSubscribeUrl", unSubscribeUrl);
            String html = generatePageByTemplate(response.getLocale(), "pageEmailUnsubscribe.ftl", context);
            PrintWriter out = response.getWriter();
            out.print(html);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
