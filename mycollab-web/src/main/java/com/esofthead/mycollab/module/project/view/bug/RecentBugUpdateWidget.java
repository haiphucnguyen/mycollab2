/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
public class RecentBugUpdateWidget extends BugDisplayWidget {
	private static final long serialVersionUID = 1L;

	public RecentBugUpdateWidget() {
		super("Updated Bug Recently", RecentBugRowDisplayHandler.class);
	}

	public static class RecentBugRowDisplayHandler implements
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
			layout.addComponent(defectLink);
			layout.setColumnExpandRatio(1, 1.0f);
			Label descInfo = new Label(obj.getDescription());
			layout.addComponent(descInfo, 1, 1);

			Label dateInfo = new Label("last updated on "
					+ AppContext.formatDate(obj.getLastupdatedtime()));
			dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			layout.addComponent(dateInfo, 1, 2);
			CssLayout rowLayout = new CssLayout();
			rowLayout.addComponent(layout);
			rowLayout.setStyleName(UIConstants.WIDGET_ROW);
			rowLayout.setWidth("100%");
			return rowLayout;
		}
	}
}
