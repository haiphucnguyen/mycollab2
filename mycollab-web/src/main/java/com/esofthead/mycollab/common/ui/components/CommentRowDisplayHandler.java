/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import java.util.List;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class CommentRowDisplayHandler implements
		BeanList.RowDisplayHandler<SimpleComment> {

	@Override
	public Component generateRow(SimpleComment comment, int rowIndex) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setStyleName("message");
		layout.setWidth("100%");
		layout.addComponent(UserAvatarControlFactory
				.createUserAvatarButtonLink(comment.getOwnerAvatarId(),
						comment.getOwnerFullName()));

		CssLayout rowLayout = new CssLayout();
		rowLayout.setStyleName("message-container");
		rowLayout.setWidth("100%");

		HorizontalLayout messageHeader = new HorizontalLayout();
		messageHeader.setStyleName("message-header");
		VerticalLayout leftHeader = new VerticalLayout();
		Label username = new Label(comment.getOwnerFullName());
		username.setStyleName("user-name");
		leftHeader.addComponent(username);

		VerticalLayout rightHeader = new VerticalLayout();
		Label timePostLbl = new Label(
				DateTimeUtils.getStringDateFromNow(comment.getCreatedtime()));
		timePostLbl.setSizeUndefined();
		timePostLbl.setStyleName("time-post");
		rightHeader.addComponent(timePostLbl);

		messageHeader.addComponent(leftHeader);
		messageHeader.setExpandRatio(leftHeader, 1.0f);
		messageHeader.addComponent(timePostLbl);
		messageHeader.setWidth("100%");

		rowLayout.addComponent(messageHeader);

		Label messageContent = new Label(comment.getComment(),
				Label.CONTENT_XHTML);
		messageContent.setStyleName("message-body");
		rowLayout.addComponent(messageContent);

		List<Content> attachments = comment.getAttachments();
		if (attachments != null && !attachments.isEmpty()) {
			rowLayout.addComponent(new AttachmentDisplayComponent(attachments));
		}

		layout.addComponent(rowLayout);
		layout.setExpandRatio(rowLayout, 1.0f);
		return layout;
	}
}
