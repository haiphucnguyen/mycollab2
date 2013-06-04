/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author haiphucnguyen
 */
public class ComponentSearchPanel extends
		GenericSearchPanel<ComponentSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private SimpleProject project;
	protected ComponentSearchCriteria searchCriteria;

	public ComponentSearchPanel() {
		this.project = CurrentProjectVariables.getProject();
	}

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new ComponentBasicSearchCriteria());
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Components");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createBtn = new Button(
				LocalizationHelper.getMessage(BugI18nEnum.NEW_COMPONENT_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.COMPONENTS));
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class ComponentBasicSearchCriteria extends
			GenericSearchPanel.BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public ComponentBasicSearchCriteria() {
			super(ComponentSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.addComponent(new Label("Name"));
			nameField = new TextField();
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, nameField,
					Alignment.MIDDLE_CENTER);
			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			Button searchBtn = new Button("Search", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(Button.ClickEvent event) {
					ComponentBasicSearchCriteria.this.callSearchAction();
				}
			});
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			basicSearchBody.addComponent(searchBtn);

			Button clearBtn = new Button("Clear", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(Button.ClickEvent event) {
					nameField.setValue("");
				}
			});
			clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			basicSearchBody.addComponent(clearBtn);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new ComponentSearchCriteria();
			searchCriteria.setProjectid(new NumberSearchField(
					SearchField.AND, project.getId()));
			searchCriteria.setComponentName(new StringSearchField(
					nameField.getValue().toString().trim()));
			return searchCriteria;
		}
	}

}
