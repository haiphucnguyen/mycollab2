package com.mycollab.module.billing.servlet;

import com.mycollab.servlet.GenericHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
@WebServlet(name = "activateBillingAccountServlet", urlPatterns = "/billing/activate")
public class ActivateBillingAccountHandler extends GenericHttpServlet {

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
