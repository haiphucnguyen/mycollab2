/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * 
 * @author haiphucnguyen
 */
public class MeetingTableDisplay extends
		PagedBeanTable2<MeetingService, MeetingSearchCriteria, SimpleMeeting> {

	private static final long serialVersionUID = 1L;

	public MeetingTableDisplay(List<TableViewField> displaycolumns) {
		super(AppContext.getSpringBean(MeetingService.class),
				SimpleMeeting.class, displaycolumns);

		this.addGeneratedColumn("subject", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleMeeting meeting = MeetingTableDisplay.this
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(meeting.getSubject(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new ActivityEvent.CallRead(this,
												meeting.getId()));
							}
						});
				b.addStyleName(UIConstants.LINK_COMPLETED);

				if ("Held".equals(meeting.getStatus())) {
					b.addStyleName(UIConstants.LINK_COMPLETED);
				} else {
					if (meeting.getEnddate() != null
							&& (meeting.getEnddate()
									.before(new GregorianCalendar().getTime()))) {
						b.addStyleName(UIConstants.LINK_OVERDUE);
					}
				}
				return b;

			}
		});

		this.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleMeeting meeting = MeetingTableDisplay.this
						.getBeanByIndex(itemId);
				return new Label(AppContext.formatDateTime(meeting
						.getStartdate()));

			}
		});

		this.setColumnExpandRatio("subject", 1);
		this.setColumnWidth("relatedTo", UIConstants.TABLE_X_LABEL_WIDTH);
		this.setColumnWidth("status", UIConstants.TABLE_S_LABEL_WIDTH);
		this.setColumnWidth("startdate", UIConstants.TABLE_DATE_TIME_WIDTH);
	}
}
