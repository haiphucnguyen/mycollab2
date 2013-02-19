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

public class MyBugListWidget extends BugDisplayWidget {
	private static final long serialVersionUID = 1L;

	public MyBugListWidget() {
		super("My Bugs", MyBugRowDisplayHandler.class);
	}

	public static class MyBugRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug obj, int rowIndex) {
			GridLayout layout = new GridLayout(2, 2);
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
			Label dateInfo = new Label("last updated on "
					+ AppContext.formatDate(obj.getLastupdatedtime()));
			dateInfo.setStyleName(UIConstants.WIDGET_ROW_METADATA);
			layout.addComponent(dateInfo, 1, 1, 1, 1);
			CssLayout rowLayout = new CssLayout();
			rowLayout.addComponent(layout);
			rowLayout.setStyleName(UIConstants.WIDGET_ROW);
			rowLayout.setWidth("100%");
			return rowLayout;
		}
	}

}
