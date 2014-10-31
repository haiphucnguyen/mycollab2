package com.esofthead.mycollab.mobile.module.project.ui.form.field;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
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
		ResourceService resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);
		List<Content> attachments = resourceService.getContents(AttachmentUtils
				.getProjectEntityAttachmentPath(AppContext.getAccountId(),
						projectid, type, typeid));
		if (CollectionUtils.isNotEmpty(attachments)) {
			VerticalLayout comp = new VerticalLayout();
			comp.setStyleName("attachment-view-panel");

			for (Content attachment : attachments) {
				Label l = new Label(attachment.getTitle());
				l.setWidth("100%");
				comp.addComponent(l);
			}

			return comp;
		}
		return new Label("&nbsp;", ContentMode.HTML);
	}
}
