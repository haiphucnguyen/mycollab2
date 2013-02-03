/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
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
public class ProjectMemberListViewImpl extends AbstractView implements ProjectMemberListView {

    private static final long serialVersionUID = 1L;
    private final ProjectMemberSearchPanel riskSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private PagedBeanTable2<ProjectMemberService, ProjectMemberSearchCriteria, SimpleProjectMember> tableItem;
    private final VerticalLayout riskListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public ProjectMemberListViewImpl() {
        this.setSpacing(true);

        riskSearchPanel = new ProjectMemberSearchPanel();
        this.addComponent(riskSearchPanel);

        riskListLayout = new VerticalLayout();
        riskListLayout.setSpacing(true);
        this.addComponent(riskListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new PagedBeanTable2<ProjectMemberService, ProjectMemberSearchCriteria, SimpleProjectMember>(
                AppContext.getSpringBean(ProjectMemberService.class), SimpleProjectMember.class,
                new String[]{"selected", "riskname",
                    "assignedToUserFullName", "datedue", "level"},
                new String[]{"", "Name", "Assigned to", "Due Date", "Level"});

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
                        SimpleProjectMember item = tableItem.getBeanByIndex(itemId);
                        tableItem.fireSelectItemEvent(item);

                    }
                });

                SimpleProjectMember item = tableItem.getBeanByIndex(itemId);
                item.setExtraData(cb);
                return cb;
            }
        });

        tableItem.addGeneratedColumn("riskname", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleProjectMember projectMember = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(projectMember.getMemberFullName(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new ProjectMemberEvent.GotoRead(this, projectMember
                                        .getId()));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        tableItem.setColumnExpandRatio("riskname", 1);
        tableItem.setColumnWidth("assignedToUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("level",
                UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("datedue", UIConstants.TABLE_DATE_WIDTH);

        tableItem.setWidth("100%");

        riskListLayout.addComponent(constructTableActionControls());
        riskListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<ProjectMemberSearchCriteria> getSearchHandlers() {
        return riskSearchPanel;
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
    public HasSelectableItemHandlers<SimpleProjectMember> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<ProjectMemberSearchCriteria, SimpleProjectMember> getPagedBeanTable() {
        return tableItem;
    }
}