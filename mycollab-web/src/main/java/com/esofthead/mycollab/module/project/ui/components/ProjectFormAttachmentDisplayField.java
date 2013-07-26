package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class ProjectFormAttachmentDisplayField extends CustomField {
	private static final long serialVersionUID = 1L;

	public ProjectFormAttachmentDisplayField(final int projectid,
			final String type, final int typeid) {
		final Component comp = ProjectAttachmentDisplayComponentFactory
				.getAttachmentDisplayComponent(projectid, type, typeid);
		if (comp == null || !(comp instanceof AttachmentDisplayComponent)) {
			final Label l = new Label("&nbsp;", Label.CONTENT_XHTML);
			setCompositionRoot(l);
		} else {
			setCompositionRoot(comp);
		}
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}
}
