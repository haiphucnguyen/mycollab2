package com.mycollab.servlet;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache,no-store");
        PrintWriter out = response.getWriter();
//        if (ServerInstance.getInstance().isUpgrading()) {
//            out.write("Still upgrading");
//        } else {
//            out.write("Finish");
//        }
    }
}
