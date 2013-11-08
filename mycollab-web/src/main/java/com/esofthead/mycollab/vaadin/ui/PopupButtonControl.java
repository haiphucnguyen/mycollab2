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

import java.util.HashSet;
import java.util.Set;

import org.vaadin.hene.splitbutton.SplitButton;

import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

public class PopupButtonControl extends SplitButton implements
		HasPopupActionHandlers {

	private static final long serialVersionUID = 1L;
	private final VerticalLayout selectContent;
	private Set<PopupActionHandler> handlers;

	public PopupButtonControl(final String id, final String defaultName) {
		super();
		this.addStyleName(UIConstants.SPLIT_BUTTON);
		this.setCaption(defaultName);
		this.setData(id);

		this.addClickListener(new SplitButtonClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void splitButtonClick(SplitButtonClickEvent event) {
				changeOption(id, defaultName);
				PopupButtonControl.this.setPopupVisible(false);
			}
		});

		selectContent = new VerticalLayout();
		selectContent.setWidth("100px");
		this.setComponent(selectContent);
	}

	public void addOptionItem(final String id, final String name) {
		ButtonLink selectAllBtn = new ButtonLink(name,
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						changeOption(id, name);
						PopupButtonControl.this.setPopupVisible(false);
					}
				});
		selectContent.addComponent(selectAllBtn);
	}

	private void changeOption(String id, String caption) {
		if (handlers != null) {
			for (PopupActionHandler handler : handlers) {
				handler.onSelect(id, caption);
			}
		}
	}

	@Override
	public void addPopupActionHandler(PopupActionHandler handler) {
		if (handlers == null) {
			handlers = new HashSet<PopupActionHandler>();
		}
		handlers.add(handler);

	}
}
