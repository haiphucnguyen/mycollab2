package com.esofthead.mycollab.module.project.ui.form;

import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.project.ui.components.ProjectAttachmentDisplayComponentFactory;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
@SuppressWarnings("rawtypes")
public class ProjectFormAttachmentDisplayField extends CustomField {
	private static final long serialVersionUID = 1L;

	private int projectid;
	private AttachmentType type;
	private int typeid;

	public ProjectFormAttachmentDisplayField(final int projectid,
			final AttachmentType type, final int typeid) {
		this.projectid = projectid;
		this.type = type;
		this.typeid = typeid;
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	@Override
	protected Component initContent() {
		final Component comp = ProjectAttachmentDisplayComponentFactory
				.getAttachmentDisplayComponent(projectid, type, typeid);
		if (comp == null || !(comp instanceof AttachmentDisplayComponent)) {
			final Label l = new Label("&nbsp;", ContentMode.HTML);
			return l;
		} else {
			return comp;
		}
	}
}
