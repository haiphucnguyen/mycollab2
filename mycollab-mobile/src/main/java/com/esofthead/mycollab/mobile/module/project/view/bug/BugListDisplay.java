package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.common.i18n.DayI18nEnum;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.events.BugEvent;
import com.esofthead.mycollab.mobile.ui.DefaultPagedBeanList;
import com.esofthead.mycollab.mobile.ui.IconConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class BugListDisplay extends
		DefaultPagedBeanList<BugService, BugSearchCriteria, SimpleBug> {

	private static final long serialVersionUID = -8911176517887730007L;

	public BugListDisplay() {
		super(ApplicationContextUtil.getSpringBean(BugService.class),
				new BugRowDisplayHandler());
		this.addStyleName("bugs-list");
	}

	private static class BugRowDisplayHandler implements
			RowDisplayHandler<SimpleBug> {

		@Override
		public Component generateRow(final SimpleBug bug, int rowIndex) {
			HorizontalLayout bugRowLayout = new HorizontalLayout();
			bugRowLayout.setWidth("100%");
			bugRowLayout.setStyleName("list-item");
			bugRowLayout.setSpacing(true);

			Label bugIconLbl = new Label(
					"<span aria-hidden=\"true\" data-icon=\""
							+ IconConstants.PROJECT_BUG + "\"></span>");
			bugIconLbl.setContentMode(ContentMode.HTML);
			bugIconLbl.setWidthUndefined();
			bugIconLbl.setStyleName("bug-icon");
			bugRowLayout.addComponent(bugIconLbl);

			VerticalLayout bugInfoLayout = new VerticalLayout();
			bugInfoLayout.setWidth("100%");

			Button bugName = new Button("["
					+ CurrentProjectVariables.getProject().getShortname() + "-"
					+ bug.getBugkey() + "]: " + bug.getSummary(),
					new Button.ClickListener() {

						private static final long serialVersionUID = 2763986609736084480L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							EventBusFactory.getInstance().post(
									new BugEvent.GotoRead(this, bug.getId()));
						}
					});
			bugName.setWidth("100%");
			bugName.setStyleName("bug-name");
			bugInfoLayout.addComponent(bugName);

			Label lastUpdatedTimeLbl = new Label(AppContext.getMessage(
					DayI18nEnum.LAST_UPDATED_ON,
					AppContext.formatDateTime(bug.getLastupdatedtime())));
			lastUpdatedTimeLbl.setStyleName("bug-meta-info");
			bugInfoLayout.addComponent(lastUpdatedTimeLbl);

			Label assigneeLbl = new Label(
					AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE)
							+ (bug.getAssignuserFullName() == null ? ":&nbsp;N/A&nbsp;"
									: ":&nbsp;<span class='bug-assignee'>"
											+ bug.getAssignuserFullName()
											+ "</span>"));
			assigneeLbl.setStyleName("bug-meta-info");
			assigneeLbl.setContentMode(ContentMode.HTML);
			bugInfoLayout.addComponent(assigneeLbl);

			bugRowLayout.addComponent(bugInfoLayout);
			bugRowLayout.setExpandRatio(bugInfoLayout, 1.0f);

			return bugRowLayout;
		}

	}
}
