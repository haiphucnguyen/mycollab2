/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.project.ui;

import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Inc.
 * 
 * @since 4.3.1
 */
public class ProjectGenericPresenter<V extends PageView> extends
		AbstractPresenter<V> {

	private static final long serialVersionUID = 2162143696476839340L;

	public ProjectGenericPresenter(Class<V> viewClass) {
		super(viewClass);
	}

	@Override
	protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
		if (!view.isAttached()) {
			if (navigator instanceof NavigationManager)
				((NavigationManager) navigator).navigateTo(view.getWidget());
			else {
				navigator.removeAllComponents();
				navigator.addComponent(view.getWidget());
			}
		}
	}

}
