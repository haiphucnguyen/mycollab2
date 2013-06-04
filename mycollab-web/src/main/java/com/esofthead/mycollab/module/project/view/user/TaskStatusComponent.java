package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTaskCount;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskStatusComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private GenericTaskCountList taskList;

	public TaskStatusComponent() {
		super(LocalizationHelper.getMessage(ProjectCommonI18nEnum.TASKS_TITLE),
				new VerticalLayout());

		taskList = new GenericTaskCountList();
		this.bodyContent.addComponent(new LazyLoadWrapper(taskList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	private static class GenericTaskCountList
			extends
			BeanList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTaskCount> {
		private static final long serialVersionUID = 1L;

		public GenericTaskCountList() {
			super(AppContext.getSpringBean(ProjectGenericTaskService.class),
					ActivityStreamRowDisplayHandler.class);
		}

		@Override
		public int setSearchRequest(
				SearchRequest<ProjectGenericTaskSearchCriteria> searchRequest) {
			List<ProjectGenericTaskCount> currentListData = searchService
					.findPagableTaskCountListByCriteria(searchRequest);
			loadItems(currentListData);
			return currentListData.size();
		}
	}

	public void showProjectTasksByStatus() {
		ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
		searchCriteria.setsAccountId(new NumberSearchField(AppContext
				.getAccountId()));
		searchCriteria.setIsOpenned(new SearchField());
		searchCriteria.setAssignUser(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));
		taskList.setSearchCriteria(searchCriteria);
	}

	public static class ActivityStreamRowDisplayHandler implements
			BeanList.RowDisplayHandler<ProjectGenericTaskCount> {

		private boolean isShow = false;

		@Override
		public Component generateRow(final ProjectGenericTaskCount genericTask,
				int rowIndex) {

			final VerticalLayout layout = new VerticalLayout();
			layout.setStyleName("stream-content");

			HorizontalLayout header = new HorizontalLayout();

			Button toogleButton = new Button("+", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					isShow = !isShow;
					Component component = layout.getComponent(layout
							.getComponentCount() - 1);
					if (component != null
							&& !(component instanceof GenericTaskTableDisplay)) {
						GenericTaskTableDisplay taskDisplay = new GenericTaskTableDisplay(
								new String[] { "name", "dueDate" },
								new String[] { "Name", "Due Date" });
						layout.addComponent(taskDisplay);

						ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
						searchCriteria.setAssignUser(new StringSearchField(
								AppContext.getUsername()));
						searchCriteria.setIsOpenned(new SearchField());
						searchCriteria.setsAccountId(new NumberSearchField(
								AppContext.getAccountId()));
						searchCriteria.setProjectId(new NumberSearchField(
								genericTask.getProjectId()));
						taskDisplay.setSearchCriteria(searchCriteria);
					} else {
						component.setVisible(isShow);
					}
				}
			});
			header.addComponent(toogleButton);
			header.addComponent(new Label(genericTask.getProjectName() + "/"
					+ genericTask.getAssignUser() + "/"
					+ genericTask.getTaskCount()));
			//
			// String taskType = "normal";
			// if (genericTask.getDueDate() != null
			// && (genericTask.getDueDate().before(new GregorianCalendar()
			// .getTime()))) {
			// taskType = "overdue";
			// }
			//
			// String content = LocalizationHelper.getMessage(
			// ProjectCommonI18nEnum.TASK_TITLE, ResourceResolver
			// .getResourceLink("icons/16/project/project.png"),
			// ProjectLinkBuilder.WebLinkGenerator
			// .generateProjectFullLink(
			// genericTask.getProjectId(),
			// ProjectLinkBuilder.DEFAULT_PREFIX_PARAM),
			// genericTask.getProjectName(), ProjectResources
			// .getResourceLink(genericTask.getType()),
			// ProjectLinkBuilder.WebLinkGenerator
			// .generateProjectItemLink(
			// genericTask.getProjectId(),
			// genericTask.getType(),
			// genericTask.getTypeId()), taskType,
			// genericTask.getName());
			//
			// Label taskLink = new Label(content, Label.CONTENT_XHTML);
			// header.addComponent(taskLink);
			//

			//
			// CssLayout body = new CssLayout();
			// body.setStyleName("activity-date");
			// Label dateLbl = new Label("Last updated on "
			// + DateTimeUtils.getStringDateFromNow(genericTask
			// .getLastUpdatedTime()));
			// body.addComponent(dateLbl);
			//
			// layout.addComponent(body);
			layout.addComponent(header);
			return layout;
		}
	}

}