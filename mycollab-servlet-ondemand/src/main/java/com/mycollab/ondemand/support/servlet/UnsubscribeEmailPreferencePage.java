package com.mycollab.ondemand.support.servlet;

import com.mycollab.common.UrlEncodeDecoder;
import com.mycollab.common.UrlTokenizer;
import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.ondemand.module.support.dao.EmailReferenceMapper;
import com.mycollab.servlet.TemplateWebServletRequestHandler;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private EmailReferenceMapper emailReferenceMapper;

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateException {
        String encryptEmail = request.getParameter("email");
        if (encryptEmail != null) {
            String email = UrlEncodeDecoder.decode(encryptEmail);
            Map<String, Object> context = new HashMap<>();
            context.put("email", email);
            String html = generatePageByTemplate(response.getLocale(), "pageEmailUnsubscribe.ftl", context);
            PrintWriter out = response.getWriter();
            out.print(html);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
