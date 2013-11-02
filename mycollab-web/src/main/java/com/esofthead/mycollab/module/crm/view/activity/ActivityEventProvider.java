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

					StringBuffer statusStr = new StringBuffer("");
					event.setStart(crmEvent.getStartdate());
					event.setEnd(crmEvent.getEnddate());
					event.setSource(crmEvent);
					if (crmEvent.getStatus() != null) {
						if ("Held".equals(crmEvent.getStatus())) {
							event.setStyleName("eventcomplete");
							statusStr
									.append("<span style=\"background-color: #96D794;\">");
						} else if ("Planned".equals(crmEvent.getStatus())) {
							event.setStyleName("eventfuture");
							statusStr
									.append("<span style=\"background-color: #99C4DD;\">");
						} else if ("Not Held".equals(crmEvent.getStatus())) {
							if (crmEvent.getEnddate() != null) {
								if (crmEvent.getEnddate().compareTo(new Date()) == 0) {
									event.setStyleName("eventoverdue");
									statusStr
											.append("<span style=\"background-color: #EF8585;\">");
								} else if (crmEvent.getEnddate().compareTo(
										new Date()) > 0) {
									event.setStyleName("eventfuture");
									statusStr
											.append("<span style=\"background-color: #99C4DD;\">");
								} else {
									event.setStyleName("eventoverdue");
									statusStr
											.append("<span style=\"background-color: #EF8585;\">");
								}
							}
						}

					}
					statusStr.append(crmEvent.getStatus());
					statusStr.append("</span>");
					String desTooltip = "<h3>Title</h3>"
							+ "<table style=\"padding-left:10px; width:350px; color: #5a5a5a; font: 10px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;\""
							+ "<tr>"
							+ "<td style=\"font-weight:bold; width:70px;\">Start Date:</td>"
							+ "<td>"
							+ AppContext
									.formatDateTime(crmEvent.getStartdate())
							+ "</td>"
							+ "</tr>"
							+ "<td style=\"font-weight:bold; width:70px;\">End Date: </td>"
							+ "<td>"
							+ AppContext.formatDateTime(crmEvent.getEnddate())
							+ "</td>"
							+ "<tr>"
							+ "<tr>"
							+ "<td style=\"font-weight:bold; width:70px;\">Status:</td>"
							+ "<td>"
							+ statusStr.toString()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td style=\"text-align: right; vertical-align: top; font-weight:bold; width:70px;\">Description:</td>"
							+ "<td style=\"word-wrap: break-word; white-space: normal; word-break: break-all;\">tesataedatesataedatesataedatesataedatesataedatesataedatesataedatesataedatesataeda</td>"
							+ "</tr>" + "</table>";
					event.setDescription(desTooltip);
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
