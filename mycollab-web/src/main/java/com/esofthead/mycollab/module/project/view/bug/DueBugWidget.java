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
public class DueBugWidget extends BugDisplayWidget {
	private static final long serialVersionUID = 1L;

	public DueBugWidget() {
		super(LocalizationHelper.getMessage(BugI18nEnum.DUE_BUGS_WIDGET_TITLE),
				DueBugRowDisplayHandler.class);
	}

	public static class DueBugRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug obj, int rowIndex) {
			GridLayout layout = new GridLayout(2, 4);
			layout.setWidth("100%");
			layout.setSpacing(false);
			layout.addComponent(new Embedded(null, new ThemeResource(
					"icons/22/project/bug.png")), 0, 0, 0, 3);

			ButtonLink defectLink = new ButtonLink("["
					+ CurrentProjectVariables.getProject().getShortname() + "-"
					+ obj.getBugkey() + "]: " + obj.getSummary(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(this, obj.getId()));
						}
					});
			defectLink.setWidth("100%");

			if (obj.getDuedate() != null
					&& (obj.getDuedate().before(new GregorianCalendar()
							.getTime()))) {
				defectLink.addStyleName(UIConstants.LINK_OVERDUE);
			}

			layout.addComponent(defectLink);
			layout.setColumnExpandRatio(1, 1.0f);

			LabelHTMLDisplayWidget descInfo = new LabelHTMLDisplayWidget(
					obj.getDescription());
			descInfo.setWidth("100%");
			layout.addComponent(descInfo);

			Label dateInfo = new Label("Due on "
					+ AppContext.formatDate(obj.getDuedate()) + ". Status: "
					+ obj.getStatus());
			dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			layout.addComponent(dateInfo, 1, 2);

			HorizontalLayout hLayoutDateInfo = new HorizontalLayout();
			hLayoutDateInfo.setSpacing(true);
			Label lbAssignee = new Label("Assignee: ");
			lbAssignee.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			hLayoutDateInfo.addComponent(lbAssignee);
			hLayoutDateInfo.setComponentAlignment(lbAssignee,
					Alignment.MIDDLE_CENTER);

			ProjectUserLink userLink = new ProjectUserLink(
					obj.getAssignuser(), obj.getAssignuserFullName(), true);
			userLink.removeStyleName(UIConstants.WORD_WRAP);
			hLayoutDateInfo.addComponent(userLink);
			hLayoutDateInfo.setComponentAlignment(userLink,
					Alignment.MIDDLE_CENTER);

			layout.addComponent(hLayoutDateInfo, 1, 3);

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
