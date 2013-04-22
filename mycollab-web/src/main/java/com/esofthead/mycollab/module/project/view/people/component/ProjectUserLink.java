package com.esofthead.mycollab.module.project.view.people.component;

import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;

public class ProjectUserLink extends Button {
	private static final long serialVersionUID = 1L;

	public ProjectUserLink(final String username, final String displayName) {
		this(username, displayName, true, false);
	}
	
	public ProjectUserLink(final String username, final String displayName, boolean useWordWrap) {
		this(username, displayName, useWordWrap, false);
	}
	
	public ProjectUserLink(final String username, final String displayName,  boolean useWordWrap, 
			boolean isDisplayAvatar) {
		super(displayName, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ProjectMemberEvent.GotoRead(this, username));
			}
		});

		if (isDisplayAvatar && username != null && !username.equals("")) {
			this.setIcon(UserAvatarControlFactory.getResource(
					AppContext.getAccountId(), username, 16));
		}

		this.setStyleName("link");
		
		if (useWordWrap) {
			this.addStyleName(UIConstants.WORD_WRAP);
		}
	}
}
