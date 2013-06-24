package com.esofthead.mycollab.module.crm.view.account;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class AccountListViewImpl extends AbstractView implements
		AccountListView {

	private static final long serialVersionUID = 1L;
	private final AccountSearchPanel accountSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private AccountTableDisplay tableItem;
	private final VerticalLayout accountListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public AccountListViewImpl() {
		// setSpacing(true);

		this.accountSearchPanel = new AccountSearchPanel();
		this.addComponent(this.accountSearchPanel);

		this.accountListLayout = new VerticalLayout();
		// accountListLayout.setSpacing(true);
		this.addComponent(this.accountListLayout);

		this.generateDisplayTable();
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		this.selectOptionButton.setSizeUndefined();
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_ACCOUNT));

		this.tableActionControls = new PopupButtonControl("delete", deleteBtn);
		this.tableActionControls.addOptionItem("mail",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_MAIL));
		this.tableActionControls.addOptionItem("export",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT));
		this.tableActionControls.addOptionItem("massUpdate", LocalizationHelper
				.getMessage(CrmCommonI18nEnum.BUTTON_MASSUPDATE));
		this.tableActionControls.setVisible(false);

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);

		layout.setExpandRatio(this.selectedItemsNumberLabel, 1.0f);
		return layoutWrapper;
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	private void generateDisplayTable() {
		if (ScreenSize.hasSupport1024Pixels()) {
			this.tableItem = new AccountTableDisplay(
					new String[] { "selected", "accountname", "phoneoffice",
							"email", "assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			this.tableItem = new AccountTableDisplay(
					new String[] { "selected", "accountname", "city",
							"phoneoffice", "email", "assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_CITY_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_OFFICE_PHONE_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_EMAIL_ADDRESS_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER) });
		}

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleAccount account = (SimpleAccount) event
								.getData();
						if ("accountname".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(
											AccountListViewImpl.this, account
													.getId()));
						}
					}
				});

		this.accountListLayout
				.addComponent(this.constructTableActionControls());
		this.accountListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public IPagedBeanTable<AccountSearchCriteria, SimpleAccount> getPagedBeanTable() {
		return this.tableItem;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSearchHandlers<AccountSearchCriteria> getSearchHandlers() {
		return this.accountSearchPanel;
	}

	@Override
	public HasSelectableItemHandlers<SimpleAccount> getSelectableItemHandlers() {
		return this.tableItem;
	}
}
