/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.ibm.icu.util.GregorianCalendar;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 *
 * @author haiphucnguyen
 */
public class MeetingTableDisplay extends PagedBeanTable2<MeetingService, MeetingSearchCriteria, SimpleMeeting> {

    public MeetingTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(MeetingService.class),
                SimpleMeeting.class, visibleColumns, columnHeaders);

        this.addGeneratedColumn("subject", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleMeeting meeting = MeetingTableDisplay.this.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(meeting.getSubject(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;
                            
                            @Override
                            public void buttonClick(Button.ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new ActivityEvent.CallRead(this, meeting
                                        .getId()));
                            }
                        });
                b.addStyleName(UIConstants.LINK_COMPLETED);
                return b;
                
            }
        });
        
        this.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleMeeting meeting = MeetingTableDisplay.this.getBeanByIndex(itemId);
                return new Label(AppContext.formatDateTime(meeting.getStartdate()));
                
            }
        });
        
        this.setColumnExpandRatio("subject", 1);
        this.setColumnWidth("relatedTo", UIConstants.TABLE_X_LABEL_WIDTH);
        this.setColumnWidth("status", UIConstants.TABLE_S_LABEL_WIDTH);
        this.setColumnWidth("startdate", UIConstants.TABLE_DATE_TIME_WIDTH);
    }
}
