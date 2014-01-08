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

package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class HistoryLogWindow extends Window {
	private static final long serialVersionUID = 1L;

	private final HistoryLogComponent historyLogComponent;

	public HistoryLogWindow(final String module, final String type) {
		super("Change Log");

		this.setWidth("700px");

		this.historyLogComponent = new HistoryLogComponent(module, type);
		this.setContent(historyLogComponent);
		this.center();
	}

	public void loadHistory(int typeid) {
		historyLogComponent.loadHistory(typeid);
	}

	public void generateFieldDisplayHandler(final String fieldname,
			final String displayName) {
		this.historyLogComponent.generateFieldDisplayHandler(fieldname,
				displayName);
	}

	public void generateFieldDisplayHandler(final String fieldname,
			final String displayName,
			final HistoryLogComponent.HistoryFieldFormat format) {
		this.historyLogComponent.generateFieldDisplayHandler(fieldname,
				displayName, format);
	}

	public void generateFieldDisplayHandler(final String fieldname,
			final String displayName, final String formatName) {
		this.historyLogComponent.generateFieldDisplayHandler(fieldname,
				displayName, formatName);
	}
}
