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
package com.esofthead.mycollab.module.crm.view.file;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.events.DocumentEvent;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.shared.ui.MarginInfo;

@ViewComponent
public class FileDashboardViewImpl extends AbstractPageView implements
		FileDashboardView {
	private static final long serialVersionUID = 1L;

	private FileDashboardComponent dashboardComponent;

	public FileDashboardViewImpl() {
		this.setWidth("100%");
		this.setMargin(new MarginInfo(false, true, false, true));
		dashboardComponent = new FileDashboardComponent() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void doSearch(FileSearchCriteria searchCriteria) {
				EventBus.getInstance().fireEvent(
						new DocumentEvent.Search(FileDashboardViewImpl.this,
								searchCriteria));
			}

		};
		dashboardComponent.setWidth("100%");
		this.addComponent(dashboardComponent);

	}

	@Override
	public void displayFiles() {
		String rootPath = String.format("%d/.crm", AppContext.getAccountId());
		dashboardComponent.displayResources(rootPath, "Documents");
	}
}
