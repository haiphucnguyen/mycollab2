package com.esofthead.mycollab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.utils.InvalidPasswordException;

public abstract class GenericServlet implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			onHandleRequest(request, response);
		} catch (Exception e) {
			if (e instanceof ResourceNotFoundException) {
				PageNotFoundGenerator.responsePage404(response);
			} else if (e instanceof InvalidPasswordException) {
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());
			} else if (e instanceof UserInvalidInputException) {
				PageNotFoundGenerator.responsePage404(response);
			} else if (e instanceof MyCollabException) {
				String errMsg = (e.getMessage() != null && e.getMessage()
						.length() > 0) ? e.getMessage()
						: LocalizationHelper
								.getMessage(GenericI18Enum.ERROR_USER_NOTICE_INFORMATION_MESSAGE);
				PrintWriter out = response.getWriter();
				out.println(errMsg);
			} else {
				throw new ServletException(e);
			}
		}
	}

	abstract protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
