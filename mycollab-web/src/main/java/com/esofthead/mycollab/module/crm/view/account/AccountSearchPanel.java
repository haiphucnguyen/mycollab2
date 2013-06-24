package com.esofthead.mycollab.module.crm.view.account;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
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
public class AccountSearchPanel extends
		DefaultGenericSearchPanel<AccountSearchCriteria> {

	public class AccountAdvancedSearchLayout extends
			DefaultAdvancedSearchLayout<AccountSearchCriteria> {

		private TextField nameField;
		private TextField websiteField;
		private TextField anyPhoneField;
		private TextField anyMailField;
		private TextField anyAddressField;
		private TextField cityField;
		private AccountIndustryListSelect industryField;
		private AccountTypeListSelect typeField;
		private UserListSelect userField;

		public AccountAdvancedSearchLayout() {
			super(AccountSearchPanel.this, CrmTypeConstants.ACCOUNT);
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

			nameField = (TextField) gridLayout.addComponent(
					createSeachSupportTextField(new TextField(), "nameField"),
					"Name", 0, 0);

			websiteField = (TextField) gridLayout
					.addComponent(
							createSeachSupportTextField(new TextField(),
									"websiteField"), "Website", 1, 0);

			anyPhoneField = (TextField) gridLayout.addComponent(
					createSeachSupportTextField(new TextField(),
							"anyPhoneField"), "Any Phone", 2, 0);

			anyMailField = (TextField) gridLayout
					.addComponent(
							createSeachSupportTextField(new TextField(),
									"anyMailField"), "Any Email", 0, 1);

			anyAddressField = (TextField) gridLayout.addComponent(
					createSeachSupportTextField(new TextField(),
							"anyAddressField"), "Any Address", 1, 1);

			cityField = (TextField) gridLayout.addComponent(
					createSeachSupportTextField(new TextField(), "cityField"),
					"City", 2, 1);

			industryField = (AccountIndustryListSelect) gridLayout
					.addComponent(
							this.createSeachSupportComboBox(new AccountIndustryListSelect()),
							"Industry", 0, 2);

			typeField = (AccountTypeListSelect) gridLayout.addComponent(this
					.createSeachSupportComboBox(new AccountTypeListSelect()),
					"Type", 1, 2);

			userField = (UserListSelect) gridLayout.addComponent(
					this.createSeachSupportComboBox(new UserListSelect()),
					"Assigned User", 2, 2);
			gridLayout.getLayout().setSpacing(true);
			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		protected AccountSearchCriteria fillupSearchCriteria() {
			final AccountSearchCriteria searchCriteria = new AccountSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));
			searchCriteria.setAccountname(new StringSearchField(
					SearchField.AND, (String) nameField.getValue()));

			if (StringUtil.isNotNullOrEmpty((String) nameField.getValue())) {
				searchCriteria.setAccountname(new StringSearchField(
						SearchField.AND, (String) nameField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) websiteField.getValue())) {
				searchCriteria.setWebsite(new StringSearchField(
						SearchField.AND, (String) websiteField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) anyPhoneField.getValue())) {
				searchCriteria.setAnyPhone(new StringSearchField(
						SearchField.AND, (String) anyPhoneField.getValue()));
			}

			if (StringUtil
					.isNotNullOrEmpty((String) anyAddressField.getValue())) {
				searchCriteria.setAnyAddress(new StringSearchField(
						SearchField.AND, (String) anyAddressField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) anyMailField.getValue())) {
				searchCriteria.setAnyMail(new StringSearchField(
						SearchField.AND, (String) anyMailField.getValue()));
			}
			
			if (StringUtil
					.isNotNullOrEmpty((String) cityField.getValue())) {
				searchCriteria.setAnyCity(new StringSearchField(
						SearchField.AND, (String) cityField.getValue()));
			}

			final Collection<String> industries = (Collection<String>) industryField
					.getValue();
			if (industries != null && industries.size() > 0) {
				searchCriteria.setIndustries(new SetSearchField(
						SearchField.AND, industries));
			}

			final Collection<String> types = (Collection<String>) typeField
					.getValue();
			if (types != null && types.size() > 0) {
				searchCriteria.setTypes(new SetSearchField(SearchField.AND,
						types));
			}

			final Collection<String> users = (Collection<String>) userField
					.getValue();
			if (users != null && users.size() > 0) {
				searchCriteria.setAssignUsers(new SetSearchField(
						SearchField.AND, users));
			}

			return searchCriteria;
		}

		@Override
		protected void clearFields() {
			nameField.setValue("");
			websiteField.setValue("");
			anyPhoneField.setValue("");
			anyMailField.setValue("");
			anyAddressField.setValue("");
			cityField.setValue("");
			industryField.setValue(null);
			typeField.setValue(null);
			userField.setValue(null);
		}

		@Override
		protected void loadSaveSearchToField(AccountSearchCriteria value) {
			if (value != null) {
				if (value.getAccountname()!=null){
					nameField.setValue(value.getAccountname().getValue());
				}else nameField.setValue("");
				if (value.getWebsite()!=null) websiteField.setValue(value.getWebsite().getValue());
				else websiteField.setValue("");
				if (value.getAnyPhone()!=null) anyPhoneField.setValue(value.getAnyPhone().getValue());
				else anyPhoneField.setValue("");
				if (value.getAnyMail()!=null) anyMailField.setValue(value.getAnyMail().getValue());
				else anyMailField.setValue("");
				if (value.getAnyAddress()!=null) anyAddressField.setValue(value.getAnyAddress().getValue());
				else anyAddressField.setValue("");
				if (value.getAnyCity()!=null) cityField.setValue(value.getAnyCity().getValue());
				else cityField.setValue("");

				if(value.getIndustries()!=null){
					Object[] userString = value.getIndustries().values;
					industryField.setValue(Arrays.asList(userString));
				}else{
					industryField.setValue(null);
				}
				if(value.getTypes()!=null){
					Object[] typeObj = value.getTypes().values;
					typeField.setValue(Arrays.asList(typeObj));
				}else typeField.setValue(null);
				if(value.getAssignUsers()!=null){
					Object[] userObj = value.getAssignUsers().values;
					userField.setValue(Arrays.asList(userObj));
				}else userField.setValue(null);
			}
		}
	}

	private class AccountBasicSearchLayout extends
			BasicSearchLayout<AccountSearchCriteria> {

		private TextField nameField;
		private CheckBox myItemCheckbox;

		public AccountBasicSearchLayout() {
			super(AccountSearchPanel.this);
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(false);
			// basicSearchBody.addComponent(new Label("Name"));

			nameField = createSeachSupportTextField(new TextField(),
					"NameFieldOfBasicSearch");

			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, nameField,
					Alignment.MIDDLE_CENTER);
			// final Button searchBtn = new Button(
			// LocalizationHelper
			// .getMessage(CrmCommonI18nEnum.BUTTON_SEARCH));

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));
			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					AccountBasicSearchLayout.this.callSearchAction();
				}
			});

			UiUtils.addComponent(basicSearchBody, searchBtn,
					Alignment.MIDDLE_LEFT);

			myItemCheckbox = new CheckBox(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.SEARCH_MYITEMS_CHECKBOX));
			myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(basicSearchBody, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.setWidth("55px");
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					nameField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);

			final Button advancedSearchBtn = new Button(
					LocalizationHelper
							.getMessage(CrmCommonI18nEnum.BUTTON_ADVANCED_SEARCH),
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		protected AccountSearchCriteria fillupSearchCriteria() {
			final AccountSearchCriteria searchCriteria = new AccountSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));
			searchCriteria.setAccountname(new StringSearchField(
					SearchField.AND, ((String) nameField.getValue()).trim()));
			if (myItemCheckbox.booleanValue()) {
				searchCriteria.setAssignUser(new StringSearchField(
						SearchField.AND, AppContext.getUsername()));
			} else {
				searchCriteria.setAssignUsers(null);
			}

			return searchCriteria;
		}
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final Label searchtitle = new Label("Search Accounts");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		final Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_ACCOUNT));

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@Override
	protected BasicSearchLayout<AccountSearchCriteria> createBasicSearchLayout() {
		return new AccountBasicSearchLayout();
	}

	@Override
	protected SearchLayout<AccountSearchCriteria> createAdvancedSearchLayout() {
		return new AccountAdvancedSearchLayout();
	}

}
