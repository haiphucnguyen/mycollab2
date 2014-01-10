/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class NoSubDomainExistedWindow extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	public NoSubDomainExistedWindow(final String domain) {
		final NoSubDomainLayout contentLayout = new NoSubDomainLayout(domain);
		contentLayout.setWidth("616px");
		this.addComponent(contentLayout);
		this.setComponentAlignment(contentLayout, Alignment.MIDDLE_CENTER);
	}

	private class NoSubDomainLayout extends CustomLayoutExt {

		private static final long serialVersionUID = 1L;

		public NoSubDomainLayout(final String domain) {
			super("noSubdomainWindow");

			final VerticalLayout warningContent = new VerticalLayout();
			final Label warningMsg = new Label("There is no sudomain: "
					+ domain + ". Do you forgot your domain?");
			warningContent.addComponent(warningMsg);

			final Button backToHome = new Button("Go back to Home Page",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							getUI().getPage().setLocation(
									"https://www.mycollab.com");
						}
					});
			backToHome.addStyleName(UIConstants.THEME_BLUE_LINK);
			warningContent.addComponent(backToHome);
			warningContent.setComponentAlignment(backToHome,
					Alignment.MIDDLE_CENTER);
			warningContent.setHeight("97px");
			this.addComponent(warningContent, "warning-message");
		}

	}

}
