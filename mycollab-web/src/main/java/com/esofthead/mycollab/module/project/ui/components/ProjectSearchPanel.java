package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

public class ProjectSearchPanel extends
		GenericSearchPanel<ProjectSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private ProjectSearchCriteria searchCriteria;

	public ProjectSearchPanel() {
	}

	@Override
	public void attach() {
		super.attach();

		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new ProjectBasicSearchCriteria());
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Projects");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createBtn = new Button(
				LocalizationHelper
						.getMessage(ProjectCommonI18nEnum.NEW_PROJECT_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ProjectEvent.GotoAdd(
										ProjectSearchPanel.this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class ProjectBasicSearchCriteria extends
			GenericSearchPanel.BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public ProjectBasicSearchCriteria() {
			super(ProjectSearchPanel.this);
			// TODO Auto-generated constructor stub
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;

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

			Button searchBtn = new Button("Search", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(Button.ClickEvent event) {
					ProjectBasicSearchCriteria.this.callSearchAction();
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
			searchCriteria = new ProjectSearchCriteria();
			searchCriteria.setProjectName(new StringSearchField(
					nameField.getValue().toString().trim()));
			return searchCriteria;
		}
	}
}
