package com.esofthead.mycollab.module.crm.view.account;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
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
public class AccountSearchPanel extends
		GenericSearchPanel<AccountSearchCriteria> {

	protected AccountSearchCriteria searchCriteria;

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		AccountBasicSearchLayout layout = new AccountBasicSearchLayout();
		this.setCompositionRoot(layout);
	}

	private void createAdvancedSearchLayout() {
		AccountAdvancedSearchLayout layout = new AccountAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Accounts");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new AccountEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class AccountBasicSearchLayout extends BasicSearchLayout {

		private TextField nameField;
		private CheckBox myItemCheckbox;

		public AccountBasicSearchLayout() {
			super();
		}

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
			myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(basicSearchBody, myItemCheckbox,
					Alignment.MIDDLE_CENTER);
			Button searchBtn = new Button("Search");
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new AccountSearchCriteria();
					searchCriteria.setSaccountid(new NumberSearchField(
							SearchField.AND, AppContext.getAccountId()));
					searchCriteria.setAccountname(new StringSearchField(
							SearchField.AND, ((String) nameField.getValue())
									.trim()));
					if (myItemCheckbox.booleanValue()) {
						searchCriteria.setAssignUser(new StringSearchField(
								SearchField.AND, AppContext.getUsername()));
					} else {
						searchCriteria.setAssignUsers(null);
					}
					AccountSearchPanel.this.notifySearchHandler(searchCriteria);
				}
			});

			basicSearchBody.addComponent(searchBtn);

			Button cancelBtn = new Button("Clear");
			cancelBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			});
			basicSearchBody.addComponent(cancelBtn);

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							AccountSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}
	}

	private class AccountAdvancedSearchLayout extends AdvancedSearchLayout {

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
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 3,
					"90px");
			nameField = (TextField) gridLayout.addComponent(new TextField(),
					"Name", 0, 0);

			websiteField = (TextField) gridLayout.addComponent(new TextField(),
					"Website", 1, 0);

			anyPhoneField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Phone", 2, 0);

			anyMailField = (TextField) gridLayout.addComponent(new TextField(),
					"Any Email", 0, 1);

			anyAddressField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Address", 1, 1);

			cityField = (TextField) gridLayout.addComponent(new TextField(),
					"City", 2, 1);

			industryField = (AccountIndustryListSelect) gridLayout
					.addComponent(new AccountIndustryListSelect(), "Industry",
							0, 2);

			typeField = (AccountTypeListSelect) gridLayout.addComponent(
					new AccountTypeListSelect(), "Type", 1, 2);

			userField = (UserListSelect) gridLayout.addComponent(
					new UserListSelect(), "Assigned User", 2, 2);
			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			Button searchBtn = new Button("Search", new Button.ClickListener() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new AccountSearchCriteria();
					searchCriteria.setSaccountid(new NumberSearchField(
							SearchField.AND, AppContext.getAccountId()));
					searchCriteria.setAccountname(new StringSearchField(
							SearchField.AND, (String) nameField.getValue()));

					if (StringUtil.isNotNullOrEmpty((String) nameField
							.getValue())) {
						searchCriteria.setAccountname(new StringSearchField(
								SearchField.AND, (String) nameField.getValue()));
					}

					if (StringUtil.isNotNullOrEmpty((String) websiteField
							.getValue())) {
						searchCriteria.setWebsite(new StringSearchField(
								SearchField.AND, (String) websiteField
										.getValue()));
					}

					if (StringUtil.isNotNullOrEmpty((String) anyPhoneField
							.getValue())) {
						searchCriteria.setAnyPhone(new StringSearchField(
								SearchField.AND, (String) anyPhoneField
										.getValue()));
					}

					if (StringUtil.isNotNullOrEmpty((String) anyAddressField
							.getValue())) {
						searchCriteria.setAnyAddress(new StringSearchField(
								SearchField.AND, (String) anyAddressField
										.getValue()));
					}

					if (StringUtil.isNotNullOrEmpty((String) anyMailField
							.getValue())) {
						searchCriteria.setAnyMail(new StringSearchField(
								SearchField.AND, (String) anyMailField
										.getValue()));
					}

					Collection<String> industries = (Collection<String>) industryField
							.getValue();
					if (industries != null && industries.size() > 0) {
						searchCriteria.setIndustries(new SetSearchField(
								SearchField.AND, industries));
					}

					Collection<String> types = (Collection<String>) typeField
							.getValue();
					if (types != null && types.size() > 0) {
						searchCriteria.setTypes(new SetSearchField(
								SearchField.AND, types));
					}

					Collection<String> users = (Collection<String>) userField
							.getValue();
					if (users != null && users.size() > 0) {
						searchCriteria.setAssignUsers(new SetSearchField(
								SearchField.AND, users));
					}

					AccountSearchPanel.this.notifySearchHandler(searchCriteria);

				}
			});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			Button clearBtn = new Button("Clear", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
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
			});
			clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(clearBtn);

			Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							AccountSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);
			return buttonControls;
		}
	}
}
