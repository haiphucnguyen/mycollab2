package com.esofthead.mycollab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.core.UserInvalidInputException;

public abstract class GenericServlet implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			onHandleRequest(request, response);
		} catch (Exception e) {
			if (e instanceof ResourceNotFoundException) {
				throw new ResourceNotFoundException();
			} else if (e instanceof UserInvalidInputException) {
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());
			} else {
				throw new ServletException(e);
			}
		}
	}

	abstract protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
