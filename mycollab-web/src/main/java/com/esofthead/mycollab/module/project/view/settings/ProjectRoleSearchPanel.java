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
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.localization.PeopleI18nEnum;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.vaadin.MyCollabSession;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class ProjectRoleSearchPanel extends
GenericSearchPanel<ProjectRoleSearchCriteria> {
	private static final long serialVersionUID = 1L;
	private final SimpleProject project;
	protected ProjectRoleSearchCriteria searchCriteria;

	public ProjectRoleSearchPanel() {
		this.project = (SimpleProject) MyCollabSession.getVariable("project");
	}

	@Override
	public void attach() {
		super.attach();
		this.createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new ProjectRoleBasicSearchLayout());
	}



	@SuppressWarnings("rawtypes")
	private class ProjectRoleBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public ProjectRoleBasicSearchLayout() {
			super(ProjectRoleSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;


		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.setMargin(true);
			basicSearchBody.addComponent(new Label("Name"));
			this.nameField = new TextField();
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);
			this.myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button("Search",
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final Button.ClickEvent event) {
					ProjectRoleBasicSearchLayout.this
					.callSearchAction();
				}
			});
			searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			basicSearchBody.addComponent(searchBtn);

			final Button clearBtn = new Button("Clear",
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final Button.ClickEvent event) {
					ProjectRoleBasicSearchLayout.this.nameField
					.setValue("");
				}
			});
			clearBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			basicSearchBody.addComponent(clearBtn);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			ProjectRoleSearchPanel.this.searchCriteria = new ProjectRoleSearchCriteria();
			ProjectRoleSearchPanel.this.searchCriteria
			.setProjectId(new NumberSearchField(SearchField.AND,
					ProjectRoleSearchPanel.this.project.getId()));
			return ProjectRoleSearchPanel.this.searchCriteria;
		}

		@Override
		public ComponentContainer constructHeader() {
			Image titleIcon = new Image(null,
					MyCollabResource.newResource("icons/22/project/user_selected.png"));
			Label headerText = new Label("Role List");

			final Button createBtn = new Button(
					LocalizationHelper.getMessage(PeopleI18nEnum.NEW_ROLE_ACTION),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new RoleEvent.GotoAdd(this, null));
						}
					});
			createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			createBtn.setIcon(MyCollabResource
					.newResource("icons/16/addRecord.png"));
			createBtn.setEnabled(CurrentProjectVariables
					.canWrite(ProjectRolePermissionCollections.ROLES));

			HorizontalLayout header = new HorizontalLayout();
			headerText.setStyleName("hdr-text");

			UiUtils.addComponent(header, titleIcon, Alignment.MIDDLE_LEFT);
			UiUtils.addComponent(header, headerText, Alignment.MIDDLE_LEFT);
			UiUtils.addComponent(header, createBtn, Alignment.MIDDLE_RIGHT);
			header.setExpandRatio(headerText, 1.0f);

			header.setStyleName("hdr-view");
			header.setWidth("100%");
			header.setSpacing(true);
			header.setMargin(new MarginInfo(true, false, true, false));
			return header;
		}
	}

}
