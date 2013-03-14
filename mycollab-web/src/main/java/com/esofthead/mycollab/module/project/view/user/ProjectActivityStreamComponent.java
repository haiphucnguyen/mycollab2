/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserLink;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectActivityStreamComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private final DefaultBeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream> activityStreamList;
	private static LabelStringGenerator menuLinkGenerator = new ActivityLinkLabelStringGenerator();

	public ProjectActivityStreamComponent() {
		super("Project Feeds", new VerticalLayout());
		activityStreamList = new DefaultBeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(
				AppContext.getSpringBean(ActivityStreamService.class),
				ActivityStreamRowDisplayHandler.class, 10);
		this.bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjectFeeds() {
		ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.PRJ }));

		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
				CurrentProjectVariables.getProjectId()));
		activityStreamList.setSearchCriteria(searchCriteria);
	}

	public static class ActivityStreamRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleActivityStream> {

		@Override
		public Component generateRow(SimpleActivityStream activityStream,
				int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");
			header.addComponent(new ProjectUserLink(activityStream
					.getCreateduser(), activityStream.getCreatedUserFullName(),
					true));
			StringBuilder action = new StringBuilder();

			if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream
					.getAction())) {
				action.append("create a new ");
			} else if (ActivityStreamConstants.ACTION_UPDATE
					.equals(activityStream.getAction())) {
				action.append("update ");
			}

			action.append(activityStream.getType());
			Label actionLbl = new Label(action.toString());
			actionLbl.setWidth(Sizeable.SIZE_UNDEFINED, 0);
			header.addComponent(actionLbl);
			// header.setComponentAlignment(actionLbl, Alignment.TOP_CENTER);
			header.addComponent(new ActivitylLink(activityStream.getType(),
					activityStream.getNamefield(), activityStream.getTypeid()));
			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			Label dateLbl = new Label(
					DateTimeUtils.getStringDateFromNow(activityStream
							.getCreatedtime()));
			body.addComponent(dateLbl);

			layout.addComponent(body);
			return layout;
		}
	}

	private static class ActivityLinkLabelStringGenerator implements
			LabelStringGenerator {

		@Override
		public String handleText(String value) {
			int limitValue = 45;
			if (ScreenSize.hasSupport1024Pixels()) {
				limitValue = 35;
			}
			if (value.length() > limitValue) {
				return value.substring(0, limitValue) + "...";
			}
			return value;
		}

	}

	private static class ActivitylLink extends Button {
		private static final long serialVersionUID = 1L;

		public ActivitylLink(final String type, final String fieldName,
				final int typeid) {
			super(menuLinkGenerator.handleText(fieldName));

			this.setDescription(fieldName);
			this.setIcon(ProjectResources.getIconResource16size(type));
			this.setStyleName("link");

			this.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(Button.ClickEvent event) {
					if (ProjectContants.PROJECT.equals(type)) {
					} else if (ProjectContants.MESSAGE.equals(type)) {
						EventBus.getInstance().fireEvent(
								new MessageEvent.GotoRead(this, typeid));
					} else if (ProjectContants.MILESTONE.equals(type)) {
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoRead(this, typeid));
					} else if (ProjectContants.PROBLEM.equals(type)) {
						EventBus.getInstance().fireEvent(
								new ProblemEvent.GotoRead(this, typeid));
					} else if (ProjectContants.RISK.equals(type)) {
						EventBus.getInstance().fireEvent(
								new RiskEvent.GotoRead(this, typeid));
					} else if (ProjectContants.TASK.equals(type)) {
						EventBus.getInstance().fireEvent(
								new TaskEvent.GotoRead(this, typeid));
					} else if (ProjectContants.TASK_LIST.equals(type)) {
						EventBus.getInstance().fireEvent(
								new TaskListEvent.GotoRead(this, typeid));
					} else if (ProjectContants.BUG.equals(type)) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoRead(this, typeid));
					} else if (ProjectContants.BUG_COMPONENT.equals(type)) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoRead(this, typeid));
					} else if (ProjectContants.BUG_VERSION.equals(type)) {
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoRead(this, typeid));
					}
				}
			});
		}
	}
}
