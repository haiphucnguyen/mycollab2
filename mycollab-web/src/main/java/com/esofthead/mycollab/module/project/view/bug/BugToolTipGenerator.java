package com.esofthead.mycollab.module.project.view.bug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.user.UserLinkUtils;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;

public class BugToolTipGenerator {
	private static Logger log = LoggerFactory
			.getLogger(BugToolTipGenerator.class);

	public static String generateToolTip(SimpleBug bug) {
		try {
			Div div = new Div();
			H3 bugSummary = new H3();
			bugSummary.appendText(bug.getSummary());
			div.appendChild(bugSummary);

			com.hp.gagawa.java.elements.Table table = new com.hp.gagawa.java.elements.Table();
			table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font: 11px 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;");
			Tr trRow1 = new Tr();
			Td trRow1_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							(bug.getDescription() != null) ? bug
									.getDescription() : "");
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
							(bug.getEnvironment() != null) ? bug
									.getEnvironment() : "");
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
					new Td().appendText((bug.getStatus() != null) ? bug
							.getStatus() : ""));
			trRow3.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Priority:")).appendChild(
					new Td().appendText((bug.getPriority() != null) ? bug
							.getPriority() : ""));

			Tr trRow4 = new Tr();
			trRow4.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Severity:")).appendChild(
					new Td().appendText((bug.getSeverity() != null) ? bug
							.getSeverity() : ""));
			trRow4.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Resolution:")).appendChild(
					new Td().appendText((bug.getResolution() != null) ? bug
							.getResolution() : ""));

			Tr trRow5 = new Tr();
			trRow5.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Due Date:"))
					.appendChild(
							new Td().appendText(AppContext.formatDate(bug
									.getDuedate())));
			trRow5.appendChild(
					new Td().setStyle(
							"width: 110px; vertical-align: top; text-align: right;")
							.appendText("Created Time:")).appendChild(
					new Td().appendText(AppContext.formatDate(bug
							.getCreatedtime())));

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
													UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	(bug.getLogby() != null) ? bug
																			.getLogby()
																			: ""))
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					bug.getLoguserAvatarId(),
																					16)))
													.appendText(
															(bug.getLoguserFullName() != null) ? bug
																	.getLoguserFullName()
																	: "")));
			trRow6.appendChild(
					new Td().setStyle(
							"width: 70px; vertical-align: top; text-align: right;")
							.appendText("Assignee:"))
					.appendChild(
							new Td().setStyle(
									"width: 200px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
									.appendChild(
											new A().setHref(
													UserLinkUtils
															.generatePreviewFullUserLink(
																	AppContext
																			.getSiteUrl(),
																	(bug.getAssignuser() != null) ? bug
																			.getAssignuser()
																			: ""))
													.appendChild(
															new Img(
																	"",
																	UserAvatarControlFactory
																			.getAvatarLink(
																					bug.getAssignUserAvatarId(),
																					16)))
													.appendText(
															(bug.getAssignuserFullName() != null) ? bug
																	.getAssignuserFullName()
																	: "")));

			Tr trRow7 = new Tr();
			Td trRow7_value = new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(
							(bug.getMilestoneName() != null) ? bug
									.getMilestoneName() : "");
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
			log.error("Error while generate bug toolTips", e);
			return "";
		}
	}
}
