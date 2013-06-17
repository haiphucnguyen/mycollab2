package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
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
public class LeadSearchPanel extends DefaultGenericSearchPanel<LeadSearchCriteria> {
	private static final long serialVersionUID = 1L;

	protected LeadSearchCriteria searchCriteria;

	public LeadSearchPanel() {
		searchCriteria = new LeadSearchCriteria();
	}
	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Leads");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_LEAD));
		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class LeadBasicSearchLayout extends BasicSearchLayout {
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@SuppressWarnings("unchecked")
		public LeadBasicSearchLayout() {
			super(LeadSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(false);
			
			nameField = this.createSeachSupportTextField(new TextField(), "nameFieldOfSearch");
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, nameField,Alignment.MIDDLE_CENTER);
			
			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
				.newResource("icons/16/search_white.png"));

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					LeadBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(layout, searchBtn,
					Alignment.MIDDLE_LEFT);
			
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
			UiUtils.addComponent(layout, cancelBtn,
					Alignment.MIDDLE_CENTER);
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
			searchCriteria = new LeadSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(
					SearchField.AND, AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty(nameField.getValue()
					.toString().trim())) {
				searchCriteria.setLeadName(new StringSearchField(
						SearchField.AND, (String) nameField.getValue()));
			}

			if (myItemCheckbox.booleanValue()) {
				searchCriteria
						.setAssignUsers(new SetSearchField<String>(
								SearchField.AND,
								new String[] { AppContext.getUsername() }));
			} else {
				searchCriteria.setAssignUsers(null);
			}
			return searchCriteria;
		}
	}

	private class LeadAdvancedSearchLayout extends DefaultAdvancedSearchLayout<LeadSearchCriteria> {

		private TextField firstnameField;
		private TextField lastnameField;
		private TextField accountnameField;
		private LeadStatusListSelect statusField;

		private TextField anyEmailField;
		private TextField anyAddressField;
		private CountryComboBox countryField;
		private LeadSourceListSelect sourceField;

		private TextField anyPhoneField;
		private TextField cityField;
		private TextField stateField;
		private UserListSelect userField;

		public LeadAdvancedSearchLayout() {
			super(LeadSearchPanel.this, CrmTypeConstants.LEAD);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 4,
			"90px");
			
			if (ScreenSize.hasSupport1024Pixels()) {
				gridLayout = new GridFormLayoutHelper(3, 4, UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
				"90px");
			} else if (ScreenSize.hasSupport1280Pixels()) {
				gridLayout = new GridFormLayoutHelper(3, 4,
				"90px");
			}

			firstnameField = (TextField) gridLayout.addComponent(
					new TextField(), "First Name", 0, 0);
			lastnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Last Name", 0, 1);
			accountnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Account Name", 0, 2);
			statusField = (LeadStatusListSelect) gridLayout.addComponent(
					new LeadStatusListSelect(), "Status", 0, 3);

			anyEmailField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Email", 1, 0);
			anyAddressField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Address", 1, 1);
			countryField = (CountryComboBox) gridLayout.addComponent(
					new CountryComboBox(), "Country", 1, 2);
			sourceField = (LeadSourceListSelect) gridLayout.addComponent(
					new LeadSourceListSelect(), "Source", 1, 3);

			anyPhoneField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Phone", 2, 0);
			cityField = (TextField) gridLayout.addComponent(new TextField(),
					"City", 2, 1);
			stateField = (TextField) gridLayout.addComponent(new TextField(),
					"State", 2, 2);
			userField = (UserListSelect) gridLayout.addComponent(
					new UserListSelect(), "Assigned User", 2, 3);
			
			gridLayout.getLayout().setSpacing(true);
			return gridLayout.getLayout();
		}

		@Override
		protected LeadSearchCriteria fillupSearchCriteria() {
			searchCriteria = new LeadSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(
					SearchField.AND, AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty((String) firstnameField
					.getValue())) {
				searchCriteria.setFirstname(new StringSearchField(
						SearchField.AND, ((String) firstnameField
								.getValue()).trim()));
			}

			if (StringUtil.isNotNullOrEmpty((String) lastnameField
					.getValue())) {
				searchCriteria.setLastname(new StringSearchField(
						SearchField.AND, ((String) lastnameField
								.getValue()).trim()));
			}

			if (StringUtil.isNotNullOrEmpty((String) accountnameField
					.getValue())) {
				searchCriteria.setAccountName(new StringSearchField(
						SearchField.AND, ((String) accountnameField
								.getValue()).trim()));
			}

			Collection<String> statuses = (Collection<String>) statusField
					.getValue();
			if (statuses != null && statuses.size() > 0) {
				searchCriteria.setStatuses(new SetSearchField<String>(
						SearchField.AND, statuses));
			}

			if (StringUtil.isNotNullOrEmpty((String) anyEmailField
					.getValue())) {
				searchCriteria.setAnyEmail(new StringSearchField(
						SearchField.AND, (String) anyEmailField
								.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) anyAddressField
					.getValue())) {
				searchCriteria.setAnyAddress(new StringSearchField(
						SearchField.AND, (String) anyAddressField
								.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) countryField
					.getValue())) {
				searchCriteria.setAnyCountry(new StringSearchField(
						SearchField.AND, (String) countryField
								.getValue()));
			}

			Collection<String> sources = (Collection<String>) sourceField
					.getValue();
			if (sources != null && sources.size() > 0) {
				searchCriteria.setSources(new SetSearchField<String>(
						SearchField.AND, sources));
			}

			if (StringUtil.isNotNullOrEmpty((String) anyPhoneField
					.getValue())) {
				searchCriteria.setAnyPhone(new StringSearchField(
						SearchField.AND, (String) anyPhoneField
								.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) cityField
					.getValue())) {
				searchCriteria.setAnyCity(new StringSearchField(
						SearchField.AND, (String) cityField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) stateField
					.getValue())) {
				searchCriteria.setAnyState(new StringSearchField(
						SearchField.AND, (String) stateField.getValue()));
			}

			Collection<String> users = (Collection<String>) userField
					.getValue();
			if (users != null && users.size() > 0) {
				searchCriteria
						.setAssignUsers(new SetSearchField<String>(
								SetSearchField.AND, users));
			}

			return searchCriteria;
		}

		@Override
		protected void clearFields() {
			firstnameField.setValue("");
			lastnameField.setValue("");
			accountnameField.setValue("");
			statusField.setValue(null);

			anyEmailField.setValue("");
			anyAddressField.setValue("");
			countryField.setValue(null);
			sourceField.setValue(null);

			anyPhoneField.setValue("");
			cityField.setValue("");
			stateField.setValue("");
			userField.setValue(null);
		}

		@Override
		public void loadSaveSearchToField(String value) {
			// TODO Auto-generated method stub
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BasicSearchLayout<LeadSearchCriteria> createBasicSearchLayout() {
		return new LeadBasicSearchLayout();
	}
	@Override
	protected AdvancedSearchLayout<LeadSearchCriteria> createAdvancedSearchLayout() {
		return new LeadAdvancedSearchLayout();
	}
}
