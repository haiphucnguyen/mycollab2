package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultAdvancedSearchLayout;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class LeadSearchPanel extends
		DefaultGenericSearchPanel<LeadSearchCriteria> {
	private static final long serialVersionUID = 1L;

	protected LeadSearchCriteria searchCriteria;

	public LeadSearchPanel() {
		this.searchCriteria = new LeadSearchCriteria();
	}

	private HorizontalLayout createSearchTopPanel() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final Embedded titleIcon = new Embedded();
		titleIcon.setSource(MyCollabResource
				.newResource("icons/22/crm/lead.png"));
		layout.addComponent(titleIcon);
		layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

		final Label searchtitle = new Label("Leads");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setExpandRatio(searchtitle, 1.0f);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		final Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_LEAD));
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
			return LeadSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(false);

			this.nameField = this.createSeachSupportTextField(new TextField(),
					"nameFieldOfSearch");
			this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(layout, this.nameField,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					LeadBasicSearchLayout.this.callSearchAction();
				}
			});
			UiUtils.addComponent(layout, searchBtn, Alignment.MIDDLE_LEFT);

			this.myItemCheckbox = new CheckBox(
					LocalizationHelper
							.getMessage(GenericI18Enum.SEARCH_MYITEMS_CHECKBOX));
			this.myItemCheckbox.setWidth("75px");
			UiUtils.addComponent(layout, this.myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			final Separator separator1 = new Separator();
			UiUtils.addComponent(layout, separator1, Alignment.MIDDLE_LEFT);

			final Button cancelBtn = new Button(
					LocalizationHelper.getMessage(GenericI18Enum.BUTTON_CLEAR));
			cancelBtn.setStyleName(UIConstants.THEME_LINK);
			cancelBtn.addStyleName("cancel-button");
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(final ClickEvent event) {
					LeadBasicSearchLayout.this.nameField.setValue("");
				}
			});
			UiUtils.addComponent(layout, cancelBtn, Alignment.MIDDLE_CENTER);

			final Separator separator2 = new Separator();
			UiUtils.addComponent(layout, separator2, Alignment.MIDDLE_LEFT);

			final Button advancedSearchBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
					new Button.ClickListener() {

						@Override
						public void buttonClick(final ClickEvent event) {
							LeadSearchPanel.this.moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(layout, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return layout;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			LeadSearchPanel.this.searchCriteria = new LeadSearchCriteria();
			LeadSearchPanel.this.searchCriteria
					.setSaccountid(new NumberSearchField(SearchField.AND,
							AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty(this.nameField.getValue()
					.toString().trim())) {
				LeadSearchPanel.this.searchCriteria
						.setLeadName(new StringSearchField(SearchField.AND,
								(String) this.nameField.getValue()));
			}

			if (this.myItemCheckbox.booleanValue()) {
				LeadSearchPanel.this.searchCriteria
						.setAssignUsers(new SetSearchField<String>(
								SearchField.AND, new String[] { AppContext
										.getUsername() }));
			} else {
				LeadSearchPanel.this.searchCriteria.setAssignUsers(null);
			}
			return LeadSearchPanel.this.searchCriteria;
		}
	}

	private class LeadAdvancedSearchLayout extends
			DefaultAdvancedSearchLayout<LeadSearchCriteria> {

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
		private ActiveUserListSelect userField;

		public LeadAdvancedSearchLayout() {
			super(LeadSearchPanel.this, CrmTypeConstants.LEAD);
		}

		@Override
		public ComponentContainer constructHeader() {
			return LeadSearchPanel.this.createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 4,
					"100%", "90px");
			gridLayout.getLayout().setWidth("100%");
			gridLayout.getLayout().setMargin(true, true, true, false);

			// if (ScreenSize.hasSupport1024Pixels()) {
			// gridLayout = new GridFormLayoutHelper(3, 4,
			// UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
			// "90px");
			// } else if (ScreenSize.hasSupport1280Pixels()) {
			// gridLayout = new GridFormLayoutHelper(3, 4, "90px");
			// }

			this.firstnameField = (TextField) gridLayout.addComponent(
					new TextField(), "First Name", 0, 0);
			this.lastnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Last Name", 0, 1);
			this.accountnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Account Name", 0, 2);
			this.statusField = (LeadStatusListSelect) gridLayout.addComponent(
					new LeadStatusListSelect(), "Status", 0, 3);

			this.anyEmailField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Email", 1, 0);
			this.anyAddressField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Address", 1, 1);
			this.countryField = (CountryComboBox) gridLayout.addComponent(
					new CountryComboBox(), "Country", 1, 2);
			this.sourceField = (LeadSourceListSelect) gridLayout.addComponent(
					new LeadSourceListSelect(), "Source", 1, 3);

			this.anyPhoneField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Phone", 2, 0);
			this.cityField = (TextField) gridLayout.addComponent(
					new TextField(), "City", 2, 1);
			this.stateField = (TextField) gridLayout.addComponent(
					new TextField(), "State", 2, 2);
			this.userField = (ActiveUserListSelect) gridLayout.addComponent(
					new ActiveUserListSelect(), LocalizationHelper
							.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 2,
					3);

			gridLayout.getLayout().setSpacing(true);
			return gridLayout.getLayout();
		}

		@Override
		protected LeadSearchCriteria fillupSearchCriteria() {
			LeadSearchPanel.this.searchCriteria = new LeadSearchCriteria();
			LeadSearchPanel.this.searchCriteria
					.setSaccountid(new NumberSearchField(SearchField.AND,
							AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty((String) this.firstnameField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setFirstname(new StringSearchField(SearchField.AND,
								((String) this.firstnameField.getValue())
										.trim()));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.lastnameField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setLastname(new StringSearchField(SearchField.AND,
								((String) this.lastnameField.getValue()).trim()));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.accountnameField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAccountName(new StringSearchField(SearchField.AND,
								((String) this.accountnameField.getValue())
										.trim()));
			}

			final Collection<String> statuses = (Collection<String>) this.statusField
					.getValue();
			if (statuses != null && statuses.size() > 0) {
				LeadSearchPanel.this.searchCriteria
						.setStatuses(new SetSearchField<String>(
								SearchField.AND, statuses));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.anyEmailField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAnyEmail(new StringSearchField(SearchField.AND,
								(String) this.anyEmailField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.anyAddressField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAnyAddress(new StringSearchField(SearchField.AND,
								(String) this.anyAddressField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.countryField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAnyCountry(new StringSearchField(SearchField.AND,
								(String) this.countryField.getValue()));
			}

			final Collection<String> sources = (Collection<String>) this.sourceField
					.getValue();
			if (sources != null && sources.size() > 0) {
				LeadSearchPanel.this.searchCriteria
						.setSources(new SetSearchField<String>(SearchField.AND,
								sources));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.anyPhoneField
					.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAnyPhone(new StringSearchField(SearchField.AND,
								(String) this.anyPhoneField.getValue()));
			}

			if (StringUtil.isNotNullOrEmpty((String) this.cityField.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAnyCity(new StringSearchField(SearchField.AND,
								(String) this.cityField.getValue()));
			}

			if (StringUtil
					.isNotNullOrEmpty((String) this.stateField.getValue())) {
				LeadSearchPanel.this.searchCriteria
						.setAnyState(new StringSearchField(SearchField.AND,
								(String) this.stateField.getValue()));
			}

			final Collection<String> users = (Collection<String>) this.userField
					.getValue();
			if (users != null && users.size() > 0) {
				LeadSearchPanel.this.searchCriteria
						.setAssignUsers(new SetSearchField<String>(
								SearchField.AND, users));
			}

			return LeadSearchPanel.this.searchCriteria;
		}

		@Override
		protected void clearFields() {
			this.firstnameField.setValue("");
			this.lastnameField.setValue("");
			this.accountnameField.setValue("");
			this.statusField.setValue(null);

			this.anyEmailField.setValue("");
			this.anyAddressField.setValue("");
			this.countryField.setValue(null);
			this.sourceField.setValue(null);

			this.anyPhoneField.setValue("");
			this.cityField.setValue("");
			this.stateField.setValue("");
			this.userField.setValue(null);
		}

		@Override
		protected void loadSaveSearchToField(final LeadSearchCriteria value) {
			if (value.getFirstname() != null) {
				this.firstnameField.setValue(value.getFirstname().getValue());
			}
			if (value.getLastname() != null) {
				this.lastnameField.setValue(value.getLastname().getValue());
			}
			if (value.getAccountName() != null) {
				this.accountnameField.setValue(value.getAccountName()
						.getValue());
			}
			if (value.getAnyEmail() != null) {
				this.anyEmailField.setValue(value.getAnyEmail().getValue());
			}
			if (value.getAnyAddress() != null) {
				this.anyAddressField.setValue(value.getAnyAddress().getValue());
			}

			if (value.getAnyCountry() != null) {
				this.countryField.setValue(value.getAnyCountry().getValue());
			}
			if (value.getAnyState() != null) {
				this.stateField.setValue(value.getAnyState().getValue());
			}

			if (value.getSources() != null) {
				this.sourceField.setValue(Arrays.asList((Object[]) value
						.getSources().values));
			}

			if (value.getAnyPhone() != null) {
				this.anyPhoneField.setValue(value.getAnyPhone().getValue());
			}
			if (value.getAnyCity() != null) {
				this.cityField.setValue(value.getAnyCity().getValue());
			}

			if (value.getStatuses() != null) {
				this.statusField.setValue(Arrays.asList((Object[]) value
						.getStatuses().values));
			}

			if (value.getAssignUsers() != null) {
				this.userField.setValue(Arrays.asList((Object[]) value
						.getAssignUsers().values));
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BasicSearchLayout<LeadSearchCriteria> createBasicSearchLayout() {
		return new LeadBasicSearchLayout();
	}

	@Override
	protected SearchLayout<LeadSearchCriteria> createAdvancedSearchLayout() {
		return new LeadAdvancedSearchLayout();
	}
}
