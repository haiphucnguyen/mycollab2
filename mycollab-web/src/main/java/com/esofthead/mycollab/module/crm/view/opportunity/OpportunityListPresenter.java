package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.vaadin.events.PagableHandler;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.events.SelectableItemHandler;
import com.esofthead.mycollab.vaadin.events.SelectionOptionHandler;
import com.esofthead.mycollab.vaadin.mvp.ListPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MailFormWindow;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

public class OpportunityListPresenter extends
		CrmGenericPresenter<OpportunityListView> implements
		ListPresenter<OpportunitySearchCriteria> {

	private static final long serialVersionUID = 1L;

	private static final String[] EXPORT_VISIBLE_COLUMNS = new String[] {
			"opportunityname", "accountName", "salesstage", "amount",
			"expectedcloseddate", "assignUserFullName" };
	private static final String[] EXPORT_DISPLAY_NAMES = new String[] { "Name",
			"Account Name", "Sales Stage", "Amount", "Close", "Assign User" };

	private OpportunityService opportunityService;
	private OpportunitySearchCriteria searchCriteria;
	private boolean isSelectAll = false;

	public OpportunityListPresenter() {
		super(OpportunityListView.class);
		opportunityService = AppContext.getSpringBean(OpportunityService.class);

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
				new SearchHandler<OpportunitySearchCriteria>() {
					@Override
					public void onSearch(OpportunitySearchCriteria criteria) {
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
						Collection<SimpleOpportunity> currentDataList = view
								.getPagedBeanTable().getCurrentDataList();
						isSelectAll = false;
						for (SimpleOpportunity item : currentDataList) {
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
							ConfirmDialog.show(view.getWindow(),
                                    "Please Confirm:",
                                    "Are you sure to delete selected items: ",
                                    "Yes", "No", new ConfirmDialog.Listener() {
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
						} else if ("export".equals(id)) {
							Resource res = null;

							if (isSelectAll) {
								res = new StreamResource(
										new ExportStreamResource.AllItems<OpportunitySearchCriteria>(
												EXPORT_VISIBLE_COLUMNS,
												EXPORT_DISPLAY_NAMES,
												AppContext
														.getSpringBean(OpportunityService.class),
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
						}
					}
				});

		view.getSelectableItemHandlers().addSelectableItemHandler(
				new SelectableItemHandler<SimpleOpportunity>() {
					@Override
					public void onSelect(SimpleOpportunity item) {
						isSelectAll = false;
						item.setSelected(!item.isSelected());

						checkWhetherEnableTableActionControl();
					}
				});
	}

	private void selectAllItemsInCurrentPage() {
		Collection<SimpleOpportunity> currentDataList = view
				.getPagedBeanTable().getCurrentDataList();
		for (SimpleOpportunity item : currentDataList) {
			item.setSelected(true);
			CheckBox checkBox = (CheckBox) item.getExtraData();
			checkBox.setValue(true);
		}
	}

	private void checkWhetherEnableTableActionControl() {
		Collection<SimpleOpportunity> currentDataList = view
				.getPagedBeanTable().getCurrentDataList();
		int countItems = 0;
		for (SimpleOpportunity item : currentDataList) {
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
		super.onGo(container, data);
		doSearch((OpportunitySearchCriteria) data.getParams());
	}

	@Override
	public void doSearch(OpportunitySearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
		view.getPagedBeanTable().setSearchCriteria(searchCriteria);
	}

	private void deleteSelectedItems() {
		if (!isSelectAll) {
			Collection<SimpleOpportunity> currentDataList = view
					.getPagedBeanTable().getCurrentDataList();
			List<Integer> keyList = new ArrayList<Integer>();
			for (SimpleOpportunity item : currentDataList) {
				if (item.isSelected()) {
					keyList.add(item.getId());
				}
			}

			if (keyList.size() > 0) {
				opportunityService.removeWithSession(keyList,
						AppContext.getUsername());
				doSearch(searchCriteria);
				checkWhetherEnableTableActionControl();
			}
		} else {
			opportunityService.removeByCriteria(searchCriteria);
			doSearch(searchCriteria);
		}
	}
}
