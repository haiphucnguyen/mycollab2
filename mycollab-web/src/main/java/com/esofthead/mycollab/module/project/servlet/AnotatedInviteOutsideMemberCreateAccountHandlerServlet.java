package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.utils.InvalidPasswordException;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;

@Component("acceptMemberInvitationCreateAccountServlet")
public class AnotatedInviteOutsideMemberCreateAccountHandlerServlet implements
		HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedInviteOutsideMemberCreateAccountHandlerServlet.class);

	@Autowired
	private ProjectMemberService projectMemberService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String errMsg = "";

		// email , projectId, sAccountId, projectURL
		Integer projectId = Integer.parseInt(request.getParameter("projectId"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Integer sAccountId = Integer.parseInt(request
				.getParameter("sAccountId"));
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));

		try {
			PasswordCheckerUtil.checkValidPassword(password);
		} catch (InvalidPasswordException e) {
			PrintWriter out = response.getWriter();
			out.print(e.getMessage());
			return;
		}

		try {
			projectMemberService.acceptProjectInvitationByNewUser(email,
					password, projectId, roleId, sAccountId);
		} catch (Exception e) {
			errMsg = "Error in while create your account. We so sorry for this inconvenience";
			PrintWriter out = response.getWriter();
			out.print(errMsg);

			log.error("Error while user try update user password", e);
			return;
		}
	}
}
