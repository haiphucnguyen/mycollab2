package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceListSelect;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.CountryListSelect;
import com.esofthead.mycollab.vaadin.ui.DefaultAdvancedSearchLayout;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
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
public class ContactSearchPanel extends
	DefaultGenericSearchPanel<ContactSearchCriteria> {

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Contacts");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ContactEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CONTACT));

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class ContactBasicSearchLayout extends BasicSearchLayout {

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		@SuppressWarnings("unchecked")
		public ContactBasicSearchLayout() {
			super(ContactSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(false);
			//layout.addComponent(new Label("Name"));
			nameField = this.createSeachSupportTextField(new TextField(), "NameFieldOfBasicSearch");
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, nameField, Alignment.MIDDLE_CENTER);
			
			Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(new ThemeResource("icons/16/search_white.png"));
			
			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					ContactBasicSearchLayout.this.callSearchAction();
				}
			});
			
			UiUtils.addComponent(layout, searchBtn, Alignment.MIDDLE_LEFT);
			
			myItemCheckbox = new CheckBox(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.SEARCH_MYITEMS_CHECKBOX));
			myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(layout, myItemCheckbox,
					Alignment.MIDDLE_CENTER);
			
			//UiUtils.addComponent(layout, myItemCheckbox,
			//		Alignment.MIDDLE_CENTER);

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

			Button advancedSearchBtn = new Button(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.BUTTON_ADVANCED_SEARCH),
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
			final ContactSearchCriteria searchCriteria = new ContactSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(
					SearchField.AND, AppContext.getAccountId()));
			if (StringUtil.isNotNullOrEmpty(nameField.getValue()
					.toString().trim())) {
				searchCriteria.setContactName(new StringSearchField(
						SearchField.AND, nameField.getValue()
								.toString().trim()));
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

	private class ContactAdvancedSearchLayout extends DefaultAdvancedSearchLayout<ContactSearchCriteria> {

		private static final long serialVersionUID = 1L;
		private TextField firstnameField;
		private TextField lastnameField;
		private TextField accountnameField;
		private UserListSelect assignUserField;
		private TextField anyEmailField;
		private TextField anyAddressField;
		private TextField stateField;
		private CountryListSelect countryField;
		private TextField anyPhoneField;
		private TextField postalCodeField;
		private TextField cityField;
		private LeadSourceListSelect leadSourceField;

		public ContactAdvancedSearchLayout() {
			super(ContactSearchPanel.this, CrmTypeConstants.CONTACT);
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

			firstnameField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(),"firstnameField")
					, "First Name", 0, 0);
			lastnameField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(), "lastnameField"),
					"Last Name", 0, 1);
			accountnameField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(), "accountnameField")
					, "Account Name", 0, 2);
			assignUserField = (UserListSelect) gridLayout.addComponent(createSeachSupportComboBox(new UserListSelect())
					, "Assign User", 0, 3);

			anyEmailField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(), "anyEmailField")
					, "Any Email", 1, 0);
			anyAddressField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(),"anyAddressField")
					, "Any Address", 1, 1);
			stateField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(),"stateField")
					,"State", 1, 2);
			countryField = (CountryListSelect) gridLayout.addComponent(createSeachSupportComboBox(new CountryListSelect())
					, "Country", 1, 3);

			anyPhoneField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(), "anyPhoneField")
					, "Any Phone", 2, 0);
			cityField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(),"cityField"),
					"City", 2, 1);
			postalCodeField = (TextField) gridLayout.addComponent(createSeachSupportTextField(new TextField(), "postalCodeField")
					, "Postal Code", 2, 2);
			leadSourceField = (LeadSourceListSelect) gridLayout.addComponent(createSeachSupportComboBox(new LeadSourceListSelect())
					, "Lead Source", 2, 3);

			gridLayout.getLayout().setSpacing(true);
			
			return gridLayout.getLayout();
		}
		@Override
		protected ContactSearchCriteria fillupSearchCriteria() {
			final ContactSearchCriteria searchCriteria = new ContactSearchCriteria();
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

			Collection<String> assignUsers = (Collection<String>) assignUserField
					.getValue();
			if (assignUsers != null && assignUsers.size() > 0) {
				searchCriteria
						.setAssignUsers(new SetSearchField<String>(
								SearchField.AND, assignUsers));
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

			if (StringUtil.isNotNullOrEmpty((String) stateField
					.getValue())) {
				searchCriteria.setAnyState(new StringSearchField(
						SearchField.AND, (String) stateField.getValue()));
			}

			Collection<String> countries = (Collection<String>) countryField
					.getValue();
			if (countries != null && countries.size() > 0) {
				searchCriteria.setCountries(new SetSearchField<String>(
						SearchField.AND, countries));
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

			if (StringUtil.isNotNullOrEmpty((String) postalCodeField
					.getValue())) {
				searchCriteria.setAnyPostalCode(new StringSearchField(
						SearchField.AND, (String) postalCodeField
								.getValue()));
			}

			Collection<String> leadSources = (Collection<String>) leadSourceField
					.getValue();
			if (leadSources != null && leadSources.size() > 0) {
				searchCriteria
						.setLeadSources(new SetSearchField<String>(
								SearchField.AND, leadSources));
			}
			return searchCriteria;
		}

		@Override
		protected void clearFields() {
			firstnameField.setValue("");
			lastnameField.setValue("");
			accountnameField.setValue("");
			assignUserField.setValue(null);
			anyEmailField.setValue("");
			anyAddressField.setValue("");
			stateField.setValue("");
			countryField.setValue(null);
			anyPhoneField.setValue("");
			postalCodeField.setValue("");
			cityField.setValue("");
			leadSourceField.setValue(null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BasicSearchLayout<ContactSearchCriteria> createBasicSearchLayout() {
		return new ContactBasicSearchLayout();
	}

	@Override
	protected AdvancedSearchLayout<ContactSearchCriteria> createAdvancedSearchLayout() {
		return new ContactAdvancedSearchLayout();
	}
}
