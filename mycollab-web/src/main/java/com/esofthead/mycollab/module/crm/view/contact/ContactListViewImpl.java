package com.esofthead.mycollab.module.crm.view.contact;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ContactListViewImpl extends AbstractView implements
		ContactListView {

	private static final long serialVersionUID = 1L;
	private final ContactSearchPanel contactSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private ContactTableDisplay tableItem;
	private final VerticalLayout contactListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();
	private ContactImportWindow contactImportWindow;

	public ContactListViewImpl() {

		this.contactSearchPanel = new ContactSearchPanel();
		this.addComponent(this.contactSearchPanel);

		this.contactListLayout = new VerticalLayout();
		this.addComponent(this.contactListLayout);

		this.generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {

		this.tableItem = new ContactTableDisplay(ContactListView.VIEW_DEF_ID,
				ContactTableFieldDef.selected, Arrays.asList(
						ContactTableFieldDef.name, ContactTableFieldDef.title,
						ContactTableFieldDef.account,
						ContactTableFieldDef.email,
						ContactTableFieldDef.phoneOffice));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleContact contact = (SimpleContact) event
								.getData();
						if ("contactName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new ContactEvent.GotoRead(
											ContactListViewImpl.this, contact
													.getId()));
						} else if ("accountName".equals(event.getFieldName())) {
							EventBus.getInstance().fireEvent(
									new AccountEvent.GotoRead(
											ContactListViewImpl.this, contact
													.getAccountid()));
						}
					}
				});

		this.contactListLayout
				.addComponent(this.constructTableActionControls());
		this.contactListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<ContactSearchCriteria> getSearchHandlers() {
		return this.contactSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_CONTACT));

		this.tableActionControls = new PopupButtonControl(
				PopupActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(PopupActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_MAIL));
		this.tableActionControls
				.addOptionItem(PopupActionHandler.EXPORT_CSV_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));
		this.tableActionControls
				.addOptionItem(PopupActionHandler.EXPORT_PDF_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));

		this.tableActionControls
				.addOptionItem(
						"massUpdate",
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_MASSUPDATE),
						AppContext
								.canWrite(RolePermissionCollections.CRM_CONTACT));

		this.tableActionControls.setVisible(false);

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setExpandRatio(this.selectedItemsNumberLabel, 1.0f);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setSpacing(true);
		layout.addComponent(buttonControls);

		Button customizeViewBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().addWindow(
						new ContactListCustomizeWindow(
								ContactListView.VIEW_DEF_ID, tableItem));

			}
		});
		customizeViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/customize.png"));
		customizeViewBtn.setDescription("Layout Options");
		customizeViewBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		buttonControls.addComponent(customizeViewBtn);

		Button importBtn = new Button("", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				contactImportWindow = new ContactImportWindow();
				getWindow().addWindow(contactImportWindow);
			}
		});
		importBtn.setDescription("Import");
		importBtn.setIcon(MyCollabResource.newResource("icons/16/import.png"));
		importBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		importBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CONTACT));

		buttonControls.addComponent(importBtn);

		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleContact> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<ContactSearchCriteria, SimpleContact> getPagedBeanTable() {
		return this.tableItem;
	}
}
