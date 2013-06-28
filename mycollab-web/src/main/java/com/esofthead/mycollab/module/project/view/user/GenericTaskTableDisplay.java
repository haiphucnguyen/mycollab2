package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

public class GenericTaskTableDisplay
		extends
		PagedBeanTable2<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> {
	private static final long serialVersionUID = 1L;

	public GenericTaskTableDisplay(final String[] visibleColumns,
			final String[] columnHeaders) {
		super(AppContext.getSpringBean(ProjectGenericTaskService.class),
				ProjectGenericTask.class, visibleColumns, columnHeaders);

		addGeneratedColumn("name", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				HorizontalLayout layout = new HorizontalLayout();

				final ProjectGenericTask task = GenericTaskTableDisplay.this
						.getBeanByIndex(itemId);

				if (task.getType() != null) {
					Embedded icon = new Embedded(null, new ExternalResource(
							ProjectResources.getResourceLink(task.getType())));
					layout.addComponent(icon);
					layout.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
				}
				final ButtonLink b = new ButtonLink(task.getName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								String taskType = task.getType();
								PageActionChain chain = null;

								if (ProjectContants.BUG.equals(taskType)) {
									chain = new PageActionChain(
											new ProjectScreenData.Goto(task
													.getProjectId()),
											new BugScreenData.Read(task
													.getTypeId()));

								} else if (ProjectContants.TASK
										.equals(taskType)) {
									chain = new PageActionChain(
											new ProjectScreenData.Goto(task
													.getProjectId()),
											new TaskScreenData.Read(task
													.getTypeId()));
								} else if (ProjectContants.PROBLEM
										.equals(taskType)) {
									chain = new PageActionChain(
											new ProjectScreenData.Goto(task
													.getProjectId()),
											new ProblemScreenData.Read(task
													.getTypeId()));
								} else if (ProjectContants.RISK
										.equals(taskType)) {
									chain = new PageActionChain(
											new ProjectScreenData.Goto(task
													.getProjectId()),
											new RiskScreenData.Read(task
													.getTypeId()));
								}

								if (chain != null) {
									EventBus.getInstance().fireEvent(
											new ProjectEvent.GotoMyProject(
													this, chain));
								}

							}
						});

				layout.addComponent(b);
				b.setWidth("100%");
				layout.addComponent(b);
				layout.setExpandRatio(b, 1.0f);
				layout.setWidth("100%");
				return layout;
			}
		});

		addGeneratedColumn("dueDate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, final Object columnId) {
				final ProjectGenericTask task = GenericTaskTableDisplay.this
						.getBeanByIndex(itemId);
				final Label b = new Label(AppContext.formatDate(task
						.getDueDate()));
				return b;
			}
		});

		setColumnExpandRatio("name", 1);
		setColumnWidth("dueDate", UIConstants.TABLE_DATE_WIDTH);
	}
}
