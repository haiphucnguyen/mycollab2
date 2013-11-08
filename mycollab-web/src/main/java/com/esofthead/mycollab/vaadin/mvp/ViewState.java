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
package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;

public class ViewState {
	private ComponentContainer container;
	private Presenter presenter;
	private ScreenData<?> params;

	public ViewState(ComponentContainer container, Presenter presenter,
			ScreenData<?> data) {
		this.container = container;
		this.presenter = presenter;
		this.params = data;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public ScreenData<?> getParams() {
		return params;
	}

	public ComponentContainer getContainer() {
		return container;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("View State:").append("\n");
		result.append("   Presenter: " + presenter).append("\n");
		result.append("   Params: " + ((params != null) ? params : "null"));
		return result.toString();
	}
}
