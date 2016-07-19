package com.mycollab.ondemand.support.servlet;

import com.mycollab.ondemand.module.support.dao.EmailReferenceMapper;
import com.mycollab.ondemand.module.support.domain.EmailReference;
import com.mycollab.ondemand.module.support.domain.EmailReferenceExample;
import com.mycollab.servlet.GenericHttpServlet;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@WebServlet(name = "unsubsribeEmailHandler", urlPatterns = "/unsubscribe/action")
public class UnsubscribeEmailPreferenceHandler extends GenericHttpServlet {
    @Autowired
    private EmailReferenceMapper emailReferenceMapper;

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, TemplateException {
        String email = request.getParameter("email");
        if (email != null) {
            EmailReferenceExample ex = new EmailReferenceExample();
            ex.createCriteria().andEmailEqualTo(email);
            emailReferenceMapper.deleteByExample(ex);
        }
    }
}
