package com.esofthead.mycollab.module.crm.view.cases;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
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
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CaseListViewImpl extends AbstractView implements CaseListView {

	private static final long serialVersionUID = 1L;
	private final CaseSearchPanel searchPanel;
	private SelectionOptionButton selectOptionButton;
	private CaseTableDisplay tableItem;
	private final VerticalLayout listLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public CaseListViewImpl() {
		this.setSpacing(true);

		searchPanel = new CaseSearchPanel();
		this.addComponent(searchPanel);

		listLayout = new VerticalLayout();
		listLayout.setSpacing(true);
		this.addComponent(listLayout);

		generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {

		if (ScreenSize.hasSupport1024Pixels()) {
			tableItem = new CaseTableDisplay(
					new String[] { "selected", "subject", "accountName",
							"priority", "status", "assignUserFullName" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ACCOUNT_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_PRIORITY_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			tableItem = new CaseTableDisplay(
					new String[] { "selected", "subject", "accountName",
							"priority", "status", "assignUserFullName",
							"createdtime" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ACCOUNT_NAME_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_PRIORITY_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_ASSIGNED_USER_HEADER),
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_CREATED_DATE_HEADER) });
		}

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleCase cases = (SimpleCase) event.getData();
						if ("subject".equals(event.getFieldName())) {
							EventBus.getInstance()
									.fireEvent(
											new CaseEvent.GotoRead(this, cases
													.getId()));
						} else if ("accountName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(this, cases
											.getAccountid()));
						}
					}
				});

		listLayout.addComponent(constructTableActionControls());
		listLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<CaseSearchCriteria> getSearchHandlers() {
		return searchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		Button deleteBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_ACCOUNT));

		tableActionControls = new PopupButtonControl("delete", deleteBtn);
		tableActionControls.addOptionItem("mail",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_MAIL));
		tableActionControls.addOptionItem("export",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT));
		tableActionControls.setVisible(false);

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setVisible(true);
		selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setVisible(false);
		selectOptionButton.setSelectedChecbox(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleCase> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<CaseSearchCriteria, SimpleCase> getPagedBeanTable() {
		return tableItem;
	}
}
