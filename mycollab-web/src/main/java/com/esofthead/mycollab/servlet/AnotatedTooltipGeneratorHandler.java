package com.esofthead.mycollab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component("tooltipGeneratorServlet")
public class AnotatedTooltipGeneratorHandler extends GenericServlet {

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String html = "Hello this is pure html";
		PrintWriter out = response.getWriter();
		out.println(html);
		return;
	}

}
