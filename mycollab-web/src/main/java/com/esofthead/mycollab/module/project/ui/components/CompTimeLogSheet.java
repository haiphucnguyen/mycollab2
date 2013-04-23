package com.esofthead.mycollab.module.project.ui.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.MonitorItem;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class CompTimeLogSheet<V extends ValuedBean> extends HorizontalLayout {

	protected PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> tableItem;
	protected MonitorItemService monitorItemService;
	protected V bean;
	protected Button btnAdd;

	private static Logger log = LoggerFactory
			.getLogger(CompFollowersSheet.class);

	protected CompTimeLogSheet(V bean) {
		this.bean = bean;
		this.setMargin(true);
		this.setSpacing(true);

		monitorItemService = AppContext.getSpringBean(MonitorItemService.class);

		initUI();
	}

	private void initUI() {
		
		AddTimeInvest investTimeLayout = new AddTimeInvest();
		this.addComponent(investTimeLayout);
		UpdateTimeRemain updateTimeRemainLayout = new UpdateTimeRemain();
		this.addComponent(updateTimeRemainLayout);
	}
	
	protected abstract void loadTimeInvestItem();
	
	protected abstract void saveTimeInvest();
	
	protected abstract void updateTimeRemain();

	private class AddTimeInvest extends VerticalLayout {

		private Label lbTimeTotal;

		public AddTimeInvest() {

			this.setMargin(true);
			this.setSpacing(true);
			this.setSizeUndefined();

			Label lbTimeInstructTotal = new Label("Total Time Tnvested");
			this.addComponent(lbTimeInstructTotal);
			lbTimeTotal = new Label("0.0");
			this.addComponent(lbTimeTotal);

			HorizontalLayout addLayout = new HorizontalLayout();
			addLayout.setWidth("100%");
			this.addComponent(addLayout);

			NumbericTextField numberField = new NumbericTextField();
			numberField.setWidth("80px");
			addLayout.addComponent(numberField);
			addLayout.setComponentAlignment(numberField, Alignment.MIDDLE_LEFT);

			btnAdd = new Button("Add", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					saveTimeInvest();
				}

			});

			btnAdd.setStyleName(UIConstants.THEME_BLUE_LINK);
			btnAdd.setIcon(new ThemeResource("icons/16/addRecord.png"));
			addLayout.addComponent(btnAdd);
			addLayout.setComponentAlignment(btnAdd, Alignment.MIDDLE_LEFT);

			Label lbIntructAdd = new Label("Add only time invested by you.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);

			tableItem = new PagedBeanTable2<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging>(
					AppContext.getSpringBean(ItemTimeLoggingService.class),
					SimpleItemTimeLogging.class, new String[] { "logUserFullName", "createdtime", "logvalue" }, new String[] {
						"User", "Created Time", "Time" });
			
			tableItem.addGeneratedColumn("logUserFullName", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public com.vaadin.ui.Component generateCell(Table source,
						final Object itemId, Object columnId) {
					final SimpleItemTimeLogging monitorItem = tableItem
							.getBeanByIndex(itemId);

					UserService userService = AppContext
							.getSpringBean(UserService.class);
					SimpleUser user = userService.findUserByUserName(monitorItem
							.getLoguser());

					return new ProjectUserLink(user.getUsername(), user
							.getDisplayName(), true, true);

				}
			});

			tableItem.addGeneratedColumn("createdtime", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public com.vaadin.ui.Component generateCell(Table source,
						Object itemId, Object columnId) {
					final SimpleItemTimeLogging monitorItem = tableItem
							.getBeanByIndex(itemId);
					Label l = new Label();
					l.setValue(AppContext.formatDateTime(monitorItem.getCreatedtime()));
					return l;
				}
			});
			
			tableItem.addGeneratedColumn("logvalue", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public com.vaadin.ui.Component generateCell(Table source,
						Object itemId, Object columnId) {
					final SimpleItemTimeLogging itemTimeLogging = tableItem
							.getBeanByIndex(itemId);
					Label l = new Label();
					l.setValue(itemTimeLogging.getLogvalue());
					return l;
				}
			});
			
			tableItem.setWidth("100%");

			tableItem.setColumnExpandRatio("logUserFullName", 1.0f);
			tableItem.setColumnWidth("monitorDate", UIConstants.TABLE_DATE_TIME_WIDTH);
			tableItem.setColumnWidth("logvalue", UIConstants.TABLE_S_LABEL_WIDTH);

			this.addComponent(tableItem);
			loadTimeInvestItem();
		}
	}

	private class UpdateTimeRemain extends VerticalLayout {

		public UpdateTimeRemain() {

			this.setMargin(true);
			this.setSpacing(true);
			
			Label lbTimeInstructTotal = new Label("Total Time Tnvested");
			this.addComponent(lbTimeInstructTotal);
			Label lbTimeTotal = new Label("_");
			this.addComponent(lbTimeTotal);

			HorizontalLayout addLayout = new HorizontalLayout();
			addLayout.setWidth("100%");
			this.addComponent(addLayout);

			NumbericTextField numberField = new NumbericTextField();
			numberField.setWidth("80px");
			addLayout.addComponent(numberField);
			addLayout.setComponentAlignment(numberField, Alignment.MIDDLE_LEFT);

			btnAdd = new Button("Update", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					updateTimeRemain();
				}

			});

			btnAdd.setStyleName(UIConstants.THEME_BLUE_LINK);
			addLayout.addComponent(btnAdd);
			addLayout.setComponentAlignment(btnAdd, Alignment.MIDDLE_LEFT);

			Label lbIntructAdd = new Label("Update remaining estimate.");
			addLayout.addComponent(lbIntructAdd);
			addLayout
					.setComponentAlignment(lbIntructAdd, Alignment.MIDDLE_LEFT);
		}
	}

	private static class NumbericTextField extends TextField {
		private static final long serialVersionUID = 1L;

		@Override
		protected void setValue(Object newValue, boolean repaintIsNotNeeded) {
			try {
				double d = Double.parseDouble((String) newValue);
				super.setValue(d, repaintIsNotNeeded);
			} catch (Exception e) {
				super.setValue(0, repaintIsNotNeeded);
			}
		}
	}

}
