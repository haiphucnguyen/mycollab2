package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
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
public class CampaignSearchPanel extends
		GenericSearchPanel<CampaignSearchCriteria> {

	protected CampaignSearchCriteria searchCriteria;

	public CampaignSearchPanel() {
		searchCriteria = new CampaignSearchCriteria();
	}

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		CampaignBasicSearchLayout layout = new CampaignBasicSearchLayout();
		this.setCompositionRoot(layout);
	}

	private void createAdvancedSearchLayout() {
		CampaignAdvancedSearchLayout layout = new CampaignAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Campaigns");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new CampaignEvent.GotoAdd(this, null));

					}
				});
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext.canWrite(RolePermissionCollections.CRM_CAMPAIGN));
		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class CampaignBasicSearchLayout extends BasicSearchLayout {

		private TextField nameField;
		private CheckBox myItemCheckbox;

		public CampaignBasicSearchLayout() {
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
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new CampaignSearchCriteria();
					searchCriteria.setSaccountid(new NumberSearchField(
							SearchField.AND, AppContext.getAccountId()));

					if (StringUtil.isNotNullOrEmpty(nameField.getValue()
							.toString().trim())) {
						searchCriteria.setCampaignName(new StringSearchField(
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

					CampaignSearchPanel.this
							.notifySearchHandler(searchCriteria);
				}
			});
			layout.addComponent(searchBtn);

			Button cancelBtn = new Button("Clear");
			cancelBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			});
			layout.addComponent(cancelBtn);

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							CampaignSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(layout, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return layout;
		}
	}

	private class CampaignAdvancedSearchLayout extends AdvancedSearchLayout {

		private TextField nameField;
		private DateSelectionField startDateField;
		private DateSelectionField endDateField;
		private CampaignTypeListSelect typeField;
		private CampaignStatusListSelect statusField;
		private UserListSelect assignUserField;

		public CampaignAdvancedSearchLayout() {
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
					searchCriteria = new CampaignSearchCriteria();
					searchCriteria.setSaccountid(new NumberSearchField(
							SearchField.AND, AppContext.getAccountId()));

					if (StringUtil.isNotNullOrEmpty((String) nameField
							.getValue())) {
						searchCriteria.setCampaignName(new StringSearchField(
								SearchField.AND,
								((String) nameField.getValue()).trim()));
					}

					SearchField startDate = startDateField.getValue();
					if (startDate != null
							&& (startDate instanceof DateSearchField)) {
						searchCriteria
								.setStartDate((DateSearchField) startDate);
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
						searchCriteria
								.setAssignUsers(new SetSearchField<String>(
										SearchField.AND, assignUsers));
					}

					CampaignSearchPanel.this
							.notifySearchHandler(searchCriteria);

				}
			});

			buttonControls.addComponent(searchBtn);
			searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);

			Button clearBtn = new Button("Clear", new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
					startDateField.setDefaultSelection();
					endDateField.setDefaultSelection();
					typeField.setValue(null);
					statusField.setValue(null);
					assignUserField.setValue(null);
				}
			});
			clearBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			buttonControls.addComponent(clearBtn);

			Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							CampaignSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);
			return buttonControls;
		}
	}
}
