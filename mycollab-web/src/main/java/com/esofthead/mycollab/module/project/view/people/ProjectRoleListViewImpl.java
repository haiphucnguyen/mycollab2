/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
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

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectRoleListViewImpl extends AbstractView implements
		ProjectRoleListView {
	private static final long serialVersionUID = 1L;

	private final ProjectRoleSearchPanel searchPanel;
	private SelectionOptionButton selectOptionButton;
	private PagedBeanTable2<ProjectRoleService, ProjectRoleSearchCriteria, ProjectRole> tableItem;
	private final VerticalLayout listLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public ProjectRoleListViewImpl() {
		this.setSpacing(true);

		searchPanel = new ProjectRoleSearchPanel();
		this.addComponent(searchPanel);

		listLayout = new VerticalLayout();
		listLayout.setSpacing(true);
		this.addComponent(listLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<ProjectRoleService, ProjectRoleSearchCriteria, ProjectRole>(
				AppContext.getSpringBean(ProjectRoleService.class),
				ProjectRole.class, new String[] { "selected", "rolename",
						"description" }, new String[] { "", "Name",
						"Description" });
		
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
                        ProjectRole role = tableItem
                                .getBeanByIndex(itemId);
                        tableItem.fireSelectItemEvent(role);
                    }
                });

                ProjectRole role = tableItem.getBeanByIndex(itemId);
                role.setExtraData(cb);
                return cb;
            }
        });
		
		tableItem.addGeneratedColumn("rolename", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final ProjectRole role = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(role.getRolename(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                            	EventBus.getInstance().fireEvent(
    									new ProjectRoleEvent.GotoRead(
    											ProjectRoleListViewImpl.this, role.getId()));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });
		
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnExpandRatio("rolename", 1);
		tableItem.setColumnWidth("description", UIConstants.TABLE_X_LABEL_WIDTH);

		listLayout.addComponent(constructTableActionControls());
		listLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<ProjectRoleSearchCriteria> getSearchHandlers() {
		return searchPanel;
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
	public HasSelectableItemHandlers<ProjectRole> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<ProjectRoleSearchCriteria, ProjectRole> getPagedBeanTable() {
		return tableItem;
	}
}
