package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.localization.RiskI18nEnum;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

public class RiskSearchPanel extends GenericSearchPanel<RiskSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private SimpleProject project;
	protected RiskSearchCriteria searchCriteria;

	public RiskSearchPanel() {
		this.project = (SimpleProject) AppContext.getVariable("project");
	}

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {

		this.setCompositionRoot(new RiskBasicSearchLayout());
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Risks");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createBtn = new Button(
				LocalizationHelper.getMessage(RiskI18nEnum.NEW_RISK_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS));

		UiUtils.addComponent(layout, createBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class RiskBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public RiskBasicSearchLayout() {
			super(RiskSearchPanel.this);
			// TODO Auto-generated constructor stub
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
			basicSearchBody.setSpacing(false);

			nameField = createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");

			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, nameField,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));
			searchBtn.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					RiskBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(basicSearchBody, searchBtn,
					Alignment.MIDDLE_LEFT);

			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.setWidth("55px");
			cancelBtn.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new RiskSearchCriteria();
			searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
					project.getId()));
			searchCriteria.setRiskname(new StringSearchField(nameField
					.getValue().toString().trim()));

			if (myItemCheckbox.booleanValue()) {
				searchCriteria.setAssignToUser(new StringSearchField(
						SearchField.AND, AppContext.getUsername()));
			} else {
				searchCriteria.setAssignToUser(null);
			}
			return searchCriteria;
		}
	}

}
