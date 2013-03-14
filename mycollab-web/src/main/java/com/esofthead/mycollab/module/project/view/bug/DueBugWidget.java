/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.LabelHTMLDisplayWidget;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
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
public class DueBugWidget extends BugDisplayWidget {
	private static final long serialVersionUID = 1L;

	public DueBugWidget() {
		super("Due Bugs", DueBugRowDisplayHandler.class);
	}

	public static class DueBugRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug obj, int rowIndex) {
			GridLayout layout = new GridLayout(2, 3);
			layout.setWidth("100%");
			layout.setSpacing(false);
			layout.addComponent(new Embedded(null, new ThemeResource(
					"icons/22/project/bug.png")), 0, 0, 0, 1);

			ButtonLink defectLink = new ButtonLink(obj.getSummary(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(this, obj.getId()));
						}
					});
			defectLink.setWidth("70%");

			if (obj.getDuedate() != null
					&& (obj.getDuedate().before(new GregorianCalendar()
							.getTime()))) {
				defectLink.addStyleName(UIConstants.LINK_OVERDUE);
			}

			layout.addComponent(defectLink);
			layout.setColumnExpandRatio(1, 1.0f);

			LabelHTMLDisplayWidget descInfo = new LabelHTMLDisplayWidget(
					obj.getDescription());
			descInfo.setWidth("70%");
			layout.addComponent(descInfo);

			HorizontalLayout hLayoutDateInfo = new HorizontalLayout();
			hLayoutDateInfo.setSpacing(true);
			Label dateInfo = new Label("due on "
					+ AppContext.formatDate(obj.getDuedate()) + ". Status: "
					+ obj.getStatus() + ". Assignee: ");
			dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			hLayoutDateInfo.addComponent(dateInfo);
			hLayoutDateInfo.setComponentAlignment(dateInfo,
					Alignment.MIDDLE_CENTER);

			ProjectUserFormLinkField userLink = new ProjectUserFormLinkField(
					obj.getAssignuser(), obj.getAssignuserFullName());
			hLayoutDateInfo.addComponent(userLink);
			hLayoutDateInfo.setComponentAlignment(userLink,
					Alignment.MIDDLE_CENTER);

			layout.addComponent(hLayoutDateInfo, 1, 2);

			CssLayout rowLayout = new CssLayout();
			rowLayout.addComponent(layout);
			rowLayout.setStyleName(UIConstants.WIDGET_ROW);
			rowLayout.setWidth("100%");
			return rowLayout;
		}
	}

	@Override
	protected BugSearchParameter constructMoreDisplayFilter() {
		return new BugSearchParameter("Due Bugs", searchCriteria);
	}
}
