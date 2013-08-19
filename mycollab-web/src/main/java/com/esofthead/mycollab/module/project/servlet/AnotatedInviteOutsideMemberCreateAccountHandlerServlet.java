package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.utils.ParsingUtils;
import com.esofthead.mycollab.utils.PasswordCheckerUtil;
import com.esofthead.mycollab.web.AppContext;

@Component("inviteOutsideMemberCreateAccountHandlerServlet")
public class AnotatedInviteOutsideMemberCreateAccountHandlerServlet implements
		HttpRequestHandler {

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
		Boolean error = false;
		String errMsg = "";

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Integer projectId = Integer.parseInt(request.getParameter("projectId"));
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		Integer sAccountId = Integer.parseInt(request
				.getParameter("sAccountId"));

		// here to validate input from USER------------
		if (userService.findUserByUserName(username) != null) {
			error = true;
			errMsg = "User name has already exist on system";
		} else if (!email.matches(ParsingUtils.EMAIL_PATTERN)) {
			error = true;
			errMsg = "Email's not correct format";
		} else if (password.length() < 8) {
			error = true;
			errMsg = "Your password too short";
		} else if (PasswordCheckerUtil.checkPasswordStrength(password)) {
			error = true;
			errMsg = "Recommend you should type password at least contain one digit and symbol";
		}
		if (error) {
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}

		SimpleUser simpleUser = new SimpleUser();
		simpleUser.setUsername(username);
		simpleUser.setFirstname(firstname);
		simpleUser.setLastname(lastname);
		simpleUser.setEmail(email);
		simpleUser.setPassword(password);
		simpleUser.setRoleid(roleId);
		simpleUser.setAccountId(sAccountId);
		simpleUser.setRegisterstatus(RegisterStatusConstants.ACTIVE);

		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(username);
		userAccount.setAccountid(sAccountId);
		userAccount.setRegisterstatus(RegisterStatusConstants.ACTIVE);
		userAccount.setIsaccountowner(false);
		userAccount.setRegisteredtime(new Date());
		userAccount.setRoleid(roleId);
		userAccount.setIsadmin(false);

		ProjectMember member = new ProjectMember();
		member.setProjectid(projectId);
		member.setUsername(username);
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
			error = true;
			errMsg = "Error in while create your account. We so sorry for this inconvenience";
		}
		if (error) {
			PrintWriter out = response.getWriter();
			out.print(errMsg);
			return;
		}
		PrintWriter out = response.getWriter();
		out.print(errMsg);
	}
}
