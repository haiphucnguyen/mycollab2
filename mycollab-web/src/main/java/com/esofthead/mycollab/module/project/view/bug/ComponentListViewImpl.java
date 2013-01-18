/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;


import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.splitbutton.PopupButtonControl;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class ComponentListViewImpl extends AbstractView implements ComponentListView {

    private static final long serialVersionUID = 1L;
    private final ComponentSearchPanel componentSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private PagedBeanTable2<ComponentService, ComponentSearchCriteria, SimpleComponent> tableItem;
    private final VerticalLayout componentListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public ComponentListViewImpl() {
        this.setSpacing(true);

        componentSearchPanel = new ComponentSearchPanel();
        this.addComponent(componentSearchPanel);

        componentListLayout = new VerticalLayout();
        componentListLayout.setSpacing(true);
        this.addComponent(componentListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new PagedBeanTable2<ComponentService, ComponentSearchCriteria, SimpleComponent>(
                AppContext.getSpringBean(ComponentService.class), SimpleComponent.class,
                new String[]{"selected", "componentname",
                    "description"},
                new String[]{"", "Name", "Description"});

        tableItem.addGeneratedColumn("selected", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(final Table source, final Object itemId,
                    Object columnId) {
                final CheckBox cb = new CheckBox("", false);
                cb.setImmediate(true);
                cb.addListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        SimpleComponent account = tableItem.getBeanByIndex(itemId);
                        tableItem.fireSelectItemEvent(account);

                    }
                });

                SimpleComponent account = tableItem.getBeanByIndex(itemId);
                account.setExtraData(cb);
                return cb;
            }
        });

        tableItem.addGeneratedColumn("componentname", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleComponent bugComponent = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(bugComponent.getComponentname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new BugComponentEvent.GotoRead(this, bugComponent
                                        .getId()));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        

        tableItem.setColumnExpandRatio("componentname", 1);
        tableItem.setColumnWidth("description", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("selected",
                UIConstants.TABLE_CONTROL_WIDTH);

        tableItem.setWidth("100%");

        componentListLayout.addComponent(constructTableActionControls());
        componentListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<ComponentSearchCriteria> getSearchHandlers() {
        return componentSearchPanel;
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
        tableActionControls.setVisible(true);
        selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setVisible(false);
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
    public HasSelectableItemHandlers<SimpleComponent> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<ComponentSearchCriteria, SimpleComponent> getPagedBeanTable() {
        return tableItem;
    }
    
}
