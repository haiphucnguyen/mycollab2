package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NoSubDomainExistedWindow extends Window {
	private static final long serialVersionUID = 1L;

	public NoSubDomainExistedWindow(final String domain) {
		final NoSubDomainLayout contentLayout = new NoSubDomainLayout(domain);
		contentLayout.setWidth("616px");
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

			final Button backToHome = new Button(
					"Go back to Home Page", new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							NoSubDomainExistedWindow.this.open(new ExternalResource("https://www.mycollab.com"));
						}
					});
			backToHome.addStyleName(UIConstants.THEME_BLUE_LINK);
			warningContent.addComponent(backToHome);
			warningContent.setComponentAlignment(backToHome, Alignment.MIDDLE_CENTER);
			warningContent.setHeight("97px");
			this.addComponent(warningContent, "warning-message");
		}

	}

}
