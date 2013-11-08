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

import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.vaadin.ui.VerticalLayout;

public class DefaultLazyComponent<V extends View> extends VerticalLayout
		implements ILazyComponent<V> {
	private static final long serialVersionUID = 1L;
	private boolean isInit = false;
	private Class<V> viewClass;

	public DefaultLazyComponent(Class<V> viewClass) {
		super();
		this.viewClass = viewClass;
	}

	@Override
	public void attach() {
		super.attach();
		
		if (!isInit) {
			V view = ViewManager.getView(viewClass);
			this.addComponent(view.getWidget());
			isInit = true;
		}
		
	}
}
