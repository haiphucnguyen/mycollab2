package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@ViewComponent
public class FollowingTicketViewImpl extends AbstractView implements
		FollowingTicketView {
	private static final long serialVersionUID = 1L;

	private FollowingTicketTable ticketTable;

	public FollowingTicketViewImpl() {
		this.addComponent(new Label("Display tickets"));

		ticketTable = new FollowingTicketTable();
		this.addComponent(ticketTable);
	}

	@Override
	public void displayFollowingTicket(List<Integer> prjKeys) {
		MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(prjKeys
				.toArray(new Integer[0])));
		searchCriteria.setUser(new StringSearchField(AppContext.getUsername()));
		ticketTable.setSearchCriteria(searchCriteria);

	}

	private class FollowingTicketTable extends
			AbstractPagedBeanTable<MonitorSearchCriteria, FollowingTicket> {

		private static final long serialVersionUID = 1L;
		private ProjectService projectService;

		public FollowingTicketTable() {
			super(FollowingTicket.class, new String[] { "summary",
					"projectName", "assignUser" }, new String[] { "Summary",
					"Project", "Assignee" });

			projectService = AppContext.getSpringBean(ProjectService.class);

			this.addGeneratedColumn("summary", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, Object itemId,
						Object columnId) {
					final FollowingTicket ticket = FollowingTicketTable.this
							.getBeanByIndex(itemId);
					ButtonLink ticketLink = new ButtonLink(ticket.getSummary());
					ticketLink.addListener(new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if ("Bug".equals(ticket.getType())) {
								int projectId = ticket.getProjectId();
								int bugId = ticket.getTypeId();
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new BugScreenData.Read(bugId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if ("Task".equals(ticket.getType())) {
								int projectId = ticket.getProjectId();
								int taskId = ticket.getTypeId();
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new TaskScreenData.Read(taskId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							}

						}
					});

					if ("Bug".equals(ticket.getType())) {
						ticketLink.setIcon(MyCollabResource
								.newResource("icons/16/project/bug.png"));
					} else if ("Task".equals(ticket.getType())) {
						ticketLink.setIcon(MyCollabResource
								.newResource("icons/16/project/task.png"));
					}
					return ticketLink;
				}
			});

			this.setColumnWidth("projectName", UIConstants.TABLE_X_LABEL_WIDTH);
			this.setColumnWidth("assignUser", UIConstants.TABLE_X_LABEL_WIDTH);
			this.setColumnExpandRatio("summary", 1.0f);
		}

		@Override
		protected int queryTotalCount() {
			return projectService.getTotalFollowingTickets(searchRequest
					.getSearchCriteria());
		}

		@Override
		protected List<FollowingTicket> queryCurrentData() {
			return projectService.getProjectFollowingTickets(searchRequest);
		}

	}
}
