/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class RoleListPresenter extends AbstractPresenter<RoleListView>
		implements ListPresenter<RoleSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private RoleService userService;
	private RoleSearchCriteria searchCriteria;
	private boolean isSelectAll = false;

	public RoleListPresenter() {
		super(RoleListView.class);
		userService = AppContext.getSpringBean(RoleService.class);

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
				new SearchHandler<RoleSearchCriteria>() {
					@Override
					public void onSearch(RoleSearchCriteria criteria) {
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
						Collection<Role> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (Role item : currentDataList) {
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
							view.getWidget().getWindow()
									.addWindow(new MailFormWindow());
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<Role>() {
					@Override
					public void onSelect(Role item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<Role> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (Role item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<Role> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (Role item : currentDataList) {
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
	public void doSearch(RoleSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<Role> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (Role item : currentDataList) {
				if (item.isSelected()
						&& (item.getIssystemrole() == null || item
								.getIssystemrole() == Boolean.FALSE)) {
					keyList.add(item.getId());
				} else {
					MessageBox mb = new MessageBox(
							AppContext.getApplication().getMainWindow(),
							LocalizationHelper
									.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
							MessageBox.Icon.WARN, "Can not delete role "
									+ item.getRolename()
									+ " because it is the system role.",
							new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show();
				}
			}

			if (keyList.size() > 0) {
				userService.massRemoveWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
			}
		} else {
			userService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(RolePermissionCollections.USER_ROLE)) {
			RoleContainer roleContainer = (RoleContainer) container;
			roleContainer.removeAllComponents();
			roleContainer.addComponent(view.getWidget());
			doSearch((RoleSearchCriteria) data.getParams());

			AccountSettingBreadcrumb breadcrumb = ViewManager
					.getView(AccountSettingBreadcrumb.class);
			breadcrumb.gotoRoleList();
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
