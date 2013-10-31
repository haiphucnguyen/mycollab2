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

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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
	private MeetingService meetingService;

	public ActivityEventProvider() {
		meetingService = ApplicationContextUtil
				.getSpringBean(MeetingService.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarEvent> getEvents(Date startDate, Date endDate) {
		List<CalendarEvent> events = new ArrayList<CalendarEvent>();

		MeetingSearchCriteria searchCriteria = new MeetingSearchCriteria();
		searchCriteria.setStartDate(new DateSearchField(SearchField.AND,
				DateTimeSearchField.GREATERTHANEQUAL, startDate));
		searchCriteria.setEndDate(new DateSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, endDate));

		log.debug("Get events from: " + startDate + " to " + endDate);
		List<SimpleMeeting> crmEvents = (List<SimpleMeeting>) meetingService
				.findPagableListByCriteria(new SearchRequest<MeetingSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		log.debug("There are " + crmEvents.size() + " events from " + startDate
				+ " to " + endDate);

		if (crmEvents != null && crmEvents.size() > 0) {
			for (SimpleMeeting crmEvent : crmEvents) {
				if (crmEvent.getStartdate() == null
						|| crmEvent.getEnddate() == null) {
					continue;
				} else {
					CrmEvent event = new CrmEvent();
					event.setCaption(crmEvent.getSubject());
					event.setDescription(crmEvent.getDescription());
					event.setStart(crmEvent.getStartdate());
					event.setEnd(crmEvent.getEnddate());
					event.setSource(crmEvent);

					events.add(event);
				}
			}
		}

		return events;
	}

	public static class CrmEvent extends BasicEvent {
		private static final long serialVersionUID = 1L;
		private SimpleMeeting source;

		public SimpleMeeting getSource() {
			return source;
		}

		public void setSource(SimpleMeeting source) {
			this.source = source;
		}
	}
}
