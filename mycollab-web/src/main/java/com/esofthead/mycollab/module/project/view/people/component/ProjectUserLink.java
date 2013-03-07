package com.esofthead.mycollab.module.project.view.people.component;

import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.Button;

public class ProjectUserLink extends Button {
	private static final long serialVersionUID = 1L;

	public ProjectUserLink(final String username, final String displayName) {
		super(displayName, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ProjectMemberEvent.GotoRead(this, username));
			}
		});

		this.setIcon(UserAvatarControlFactory.getResource(username, 16));
		this.setStyleName("link");
	}
}
