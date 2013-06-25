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
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@ViewComponent
public class FollowingTicketViewImpl extends AbstractView implements
		FollowingTicketView {
	private static final long serialVersionUID = 1L;

	private final FollowingTicketTable ticketTable;

	public FollowingTicketViewImpl() {
		this.setSpacing(true);
		this.setMargin(false, false, true, false);
		this.setWidth("1130px");
		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.setStyleName("projectfeed-hdr-wrapper");
		final Label layoutHeader = new Label("Display tickets");
		layoutHeader.addStyleName("h2");
		headerWrapper.addComponent(layoutHeader);
		this.addComponent(headerWrapper);

		final Button backBtn = new Button("Back to Work Board");
		backBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		this.addComponent(backBtn);

		this.ticketTable = new FollowingTicketTable();
		this.addComponent(this.ticketTable);
	}

	@Override
	public void displayFollowingTicket(final List<Integer> prjKeys) {
		final MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(prjKeys
				.toArray(new Integer[0])));
		searchCriteria.setUser(new StringSearchField(AppContext.getUsername()));
		this.ticketTable.setSearchCriteria(searchCriteria);

	}

	private class FollowingTicketTable extends
			AbstractPagedBeanTable<MonitorSearchCriteria, FollowingTicket> {

		private static final long serialVersionUID = 1L;
		private ProjectService projectService;

		public FollowingTicketTable() {
			super(FollowingTicket.class, new String[] { "summary",
					"projectName", "assignUser" }, new String[] { "Summary",
					"Project", "Assignee" });

			this.projectService = AppContext
					.getSpringBean(ProjectService.class);

			this.addGeneratedColumn("summary", new ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {
					final FollowingTicket ticket = FollowingTicketTable.this
							.getBeanByIndex(itemId);
					final ButtonLink ticketLink = new ButtonLink(ticket
							.getSummary());
					ticketLink.addListener(new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							if ("Bug".equals(ticket.getType())) {
								final int projectId = ticket.getProjectId();
								final int bugId = ticket.getTypeId();
								final PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectId),
										new BugScreenData.Read(bugId));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if ("Task".equals(ticket.getType())) {
								final int projectId = ticket.getProjectId();
								final int taskId = ticket.getTypeId();
								final PageActionChain chain = new PageActionChain(
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
			return this.projectService
					.getTotalFollowingTickets(this.searchRequest
							.getSearchCriteria());
		}

		@Override
		protected List<FollowingTicket> queryCurrentData() {
			return this.projectService
					.getProjectFollowingTickets(this.searchRequest);
		}

	}
}
