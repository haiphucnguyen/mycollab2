package com.esofthead.mycollab.module.billing.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserAccountExample;
import com.esofthead.mycollab.module.user.service.UserService;

@Component("verifyUserServletHandler")
public class AnotatedVerifyUserServletRequestHandler implements
		HttpRequestHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountMapper userAccountMapper;

	@Autowired
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);
				pathInfo = UrlEncodeDecoder.decode(pathInfo);
				String[] params = pathInfo.split("/");
				if (params.length != 2) {
					throw new MyCollabException("Invalid params");
				} else {
					int accountId = Integer.parseInt(params[0]);
					String username = params[1];
					boolean isCreatePassword = false;
					User user = userService.findUserByUserName(username);

					if (!RegisterStatusConstants.ACTIVE.equals(user
							.getRegisterstatus())) {
						user.setRegisterstatus(RegisterStatusConstants.ACTIVE);
						isCreatePassword = true;
						user.setPassword("123456");
						userService.updateWithSession(user, user.getUsername());
					}

					// update user account status
					UserAccountExample userAccountEx = new UserAccountExample();
					userAccountEx.createCriteria().andUsernameEqualTo(username)
							.andAccountidEqualTo(accountId);
					UserAccount userAccount = new UserAccount();
					userAccount
							.setRegisterstatus(RegisterStatusConstants.ACTIVE);
					userAccountMapper.updateByExampleSelective(userAccount,
							userAccountEx);

					// remove account invitation

					if (isCreatePassword) {
						// forward to page create password for new user
					} else {
						// redirect to account site
						request.getRequestDispatcher(
								request.getContextPath() + "/").forward(
								request, response);
						request.setAttribute("username", user.getUsername());
						request.setAttribute("password", user.getPassword());
					}

				}
			}
		} else {
			// TODO: response to user invalid page
		}
	}
}
