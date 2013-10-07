/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Button;

/**
 * 
 * @author haiphucnguyen
 */
public class UserLink extends Button {
	private static final long serialVersionUID = 1L;

	public UserLink(final String username, String userAvatarId,
			final String displayName, boolean useWordWrap) {
		super(displayName);

		this.addStyleName("link");

		if (username != null && !username.equals("")) {
			this.setIcon(UserAvatarControlFactory.createAvatarResource(
					userAvatarId, 16));
		}

		if (useWordWrap) {
			this.addStyleName(UIConstants.WORD_WRAP);
		}
	}

	public UserLink(final String username, String userAvatarId,
			final String displayName) {
		this(username, userAvatarId, displayName, true);
	}
}
