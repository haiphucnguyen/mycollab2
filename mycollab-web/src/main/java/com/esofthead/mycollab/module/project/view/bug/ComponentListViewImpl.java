/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
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
public class ComponentListViewImpl extends AbstractView implements
		ComponentListView {

	private static final long serialVersionUID = 1L;
	private final ComponentSearchPanel componentSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private DefaultPagedBeanTable<ComponentService, ComponentSearchCriteria, SimpleComponent> tableItem;
	private final VerticalLayout componentListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public ComponentListViewImpl() {
		this.setMargin(false, true, true, true);

		this.componentSearchPanel = new ComponentSearchPanel();
		this.addComponent(this.componentSearchPanel);

		this.componentListLayout = new VerticalLayout();
		this.addComponent(this.componentListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new DefaultPagedBeanTable<ComponentService, ComponentSearchCriteria, SimpleComponent>(
				ApplicationContextUtil.getSpringBean(ComponentService.class),
				SimpleComponent.class, new TableViewField("", "selected",
						UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
						new TableViewField("Name", "componentname",
								UIConstants.TABLE_EX_LABEL_WIDTH),
						new TableViewField("Lead Name", "userLeadFullName",
								UIConstants.TABLE_X_LABEL_WIDTH),
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
								final SimpleComponent component = ComponentListViewImpl.this.tableItem
										.getBeanByIndex(itemId);
								ComponentListViewImpl.this.tableItem
										.fireSelectItemEvent(component);

							}
						});

						final SimpleComponent component = ComponentListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						component.setExtraData(cb);
						return cb;
					}
				});

		this.tableItem.addGeneratedColumn("componentname",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleComponent bugComponent = ComponentListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						final ButtonLink b = new ButtonLink(bugComponent
								.getComponentname(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(
											final Button.ClickEvent event) {
										EventBus.getInstance().fireEvent(
												new BugComponentEvent.GotoRead(
														this, bugComponent
																.getId()));
									}
								});
						if (bugComponent.getStatus() != null
								&& bugComponent.getStatus().equals("Close")) {
							b.addStyleName(UIConstants.LINK_COMPLETED);
						}
						return b;

					}
				});

		this.tableItem.addGeneratedColumn("userLeadFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(
							final Table source, final Object itemId,
							final Object columnId) {
						final SimpleComponent bugComponent = ComponentListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						return new ProjectUserLink(bugComponent.getUserlead(),
								bugComponent.getUserLeadAvatarId(),
								bugComponent.getUserLeadFullName());

					}
				});

		this.tableItem.setWidth("100%");

		this.componentListLayout.addComponent(this
				.constructTableActionControls());
		this.componentListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<ComponentSearchCriteria> getSearchHandlers() {
		return this.componentSearchPanel;
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
				.canAccess(ProjectRolePermissionCollections.COMPONENTS));

		this.tableActionControls = new PopupButtonControl(
				PopupActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(PopupActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_MAIL));
		this.tableActionControls
				.addOptionItem(PopupActionHandler.EXPORT_CSV_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));
		this.tableActionControls
				.addOptionItem(PopupActionHandler.EXPORT_PDF_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				PopupActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));

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
	public HasSelectableItemHandlers<SimpleComponent> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<ComponentSearchCriteria, SimpleComponent> getPagedBeanTable() {
		return this.tableItem;
	}

}
