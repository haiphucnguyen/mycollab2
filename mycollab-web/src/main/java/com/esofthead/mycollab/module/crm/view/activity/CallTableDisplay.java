/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class CallTableDisplay extends
		PagedBeanTable2<CallService, CallSearchCriteria, SimpleCall> {
	private static final long serialVersionUID = 1L;

	public CallTableDisplay(final String[] visibleColumns,
			String[] columnHeaders) {
		super(AppContext.getSpringBean(CallService.class), SimpleCall.class,
				visibleColumns, columnHeaders);

		this.addGeneratedColumn("subject", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleCall call = CallTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(call.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new ActivityEvent.CallRead(this, call
												.getId()));
							}
						});

				if ("Held".equals(call.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				}
				return b;

			}
		});

		this.addGeneratedColumn("isClosed", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleCall call = CallTableDisplay.this
						.getBeanByIndex(itemId);
				Button b = new Button(null, new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						fireTableEvent(new TableClickEvent(
								CallTableDisplay.this, call, "isClosed"));
					}
				});
				b.setIcon(new ThemeResource("icons/16/close.png"));
				b.setStyleName("link");
				b.setDescription("Close this call");
				return b;

			}
		});

		this.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleCall call = CallTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDateTime(call.getStartdate()));

			}
		});

		this.setColumnExpandRatio("subject", 1);
		this.setColumnWidth("relatedTo", UIConstants.TABLE_X_LABEL_WIDTH);
		this.setColumnWidth("status", UIConstants.TABLE_S_LABEL_WIDTH);
		this.setColumnWidth("startdate", UIConstants.TABLE_DATE_TIME_WIDTH);
	}
}
