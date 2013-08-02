package com.esofthead.mycollab.module.project.view;

import java.util.Arrays;
import java.util.GregorianCalendar;
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
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class FollowingTicketViewImpl extends AbstractView implements
        FollowingTicketView {
    private static final long serialVersionUID = 1L;

    private final FollowingTicketTable ticketTable;

    public FollowingTicketViewImpl() {
        this.setSpacing(true);
        this.setWidth("100%");

        final CssLayout contentWrapper = new CssLayout();
        contentWrapper.setWidth("100%");
        contentWrapper.addStyleName("main-content-wrapper");
        this.addComponent(contentWrapper);

        final CssLayout headerWrapper = new CssLayout();
        headerWrapper.setWidth("100%");
        headerWrapper.setStyleName("projectfeed-hdr-wrapper");

        final HorizontalLayout header = new HorizontalLayout();
        header.setWidth("100%");
        header.setSpacing(true);

        final Embedded timeIcon = new Embedded();
        timeIcon.setSource(MyCollabResource.newResource("icons/24/follow.png"));
        header.addComponent(timeIcon);

        final Label layoutHeader = new Label("Your Following Tickets");
        layoutHeader.addStyleName("h2");
        header.addComponent(layoutHeader);
        header.setComponentAlignment(layoutHeader, Alignment.MIDDLE_LEFT);
        header.setExpandRatio(layoutHeader, 1.0f);

        headerWrapper.addComponent(header);
        contentWrapper.addComponent(headerWrapper);

        final Button backBtn = new Button("Back to Work Board");
        backBtn.addListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                EventBus.getInstance().fireEvent(
                        new ShellEvent.GotoProjectModule(
                                FollowingTicketViewImpl.this, null));

            }
        });

        backBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

        VerticalLayout controlBtns = new VerticalLayout();
        controlBtns.setMargin(true, false, true, false);
        controlBtns.addComponent(backBtn);

        contentWrapper.addComponent(controlBtns);

        this.ticketTable = new FollowingTicketTable();
        contentWrapper.addComponent(this.ticketTable);
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
            super(FollowingTicket.class, Arrays.asList(new TableViewField(
                    "Summary", "summary", UIConstants.TABLE_EX_LABEL_WIDTH),
                    new TableViewField("Project", "projectName",
                            UIConstants.TABLE_X_LABEL_WIDTH),
                    new TableViewField("Assignee", "assignUser",
                            UIConstants.TABLE_X_LABEL_WIDTH),
                    new TableViewField("Created Date", "monitorDate",
                            UIConstants.TABLE_DATE_WIDTH)));

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
                                if (BugStatusConstants.VERIFIED.equals(ticket.getStatus())) {
                                	ticketLink.addStyleName(UIConstants.LINK_COMPLETED);
            					} else if (ticket.getDueDate() != null
            							&& (ticket.getDueDate()
            									.before(new GregorianCalendar().getTime()))) {
            						ticketLink.addStyleName(UIConstants.LINK_OVERDUE);
            					}
                            } else if ("Task".equals(ticket.getType())) {
                                final int projectId = ticket.getProjectId();
                                final int taskId = ticket.getTypeId();
                                final PageActionChain chain = new PageActionChain(
                                        new ProjectScreenData.Goto(projectId),
                                        new TaskScreenData.Read(taskId));
                                EventBus.getInstance().fireEvent(
                                        new ProjectEvent.GotoMyProject(this,
                                                chain));
                                if ("Closed".equals(ticket.getStatus())) {
                                	ticketLink.addStyleName(UIConstants.LINK_COMPLETED);
            					} else {
            						if ("Pending".equals(ticket.getStatus())) {
            							ticketLink.addStyleName(UIConstants.LINK_PENDING);
            						} else if (ticket.getDueDate() != null
            								&& (ticket.getDueDate()
            										.before(new GregorianCalendar()
            												.getTime()))) {
            							ticketLink.addStyleName(UIConstants.LINK_OVERDUE);
            						}
            					}
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

            this.addGeneratedColumn("projectName", new ColumnGenerator() {
                private static final long serialVersionUID = 1L;

                @Override
                public Object generateCell(final Table source,
                        final Object itemId, final Object columnId) {
                    final FollowingTicket ticket = FollowingTicketTable.this
                            .getBeanByIndex(itemId);
                    final ButtonLink projectLink = new ButtonLink(ticket
                            .getProjectName());
                    projectLink.addListener(new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            final int projectId = ticket.getProjectId();
                            final PageActionChain chain = new PageActionChain(
                                    new ProjectScreenData.Goto(projectId));
                            EventBus.getInstance()
                                    .fireEvent(
                                            new ProjectEvent.GotoMyProject(
                                                    this, chain));
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
                public Object generateCell(final Table source,
                        final Object itemId, final Object columnId) {
                    final FollowingTicket ticket = FollowingTicketTable.this
                            .getBeanByIndex(itemId);
                    final UserLink userLink = new UserLink(ticket
                            .getAssignUser(), ticket.getAssignUserAvatarId(),
                            ticket.getAssignUserFullName());
                    return userLink;
                }
            });

            this.addGeneratedColumn("monitorDate", new ColumnGenerator() {
                private static final long serialVersionUID = 1L;

                @Override
                public Object generateCell(final Table source,
                        final Object itemId, final Object columnId) {
                    final FollowingTicket ticket = FollowingTicketTable.this
                            .getBeanByIndex(itemId);
                    Label lbl = new Label();
                    lbl.setValue(AppContext.formatDate(ticket.getMonitorDate()));
                    return lbl;
                }
            });
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
