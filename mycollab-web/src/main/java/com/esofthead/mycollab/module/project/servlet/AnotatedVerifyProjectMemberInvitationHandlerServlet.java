package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

@Component("confirmInvitationMemberServletHandler")
public class AnotatedVerifyProjectMemberInvitationHandlerServlet implements
		HttpRequestHandler {
	private static String OUTSIDE_MEMBER_WELCOME_PAGE = "templates/page/outsideMemberAcceptInvitationPage.mt";

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private ProjectService projectService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				String pathVariables = UrlEncodeDecoder.decode(pathInfo);
				String identifyStr = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				if (identifyStr.equals("OUTSIDE")) {
					// handle outside here
					pathVariables = pathVariables.substring(identifyStr
							.length() + 1);
					handleOutSideMemberInvite(pathVariables, response, request);
				} else if (identifyStr.equals("OUTSIDE_EXIST")) {
					pathVariables = pathVariables.substring(identifyStr
							.length() + 1);
					handleOutSideMemberInviteWithExistAccount(pathVariables,
							response);
				} else {
					// handle inside member invite
					handleInsideMemberInvite(pathVariables, response);
				}
				return;
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	private void handleOutSideMemberInviteWithExistAccount(
			String pathVariables, HttpServletResponse response)
			throws IOException {
		int memberId = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));
		pathVariables = pathVariables.substring((memberId + "").length() + 1);

		int sAccountId = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));
		pathVariables = pathVariables.substring((new Integer(sAccountId))
				.toString().length() + 1);

		String projectLinkURL = pathVariables;
		if (memberId > 0) {
			ProjectMember member = projectMemberService.findByPrimaryKey(
					memberId, sAccountId);
			if (member.getStatus().equals(
					ProjectMemberStatusContants.VERIFICATING)) {
				member.setStatus(ProjectMemberStatusContants.ACTIVE);
				projectMemberService.updateWithSession(member, "");
				try {
					response.sendRedirect(projectLinkURL);
				} catch (IOException e) {
					throw new MyCollabException(e);
				}
			}
			return;
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	private void handleInsideMemberInvite(String pathVariables,
			HttpServletResponse response) {
		int sAccount = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));
		pathVariables = pathVariables.substring((new Integer(sAccount))
				.toString().length() + 1);

		int memberId = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));

		if (memberId > 0) {
			SimpleProjectMember member = projectMemberService.findById(
					memberId, AppContext.getAccountId());
			if (member != null) {
				member.setStatus(ProjectMemberStatusContants.ACTIVE);
				projectMemberService.updateWithSession(member, "");

				String subdomain = projectService.getSubdomainOfProject(member
						.getProjectid());

				String redirectURL = SiteConfiguration.getSiteUrl(subdomain)
						+ "project/dashboard/"
						+ UrlEncodeDecoder.encode(member.getProjectid());
				try {
					response.sendRedirect(redirectURL);
				} catch (IOException e) {
					throw new MyCollabException(e);
				}
			}
		}
	}

	private void handleOutSideMemberInvite(String pathVariables,
			HttpServletResponse response, HttpServletRequest request) {
		// email , projectId, sAccountId,roleId,projectURL

		String email = pathVariables.substring(0, pathVariables.indexOf("/"));
		pathVariables = pathVariables.substring(email.length() + 1);

		int projectId = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));
		pathVariables = pathVariables.substring((new Integer(projectId))
				.toString().length() + 1);

		int sAccountId = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));
		pathVariables = pathVariables.substring((new Integer(sAccountId))
				.toString().length() + 1);

		int roleId = Integer.parseInt(pathVariables.substring(0,
				pathVariables.indexOf("/")));
		pathVariables = pathVariables.substring((new Integer(roleId))
				.toString().length() + 1);

		String projectLinkURL = pathVariables;

		String handelCreateAccountURL = request.getContextPath() + "/"
				+ "project/outside/createAccount/";

		String html = generateOutsideMemberAcceptPage(sAccountId, email,
				projectId, roleId, projectLinkURL, handelCreateAccountURL);
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
			String handelCreateAccountURL) {
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
