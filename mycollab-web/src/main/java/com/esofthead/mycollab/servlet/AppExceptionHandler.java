package com.esofthead.mycollab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.ServerErrPageGenerator;

public class AppExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	private void processError(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Class errorTypeClass = (Class) request
				.getAttribute("javax.servlet.error.exception_type");
		Integer status_code = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		if (ResourceNotFoundException.class.getName().equals(
				errorTypeClass.getName())) {
			PageNotFoundGenerator.responsePage404(response);
		} else if (status_code == 404) {
			PageNotFoundGenerator.responsePage404(response);
		} else {
			ServerErrPageGenerator.responsePage500(response);
		}
	}

}
