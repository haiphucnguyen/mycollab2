/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.service.EventService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.event.CalendarEventProvider;

/**
 * 
 * @author haiphucnguyen
 */
public class ActivityEventProvider implements CalendarEventProvider {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ActivityEventProvider.class);
	private EventService eventService;

	public ActivityEventProvider() {
		eventService = AppContext.getSpringBean(EventService.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();

		EventSearchCriteria searchCriteria = new EventSearchCriteria();
		searchCriteria.setStartDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.GREATERTHANEQUAL, startDate));
		searchCriteria.setStartDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, endDate));

		log.debug("Get events from: " + startDate + " to " + endDate);
		List<SimpleEvent> crmEvents = (List<SimpleEvent>) eventService
				.findPagableListByCriteria(new SearchRequest<EventSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		log.debug("There are " + crmEvents.size() + " events from " + startDate
				+ " to " + endDate);

		if (crmEvents != null && crmEvents.size() > 0) {
			for (SimpleEvent crmEvent : crmEvents) {
				CrmEvent event = new CrmEvent();
				event.setCaption(crmEvent.getSubject());
				event.setDescription(crmEvent.getDescription());
				event.setStart(crmEvent.getStartDate());
				event.setEnd(crmEvent.getEndDate());
				event.setSource(crmEvent);

				events.add(event);
			}
		}

		return events;
	}

	public static class CrmEvent extends BasicEvent {
		private static final long serialVersionUID = 1L;
		private SimpleEvent source;

		public SimpleEvent getSource() {
			return source;
		}

		public void setSource(SimpleEvent source) {
			this.source = source;
		}
	}
}
