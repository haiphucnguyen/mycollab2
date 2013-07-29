package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class BugSimpleDisplayWidget extends
		BeanList<BugService, BugSearchCriteria, SimpleBug> {
	private static final long serialVersionUID = 1L;

	public BugSimpleDisplayWidget() {
		super(null, AppContext.getSpringBean(BugService.class),
				TaskRowDisplayHandler.class, false);
	}

	public static class TaskRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug bug, int rowIndex) {
			HorizontalLayout layout = new HorizontalLayout();
			Button bugLink = new Button("Issue #" + bug.getBugkey() + ": ",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoRead(this, bug.getId()));
						}
					});
			bugLink.setStyleName("link");
			if (bug.isCompleted()) {
				bugLink.addStyleName(UIConstants.LINK_COMPLETED);
			} else if (bug.isOverdue()) {
				bugLink.addStyleName(UIConstants.LINK_OVERDUE);
			}
			layout.addComponent(bugLink);
			layout.addComponent(new Label(bug.getSummary()));
			return layout;
		}
	}
}
