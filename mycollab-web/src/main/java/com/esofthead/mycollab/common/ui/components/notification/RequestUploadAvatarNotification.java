package com.esofthead.mycollab.common.ui.components.notification;

import com.esofthead.mycollab.common.ui.components.AbstractNotification;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Span;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class RequestUploadAvatarNotification extends AbstractNotification {

	public RequestUploadAvatarNotification() {
		super("You haven't uploaded your avatar yet. Please upload it at ",
				AbstractNotification.WARNING);
	}

	@Override
	public String renderContent() {
		Span spanEl = new Span();
		spanEl.appendText(getMessage());

		A link = new A(AccountLinkGenerator.generateFullProfileLink(AppContext
				.getSiteUrl()));
		link.appendText("here");
		spanEl.appendChild(link);
		return spanEl.write();
	}

}
