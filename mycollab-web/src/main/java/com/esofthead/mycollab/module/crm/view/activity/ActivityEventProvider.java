/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
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
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.event.CalendarEventProvider;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
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
		searchCriteria.setStartDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.GREATERTHANEQUAL, startDate));
		searchCriteria.setEndDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, endDate));

		log.debug("Get events from: " + startDate + " to " + endDate);
		List<SimpleMeeting> crmEvents = (List<SimpleMeeting>) meetingService
				.findPagableListByCriteria(new SearchRequest<MeetingSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		log.debug("There are " + crmEvents.size() + " events from " + startDate
				+ " to " + endDate);

		filterListEventRecurringActivity(crmEvents, startDate, endDate);

		if (crmEvents != null && crmEvents.size() > 0) {
			for (SimpleMeeting crmEvent : crmEvents) {
				if (crmEvent.getStartdate() == null
						|| crmEvent.getEnddate() == null) {
					continue;
				} else {
					CrmEvent event = new CrmEvent();
					event.setCaption(crmEvent.getSubject());

					StringBuffer statusStr = new StringBuffer("");
					statusStr.append("<span>");
					event.setStart(crmEvent.getStartdate());
					event.setEnd(crmEvent.getEnddate());
					event.setSource(crmEvent);
					if (crmEvent.getStatus() != null) {
						if ("Held".equals(crmEvent.getStatus())) {
							event.setStyleName("eventcomplete");
						} else if ("Planned".equals(crmEvent.getStatus())) {
							event.setStyleName("eventfuture");
						} else if ("Not Held".equals(crmEvent.getStatus())) {
							if (crmEvent.getEnddate() != null) {
								if (crmEvent.getEnddate().compareTo(new Date()) == 0) {
									event.setStyleName("eventoverdue");
								} else if (crmEvent.getEnddate().compareTo(
										new Date()) > 0) {
									event.setStyleName("eventfuture");
								} else {
									event.setStyleName("eventoverdue");
								}
							}
						}

					} else {
						event.setStyleName("eventfuture");
					}
					if (crmEvent.getStatus() != null) {
						statusStr.append(crmEvent.getStatus());
					} else {
						statusStr.append("");
					}
					statusStr.append("</span>");
					String crmEventDes = (crmEvent.getDescription() != null) ? crmEvent
							.getDescription() : "";
					String desTooltip = String
							.format("<h3>%s</h3><table style=\"padding-left:10px; width:350px; color: #5a5a5a;\"<tr><td style=\"font-weight:bold; width:70px;\">Start Date:</td><td>%s</td></tr><td style=\"font-weight:bold; width:70px;\">End Date: </td><td>%s</td><tr><tr><td style=\"font-weight:bold; width:70px;\">Status:</td><td>%s</td></tr><tr><td style=\"text-align: right; vertical-align: top; font-weight:bold; width:70px;\">Description:</td><td style=\"word-wrap: break-word; white-space: normal; word-break: break-all;\">%s</td></tr></table>",
									crmEvent.getSubject(), AppContext
											.formatDateTime(crmEvent
													.getStartdate()),
									AppContext.formatDateTime(crmEvent
											.getEnddate()), statusStr
											.toString(), crmEventDes);
					event.setDescription(desTooltip);
					events.add(event);
				}
			}
		}

		return events;
	}

	private void filterListEventRecurringActivity(
			final List<SimpleMeeting> crmEvents, Date eventStartDate,
			Date eventEndDate) {
//		for (SimpleMeeting meeting : crmEvents) {
//			if (meeting.getIsrecurrence()) {
//				// TODO:Revise this implementation
//				if (meeting.getRecurrencetype() != null) {
//					if (meeting.getRecurrencetype().equals("DailyEvent")) {
//						DailyEvent dailyEvent = JsonDeSerializer.fromJson(
//								meeting.getRecurrenceinfo(), DailyEvent.class);
//					} else if (meeting.getRecurrencetype()
//							.equals("WeeklyEvent")) {
//						WeeklyEvent weeklyEvent = JsonDeSerializer.fromJson(
//								meeting.getRecurrenceinfo(), WeeklyEvent.class);
//					} else if (meeting.getRecurrencetype().equals(
//							"MonthlyEventFollowDay")) {
//						MonthlyEventFollowDay monthEventFollowDay = JsonDeSerializer
//								.fromJson(meeting.getRecurrenceinfo(),
//										MonthlyEventFollowDay.class);
//					} else if (meeting.getRecurrencetype().equals(
//							"MonthlyEventFollowKindDay")) {
//						MonthlyEventFollowKindDay monthEventFollowKindDay = JsonDeSerializer
//								.fromJson(meeting.getRecurrenceinfo(),
//										MonthlyEventFollowKindDay.class);
//					} else if (meeting.getRecurrencetype().equals(
//							"YearlyEventFollowEveryMonth")) {
//						YearlyEventFollowEveryMonth yearlyEventFollowEveryMonth = JsonDeSerializer
//								.fromJson(meeting.getRecurrenceinfo(),
//										YearlyEventFollowEveryMonth.class);
//					} else if (meeting.getRecurrencetype().equals(
//							"YearlyEventFollowAdvanceSettingMonth")) {
//						YearlyEventFollowAdvanceSettingMonth yearlyEventFollowAdvanceSettingMonth = JsonDeSerializer
//								.fromJson(
//										meeting.getRecurrenceinfo(),
//										YearlyEventFollowAdvanceSettingMonth.class);
//					}
//				}
//			}
//		}
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
