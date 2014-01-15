/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.servlet.GenericServlet;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
@Component("denyMemberInvitationFeedbackServlet")
public class AnnotatedMemberDenyInvitationFeedBackHandlerServlet extends
		GenericServlet {

	@Autowired
	private MailRelayService mailRelayService;

	@Autowired
	private ProjectMemberService projectMemberService;

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			String inviterEmail = request.getParameter("inviterEmail");
			String toEmail = request.getParameter("toEmail");
			String message = request.getParameter("message");
			String toName = request.getParameter("toName");
			String inviterName = request.getParameter("inviterName");

			if (inviterName.indexOf("/") != -1) {
				inviterName = inviterName
						.substring(0, inviterName.indexOf("/"));
			}
			toName = (toName.equals("You")) ? "" : toName;
			mailRelayService.saveRelayEmail(new String[] { inviterName },
					new String[] { inviterEmail }, toName + "(" + toEmail + ")"
							+ " has denied your invitation", message);
		} else {
			throw new ResourceNotFoundException();
		}
	}
};
