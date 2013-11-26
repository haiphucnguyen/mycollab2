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
package com.esofthead.mycollab.common.ui.components;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;

public class ProjectTooltipGenerator {
	private static Logger log = LoggerFactory
			.getLogger(ProjectTooltipGenerator.class);

	public static String generateToolTipTask(SimpleTask task, String siteURL,
			String timeZone) {
		try {
			if (task == null)
				return null;
			Div div = new Div();
			H3 taksName = new H3();
			taksName.appendText(Jsoup.parse(task.getTaskname()).html());
			div.appendChild(taksName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:12px;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Start Date:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									task.getStartdate(), timeZone)));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Actual Start Date:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									task.getActualstartdate(), timeZone)));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("End Date:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									task.getEnddate(), timeZone)));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Actual End Date:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									task.getActualenddate(), timeZone)));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Deadline:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									task.getDeadline(), timeZone)));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Priority:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(task
							.getPriority())));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(task.getAssignuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	task.getAssignuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					task.getAssignUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(task
																			.getAssignUserFullName()))));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("TaskGroup:"))
					.appendChild(
							new Td().setStyle(
									"width: 200px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils
													.getStringFieldValue(task
															.getTaskListName())));

			Tr trRow5 = new Tr();
			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Complete(%):"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils.getStringFieldValue(task
													.getPercentagecomplete())));
			Tr trRow6 = new Tr();
			Td trRow6_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(task.getNotes()));
			trRow6_value.setAttribute("colspan", "3");
			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Notes:")).appendChild(trRow6_value);

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow5);
			table.appendChild(trRow6);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error(
					"Error while generate tooltip for servlet project-task tooltip",
					e);
			return null;
		}
	}

	public static String generateToolTipBug(SimpleBug bug, String siteURL,
			String timeZone) {
		try {
			if (bug == null)
				return null;
			Div div = new Div();
			H3 bugSummary = new H3();
			bugSummary.appendText(Jsoup.parse(bug.getSummary()).html());
			div.appendChild(bugSummary);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:12px;");
			Tr trRow1 = new Tr();
			Td trRow1_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(bug
									.getDescription()));
			trRow1_value.setAttribute("colspan", "3");
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow1_value);

			Tr trRow2 = new Tr();
			Td trRow2_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(bug
									.getEnvironment()));
			trRow2_value.setAttribute("colspan", "3");
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Environment:")).appendChild(
					trRow2_value);

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Status:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(bug
							.getStatus())));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Priority:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(bug
							.getPriority())));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Severity:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(bug
							.getSeverity())));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Resolution:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(bug
							.getResolution())));

			Tr trRow5 = new Tr();
			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Due Date:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									bug.getDuedate(), timeZone)));
			trRow5.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Created Time:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									bug.getCreatedtime(), timeZone)));

			// Assignee

			Tr trRow6 = new Tr();
			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Logged by:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(bug.getLogby() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	(bug.getLogby() != null) ? bug
																			.getLogby()
																			: "")
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					bug.getLoguserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(bug
																			.getLoguserFullName()))));
			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 200px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(bug.getAssignuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	(bug.getAssignuser() != null) ? bug
																			.getAssignuser()
																			: "")
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					bug.getAssignUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(bug
																			.getAssignuserFullName()))));

			Tr trRow7 = new Tr();
			Td trRow7_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(bug
									.getMilestoneName()));
			trRow7_value.setAttribute("colspan", "3");
			trRow7.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Phase name:")).appendChild(
					trRow7_value);

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow5);
			table.appendChild(trRow6);
			table.appendChild(trRow7);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error(
					"Error while generate tooltip for servlet project-bug tooltip",
					e);
			return null;
		}
	}

	public static String generateToolTipRisk(SimpleRisk risk, String siteURL,
			String timeZone) {
		try {
			if (risk == null)
				return null;
			Div div = new Div();
			H3 riskName = new H3();
			riskName.appendText(risk.getRiskname());
			div.appendChild(riskName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:12px;");

			Tr trRow5 = new Tr();
			Td trRow5_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(risk
									.getDescription()));
			trRow5_value.setAttribute("colspan", "3");

			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow5_value);

			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Raised by:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(risk.getRaisedbyuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	risk.getRaisedbyuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					risk.getRaisedByUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(risk
																			.getRaisedByUserFullName()))));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Consequence:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(risk
							.getConsequence())));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(risk.getAssigntouser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	risk.getAssigntouser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					risk.getAssignToUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(risk
																			.getAssignedToUserFullName()))));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Probability:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(risk
							.getProbalitity())));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Date due:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									risk.getDatedue(), timeZone)));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Rating:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(risk
							.getLevel())));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Status:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(risk
							.getStatus())));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Related to:")).appendChild(
					new Td().appendText(""));

			Tr trRow6 = new Tr();
			Td trRow6_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(risk.getResponse()));
			trRow6_value.setAttribute("colspan", "3");

			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Response:")).appendChild(trRow6_value);

			table.appendChild(trRow5);
			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow6);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error(
					"Error while generate tooltip for Risk in TooptipGeneratorServlet",
					e);
			return null;
		}
	}

	public static String generateToolTipProblem(SimpleProblem problem,
			String siteURL, String timeZone) {
		try {
			if (problem == null)
				return null;
			Div div = new Div();
			H3 problemName = new H3();
			problemName.appendText(problem.getIssuename());
			div.appendChild(problemName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:12px;");

			Tr trRow5 = new Tr();
			Td trRow5_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(problem
									.getDescription()));
			trRow5_value.setAttribute("colspan", "3");

			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow5_value);

			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Raised by:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(problem.getRaisedbyuser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	problem.getRaisedbyuser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					problem.getRaisedByUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(problem
																			.getRaisedByUserFullName()))));
			trRow1.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Impact:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(problem
							.getImpact())));

			Tr trRow2 = new Tr();
			trRow2.appendChild(
					new Td().setStyle(
							"width: 80px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(problem.getAssigntouser() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	problem.getAssigntouser())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					problem.getAssignUserAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(problem
																			.getAssignedUserFullName()))));
			trRow2.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Priority:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(problem
							.getPriority())));

			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Date due:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									problem.getDatedue(), timeZone)));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Rating:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(problem
							.getLevel())));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Status:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(problem
							.getStatus())));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Related to:")).appendChild(
					new Td().appendText(""));

			Tr trRow6 = new Tr();
			Td trRow6_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(problem
									.getResolution()));
			trRow6_value.setAttribute("colspan", "3");

			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Resolution:")).appendChild(
					trRow6_value);

			table.appendChild(trRow5);
			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			table.appendChild(trRow4);
			table.appendChild(trRow6);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error(
					"Error while generator tooltip for Problem in TooltipGenertor Servlet",
					e);
			return null;
		}
	}

	public static String generateToolTipVersion(SimpleVersion version,
			String siteURL, String timeZone) {
		try {
			if (version == null)
				return null;
			Div div = new Div();
			H3 versionName = new H3();
			versionName
					.appendText(Jsoup.parse(version.getVersionname()).html());
			div.appendChild(versionName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :300px; color: #5a5a5a; font-size:12px;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Version Name:")).appendChild(
					new Td().appendText(StringUtils.getStringFieldValue(version
							.getVersionname())));

			Tr trRow2 = new Tr();

			Td trRow2_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(version
									.getDescription()));
			trRow2_value.setAttribute("colspan", "3");
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow2_value);
			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Due Date:")).appendChild(
					new Td().appendText(DateTimeUtils
							.converToStringWithUserTimeZone(
									version.getDuedate(), timeZone)));

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error("Error while generate tooltip for Version", e);
			return null;
		}
	}

	public static String generateToolTipComponent(SimpleComponent component,
			String siteURL, String timeZone) {
		try {
			if (component == null)
				return null;
			Div div = new Div();
			H3 componentName = new H3();
			componentName.appendText(Jsoup.parse(component.getComponentname())
					.html());
			div.appendChild(componentName);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :300px; color: #5a5a5a; font-size:12px;");
			Tr trRow1 = new Tr();
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Component Name:"))
					.appendChild(
							new Td().appendText(StringUtils
									.getStringFieldValue(component
											.getComponentname())));

			Tr trRow2 = new Tr();
			Td trRow2_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(component
									.getDescription()));
			trRow2_value.setAttribute("colspan", "3");
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow2_value);
			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Lead:"))
					.appendChild(
							new Td().setStyle(
									"width: 150px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(component.getUserlead() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	component
																			.getUserlead())
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					component
																							.getUserLeadAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(component
																			.getUserLeadFullName()))));

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error("Error while generate tooltip for Component", e);
			return null;
		}
	}

	public static String generateToolTipTaskList(SimpleTaskList taskList,
			String siteURL, String timeZone) {
		try {
			if (taskList == null)
				return null;
			Div div = new Div();
			H3 bugSummary = new H3();
			bugSummary.appendText(Jsoup.parse(taskList.getName()).html());
			div.appendChild(bugSummary);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:12px;");
			Tr trRow1 = new Tr();
			Td trRow1_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(taskList.getName()));
			trRow1_value.setAttribute("colspan", "3");
			trRow1.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("TaskGroup:"))
					.appendChild(trRow1_value);

			Tr trRow2 = new Tr();
			Td trRow2_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							StringUtils.getStringFieldValue(taskList
									.getDescription()));
			trRow2_value.setAttribute("colspan", "3");
			trRow2.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Description:")).appendChild(
					trRow2_value);
			// Assignee
			Tr trRow3 = new Tr();
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 200px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													(taskList.getOwner() != null) ? UserLinkUtils
															.generatePreviewFullUserLink(
																	siteURL,
																	(taskList
																			.getOwner() != null) ? taskList
																			.getOwner()
																			: "")
															: "")
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					taskList.getOwnerAvatarId(),
																					16)))
													.appendText(
															StringUtils
																	.getStringFieldValue(taskList
																			.getOwnerFullName()))));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Related Milestone:"))
					.appendChild(
							new Td().setStyle(
									"width: 200px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendText(
											StringUtils
													.getStringFieldValue(taskList
															.getMilestoneName())));

			table.appendChild(trRow1);
			table.appendChild(trRow2);
			table.appendChild(trRow3);
			div.appendChild(table);
			return div.write();
		} catch (Exception e) {
			log.error("Error while generate tooltip for TaskGroup", e);
			return null;
		}
	}

	public static String generateToolTipStandUp(SimpleStandupReport standup,
			String siteURL, String timeZone) {
		return null;
	}
}
