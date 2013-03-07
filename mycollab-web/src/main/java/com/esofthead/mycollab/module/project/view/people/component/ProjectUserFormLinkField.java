package com.esofthead.mycollab.module.project.view.people.component;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.UIConstants;

public class ProjectUserFormLinkField extends CustomField {
	private static final long serialVersionUID = 1L;

	public ProjectUserFormLinkField(String username, String displayName) {
		ProjectUserLink l = new ProjectUserLink(username, displayName, false);
		l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		this.setCompositionRoot(l);
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}
}
