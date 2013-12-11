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

import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Mass update
 * 
 * @author MyCollab Ltd.
 * 
 * @param <B>
 */
public abstract class MassUpdateWindow<B> extends Window {
	private static final long serialVersionUID = 1L;

	private MassUpdateCommand<B> massUpdateCommand;

	private VerticalLayout layout;
	private Button updateBtn, closeBtn;

	public MassUpdateWindow(String title,
			MassUpdateCommand<B> massUpdatePresenter) {
		super(title);
		center();

		this.massUpdateCommand = massUpdatePresenter;
	}

	abstract protected B getItem();

	public VerticalLayout getLayout() {
		layout = new VerticalLayout();

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setMargin(true);
		controlsLayout.setSpacing(true);
		controlsLayout.setStyleName("addNewControl");

		updateBtn = new Button("Update", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				massUpdateCommand.massUpdate(getItem());
				MassUpdateWindow.this.close();
			}
		});
		updateBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(updateBtn);
		controlsLayout
				.setComponentAlignment(updateBtn, Alignment.MIDDLE_CENTER);

		closeBtn = new Button("Close", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MassUpdateWindow.this.close();
			}
		});
		closeBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(closeBtn);
		controlsLayout.setComponentAlignment(closeBtn, Alignment.MIDDLE_CENTER);

		layout.addComponent(controlsLayout);
		layout.setComponentAlignment(controlsLayout, Alignment.BOTTOM_CENTER);
		return layout;
	}

}
