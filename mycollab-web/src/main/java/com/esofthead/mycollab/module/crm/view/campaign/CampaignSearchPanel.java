package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
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
public class CampaignSearchPanel extends
		DefaultGenericSearchPanel<CampaignSearchCriteria> {

	protected CampaignSearchCriteria searchCriteria;

	public CampaignSearchPanel() {
		searchCriteria = new CampaignSearchCriteria();
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Campaigns");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoAdd(this, null));

					}
				});
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class CampaignBasicSearchLayout extends BasicSearchLayout {

		private TextField nameField;
		private CheckBox myItemCheckbox;

		@SuppressWarnings("unchecked")
		public CampaignBasicSearchLayout() {
			super(CampaignSearchPanel.this);
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
					"NameFieldOfBasicSearch");

			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, nameField, Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));
			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					CampaignBasicSearchLayout.this.callSearchAction();
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
			searchCriteria = new CampaignSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty(nameField.getValue().toString()
					.trim())) {
				searchCriteria.setCampaignName(new StringSearchField(
						SearchField.AND, (String) nameField.getValue()));
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

	private class CampaignAdvancedSearchLayout extends
			DefaultAdvancedSearchLayout<CampaignSearchCriteria> {

		private TextField nameField;
		private DateSelectionField startDateField;
		private DateSelectionField endDateField;
		private CampaignTypeListSelect typeField;
		private CampaignStatusListSelect statusField;
		private UserListSelect assignUserField;

		public CampaignAdvancedSearchLayout() {
			super(CampaignSearchPanel.this, CrmTypeConstants.CAMPAIGN);
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

			nameField = (TextField) gridLayout.addComponent(new TextField(),
					"Name", 0, 0);
			startDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(), "Start Date", 1, 0);
			startDateField.setDateFormat(AppContext.getDateFormat());

			endDateField = (DateSelectionField) gridLayout.addComponent(
					new DateSelectionField(), "End Date", 2, 0);
			endDateField.setDateFormat(AppContext.getDateFormat());

			typeField = (CampaignTypeListSelect) gridLayout.addComponent(
					new CampaignTypeListSelect(), "Type", 0, 1);
			statusField = (CampaignStatusListSelect) gridLayout.addComponent(
					new CampaignStatusListSelect(), "Status", 1, 1);
			assignUserField = (UserListSelect) gridLayout.addComponent(
					new UserListSelect(), "Assign User", 2, 1);

			gridLayout.getLayout().setSpacing(true);

			return gridLayout.getLayout();
		}

		@Override
		protected CampaignSearchCriteria fillupSearchCriteria() {
			searchCriteria = new CampaignSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty((String) nameField.getValue())) {
				searchCriteria
						.setCampaignName(new StringSearchField(SearchField.AND,
								((String) nameField.getValue()).trim()));
			}

			SearchField startDate = startDateField.getValue();
			if (startDate != null && (startDate instanceof DateSearchField)) {
				searchCriteria.setStartDate((DateSearchField) startDate);
			} else if (startDate != null
					&& (startDate instanceof RangeDateSearchField)) {
				searchCriteria
						.setStartDateRange((RangeDateSearchField) startDate);
			}

			SearchField endDate = endDateField.getValue();
			if (endDate != null && (endDate instanceof DateSearchField)) {
				searchCriteria.setStartDate((DateSearchField) endDate);
			} else if (endDate != null
					&& (endDate instanceof RangeDateSearchField)) {
				searchCriteria
						.setStartDateRange((RangeDateSearchField) endDate);
			}

			Collection<String> types = (Collection<String>) typeField
					.getValue();
			if (types != null && types.size() > 0) {
				searchCriteria.setTypes(new SetSearchField<String>(
						SearchField.AND, types));
			}

			Collection<String> statuses = (Collection<String>) statusField
					.getValue();
			if (statuses != null && statuses.size() > 0) {
				searchCriteria.setStatuses(new SetSearchField<String>(
						SearchField.AND, statuses));
			}

			Collection<String> assignUsers = (Collection<String>) assignUserField
					.getValue();
			if (assignUsers != null && assignUsers.size() > 0) {
				searchCriteria.setAssignUsers(new SetSearchField<String>(
						SearchField.AND, assignUsers));
			}
			return searchCriteria;
		}

		@Override
		protected void clearFields() {
			nameField.setValue("");
			startDateField.setDefaultSelection();
			endDateField.setDefaultSelection();
			typeField.setValue(null);
			statusField.setValue(null);
			assignUserField.setValue(null);
		}

		@Override
		public void loadSaveSearchToField(String value) {
			// TODO Auto-generated method stub
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BasicSearchLayout<CampaignSearchCriteria> createBasicSearchLayout() {
		return new CampaignBasicSearchLayout();
	}

	@Override
	protected SearchLayout<CampaignSearchCriteria> createAdvancedSearchLayout() {
		return new CampaignAdvancedSearchLayout();
	}
}
