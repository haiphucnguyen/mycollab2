package com.esofthead.mycollab.module.crm.view.contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.localization.WebExceptionI18nEnum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.MassUpdatePresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

public class ContactListPresenter extends CrmGenericPresenter<ContactListView>
		implements ListPresenter<ContactSearchCriteria>,
		MassUpdatePresenter<Contact> {

	private static final long serialVersionUID = 1L;
	private static final String[] EXPORT_VISIBLE_COLUMNS = new String[] {
			"contactName", "title", "accountName", "email", "officephone",
			"assignUserFullName" };
	private static final String[] EXPORT_DISPLAY_NAMES = new String[] { "Name",
			"Title", "Account Name", "Email", "Office Phone", "Assign User" };
	private ContactService contactService;
	private ContactSearchCriteria searchCriteria;
	private boolean isSelectAll = false;

	public ContactListPresenter() {
		super(ContactListView.class);
		contactService = AppContext.getSpringBean(ContactService.class);

		view.getPagedBeanTable().addPagableHandler(new PagableHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void move(int newPageNumber) {
				pageChange();
			}

			@Override
			public void displayItemChange(int numOfItems) {
				pageChange();
			}

			private void pageChange() {
				if (isSelectAll) {
					selectAllItemsInCurrentPage();
				}

				checkWhetherEnableTableActionControl();
			}
		});

		view.getSearchHandlers().addSearchHandler(
				new SearchHandler<ContactSearchCriteria>() {
					@Override
					public void onSearch(ContactSearchCriteria criteria) {
						doSearch(criteria);
					}
				});

		view.getOptionSelectionHandlers().addSelectionOptionHandler(
				new SelectionOptionHandler() {
					@Override
					public void onSelectCurrentPage() {
						isSelectAll = false;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();
					}

					@Override
					public void onDeSelect() {
						Collection<SimpleContact> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleContact item : currentDataList) {
							item.setSelected(false);
							CheckBox checkBox = (CheckBox) item.getExtraData();
							checkBox.setValue(false);
						}

						checkWhetherEnableTableActionControl();

					}

					@Override
					public void onSelectAll() {
						isSelectAll = true;
						selectAllItemsInCurrentPage();

						checkWhetherEnableTableActionControl();
					}
				});

		view.getPopupActionHandlers().addPopupActionHandler(
				new PopupActionHandler() {
					@Override
					public void onSelect(String id, String caption) {
						if ("delete".equals(id)) {
							ConfirmDialogExt.show(
									view.getWindow(),
									LocalizationHelper.getMessage(
											GenericI18Enum.DELETE_DIALOG_TITLE,
											SiteConfiguration.getSiteName()),
									LocalizationHelper
											.getMessage(GenericI18Enum.DELETE_MULTIPLE_ITEMS_DIALOG_MESSAGE),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
									new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void onClose(ConfirmDialog dialog) {
											if (dialog.isConfirmed()) {
												deleteSelectedItems();
											}
										}
									});
						} else if ("mail".equals(id)) {
							if (isSelectAll) {
								AppContext
										.getApplication()
										.getMainWindow()
										.showNotification(
												LocalizationHelper
														.getMessage(WebExceptionI18nEnum.NOT_SUPPORT_SENDING_EMAIL_TO_ALL_USERS));
							} else {
								List<String> lstMail = new ArrayList<String>();
								List<SimpleContact> tableData = view
										.getPagedBeanTable()
										.getCurrentDataList();
								for (SimpleContact item : tableData) {
									if (item.isSelected()) {
										lstMail.add(item.getContactName()
												+ " <" + item.getEmail() + ">");
									}
								}
								view.getWidget().getWindow()
										.addWindow(new MailFormWindow(lstMail));
							}

						} else if ("export".equals(id)) {
							Resource res = null;

							if (isSelectAll) {
								res = new StreamResource(
										new ExportStreamResource.AllItems<ContactSearchCriteria>(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES,
												AppContext
														.getSpringBean(ContactService.class),
												searchCriteria), "export.csv",
										view.getApplication());
							} else {
								List tableData = view.getPagedBeanTable()
										.getCurrentDataList();
								res = new StreamResource(
										new ExportStreamResource.ListData(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES, tableData),
										"export.csv", view.getApplication());
							}

							view.getWidget().getWindow().open(res, "_blank");
						} else if ("massUpdate".equals(id)) {
							MassUpdateContactWindow massUpdateWindow = new MassUpdateContactWindow(
									LocalizationHelper
											.getMessage(
													GenericI18Enum.MASS_UPDATE_WINDOW_TITLE,
													"Contact"),
									ContactListPresenter.this);
							view.getWindow().addWindow(massUpdateWindow);
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleContact>() {
					@Override
					public void onSelect(SimpleContact item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<SimpleContact> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (SimpleContact item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleContact> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (SimpleContact item : currentDataList) {
			if (item.isSelected()) {
				countItems++;
			}
		}
		if (countItems > 0) {
			view.enableActionControls(countItems);
		} else {
			view.disableActionControls();
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CONTACT)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_CONTACTS_HEADER));

			super.onGo(container, data);
			doSearch((ContactSearchCriteria) data.getParams());
			AppContext.addFragment("crm/contact/list", LocalizationHelper
					.getMessage(GenericI18Enum.BROWSER_LIST_ITEMS_TITLE,
							"Contact"));
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	@Override
	public void doSearch(ContactSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleContact> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleContact item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				contactService.removeWithSession(keyList,
						AppContext.getUsername());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			contactService.removeByCriteria(searchCriteria);
			doSearch(searchCriteria);
		}
	}

	@Override
	public void massUpdate(Contact value) {
		if (!isSelectAll) {
			Collection<SimpleContact> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleContact item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}
			if (keyList.size() > 0) {
				contactService.massUpdateWithSession(value, keyList,
						AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			contactService.updateBySearchCriteria(value, searchCriteria);
			doSearch(searchCriteria);
		}
	}
}
