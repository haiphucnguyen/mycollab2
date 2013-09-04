package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserAccountExample;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

@Component("confirmInvitationMemberServletHandler")
public class AnotatedVerifyProjectMemberInvitationHandlerServlet implements
		HttpRequestHandler {
	private static String OUTSIDE_MEMBER_WELCOME_PAGE = "templates/page/OutsideMemberAcceptInvitationPage.mt";

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountMapper userAccountMapper;

	@Autowired
	private ProjectService projectService;

	private static org.slf4j.Logger log = LoggerFactory
			.getLogger(AnotatedVerifyProjectMemberInvitationHandlerServlet.class);

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// email,projectId,sAccountId,projectRoleId, inviterName
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				String pathVariables = UrlEncodeDecoder.decode(pathInfo);
				String email = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				pathVariables = pathVariables.substring(email.length() + 1);

				int projectId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((projectId + "")
						.length() + 1);

				int sAccountId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((sAccountId + "")
						.length() + 1);

				int projectRoleId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((projectRoleId + "")
						.length() + 1);
				String inviterName = pathVariables;

				log.debug("Checking Member status --------");
				User user = userService.findUserByUserName(email);
				if (user != null) { // user exit
					log.debug("User exist on System -------------");
					handleMemberInviteWithExistAccount(email, projectId,
							sAccountId, projectRoleId, response);
				} else {
					log.debug("User not exist on System --------- to enter password'Page");
					handleOutSideMemberInvite(email, projectId, sAccountId,
							projectRoleId, inviterName, response, request);
				}
				return;
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	private void handleMemberInviteWithExistAccount(String username,
			Integer projectId, Integer sAccountId, Integer projectRoleId,
			HttpServletResponse response) throws IOException {

		// search has in table User account
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUsernameEqualTo(username)
				.andAccountidEqualTo(sAccountId);
		try {
			List<UserAccount> lst = userAccountMapper.selectByExample(example);
			if (lst.size() > 0) {
				for (UserAccount record : lst) {
					record.setRegisterstatus(RegisterStatusConstants.ACTIVE);
					userAccountMapper.updateByPrimaryKeySelective(record);
				}
			} else {
				UserAccount userAccount = new UserAccount();
				userAccount.setUsername(username);
				userAccount.setAccountid(sAccountId);
				userAccount.setRegisterstatus(RegisterStatusConstants.ACTIVE);
				userAccount.setIsaccountowner(false);
				userAccount.setRegisteredtime(new Date());
				userAccount.setIsadmin(false);

				userAccountMapper.insert(userAccount);
			}
			// search has in table projectMember
			ProjectMember member = projectMemberService.findMemberByUsername(
					username, projectId, sAccountId);
			if (member == null) {
				member = new ProjectMember();
				member.setProjectid(projectId);
				member.setUsername(username);
				member.setJoindate(new Date());
				member.setSaccountid(sAccountId);
				member.setIsadmin(false);
				member.setStatus(RegisterStatusConstants.ACTIVE);
				projectMemberService.saveWithSession(member,
						AppContext.getUsername());
			} else if (member != null) {
				member.setStatus(RegisterStatusConstants.ACTIVE);
				projectMemberService.updateWithSession(member,
						AppContext.getUsername());
			}
			MailLinkGenerator linkGenerator = new MailLinkGenerator(projectId);
			response.sendRedirect(linkGenerator.generateProjectFullLink());
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	private void handleOutSideMemberInvite(String email, Integer projectId,
			Integer sAccountId, Integer roleId, String inviterName,
			HttpServletResponse response, HttpServletRequest request) {
		MailLinkGenerator linkGenerator = new MailLinkGenerator(projectId);
		String projectLinkURL = linkGenerator.generateProjectFullLink();

		String handelCreateAccountURL = request.getContextPath() + "/"
				+ "project/outside/createAccount/";

		String html = generateOutsideMemberAcceptPage(sAccountId, email,
				projectId, roleId, projectLinkURL, handelCreateAccountURL,
				inviterName);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			throw new MyCollabException(e);
		}
		out.println(html);
	}

	private String generateOutsideMemberAcceptPage(int sAccountId,
			String email, int projectId, int roleId, String projectLinkURL,
			String handelCreateAccountURL, String inviterName) {
		TemplateContext context = new TemplateContext();

		Reader reader;
		try {
			reader = new InputStreamReader(
					AnotatedVerifyProjectMemberInvitationHandlerServlet.class
							.getClassLoader().getResourceAsStream(
									OUTSIDE_MEMBER_WELCOME_PAGE), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(
					AnotatedVerifyProjectMemberInvitationHandlerServlet.class
							.getClassLoader().getResourceAsStream(
									OUTSIDE_MEMBER_WELCOME_PAGE));
		}
		context.put("projectLinkURL", projectLinkURL);
		context.put("email", email);
		context.put("handelCreateAccountURL", handelCreateAccountURL);
		context.put("sAccountId", sAccountId);
		context.put("projectId", projectId);
		context.put("roleId", roleId);
		context.put("inviterName", inviterName);

		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());

		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);
		return writer.toString();
	}

	public static class PageNotFoundGenerator {
		public static void responsePage404(HttpServletResponse response)
				throws IOException {
			String pageNotFoundTemplate = "templates/page/404Page.mt";
			TemplateContext context = new TemplateContext();

			Reader reader;
			try {
				reader = new InputStreamReader(PageNotFoundGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				reader = new InputStreamReader(PageNotFoundGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate));
			}
			Map<String, String> defaultUrls = new HashMap<String, String>();

			defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
			context.put("defaultUrls", defaultUrls);

			StringWriter writer = new StringWriter();
			TemplateEngine.evaluate(context, writer, "log task", reader);

			String html = writer.toString();
			PrintWriter out = response.getWriter();
			out.println(html);
		}
	}
}
