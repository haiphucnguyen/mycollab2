package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.localization.RiskI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.MyCollabSession;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.DynamicQueryParamLayout;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
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
public class RiskSearchPanel extends
		DefaultGenericSearchPanel<RiskSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private final SimpleProject project;

	private static Param[] paramFields = new Param[] {
			RiskSearchCriteria.p_assignee, RiskSearchCriteria.p_raisedUser,
			RiskSearchCriteria.p_status, RiskSearchCriteria.p_probalitity,
			RiskSearchCriteria.p_consequence, RiskSearchCriteria.p_duedate,
			RiskSearchCriteria.p_raiseddate, RiskSearchCriteria.p_createdtime,
			RiskSearchCriteria.p_lastupdatedtime };

	protected RiskSearchCriteria searchCriteria;

	public RiskSearchPanel() {
		this.project = (SimpleProject) MyCollabSession.getVariable("project");
	}

	private HorizontalLayout createSearchTopPanel() {
		Image titleIcon = new Image(null,
				MyCollabResource
						.newResource("icons/22/project/risk_selected.png"));
		Label headerText = new Label("Risk List");

		final Button createBtn = new Button(
				LocalizationHelper.getMessage(RiskI18nEnum.NEW_RISK_ACTION),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoAdd(this, null));
					}
				});
		createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		createBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS));

		HorizontalLayout header = new HorizontalLayout();
		headerText.setStyleName(UIConstants.HEADER_TEXT);

		UiUtils.addComponent(header, titleIcon, Alignment.MIDDLE_LEFT);
		UiUtils.addComponent(header, headerText, Alignment.MIDDLE_LEFT);
		UiUtils.addComponent(header, createBtn, Alignment.MIDDLE_RIGHT);
		header.setExpandRatio(headerText, 1.0f);

		header.setStyleName(UIConstants.HEADER_VIEW);
		header.setWidth("100%");
		header.setSpacing(true);
		header.setMargin(new MarginInfo(true, false, true, false));
		return header;
	}

	@Override
	protected SearchLayout<RiskSearchCriteria> createBasicSearchLayout() {
		return new RiskBasicSearchLayout();
	}

	@Override
	protected SearchLayout<RiskSearchCriteria> createAdvancedSearchLayout() {
		return new RiskAdvancedSearchLayout();
	}

	@SuppressWarnings("rawtypes")
	private class RiskBasicSearchLayout extends BasicSearchLayout {

		@SuppressWarnings("unchecked")
		public RiskBasicSearchLayout() {
			super(RiskSearchPanel.this);
		}

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.setMargin(true);

			this.nameField = this.createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");

			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, this.nameField,
					Alignment.MIDDLE_CENTER);

			this.myItemCheckbox = new CheckBox("My Items");
			this.myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button("Search");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search.png"));
			searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			searchBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					RiskBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(basicSearchBody, searchBtn,
					Alignment.MIDDLE_LEFT);

			final Button cancelBtn = new Button(
					LocalizationHelper.getMessage(GenericI18Enum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					RiskBasicSearchLayout.this.nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);

			final Button advancedSearchBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							RiskSearchPanel.this.moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);

			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			RiskSearchPanel.this.searchCriteria = new RiskSearchCriteria();
			RiskSearchPanel.this.searchCriteria
					.setProjectId(new NumberSearchField(SearchField.AND,
							RiskSearchPanel.this.project.getId()));
			RiskSearchPanel.this.searchCriteria
					.setRiskname(new StringSearchField(this.nameField
							.getValue().toString().trim()));

			if (this.myItemCheckbox.getValue()) {
				RiskSearchPanel.this.searchCriteria
						.setAssignToUser(new StringSearchField(SearchField.AND,
								AppContext.getUsername()));
			} else {
				RiskSearchPanel.this.searchCriteria.setAssignToUser(null);
			}
			return RiskSearchPanel.this.searchCriteria;
		}

		@Override
		public ComponentContainer constructHeader() {
			return RiskSearchPanel.this.createSearchTopPanel();
		}
	}

	private class RiskAdvancedSearchLayout extends
			DynamicQueryParamLayout<RiskSearchCriteria> {
		private static final long serialVersionUID = 1L;

		public RiskAdvancedSearchLayout() {
			super(RiskSearchPanel.this, ProjectTypeConstants.RISK);
		}

		@Override
		public ComponentContainer constructHeader() {
			return RiskSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public Param[] getParamFields() {
			return paramFields;
		}

		@Override
		protected Class<RiskSearchCriteria> getType() {
			return RiskSearchCriteria.class;
		}

		@Override
		protected Component buildSelectionComp(String fieldId) {
			if ("risk-assignuser".equals(fieldId)) {
				return new ProjectMemberListSelect();
			} else if ("risk-raiseduser".equals(fieldId)) {
				return new ProjectMemberListSelect();
			}
			return null;
		}
	}
}
