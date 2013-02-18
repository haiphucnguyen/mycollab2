package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.view.user.MyFeedsPresenter;
import com.esofthead.mycollab.module.project.view.user.MyProjectsPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
@ViewComponent
public class UserDashboardViewImpl extends AbstractView implements
        UserDashboardView {

    private final HorizontalLayout root;
    private final DetachedTabs mySpaceTabs;
    private final CssLayout mySpaceArea = new CssLayout();
    private final DetachedTabs calendarToolTabs;
    private MyProjectsPresenter myProjectPresenter;
    private MyFeedsPresenter myFeedsPresenter;

    public UserDashboardViewImpl() {
        this.setStyleName("projectDashboardView");
        this.setMargin(false);
        root = new HorizontalLayout();
        root.setStyleName("menuContent");
        root.setWidth("100%");

        mySpaceArea.setWidth("100%");
        mySpaceArea.setStyleName("project-dashboard");
        mySpaceTabs = new DetachedTabs.Vertical(mySpaceArea);
        mySpaceTabs.setSizeFull();
        mySpaceTabs.setHeight(null);

        calendarToolTabs = new DetachedTabs.Vertical(mySpaceArea);
        calendarToolTabs.setSizeFull();
        calendarToolTabs.setHeight(null);

        CssLayout menu = new CssLayout();
        menu.setWidth("200px");
        menu.setStyleName("sidebar-menu");

        Label myHome = new Label("My Home");
        myHome.setStyleName("sectionHeader");
        menu.addComponent(myHome);
        menu.addComponent(mySpaceTabs);

        mySpaceTabs.setStyleName("hide-selection");
        calendarToolTabs.setStyleName("hide-selection");
        menu.addListener(new LayoutClickListener() {
            @Override
            public void layoutClick(LayoutClickEvent event) {
                if (!root.getComponent(1).equals(mySpaceArea)) {
                    root.replaceComponent(root.getComponent(1), mySpaceArea);
                    root.setExpandRatio(root.getComponent(1), 1.0f);
                }
                if (event.getChildComponent() == mySpaceTabs) {
                    calendarToolTabs.setStyleName("hide-selection");
                    mySpaceTabs.setStyleName("");
                } else if (event.getChildComponent() == calendarToolTabs) {
                    mySpaceTabs.setStyleName("hide-selection");
                    calendarToolTabs.setStyleName("");
                }
            }
        });

        root.addComponent(menu);
        root.addComponent(mySpaceArea);
        root.setExpandRatio(mySpaceArea, 1.0f);

        buildComponents();
        gotoMyFeeds();

        this.addComponent(root);
    }

    private void showWelcomeScreen() {
        CustomLayout welcome = new CustomLayout("projectWelcomeScreen");
        welcome.setSizeFull();
        root.addComponent(welcome);
    }

    private void buildComponents() {
        mySpaceTabs.addTab(constructMyFeedsComponents(), "My Feeds");
        mySpaceTabs.addTab(constructMyProjectsComponents(), "My Projects");

        mySpaceTabs
                .addTabChangedListener(new DetachedTabs.TabChangedListener() {
            @Override
            public void tabChanged(TabChangedEvent event) {
                Button btn = event.getSource();
                String caption = btn.getCaption();
                if ("My Projects".equals(caption)) {
                    ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
                    // TODO: wrong search criteria

                    gotoMyProjectList(new ScreenData.Search<ProjectSearchCriteria>(
                            searchCriteria));
                } else if ("My Feeds".equals(caption)) {
                    gotoMyFeeds();
                }
            }
        });
    }

    private ComponentContainer constructMyFeedsComponents() {
        myFeedsPresenter = PresenterResolver
                .getPresenter(MyFeedsPresenter.class);
        return myFeedsPresenter.getView();
    }

    private ComponentContainer constructMyProjectsComponents() {
        myProjectPresenter = PresenterResolver
                .getPresenter(MyProjectsPresenter.class);
        return myProjectPresenter.getView();
    }

    @Override
    public void gotoMyProjectList(ScreenData data) {
        myProjectPresenter.go(this, data);
    }

    @Override
    public void gotoMyFeeds() {
        myFeedsPresenter.go(this, null);
    }

    @Override
    public Component gotoSubView(String name) {
        View component = (View) mySpaceTabs.selectTab(name);
        return component;
    }
}
