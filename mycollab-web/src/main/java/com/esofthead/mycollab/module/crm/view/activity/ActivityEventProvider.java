/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.event.CalendarEventProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author haiphucnguyen
 */
public class ActivityEventProvider implements CalendarEventProvider {
    
    private static Logger log = LoggerFactory.getLogger(ActivityEventProvider.class);
    private EventService eventService;
    
    public ActivityEventProvider() {
        eventService = AppContext.getSpringBean(EventService.class);
    }
    
    @Override
    public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
        List<CalendarEvent> events = new ArrayList<CalendarEvent>();
        
        EventSearchCriteria searchCriteria = new EventSearchCriteria();
        searchCriteria.setStartDate(new DateSearchField(SearchField.AND, DateSearchField.GRREATERTHANEQUAL, startDate));
        searchCriteria.setStartDate(new DateSearchField(SearchField.AND, DateSearchField.LESSTHANEQUAL, endDate));
        
        
        log.debug("Get events from: " + startDate + " to " + endDate);
        List<SimpleEvent> crmEvents = (List<SimpleEvent>) eventService.findPagableListByCriteria(new SearchRequest<EventSearchCriteria>(searchCriteria, 0, Integer.MAX_VALUE));
        log.debug("There are " + crmEvents.size() + " from " + startDate + " to " + endDate);
        
        return events;
    }
}
