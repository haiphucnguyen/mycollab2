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

	public UserLink(final String username, final String displayName,
			boolean useWordWrap) {
		super(displayName, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
			}
		});

		this.addStyleName("link");

		if (username != null && !username.equals("")) {
			this.setIcon(UserAvatarControlFactory.getResource(username, 16));
		}

		if (useWordWrap) {
			this.addStyleName(UIConstants.WORD_WRAP);
		}
	}

	public UserLink(final String username, final String displayName) {
		this(username, displayName, true);
	}
}
