/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
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
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectRoleListPresenter extends
		AbstractPresenter<ProjectRoleListView> implements
		ListPresenter<ProjectRoleSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private static final String[] EXPORT_VISIBLE_COLUMNS = new String[] {
			"rolename", "description" };
	private static final String[] EXPORT_DISPLAY_NAMES = new String[] { "Name",
			"Description" };
	private ProjectRoleService projectRoleService;
	private ProjectRoleSearchCriteria searchCriteria;
	private boolean isSelectAll = false;

	public ProjectRoleListPresenter() {
		super(ProjectRoleListView.class);
		projectRoleService = AppContext.getSpringBean(ProjectRoleService.class);

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
				new SearchHandler<ProjectRoleSearchCriteria>() {
					@Override
					public void onSearch(ProjectRoleSearchCriteria criteria) {
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
						Collection<ProjectRole> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (ProjectRole item : currentDataList) {
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

						} else if ("export".equals(id)) {
							Resource res = null;

							if (isSelectAll) {
								res = new StreamResource(
										new ExportStreamResource.AllItems<ProjectRoleSearchCriteria>(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES,
												AppContext
														.getSpringBean(ProjectRoleService.class),
												searchCriteria), "export.csv",
										view.getApplication());
							} else {
								List<ProjectRole> tableData = view
										.getPagedBeanTable()
										.getCurrentDataList();
								res = new StreamResource(
										new ExportStreamResource.ListData(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES, tableData),
										"export.csv", view.getApplication());
							}

							view.getWidget().getWindow().open(res, "_blank");
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<ProjectRole>() {
					@Override
					public void onSelect(ProjectRole item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<ProjectRole> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		for (ProjectRole item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<ProjectRole> currentDataList = view.getPagedBeanTable()
				.getCurrentDataList();
		int countItems = 0;
		for (ProjectRole item : currentDataList) {
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
	public void doSearch(ProjectRoleSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
		checkWhetherEnableTableActionControl();
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<ProjectRole> currentDataList = view.getPagedBeanTable()
					.getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (ProjectRole item : currentDataList) {
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
				projectRoleService.removeWithSession(keyList,
						AppContext.getUsername(), AppContext.getAccountId());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			projectRoleService.removeByCriteria(searchCriteria,
					AppContext.getAccountId());
			doSearch(searchCriteria);
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectRoleContainer roleContainer = (ProjectRoleContainer) container;
		roleContainer.removeAllComponents();
		roleContainer.addComponent(view.getWidget());
		doSearch((ProjectRoleSearchCriteria) data.getParams());

		ProjectBreadcrumb breadCrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		breadCrumb.gotoRoleList();
	}
}
