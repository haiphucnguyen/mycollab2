package com.esofthead.mycollab.module.project.view;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.FollowingTicket;
import com.esofthead.mycollab.module.project.domain.criteria.FollowingTicketSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.project.service.ProjectFollowingTicketService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.ColumnGenerator;

public class FollowingTicketTableDisplay extends
		AbstractPagedBeanTable<FollowingTicketSearchCriteria, FollowingTicket> {

	private static final long serialVersionUID = 1L;
	private ProjectFollowingTicketService projectFollowingTicketService;

	public FollowingTicketTableDisplay() {
		super(FollowingTicket.class, Arrays.asList(
				FollowingTicketFieldDef.summary,
				FollowingTicketFieldDef.project,
				FollowingTicketFieldDef.assignee,
				FollowingTicketFieldDef.createdDate));

		this.projectFollowingTicketService = ApplicationContextUtil
				.getSpringBean(ProjectFollowingTicketService.class);

		this.addGeneratedColumn("summary", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final FollowingTicket ticket = FollowingTicketTableDisplay.this
						.getBeanByIndex(itemId);
				final ButtonLink ticketLink = new ButtonLink(ticket
						.getSummary());

				if (ProjectTypeConstants.BUG.equals(ticket.getType())) {
					ticketLink.setIcon(MyCollabResource
							.newResource("icons/16/project/bug.png"));

					if (BugStatus.Verified.name().equals(ticket.getStatus())) {
						ticketLink.addStyleName(UIConstants.LINK_COMPLETED);
					} else if (ticket.getDueDate() != null
							&& ticket.getDueDate().before(
									new GregorianCalendar().getTime())) {
						ticketLink.addStyleName(UIConstants.LINK_OVERDUE);
					}

					ticketLink.addClickListener(new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							final int projectId = ticket.getProjectId();
							final int bugId = ticket.getTypeId();
							final PageActionChain chain = new PageActionChain(
									new ProjectScreenData.Goto(projectId),
									new BugScreenData.Read(bugId));
							EventBusFactory.getInstance()
									.post(new ProjectEvent.GotoMyProject(this,
											chain));
						}
					});
				} else if (ProjectTypeConstants.TASK.equals(ticket.getType())) {
					ticketLink.setIcon(MyCollabResource
							.newResource("icons/16/project/task.png"));

					if ("Closed".equals(ticket.getStatus())) {
						ticketLink.addStyleName(UIConstants.LINK_COMPLETED);
					} else {
						if ("Pending".equals(ticket.getStatus())) {
							ticketLink.addStyleName(UIConstants.LINK_PENDING);
						} else if (ticket.getDueDate() != null
								&& ticket.getDueDate().before(
										new GregorianCalendar().getTime())) {
							ticketLink.addStyleName(UIConstants.LINK_OVERDUE);
						}
					}

					ticketLink.addClickListener(new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							final int projectId = ticket.getProjectId();
							final int taskId = ticket.getTypeId();
							final PageActionChain chain = new PageActionChain(
									new ProjectScreenData.Goto(projectId),
									new TaskScreenData.Read(taskId));
							EventBusFactory.getInstance()
									.post(new ProjectEvent.GotoMyProject(this,
											chain));
						}
					});
				}

				return ticketLink;
			}
		});

		this.addGeneratedColumn("projectName", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final FollowingTicket ticket = FollowingTicketTableDisplay.this
						.getBeanByIndex(itemId);
				final ButtonLink projectLink = new ButtonLink(ticket
						.getProjectName());
				projectLink.addClickListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						final int projectId = ticket.getProjectId();
						final PageActionChain chain = new PageActionChain(
								new ProjectScreenData.Goto(projectId));
						EventBusFactory.getInstance().post(
								new ProjectEvent.GotoMyProject(this, chain));
					}
				});
				projectLink.setIcon(MyCollabResource
						.newResource("icons/16/project/project.png"));
				return projectLink;
			}
		});

		this.addGeneratedColumn("assignUser", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final FollowingTicket ticket = FollowingTicketTableDisplay.this
						.getBeanByIndex(itemId);
				final UserLink userLink = new UserLink(ticket.getAssignUser(),
						ticket.getAssignUserAvatarId(), ticket
								.getAssignUserFullName());
				return userLink;
			}
		});

		this.addGeneratedColumn("monitorDate", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					final Object columnId) {
				final FollowingTicket ticket = FollowingTicketTableDisplay.this
						.getBeanByIndex(itemId);
				Label lbl = new Label();
				lbl.setValue(AppContext.formatDate(ticket.getMonitorDate()));
				return lbl;
			}
		});
	}

	@Override
	protected int queryTotalCount() {
		return this.projectFollowingTicketService
				.getTotalCount(this.searchRequest.getSearchCriteria());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<FollowingTicket> queryCurrentData() {
		return this.projectFollowingTicketService
				.findPagableListByCriteria(this.searchRequest);
	}

}
