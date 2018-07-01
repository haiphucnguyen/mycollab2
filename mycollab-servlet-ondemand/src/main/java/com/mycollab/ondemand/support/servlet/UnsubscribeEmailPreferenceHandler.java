package com.mycollab.ondemand.support.servlet;

import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.ondemand.module.support.service.EmailReferenceService;
import com.mycollab.servlet.GenericHttpServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@WebServlet(name = "unsubsribeEmailHandler", urlPatterns = "/unsubscribe/action")
public class UnsubscribeEmailPreferenceHandler extends GenericHttpServlet {
    @Autowired
    private EmailReferenceService emailReferenceService;

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        if (email != null) {
            emailReferenceService.remove(email);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
