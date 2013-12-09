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
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;

public class ReadViewLayout extends CssLayout {
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout header;
	private final Embedded iconEmbed;
	private final Label titleLbl;
	private final TabsheetDecor viewTab;

	public ReadViewLayout(final Resource icon) {
		this.setSizeFull();
		this.setStyleName("readview-layout");

		this.header = new HorizontalLayout();
		this.header.setWidth("100%");
		header.setSpacing(true);
		this.header.setStyleName("readview-layout-header");
		this.addComponent(this.header);

		final HorizontalLayout headerLeft = new HorizontalLayout();
		headerLeft.setSizeFull();
		headerLeft.addStyleName("readview-header-left");
		headerLeft.setSpacing(true);
		this.iconEmbed = new Embedded();
		headerLeft.addComponent(this.iconEmbed);

		this.setTitleIcon(icon);

		headerLeft.setComponentAlignment(this.iconEmbed, Alignment.MIDDLE_LEFT);

		this.titleLbl = new Label();
		this.titleLbl.setStyleName("h2");
		this.titleLbl.setWidth("100%");
		headerLeft.addComponent(this.titleLbl);
		headerLeft.setExpandRatio(this.titleLbl, 1.0f);
		headerLeft.setComponentAlignment(this.titleLbl, Alignment.MIDDLE_LEFT);
		this.header.addComponent(headerLeft);
		this.header.setComponentAlignment(headerLeft, Alignment.TOP_LEFT);
		this.header.setExpandRatio(headerLeft, 1.0f);

		this.viewTab = new TabsheetDecor();
		this.viewTab.setSizeUndefined();
		this.header.addComponent(this.viewTab);
		this.header
				.setComponentAlignment(this.viewTab, Alignment.BOTTOM_CENTER);
	}

	public void addControlButtons(final Component controlsBtn) {
		if (this.header != null) {
			this.header.addComponent(controlsBtn);
			this.header.setComponentAlignment(controlsBtn,
					Alignment.MIDDLE_CENTER);
		}
	}

	public void addTab(final Component content, final String caption) {
		if (this.viewTab != null) {
			this.viewTab.addTab(content, caption);
		}
	}

	public void addSelectedTabChangeListener(
			final TabSheet.SelectedTabChangeListener listener) {
		if (this.viewTab != null) {
			this.viewTab.addSelectedTabChangeListener(listener);
		}
	}

	public void selectTab(final String viewName) {
		if (this.viewTab != null) {
			viewTab.selectTab(viewName);
		}
	}

	public void setTitle(final String title) {
		if (this.titleLbl != null) {
			this.titleLbl.setValue(title);
		}
	}

	public void setTitleIcon(final Resource iconResource) {
		if (this.iconEmbed != null && iconResource != null) {
			this.iconEmbed.setSource(iconResource);
		}
	}
}
