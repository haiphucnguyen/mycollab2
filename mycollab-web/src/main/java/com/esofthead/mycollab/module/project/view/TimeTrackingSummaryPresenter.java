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
package com.esofthead.mycollab.module.project.view;

import java.util.Collection;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class TimeTrackingSummaryPresenter extends
		AbstractPresenter<TimeTrackingSummaryView> {
	private static final long serialVersionUID = 1L;

	public TimeTrackingSummaryPresenter() {
		super(TimeTrackingSummaryView.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectModule prjContainer = (ProjectModule) container;
		prjContainer.removeAllComponents();
		prjContainer.addComponent((Component) view);
		prjContainer.setComponentAlignment(view, Alignment.TOP_CENTER);
		view.display((Collection<Integer>) data.getParams());
	}
}
