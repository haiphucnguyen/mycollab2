package com.esofthead.mycollab.module.project.ui.components;

import java.util.List;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ProjectAttachmentDisplayComponentFactory {
	public static Component getAttachmentDisplayComponent(int projectid,
			AttachmentType type, int typeid) {
		ResourceService resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);
		List<Content> attachments = resourceService.getContents(AttachmentUtils
				.getProjectEntityAttachmentPath(AppContext.getAccountId(),
						projectid, type, typeid));
		if (attachments != null && !attachments.isEmpty()) {
			return new AttachmentDisplayComponent(attachments);
		} else {
			return new VerticalLayout();
		}
	}
}
