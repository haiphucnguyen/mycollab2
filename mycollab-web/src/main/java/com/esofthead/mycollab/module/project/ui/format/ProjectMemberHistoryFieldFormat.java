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
package com.esofthead.mycollab.module.project.ui.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.mail.MailUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.format.html.TagBuilder;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.HistoryFieldFormat;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class ProjectMemberHistoryFieldFormat implements HistoryFieldFormat {

	private static Logger log = LoggerFactory
			.getLogger(ProjectMemberHistoryFieldFormat.class);

	@Override
	public Component toVaadinComponent(String value) {
		String html = ProjectLinkBuilder.generateProjectMemberHtmlLink(value,
				CurrentProjectVariables.getProjectId());
		return (value != null) ? new Label(html, ContentMode.HTML) : new Label(
				"");
	}

	@Override
	public String toString(String value) {
		if (value == null || "".equals(value)) {
			return new Span().write();
		}

		try {
			UserService userService = ApplicationContextUtil
					.getSpringBean(UserService.class);
			User user = userService.findUserByUserName(value);
			if (user != null) {
				String userAvatarLink = MailUtils.getAvatarLink(
						user.getAvatarid(), 16);
				Img img = TagBuilder.newImg("avatar", userAvatarLink);

				String userLink = AccountLinkGenerator
						.generatePreviewFullUserLink(
								MailUtils.getSiteUrl(AppContext.getAccountId()),
								user.getUsername());

				String userDisplayName = user.getFirstname() + " "
						+ user.getLastname();
				if (userDisplayName.trim().equals("")) {
					String displayName = user.getUsername();
					userDisplayName = StringUtils
							.extractNameFromEmail(displayName);
				}

				A link = TagBuilder.newA(userLink, userDisplayName);
				return TagBuilder.newLink(img, link).write();
			}
		} catch (Exception e) {
			log.error("Error", e);
		}
		return value;
	}

}
