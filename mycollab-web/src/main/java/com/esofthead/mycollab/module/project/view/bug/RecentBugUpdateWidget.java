/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.localization.BugI18nEnum;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.LabelHTMLDisplayWidget;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
public class RecentBugUpdateWidget extends BugDisplayWidget {
	public static class RecentBugRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug obj, final int rowIndex) {
			final GridLayout layout = new GridLayout(2, 4);
			layout.setWidth("100%");
			layout.setSpacing(false);
			layout.addComponent(new Embedded(null, new ThemeResource(
					"icons/22/project/bug.png")), 0, 0, 0, 1);

			final ButtonLink defectLink = new ButtonLink("["
					+ CurrentProjectVariables.getProject().getShortname() + "-"
					+ obj.getBugkey() + "]: " + obj.getSummary(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final Button.ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(this, obj.getId()));
						}
					});
			defectLink.setWidth("100%");

			if (BugStatusConstants.CLOSE.equals(obj.getStatus())) {
				defectLink.addStyleName(UIConstants.LINK_COMPLETED);
			} else if (obj.getDuedate() != null
					&& (obj.getDuedate().before(new GregorianCalendar()
							.getTime()))) {
				defectLink.addStyleName(UIConstants.LINK_OVERDUE);
			}
			layout.addComponent(defectLink);
			layout.setColumnExpandRatio(1, 1.0f);

			final LabelHTMLDisplayWidget descInfo = new LabelHTMLDisplayWidget(
					obj.getDescription());
			descInfo.setWidth("100%");
			layout.addComponent(descInfo);

			final Label dateInfo = new Label("Last updated on "
					+ AppContext.formatDateTime(obj.getLastupdatedtime()));
			dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			layout.addComponent(dateInfo, 1, 2);

			final HorizontalLayout hLayoutAssigneeInfo = new HorizontalLayout();
			hLayoutAssigneeInfo.setSpacing(true);
			final Label assignee = new Label("Assignee: ");
			assignee.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			hLayoutAssigneeInfo.addComponent(assignee);
			hLayoutAssigneeInfo.setComponentAlignment(assignee,
					Alignment.MIDDLE_CENTER);

			final ProjectUserLink userLink = new ProjectUserLink(
					obj.getAssignuser(), obj.getAssignuserFullName(), false,
					true);
			hLayoutAssigneeInfo.addComponent(userLink);
			hLayoutAssigneeInfo.setComponentAlignment(userLink,
					Alignment.MIDDLE_CENTER);
			layout.addComponent(hLayoutAssigneeInfo, 1, 3);

			final CssLayout rowLayout = new CssLayout();
			rowLayout.addComponent(layout);
			rowLayout.setStyleName(UIConstants.WIDGET_ROW);
			if ((rowIndex + 1) % 2 != 0) {
				rowLayout.addStyleName("odd");
			}
			rowLayout.setWidth("100%");
			return rowLayout;
		}
	}

	private static final long serialVersionUID = 1L;

	public RecentBugUpdateWidget() {
		super(LocalizationHelper
				.getMessage(BugI18nEnum.UPDATED_RECENTLY_WIDGET_TITLE),
				RecentBugRowDisplayHandler.class);
	}

	@Override
	protected BugSearchParameter constructMoreDisplayFilter() {
		return new BugSearchParameter("Recent Bugs", searchCriteria);
	}
}
