package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
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
    private BugTableDisplay tableItem;
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
        tableItem = new BugTableDisplay(new String[]{"selected", "summary",
                    "assignuserFullName", "severity", "resolution", "duedate"},
                new String[]{"", "Summary", "Assigned User", "Severity", "Resolution", "Due Date"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleBug bug = (SimpleBug) event.getData();
                if ("summary".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(new BugEvent.GotoRead(BugListViewImpl.this, bug.getId()));
                }
            }
        });

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
