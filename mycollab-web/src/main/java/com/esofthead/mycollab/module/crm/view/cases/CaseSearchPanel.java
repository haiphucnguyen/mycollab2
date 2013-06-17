package com.esofthead.mycollab.module.crm.view.cases;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
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

public class CaseSearchPanel extends
		DefaultGenericSearchPanel<CaseSearchCriteria> {

	private static final long serialVersionUID = 1L;
	private CaseSearchCriteria searchCriteria;

	public CaseSearchPanel() {
		super();
	}

	private class CaseAdvancedSearchLayout extends
			DefaultAdvancedSearchLayout<CaseSearchCriteria> {

		public CaseAdvancedSearchLayout() {
			super(CaseSearchPanel.this, CrmTypeConstants.CASE);
		}

		private static final long serialVersionUID = 1L;
		private TextField numberField;
		private TextField subjectField;
		private AccountSelectionField accountField;
		private CaseStatusListSelect statusField;
		private UserListSelect userField;
		private CasePriorityListSelect priorityField;

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

			numberField = (TextField) gridLayout.addComponent(new TextField(),
					"Number", 0, 0);
			subjectField = (TextField) gridLayout.addComponent(new TextField(),
					"Subject", 1, 0);
			accountField = (AccountSelectionField) gridLayout.addComponent(
					new AccountSelectionField(), "Account", 2, 0);

			statusField = (CaseStatusListSelect) gridLayout.addComponent(
					new CaseStatusListSelect(), "Status", 0, 1);
			userField = (UserListSelect) gridLayout.addComponent(
					new UserListSelect(), "Assigned to", 1, 1);
			priorityField = (CasePriorityListSelect) gridLayout.addComponent(
					new CasePriorityListSelect(), "Priority", 2, 1);

			gridLayout.getLayout().setSpacing(true);
			return gridLayout.getLayout();
		}

		@Override
		protected CaseSearchCriteria fillupSearchCriteria() {
			searchCriteria = new CaseSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty((String) subjectField.getValue())) {
				searchCriteria.setSubject(new StringSearchField(
						SearchField.AND, ((String) subjectField.getValue())
								.trim()));
			}

			SimpleAccount account = accountField.getAccount();
			if (StringUtil.isNotNullOrEmpty(account.getAccountname())) {
				searchCriteria.setAccountName(new StringSearchField(
						SearchField.AND, account.getAccountname()));
			}

			Collection<String> statuses = (Collection<String>) statusField
					.getValue();
			if (statuses != null && statuses.size() > 0) {
				searchCriteria.setStatuses(new SetSearchField<String>(
						SearchField.AND, statuses));
			}

			Collection<String> assignUsers = (Collection<String>) userField
					.getValue();
			if (assignUsers != null && assignUsers.size() > 0) {
				searchCriteria.setAssignUsers(new SetSearchField<String>(
						SearchField.AND, assignUsers));
			}

			Collection<String> priorities = (Collection<String>) priorityField
					.getValue();
			if (priorities != null && priorities.size() > 0) {
				searchCriteria.setPriorities(new SetSearchField<String>(
						SearchField.AND, priorities));
			}
			return searchCriteria;
		}

		@Override
		protected void clearFields() {
			numberField.setValue("");
			subjectField.setValue("");
			accountField.clearValue();
			statusField.setValue(null);
			userField.setValue(null);
			priorityField.setValue(null);
		}

		@Override
		public void loadSaveSearchToField(String value) {
			// TODO Auto-generated method stub
			
		}
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Cases");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);
		layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		createAccountBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CASE));
		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	@SuppressWarnings("rawtypes")
	private class CaseBasicSearchLayout extends BasicSearchLayout {

		private static final long serialVersionUID = 1L;
		private TextField subjectField;
		private CheckBox myItemCheckbox;

		@SuppressWarnings("unchecked")
		public CaseBasicSearchLayout() {
			super(CaseSearchPanel.this);
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@SuppressWarnings("serial")
		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(false);

			subjectField = this.createSeachSupportTextField(new TextField(),
					"subjectFieldName");
			subjectField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, subjectField,
					Alignment.MIDDLE_CENTER);
			subjectField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, subjectField,
					Alignment.MIDDLE_CENTER);

			final Button searchBtn = new Button();
			searchBtn.setStyleName("search-icon-button");
			searchBtn.setIcon(MyCollabResource
					.newResource("icons/16/search_white.png"));

			searchBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					CaseBasicSearchLayout.this.callSearchAction();
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
			cancelBtn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					subjectField.setValue("");
				}
			});
			UiUtils.addComponent(basicSearchBody, cancelBtn,
					Alignment.MIDDLE_CENTER);
			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							moveToAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}

		@Override
		protected SearchCriteria fillupSearchCriteria() {
			searchCriteria = new CaseSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));

			if (StringUtil.isNotNullOrEmpty(subjectField.getValue().toString()
					.trim())) {
				searchCriteria.setSubject(new StringSearchField(
						SearchField.AND, ((String) subjectField.getValue())
								.trim()));
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

	@SuppressWarnings("unchecked")
	@Override
	protected BasicSearchLayout<CaseSearchCriteria> createBasicSearchLayout() {
		return new CaseBasicSearchLayout();
	}

	@Override
	protected SearchLayout<CaseSearchCriteria> createAdvancedSearchLayout() {
		return new CaseAdvancedSearchLayout();
	}
}
