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

import com.vaadin.ui.Button;

/**
 * Button link
 */
public class ButtonLink extends Button {

	private static final long serialVersionUID = 1L;

	public ButtonLink(String caption, Boolean wordWrap) {
		super(caption);
		this.setStyleName("link");
		if (wordWrap)
			this.addStyleName("wordWrap");
	}

	public ButtonLink(String caption) {
		this(caption, true);
	}

	public ButtonLink(String caption, ClickListener listener, Boolean wordWrap) {
		super(caption, listener);
		this.setStyleName("link");
		if (wordWrap)
			this.addStyleName("wordWrap");
	}

	public ButtonLink(String caption, ClickListener listener) {
		this(caption, listener, true);
	}
}
