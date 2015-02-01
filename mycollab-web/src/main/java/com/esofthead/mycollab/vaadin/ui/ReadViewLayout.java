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

import org.apache.commons.lang3.StringUtils;

import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
public class ReadViewLayout extends CustomLayoutExt {
	private static final long serialVersionUID = 1L;

	private Label titleLbl;

	public ReadViewLayout(final String title) {
		super("readView");
		ComponentContainer headerPanel = buildHeader(title);
		this.addComponent(headerPanel, "readViewHeader");
	}

	private ComponentContainer buildHeader(String title) {
		MHorizontalLayout header = new MHorizontalLayout().withWidth("100%").withSpacing(true);

		this.titleLbl = new Label();
		this.titleLbl.setStyleName("headerName");
		this.titleLbl.setImmediate(true);

		header.with(titleLbl).expand(titleLbl);

		if (StringUtils.isBlank(title)) {
			this.setTitle("Undefined");
		} else {
			this.setTitle(title);
		}
		return header;
	}

	public void addBody(final ComponentContainer body) {
		this.addComponent(body, "readViewBody");
	}

	public void addBottomControls(final ComponentContainer bottomControls) {
		this.addComponent(bottomControls, "readViewBottomControls");
	}

	public void clearTitleStyleName() {
		this.titleLbl.setStyleName("headerName");
	}

	public void addTitleStyleName(final String styleName) {
		this.titleLbl.addStyleName(styleName);
	}

	public void setTitleStyleName(final String styleName) {
		this.titleLbl.setStyleName(styleName);
	}

	public void removeTitleStyleName(final String styleName) {
		this.titleLbl.removeStyleName(styleName);
	}

	public void setTitle(final String title) {
		this.titleLbl.setValue(title);
	}
}
