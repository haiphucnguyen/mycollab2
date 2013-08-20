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

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.template.velocity.EngineFactory;

@Component("confirmInvitationMemberServletHandler")
public class AnotatedVerifyProjectMemberInvitationHandlerServlet implements
		HttpRequestHandler {

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private RelayEmailNotificationService relayEmailService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				String pathVariables = UrlEncodeDecoder.decode(pathInfo);
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

						String subdomain = projectService
								.getSubdomainOfProject(member.getProjectid());

						String redirectURL = SiteConfiguration
								.getSiteUrl(subdomain)
								+ "project/dashboard/"
								+ UrlEncodeDecoder
										.encode(member.getProjectid());
						response.sendRedirect(redirectURL);
						return;
					}
				}
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	public static class PageNotFoundGenerator {
		public static void responsePage404(HttpServletResponse response)
				throws IOException {
			String pageNotFoundTemplate = "templates/email/project/404Page.mt";
			VelocityContext context = new VelocityContext(
					EngineFactory.createContext());

			Reader reader;
			try {
				reader = new InputStreamReader(TemplateGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				reader = new InputStreamReader(TemplateGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate));
			}
			Map<String, String> defaultUrls = new HashMap<String, String>();

			defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
			context.put("defaultUrls", defaultUrls);

			StringWriter writer = new StringWriter();
			EngineFactory.getTemplateEngine().evaluate(context, writer,
					"log task", reader);
			String html = writer.toString();
			PrintWriter out = response.getWriter();
			out.println(html);
		}
	}
}
