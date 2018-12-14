package com.mycollab.module.project.view;

import com.github.appreciated.app.layout.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.design.AppLayoutDesign;
import com.github.appreciated.app.layout.builder.elements.builders.CDISubmenuBuilder;
import com.github.appreciated.app.layout.builder.entities.DefaultBadgeHolder;
import com.github.appreciated.app.layout.builder.entities.DefaultNotificationHolder;
import com.github.appreciated.app.layout.builder.factories.DefaultSpringNavigationElementInfoProducer;
import com.github.appreciated.app.layout.component.MenuHeader;
import com.github.appreciated.app.layout.component.button.AppBarNotificationButton;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.github.appreciated.app.layout.builder.Section.HEADER;

@SpringView(name = ProjectMainView.VIEW_NAME)
public class ProjectMainView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "project";

    DefaultNotificationHolder notifications = new DefaultNotificationHolder();
    DefaultBadgeHolder badge = new DefaultBadgeHolder();

    @Autowired
    private ProjectMainPresenter projectMainPresenter;

    @Autowired
    private SpringViewProvider viewProvider;

    @PostConstruct
    public void init() {
        addComponent(AppLayout.getCDIBuilder(Behaviour.LEFT_RESPONSIVE_HYBRID)
                // You can either set the SpringViewProvider
                .withViewProvider(() -> viewProvider)
                // or the SpringNavigator here
                //.withNavigator(components -> {
                //    navigator.init(this, components);
                //    return navigator;
                //})
                //
                // You will need to provide your own NavigationElementInfoProvider when using a different CDI than Vaadin Spring.
                // The same applies if you want to use other Icons for the MenuButtons than Vaadin Icons. Just checkout the default
                // Implementation and provide your own. If you have a good idea how to solve this better just make a pull
                // Request on Github
                .withNavigationElementInfoProducer(new DefaultSpringNavigationElementInfoProducer())
                //
                .withTitle("MyCollab")
                .addToAppBar(new AppBarNotificationButton(notifications, true))
                .withDesign(AppLayoutDesign.MATERIAL)
                .add(new MenuHeader("Version 7.0.0", new ThemeResource("logo.png")), HEADER)
                .add("Dashboard", VaadinIcons.CLIPBOARD, DashboardView.class)
                .add(CDISubmenuBuilder.get("Reports", VaadinIcons.PLUS)
                        .add(StandupMeetingView.class)
                        .add(TimeLogView.class)
                        .build())
                .add(FavoriteView.class)
                .build());

        projectMainPresenter.initView(this);
    }
}
