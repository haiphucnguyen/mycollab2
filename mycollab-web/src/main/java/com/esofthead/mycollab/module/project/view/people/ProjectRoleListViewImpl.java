/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
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
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
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

		this.searchPanel = new ProjectRoleSearchPanel();
		this.addComponent(this.searchPanel);

		this.listLayout = new VerticalLayout();
		this.addComponent(this.listLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new PagedBeanTable2<ProjectRoleService, ProjectRoleSearchCriteria, ProjectRole>(
				AppContext.getSpringBean(ProjectRoleService.class),
				ProjectRole.class, new TableViewField("", "selected",
						UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
						new TableViewField("Name", "rolename",
								UIConstants.TABLE_EX_LABEL_WIDTH),
						new TableViewField("Description", "description",
								UIConstants.TABLE_EX_LABEL_WIDTH)));

		this.tableItem.addGeneratedColumn("selected",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public Object generateCell(final Table source,
							final Object itemId, final Object columnId) {
						final CheckBox cb = new CheckBox("", false);
						cb.setImmediate(true);
						cb.addListener(new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								final ProjectRole role = ProjectRoleListViewImpl.this.tableItem
										.getBeanByIndex(itemId);
								ProjectRoleListViewImpl.this.tableItem
										.fireSelectItemEvent(role);
							}
						});

						final ProjectRole role = ProjectRoleListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						role.setExtraData(cb);
						return cb;
					}
				});

		this.tableItem.addGeneratedColumn("rolename",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final ProjectRole role = ProjectRoleListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						final ButtonLink b = new ButtonLink(role.getRolename(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(
											final Button.ClickEvent event) {
										EventBus.getInstance()
												.fireEvent(
														new ProjectRoleEvent.GotoRead(
																ProjectRoleListViewImpl.this,
																role.getId()));
									}
								});
						return b;

					}
				});

		this.listLayout.addComponent(this.constructTableActionControls());
		this.listLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<ProjectRoleSearchCriteria> getSearchHandlers() {
		return this.searchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button("Delete");
		deleteBtn.setEnabled(CurrentProjectVariables
				.canAccess(ProjectRolePermissionCollections.ROLES));

		this.tableActionControls = new PopupButtonControl("delete", deleteBtn);
		this.tableActionControls.addOptionItem("mail", "Mail");
		this.tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue("Selected: "
				+ numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<ProjectRole> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public IPagedBeanTable<ProjectRoleSearchCriteria, ProjectRole> getPagedBeanTable() {
		return this.tableItem;
	}
}
