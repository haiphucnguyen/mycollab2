package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class BugListViewImpl extends AbstractView implements BugListView {

    private static final long serialVersionUID = 1L;
    private final BugSearchPanel problemSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private PagedBeanTable2<BugService, BugSearchCriteria, SimpleBug> tableItem;
    private final VerticalLayout problemListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public BugListViewImpl() {
        this.setSpacing(true);

        problemSearchPanel = new BugSearchPanel();
        this.addComponent(problemSearchPanel);

        problemListLayout = new VerticalLayout();
        problemListLayout.setSpacing(true);
        this.addComponent(problemListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new PagedBeanTable2<BugService, BugSearchCriteria, SimpleBug>(
                AppContext.getSpringBean(BugService.class),
                SimpleBug.class, new String[]{"selected", "issuename",
                    "assignedUserFullName", "datedue", "level"},
                new String[]{"", "Name", "Assigned to", "Due Date", "Level"});

        tableItem.setColumnExpandRatio("issuename", 1);
        tableItem.setColumnWidth("assignedUserFullName",
                UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("level", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("datedue", UIConstants.TABLE_DATE_WIDTH);

        tableItem.setWidth("100%");

        problemListLayout.addComponent(constructTableActionControls());
        problemListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<BugSearchCriteria> getSearchHandlers() {
        return problemSearchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        selectOptionButton = new SelectionOptionButton(tableItem);
        layout.addComponent(selectOptionButton);

        tableActionControls = new PopupButtonControl("delete", "Delete");
        tableActionControls.addOptionItem("mail", "Mail");
        tableActionControls.addOptionItem("export", "Export");

        layout.addComponent(tableActionControls);
        layout.addComponent(selectedItemsNumberLabel);
        layout.setComponentAlignment(selectedItemsNumberLabel,
                Alignment.MIDDLE_CENTER);
        return layout;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setEnabled(true);
        selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setEnabled(false);
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
    public HasSelectableItemHandlers<SimpleBug> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<BugSearchCriteria, SimpleBug> getPagedBeanTable() {
        return tableItem;
    }
}
