package com.esofthead.mycollab.mobile.module.project.ui;

import java.util.List;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.mobile.ui.MobileAttachmentUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class ProjectAttachmentDisplayComp extends CssLayout {
	private static final long serialVersionUID = -3401160588430768707L;

	private List<Content> attachments;

	public ProjectAttachmentDisplayComp(List<Content> attachments) {
		this.attachments = attachments;

		this.constructUI();
	}

	private void constructUI() {
		this.setStyleName("attachment-display-comp");
		Label compHeader = new Label(
				AppContext.getMessage(GenericI18Enum.M_FORM_ATTACHMENT));
		compHeader.setStyleName("h2");
		this.addComponent(compHeader);
		VerticalLayout comp = new VerticalLayout();
		comp.setStyleName("attachment-view-panel");
		comp.setWidth("100%");

		for (final Content attachment : attachments) {
			Component attachmentRow = MobileAttachmentUtils
					.renderAttachmentRow(attachment);
			comp.addComponent(attachmentRow);
		}
		this.addComponent(comp);
	}
}
