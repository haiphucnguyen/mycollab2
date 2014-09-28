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
package com.esofthead.mycollab.module.file.view.components;

import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public abstract class FileDashboardComponent extends VerticalLayout {
	private static final long serialVersionUID = 1L;
	private String rootPath;

	private final FileSearchPanel fileSearchPanel;
	private ResourcesDisplayComponent resourceHandlerComponent;
	private Folder baseFolder;
	private HorizontalLayout resourceContainer;

	private final ResourceService resourceService;

	public FileDashboardComponent() {
		this.setWidth("100%");
		this.setSpacing(true);
		this.resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);
		this.fileSearchPanel = new FileSearchPanel(rootPath);

		this.addComponent(this.fileSearchPanel);

		resourceContainer = new HorizontalLayout();
		resourceContainer.setSizeFull();

		this.resourceHandlerComponent = new ResourcesDisplayComponent(
				baseFolder, rootPath, null);
		this.resourceHandlerComponent.setSpacing(true);
		resourceContainer.addComponent(resourceHandlerComponent);
		resourceContainer.setComponentAlignment(resourceHandlerComponent,
				Alignment.TOP_LEFT);
		resourceContainer.setExpandRatio(resourceHandlerComponent, 1.0f);

		this.addComponent(resourceContainer);

	}

	abstract protected void doSearch(FileSearchCriteria searchCriteria);

	public void displayResources(String rootPath, String rootFolderName) {
		this.rootPath = rootPath;

		this.baseFolder = new Folder();
		this.baseFolder.setPath(this.rootPath);

		resourceHandlerComponent.displayComponent(this.baseFolder, rootPath,
				rootFolderName, false);

		resourceHandlerComponent
				.addSearchHandlerToBreadCrumb(new SearchHandler<FileSearchCriteria>() {
					@Override
					public void onSearch(FileSearchCriteria criteria) {
						Folder selectedFolder = null;
						selectedFolder = (Folder) FileDashboardComponent.this.resourceService
								.getResource(criteria.getBaseFolder());
						resourceHandlerComponent
								.constructBodyItemContainer(selectedFolder);
						resourceHandlerComponent
								.gotoFolderBreadCumb(selectedFolder);
						FileDashboardComponent.this.baseFolder = selectedFolder;
						resourceHandlerComponent
								.setCurrentBaseFolder(selectedFolder);
					}
				});
	}
}
