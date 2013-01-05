/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;

/**
 *
 * @author haiphucnguyen
 */
public class MeetingTableDisplay extends PagedBeanTable2<MeetingService, MeetingSearchCriteria, SimpleMeeting> {
    public MeetingTableDisplay(final String[] visibleColumns, String[] columnHeaders) {
        super(AppContext.getSpringBean(MeetingService.class),
                SimpleMeeting.class, visibleColumns, columnHeaders);
    }
}
