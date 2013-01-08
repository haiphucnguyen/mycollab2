package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.view.account.AccountSearchPanel;
import com.esofthead.mycollab.module.crm.view.lead.LeadSourceListSelect;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.CountryListSelect;
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
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;
import java.util.Collection;

@SuppressWarnings("serial")
public class ContactSearchPanel extends
		GenericSearchPanel<ContactSearchCriteria> {

	protected ContactSearchCriteria searchCriteria;

	@Override
	public void attach() {
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		ContactBasicSearchLayout layout = new ContactBasicSearchLayout();
		this.setCompositionRoot(layout);
	}

	private void createAdvancedSearchLayout() {
		ContactAdvancedSearchLayout layout = new ContactAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Contacts");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

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
		createAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class ContactBasicSearchLayout extends BasicSearchLayout {

		private static final long serialVersionUID = 1L;
		private TextField nameField;
		private CheckBox myItemCheckbox;

		public ContactBasicSearchLayout() {
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.addComponent(new Label("Name"));
			nameField = new TextField();
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, nameField, Alignment.MIDDLE_CENTER);
			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(layout, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			Button searchBtn = new Button("Search");
			searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new ContactSearchCriteria();
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

					ContactSearchPanel.this.notifySearchHandler(searchCriteria);
				}
			});
			layout.addComponent(searchBtn);

			Button cancelBtn = new Button("Cancel");
			cancelBtn.setStyleName("bluebtn");
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			});
			layout.addComponent(cancelBtn);

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							ContactSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(layout, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return layout;
		}
	}

	private class ContactAdvancedSearchLayout extends AdvancedSearchLayout {

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
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 4);

			firstnameField = (TextField) gridLayout.addComponent(
					new TextField(), "First Name", 0, 0);
			lastnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Last Name", 0, 1);
			accountnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Account Name", 0, 2);
			assignUserField = (UserListSelect) gridLayout.addComponent(
					new UserListSelect(), "Assign User", 0, 3);

			anyEmailField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Email", 1, 0);
			anyAddressField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Address", 1, 1);
			stateField = (TextField) gridLayout.addComponent(new TextField(),
					"State", 1, 2);
			countryField = (CountryListSelect) gridLayout.addComponent(
					new CountryListSelect(), "Country", 1, 3);

			anyPhoneField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Phone", 2, 0);
			cityField = (TextField) gridLayout.addComponent(new TextField(),
					"City", 2, 1);
			postalCodeField = (TextField) gridLayout.addComponent(
					new TextField(), "Postal Code", 2, 2);
			leadSourceField = (LeadSourceListSelect) gridLayout.addComponent(
					new LeadSourceListSelect(), "Lead Source", 2, 3);

			return gridLayout.getLayout();
		}

		@Override
		public ComponentContainer constructFooter() {
			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);

			Button searchBtn = new Button("Search", new Button.ClickListener() {
				@SuppressWarnings({ "unchecked" })
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new ContactSearchCriteria();
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

					ContactSearchPanel.this.notifySearchHandler(searchCriteria);

				}
			});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

			Button clearBtn = new Button("Clear", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
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
			});
			clearBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			buttonControls.addComponent(clearBtn);

			Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							ContactSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);
			return buttonControls;
		}
	}
}
