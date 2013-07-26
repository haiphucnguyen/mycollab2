package com.esofthead.mycollab.module.project.ui.components;

import java.util.List;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ProjectFormAttachmentDisplayField extends CustomField {
	private static final long serialVersionUID = 1L;

	public ProjectFormAttachmentDisplayField(final int projectid,
			final String type, final int typeid) {
		final Component comp = getAttachmentDisplayComponent(projectid, type,
				typeid);
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

	private Component getAttachmentDisplayComponent(int projectid, String type,
			int typeid) {
		ResourceService resourceService = AppContext
				.getSpringBean(ResourceService.class);
		List<Content> attachments = resourceService
				.getContents(AttachmentUtils.getProjectEntityAttachmentPath(
						AppContext.getAccountId(), projectid, type, typeid));
		if (attachments != null && !attachments.isEmpty()) {
			return new AttachmentDisplayComponent(attachments);
		} else {
			return new VerticalLayout();
		}
	}

}
