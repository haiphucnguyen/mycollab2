package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceListSelect;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DefaultAdvancedSearchLayout;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
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

@SuppressWarnings("serial")
public class OpportunitySearchPanel extends
		DefaultGenericSearchPanel<OpportunitySearchCriteria> {

	protected OpportunitySearchCriteria searchCriteria;

	public OpportunitySearchPanel() {
		searchCriteria = new OpportunitySearchCriteria();
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Opportunities");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoAdd(
										OpportunitySearchPanel.this, null));
					}
				});
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_OPPORTUNITY));
		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class OpportunityBasicSearchLayout extends BasicSearchLayout {

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@SuppressWarnings("unchecked")
		public OpportunityBasicSearchLayout() {
			super(OpportunitySearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(false);

			nameField = this.createSeachSupportTextField(new TextField(),
					"NameFieldOfSearch");
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, nameField, Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					OpportunityBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(layout, searchBtn, Alignment.MIDDLE_LEFT);

			myItemCheckbox = new CheckBox(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.SEARCH_MYITEMS_CHECKBOX));
			myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(layout, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			});
			UiUtils.addComponent(layout, cancelBtn, Alignment.MIDDLE_CENTER);

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(layout, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return layout;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new OpportunitySearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty(nameField.getValue().toString()
					.trim())) {
				searchCriteria
						.setOpportunityName(new StringSearchField(
								SearchField.AND,
								((String) nameField.getValue()).trim()));
			}

			if (myItemCheckbox.booleanValue()) {
				searchCriteria.setAssignUsers(new SetSearchField<String>(
						SearchField.AND, new String[] { AppContext
								.getUsername() }));
			} else {
				searchCriteria.setAssignUsers(null);
			}

			return searchCriteria;
		}
	}

	private class OpportunityAdvancedSearchLayout extends
			DefaultAdvancedSearchLayout<OpportunitySearchCriteria> {

		private static final long serialVersionUID = 1L;
		private TextField opportunityNameField;
		private AccountSelectionField accountField;
		private TextField nextStepField;
		private UserListSelect userField;
		private OpportunitySalesStageListSelect stageField;
		private LeadSourceListSelect sourceField;

		@SuppressWarnings("unchecked")
		public OpportunityAdvancedSearchLayout() {
			super(OpportunitySearchPanel.this, CrmTypeConstants.OPPORTUNITY);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 3,
					"90px");

			if (ScreenSize.hasSupport1024Pixels()) {
				gridLayout = new GridFormLayoutHelper(3, 3,
						UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
						"90px");
			} else if (ScreenSize.hasSupport1280Pixels()) {
				gridLayout = new GridFormLayoutHelper(3, 3, "90px");
			}

			opportunityNameField = (TextField) gridLayout.addComponent(
					new TextField(), "Name", 0, 0);
			accountField = (AccountSelectionField) gridLayout.addComponent(
					new AccountSelectionField(), "Account", 1, 0);
			nextStepField = (TextField) gridLayout.addComponent(
					new TextField(), "Next Step", 2, 0);

			userField = (UserListSelect) gridLayout.addComponent(
					new UserListSelect(), "Assigned to", 0, 1);
			stageField = (OpportunitySalesStageListSelect) gridLayout
					.addComponent(new OpportunitySalesStageListSelect(),
							"Sales Stage", 1, 1);
			sourceField = (LeadSourceListSelect) gridLayout.addComponent(
					new LeadSourceListSelect(), "Lead Source", 2, 1);

			gridLayout.getLayout().setSpacing(true);
			return gridLayout.getLayout();
		}

		@Override
		protected OpportunitySearchCriteria fillupSearchCriteria() {
			searchCriteria = new OpportunitySearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty((String) opportunityNameField
					.getValue())) {
				searchCriteria.setOpportunityName(new StringSearchField(
						SearchField.AND, ((String) opportunityNameField
								.getValue()).trim()));
			}

			SimpleAccount account = accountField.getAccount();
			if (StringUtil.isNotNullOrEmpty(account.getAccountname())) {
				searchCriteria.setAccountName(new StringSearchField(
						SearchField.AND, account.getAccountname()));
			}

			if (StringUtil.isNotNullOrEmpty((String) nextStepField.getValue())) {
				searchCriteria.setNextStep(new StringSearchField(
						SearchField.AND, ((String) nextStepField.getValue())
								.trim()));
			}

			Collection<String> assignUsers = (Collection<String>) userField
					.getValue();
			if (assignUsers != null && assignUsers.size() > 0) {
				searchCriteria.setAssignUsers(new SetSearchField<String>(
						SearchField.AND, assignUsers));
			}

			Collection<String> saleStages = (Collection<String>) stageField
					.getValue();
			if (saleStages != null && saleStages.size() > 0) {
				searchCriteria.setSalesStages(new SetSearchField<String>(
						SearchField.AND, saleStages));
			}

			Collection<String> leadSources = (Collection<String>) sourceField
					.getValue();
			if (leadSources != null && leadSources.size() > 0) {
				searchCriteria.setLeadSources(new SetSearchField<String>(
						SearchField.AND, leadSources));
			}
			return searchCriteria;
		}

		@Override
		protected void clearFields() {
			opportunityNameField.setValue("");
			accountField.clearValue();
			nextStepField.setValue("");
			userField.setValue(null);
			stageField.setValue(null);
			sourceField.setValue(null);
		}

		@Override
		protected void loadSaveSearchToField(OpportunitySearchCriteria value) {
			if (value.getOpportunityName() != null)
				opportunityNameField.setValue(value.getOpportunityName()
						.getValue());
			if (value.getAccountName() != null)
				accountField.setValue(value.getAccountName().getValue());
			if (value.getNextStep() != null)
				nextStepField.setValue(value.getNextStep().getValue());
			if (value.getAssignUsers() != null)
				userField
						.setValue(Arrays.asList((Object[])value.getAssignUsers().values));
			if (value.getSalesStages() != null)
				stageField
						.setValue(Arrays.asList((Object[])value.getSalesStages().values));
			if (value.getLeadSources() != null)
				sourceField
						.setValue(Arrays.asList((Object[])value.getLeadSources().values));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BasicSearchLayout<OpportunitySearchCriteria> createBasicSearchLayout() {
		return new OpportunityBasicSearchLayout();
	}

	@Override
	protected SearchLayout<OpportunitySearchCriteria> createAdvancedSearchLayout() {
		return new OpportunityAdvancedSearchLayout();
	}
}
