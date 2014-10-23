package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
@SuppressWarnings("rawtypes")
public class UserLinkViewField extends CustomField {
	private static final long serialVersionUID = 1L;

	private String username;
	private String userAvatarId;
	private String fullName;

	public UserLinkViewField(String username, String userAvatarId,
			final String fullName) {
		this.username = username;
		this.userAvatarId = userAvatarId;
		this.fullName = fullName;
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	@Override
	protected Component initContent() {
		final UserLink userLink = new UserLink(username, userAvatarId,
				fullName);
		userLink.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		return userLink;
	}
}
