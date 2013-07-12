package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NoSubDomainExistedWindow extends Window {
	private static final long serialVersionUID = 1L;

	public NoSubDomainExistedWindow(final String domain) {
		// this.addComponent(new Label("There is no sudomain: " + domain
		// + ". Do you forgot your domain?"));
		final NoSubDomainLayout contentLayout = new NoSubDomainLayout(domain);
		contentLayout.setWidth("700px");
		this.addComponent(contentLayout);
		((VerticalLayout) this.getContent()).setComponentAlignment(
				contentLayout, Alignment.MIDDLE_CENTER);
	}

	private class NoSubDomainLayout extends CustomLayoutExt {

		private static final long serialVersionUID = 1L;

		public NoSubDomainLayout(final String domain) {
			super("noSubdomainWindow");

			final VerticalLayout warningContent = new VerticalLayout();
			final Label warningMsg = new Label("There is no sudomain: "
					+ domain + ". Do you forgot your domain?");
			warningContent.addComponent(warningMsg);

			final ButtonLink backToHome = new ButtonLink("Go back to Home Page");
			warningContent.addComponent(backToHome);
			this.addComponent(warningContent, "warning-message");
		}

	}

}
