package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.module.billing.UserStatusConstants;
import com.esofthead.mycollab.module.billing.servlet.AnotatedDenyUserServletRequestHandler.PageUserNotExistGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.servlet.GenericServlet;

@Component("userconfirmsignupServlet")
public class AnotatedUserSignUpConfirmEmailActionHandler extends GenericServlet {

	@Autowired
	private UserService userServices;

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		try {
			if (pathInfo != null) {
				if (pathInfo.startsWith("/")) {
					pathInfo = pathInfo.substring(1);

					pathInfo = UrlEncodeDecoder.decode(pathInfo);

					String username = pathInfo.substring(0,
							pathInfo.indexOf("/"));
					pathInfo = pathInfo.substring(username.length() + 1);

					Integer accountId = Integer.parseInt(pathInfo);

					SimpleUser user = userServices.findUserByUserNameInAccount(
							username, accountId);
					if (user != null) {
						user.setStatus(UserStatusConstants.EMAIL_VERIFIED);
						userServices.updateWithSession(user, username);
						request.getRequestDispatcher(
								request.getContextPath() + "/").forward(
								request, response);
						return;
					} else {
						PageUserNotExistGenerator.responeUserNotExistPage(
								response, request.getContextPath() + "/");
						return;
					}
				}
			}
			throw new ResourceNotFoundException();
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}
