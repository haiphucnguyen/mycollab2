/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserListViewImpl extends AbstractView implements UserListView {

	private static final long serialVersionUID = 1L;
	private final UserSearchPanel searchPanel;
	private SelectionOptionButton selectOptionButton;
	private UserTableDisplay tableItem;
	private final VerticalLayout listLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public UserListViewImpl() {

		this.searchPanel = new UserSearchPanel();
		this.addComponent(this.searchPanel);

		this.listLayout = new VerticalLayout();
		this.addComponent(this.listLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new UserTableDisplay(new TableViewField("",
				"selected", UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
				new TableViewField("Name", "displayName",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"User Name", "username",
						UIConstants.TABLE_X_LABEL_WIDTH), new TableViewField(
						"Email", "email", UIConstants.TABLE_EMAIL_WIDTH),
				new TableViewField("Last Accessed Time", "lastAccessedTime",
						UIConstants.TABLE_DATE_TIME_WIDTH)));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleUser user = (SimpleUser) event.getData();
						if (("username".equals(event.getFieldName()) || ("displayName"
								.equals(event.getFieldName())))) {
							EventBus.getInstance().fireEvent(
									new UserEvent.GotoRead(
											UserListViewImpl.this, user));
						}
					}
				});

		this.listLayout.addComponent(this.constructTableActionControls());
		this.listLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<UserSearchCriteria> getSearchHandlers() {
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

		this.tableActionControls = new PopupButtonControl("delete", "Delete");
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
		this.tableActionControls.setEnabled(true);
		this.selectedItemsNumberLabel.setValue("Selected: "
				+ numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setEnabled(false);
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
	public HasSelectableItemHandlers<SimpleUser> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public IPagedBeanTable<UserSearchCriteria, SimpleUser> getPagedBeanTable() {
		return this.tableItem;
	}
}
