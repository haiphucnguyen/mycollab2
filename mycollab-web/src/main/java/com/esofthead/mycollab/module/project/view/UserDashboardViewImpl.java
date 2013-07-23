package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.common.domain.criteria.MonitorSearchCriteria;
import com.esofthead.mycollab.common.service.MonitorItemService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.events.FollowingTicketEvent;
import com.esofthead.mycollab.module.project.events.TimeTrackingEvent;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.user.ActivityStreamComponent;
import com.esofthead.mycollab.module.project.view.user.MyProjectListComponent;
import com.esofthead.mycollab.module.project.view.user.TaskStatusComponent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class UserDashboardViewImpl extends AbstractView implements
        UserDashboardView {
    private static final long serialVersionUID = 1L;

    private final ButtonLink followingTicketsLink;

    private final ButtonLink timeTrackingLink;

    private final MyProjectListComponent myProjectListComponent;

    private final ActivityStreamComponent activityStreamComponent;

    private final TaskStatusComponent taskStatusComponent;

    private List<Integer> prjKeys;

    public UserDashboardViewImpl() {
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
        header.setMargin(false);
        header.setSpacing(true);
        header.addStyleName("projectfeed-hdr");

        header.addComponent(UserAvatarControlFactory
                .createUserAvatarEmbeddedComponent(
                        AppContext.getUserAvatarId(), 64));

        final VerticalLayout headerContent = new VerticalLayout();
        headerContent.addStyleName("projectfeed-hdr-content");

        final Label headerLabel = new Label(AppContext.getSession()
                .getDisplayName());
        headerLabel.setStyleName(Reindeer.LABEL_H1);

        final HorizontalLayout headerContentTop = new HorizontalLayout();
        headerContentTop.setSpacing(true);
        headerContentTop.addComponent(headerLabel);
        headerContentTop.setComponentAlignment(headerLabel,
                Alignment.MIDDLE_LEFT);

        final Button createProjectBtn = new Button(
                LocalizationHelper
                        .getMessage(ProjectCommonI18nEnum.NEW_PROJECT_ACTION),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final Button.ClickEvent event) {
                        final ProjectAddWindow projectNewWindow = new ProjectAddWindow();
                        UserDashboardViewImpl.this.getWindow().addWindow(
                                projectNewWindow);
                    }
                });
        createProjectBtn.setIcon(MyCollabResource
                .newResource("icons/16/addRecord.png"));
        createProjectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        headerContentTop.addComponent(createProjectBtn);
        headerContentTop.setComponentAlignment(createProjectBtn,
                Alignment.MIDDLE_LEFT);

        final HorizontalLayout headerContentBottom = new HorizontalLayout();
        headerContentBottom.setSpacing(true);
        followingTicketsLink = new ButtonLink("My Following Tickets (" + "0"
                + ")");

        followingTicketsLink.setIcon(MyCollabResource
                .newResource("icons/16/follow.png"));
        followingTicketsLink.removeStyleName("wordWrap");
        followingTicketsLink.addListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                if (prjKeys != null) {
                    EventBus.getInstance().fireEvent(
                            new FollowingTicketEvent.GotoMyFollowingItems(
                                    UserDashboardViewImpl.this, prjKeys));
                }
            }
        });

        timeTrackingLink = new ButtonLink("Time Tracking");
        timeTrackingLink.addListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(
                        new TimeTrackingEvent.GotoTimeTrackingView(
                                UserDashboardViewImpl.this, null));
            }
        });
        timeTrackingLink.setIcon(MyCollabResource
                .newResource("icons/16/project/time_tracking.png"));
        timeTrackingLink.removeStyleName("wordWrap");
        headerContentBottom.addComponent(followingTicketsLink);
        headerContentBottom.addComponent(timeTrackingLink);

        headerContent.addComponent(headerContentTop);
        headerContent.addComponent(headerContentBottom);

        header.addComponent(headerContent);
        header.setExpandRatio(headerContent, 1.0f);
        headerWrapper.addComponent(header);

        contentWrapper.addComponent(headerWrapper);

        final HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setMargin(false, false, true, false);
        layout.setSpacing(true);

        final VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.setMargin(false, true, false, false);
        this.activityStreamComponent = new ActivityStreamComponent();
        leftPanel.addComponent(this.activityStreamComponent);
        leftPanel.setWidth("100%");

        final VerticalLayout rightPanel = new VerticalLayout();
        this.myProjectListComponent = new MyProjectListComponent();
        this.taskStatusComponent = new TaskStatusComponent();
        rightPanel.setWidth("565px");
        rightPanel.addComponent(this.myProjectListComponent);
        rightPanel.addComponent(new LazyLoadWrapper(this.taskStatusComponent));

        layout.addComponent(leftPanel);
        layout.addComponent(rightPanel);
        layout.setExpandRatio(leftPanel, 1.0f);

        contentWrapper.addComponent(layout);
    }

    @Override
    public void display() {
        final ProjectService prjService = AppContext
                .getSpringBean(ProjectService.class);
        prjKeys = prjService.getUserProjectKeys(AppContext.getUsername());
        if (prjKeys != null && !prjKeys.isEmpty()) {
            this.activityStreamComponent.showFeeds(prjKeys);
            this.myProjectListComponent.showProjects(prjKeys);
            displayFollowingTicketsCount();
        }

        this.taskStatusComponent.showProjectTasksByStatus();

    }

    private void displayFollowingTicketsCount() {
        // show following ticket numbers
        MonitorSearchCriteria searchCriteria = new MonitorSearchCriteria();
        searchCriteria.setUser(new StringSearchField(SearchField.AND,
                AppContext.getUsername()));
        searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(prjKeys
                .toArray(new Integer[0])));
        MonitorItemService monitorService = AppContext
                .getSpringBean(MonitorItemService.class);
        int followingItemsCount = monitorService.getTotalCount(searchCriteria);
        followingTicketsLink.setCaption("My Following Tickets ("
                + followingItemsCount + ")");
    }
}
