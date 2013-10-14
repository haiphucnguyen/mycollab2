package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.utils.InvalidPasswordException;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;
import com.esofthead.mycollab.web.AppContext;

@Component("acceptMemberInvitationCreateAccountServlet")
public class AnotatedInviteOutsideMemberCreateAccountHandlerServlet implements
		HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedInviteOutsideMemberCreateAccountHandlerServlet.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserAccountMapper userAccountMapper;

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

		SimpleUser simpleUser = new SimpleUser();
		simpleUser.setAccountId(sAccountId);
		simpleUser.setFirstname(email);
		simpleUser.setLastname(email);
		simpleUser.setRegisteredtime(new Date());
		simpleUser.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		simpleUser.setPassword(PasswordEncryptHelper
				.encryptSaltPassword(password));
		simpleUser.setUsername(email);
		simpleUser.setEmail(email);

		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(email);
		userAccount.setAccountid(sAccountId);
		userAccount.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		userAccount.setIsaccountowner(false);
		userAccount.setRegisteredtime(new Date());
		userAccount.setRoleid(roleId);
		userAccount.setIsadmin(false);

		ProjectMember member = new ProjectMember();
		member.setProjectid(projectId);
		member.setUsername(email);
		member.setJoindate(new Date());
		member.setSaccountid(sAccountId);
		member.setIsadmin(false);
		member.setStatus(RegisterStatusConstants.ACTIVE);

		try {
			userMapper.insert(simpleUser);
			userAccountMapper.insert(userAccount);
			projectMemberService.saveWithSession(member,
					AppContext.getUsername());
		} catch (Exception e) {
			errMsg = "Error in while create your account. We so sorry for this inconvenience";
			PrintWriter out = response.getWriter();
			out.print(errMsg);

			log.error("Error while user try update user password", e);
			return;
		}
	}
}
