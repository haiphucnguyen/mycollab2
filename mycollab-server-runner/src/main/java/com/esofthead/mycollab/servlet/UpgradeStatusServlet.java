package com.esofthead.mycollab.servlet;

import com.esofthead.mycollab.jetty.ServerInstance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class UpgradeStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if (ServerInstance.getInstance().isUpgrading()) {
            out.write("Still upgrading");
            System.out.println("STILL UPGRADING");
        }
    }
}
