package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.web.AppContext;

@Component("denyUserServletHandler")
public class AnotatedDenyUserServletRequestHandler implements
		HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedDenyUserServletRequestHandler.class);

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				int accountId = Integer.parseInt(pathInfo.substring(0,
						pathInfo.indexOf("/")));
				pathInfo = pathInfo.substring((accountId + "").length() + 1);

				String username = pathInfo;
				try {
					UserSearchCriteria criteria = new UserSearchCriteria();
					criteria.setUsername(new StringSearchField(username));
					UserService userService = AppContext
							.getSpringBean(UserService.class);

					userService.removeByCriteria(criteria, accountId);
					userService.removeUserAccount(username, accountId);

					log.debug("Verify user successfully. Redirect to application page");
					response.sendRedirect(request.getContextPath() + "/");
				} catch (UserInvalidInputException e) {
					log.debug("Redirect user to user invalid page");
					PageNotFoundGenerator.responsePage404(response);
				}
				return;
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

}
