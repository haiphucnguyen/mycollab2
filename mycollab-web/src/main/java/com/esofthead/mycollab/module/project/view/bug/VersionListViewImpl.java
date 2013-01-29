/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
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
public class VersionListViewImpl extends AbstractView implements VersionListView {

    private static final long serialVersionUID = 1L;
    private final VersionSearchPanel componentSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private PagedBeanTable2<VersionService, VersionSearchCriteria, Version> tableItem;
    private final VerticalLayout componentListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public VersionListViewImpl() {
        this.setSpacing(true);

        componentSearchPanel = new VersionSearchPanel();
        this.addComponent(componentSearchPanel);

        componentListLayout = new VerticalLayout();
        componentListLayout.setSpacing(true);
        this.addComponent(componentListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new PagedBeanTable2<VersionService, VersionSearchCriteria, Version>(
                AppContext.getSpringBean(VersionService.class), Version.class,
                new String[]{"selected", "versionname",
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
                        Version version = tableItem.getBeanByIndex(itemId);
                        tableItem.fireSelectItemEvent(version);

                    }
                });

                Version version = tableItem.getBeanByIndex(itemId);
                version.setExtraData(cb);
                return cb;
            }
        });

        tableItem.addGeneratedColumn("versionname", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final Version bugVersion = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(bugVersion.getVersionname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new BugVersionEvent.GotoRead(this, bugVersion
                                        .getId()));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        

        tableItem.setColumnExpandRatio("versionname", 1);
        tableItem.setColumnWidth("description", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("selected",
                UIConstants.TABLE_CONTROL_WIDTH);

        tableItem.setWidth("100%");

        componentListLayout.addComponent(constructTableActionControls());
        componentListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<VersionSearchCriteria> getSearchHandlers() {
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
    public HasSelectableItemHandlers<Version> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<VersionSearchCriteria, Version> getPagedBeanTable() {
        return tableItem;
    }
    
}
