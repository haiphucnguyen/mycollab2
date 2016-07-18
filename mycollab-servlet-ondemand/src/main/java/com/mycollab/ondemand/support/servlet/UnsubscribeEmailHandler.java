package com.mycollab.ondemand.support.servlet;

import com.mycollab.servlet.GenericHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@WebServlet(name = "unsubsribeEmailHandler", urlPatterns = "/unsubscribe")
public class UnsubscribeEmailHandler extends GenericHttpServlet {
    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
