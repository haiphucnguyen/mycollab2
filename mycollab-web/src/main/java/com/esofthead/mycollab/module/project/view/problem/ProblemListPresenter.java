package com.esofthead.mycollab.module.project.view.problem;

import com.esofthead.mycollab.module.file.ExportStreamResource;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
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

public class ProblemListPresenter extends AbstractPresenter<ProblemListView>
        implements ListPresenter<ProblemSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private static final String[] EXPORT_VISIBLE_COLUMNS = new String[]{
        "issuename", "assignedUserFullName", "datedue", "level"};
    private static final String[] EXPORT_DISPLAY_NAMES = new String[]{"Name",
        "Assigned to", "Due Date", "Level"};
    private ProblemService problemService;
    private ProblemSearchCriteria searchCriteria;
    private boolean isSelectAll = false;

    public ProblemListPresenter() {
        super(ProblemListView.class);

        problemService = AppContext.getSpringBean(ProblemService.class);

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
                new SearchHandler<ProblemSearchCriteria>() {
                    @Override
                    public void onSearch(ProblemSearchCriteria criteria) {
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
                        Collection<SimpleProblem> currentDataList = view
                                .getPagedBeanTable().getCurrentDataList();
                        isSelectAll = false;
                        for (SimpleProblem item : currentDataList) {
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
                                        new ExportStreamResource.AllItems<ProblemSearchCriteria>(
                                        EXPORT_VISIBLE_COLUMNS,
                                        EXPORT_DISPLAY_NAMES,
                                        AppContext
                                        .getSpringBean(ProblemService.class),
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
                new SelectableItemHandler<SimpleProblem>() {
                    @Override
                    public void onSelect(SimpleProblem item) {
                        isSelectAll = false;
                        item.setSelected(!item.isSelected());

                        checkWhetherEnableTableActionControl();
                    }
                });
    }

    private void selectAllItemsInCurrentPage() {
        Collection<SimpleProblem> currentDataList = view.getPagedBeanTable()
                .getCurrentDataList();
        for (SimpleProblem item : currentDataList) {
            item.setSelected(true);
            CheckBox checkBox = (CheckBox) item.getExtraData();
            checkBox.setValue(true);
        }
    }

    private void checkWhetherEnableTableActionControl() {
        Collection<SimpleProblem> currentDataList = view.getPagedBeanTable()
                .getCurrentDataList();
        int countItems = 0;
        for (SimpleProblem item : currentDataList) {
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
        ProblemContainer problemContainer = (ProblemContainer) container;
        problemContainer.removeAllComponents();
        problemContainer.addComponent(view.getWidget());

        doSearch((ProblemSearchCriteria) data.getParams());
        
        ProjectBreadcrumb breadcrumb = ViewManager.getView(ProjectBreadcrumb.class);
        breadcrumb.gotoProblemList();
    }

    @Override
    public void doSearch(ProblemSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        view.getPagedBeanTable().setSearchCriteria(searchCriteria);
        checkWhetherEnableTableActionControl();
    }

    private void deleteSelectedItems() {
        if (!isSelectAll) {
            Collection<SimpleProblem> currentDataList = view
                    .getPagedBeanTable().getCurrentDataList();
            List<Integer> keyList = new ArrayList<Integer>();
            for (SimpleProblem item : currentDataList) {
                if (item.isSelected()) {
                    keyList.add(item.getId());
                }
            }

            if (keyList.size() > 0) {
                problemService.removeWithSession(keyList,
                        AppContext.getUsername());
                doSearch(searchCriteria);
                checkWhetherEnableTableActionControl();
            }
        } else {
            problemService.removeByCriteria(searchCriteria);
            doSearch(searchCriteria);
        }

    }
}
