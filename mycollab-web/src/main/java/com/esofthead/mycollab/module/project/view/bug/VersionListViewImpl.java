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
import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
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
import com.vaadin.ui.Component;
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
public class VersionListViewImpl extends AbstractView implements
		VersionListView {

	private static final long serialVersionUID = 1L;
	private final VersionSearchPanel componentSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private DefaultPagedBeanTable<VersionService, VersionSearchCriteria, SimpleVersion> tableItem;
	private final VerticalLayout componentListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public VersionListViewImpl() {
		this.setMargin(false, true, true, true);

		this.componentSearchPanel = new VersionSearchPanel();
		this.addComponent(this.componentSearchPanel);

		this.componentListLayout = new VerticalLayout();
		this.addComponent(this.componentListLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new DefaultPagedBeanTable<VersionService, VersionSearchCriteria, SimpleVersion>(
				ApplicationContextUtil.getSpringBean(VersionService.class),
				SimpleVersion.class, new TableViewField("", "selected",
						UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
						new TableViewField("Name", "versionname",
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
								final SimpleVersion version = VersionListViewImpl.this.tableItem
										.getBeanByIndex(itemId);
								VersionListViewImpl.this.tableItem
										.fireSelectItemEvent(version);

							}
						});

						final Version version = VersionListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						version.setExtraData(cb);
						return cb;
					}
				});

		this.tableItem.addGeneratedColumn("versionname",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public Component generateCell(final Table source,
							final Object itemId, final Object columnId) {
						final Version bugVersion = VersionListViewImpl.this.tableItem
								.getBeanByIndex(itemId);
						final ButtonLink b = new ButtonLink(bugVersion
								.getVersionname(), new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(
									final Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new BugVersionEvent.GotoRead(this,
												bugVersion.getId()));
							}
						});
						if (bugVersion.getStatus() != null
								&& bugVersion.getStatus().equals("Close")) {
							b.addStyleName(UIConstants.LINK_COMPLETED);
						} else if (bugVersion.getDuedate() != null
								&& (bugVersion.getDuedate()
										.before(new GregorianCalendar()
												.getTime()))) {
							b.addStyleName(UIConstants.LINK_OVERDUE);
						}
						return b;

					}
				});

		this.tableItem.setWidth("100%");

		this.componentListLayout.addComponent(this
				.constructTableActionControls());
		this.componentListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<VersionSearchCriteria> getSearchHandlers() {
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
				.canAccess(ProjectRolePermissionCollections.VERSIONS));

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
	public HasSelectableItemHandlers<SimpleVersion> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<VersionSearchCriteria, SimpleVersion> getPagedBeanTable() {
		return this.tableItem;
	}

}
