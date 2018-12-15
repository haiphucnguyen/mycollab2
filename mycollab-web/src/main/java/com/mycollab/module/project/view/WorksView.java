package com.mycollab.module.project.view;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.view.milestone.AllMilestoneTimelineWidget;
import com.mycollab.module.project.view.ticket.TicketOverdueWidget;
import com.mycollab.module.project.view.user.ActivityStreamComponent;
import com.mycollab.module.project.view.user.MyProjectListComponent;
import com.mycollab.module.project.view.user.UserUnresolvedTicketWidget;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = WorksView.VIEW_NAME)
public class WorksView extends MVerticalLayout implements View {
    public static final String VIEW_NAME = "works";

    @Autowired
    private WorksPresenter worksPresenter;

    @PostConstruct
    public void init() {
        worksPresenter.initView(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        List<Integer> prjKeys = worksPresenter.getProjectKeys();
        if (CollectionUtils.isNotEmpty(prjKeys)) {
            ResponsiveLayout contentWrapper = new ResponsiveLayout(ResponsiveLayout.ContainerType.FIXED);
            contentWrapper.setSizeFull();
            contentWrapper.addStyleName(WebThemes.MARGIN_TOP);
            addComponent(contentWrapper);

            ResponsiveRow row = new ResponsiveRow();

            AllMilestoneTimelineWidget milestoneTimelineWidget = AppContextUtil.getSpringBean(AllMilestoneTimelineWidget.class);
            TicketOverdueWidget ticketOverdueWidget = AppContextUtil.getSpringBean(TicketOverdueWidget.class);
            ActivityStreamComponent activityStreamComponent = AppContextUtil.getSpringBean(ActivityStreamComponent.class);
            UserUnresolvedTicketWidget unresolvedAssignmentThisWeekWidget = AppContextUtil.getSpringBean(UserUnresolvedTicketWidget.class);
            UserUnresolvedTicketWidget unresolvedAssignmentNextWeekWidget = AppContextUtil.getSpringBean(UserUnresolvedTicketWidget.class);

            ResponsiveColumn column1 = new ResponsiveColumn();
            column1.addRule(ResponsiveLayout.DisplaySize.LG, 7);
            column1.addRule(ResponsiveLayout.DisplaySize.MD, 7);
            column1.addRule(ResponsiveLayout.DisplaySize.SM, 12);
            column1.addRule(ResponsiveLayout.DisplaySize.XS, 12);
            MVerticalLayout leftPanel = new MVerticalLayout(milestoneTimelineWidget,
                    unresolvedAssignmentThisWeekWidget, unresolvedAssignmentNextWeekWidget, ticketOverdueWidget)
                    .withMargin(new MarginInfo(true, true, false, false)).withFullWidth();
            column1.setComponent(leftPanel);

            MVerticalLayout rightPanel = new MVerticalLayout().withMargin(false);
            MyProjectListComponent myProjectListComponent = new MyProjectListComponent();
            rightPanel.with(myProjectListComponent, activityStreamComponent);

            ResponsiveColumn column2 = new ResponsiveColumn();
            column2.addRule(ResponsiveLayout.DisplaySize.LG, 5);
            column2.addRule(ResponsiveLayout.DisplaySize.MD, 5);
            column1.addRule(ResponsiveLayout.DisplaySize.SM, 12);
            column1.addRule(ResponsiveLayout.DisplaySize.XS, 12);
            column2.setComponent(rightPanel);

            row.addColumn(column1);
            row.addColumn(column2);
            contentWrapper.addRow(row);

            activityStreamComponent.showFeeds(prjKeys);
            milestoneTimelineWidget.display();
            myProjectListComponent.displayDefaultProjectsList();
            ticketOverdueWidget.showUnresolvedTickets(prjKeys);
            unresolvedAssignmentThisWeekWidget.displayUnresolvedAssignmentsThisWeek();
            unresolvedAssignmentNextWeekWidget.displayUnresolvedAssignmentsNextWeek();
        } else {
            this.with(ELabel.h1(VaadinIcons.TASKS.getHtml()).withUndefinedWidth());
            this.with(ELabel.h2(UserUIContext.getMessage(GenericI18Enum.VIEW_NO_ITEM_TITLE)).withUndefinedWidth());
            if (UserUIContext.canWrite(RolePermissionCollections.CREATE_NEW_PROJECT)) {
                MButton newProjectBtn = new MButton(UserUIContext.getMessage(ProjectI18nEnum.NEW),
                        clickEvent -> UI.getCurrent().addWindow(AppContextUtil.getSpringBean(AbstractProjectAddWindow.class)))
                        .withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.PLUS);
                with(newProjectBtn);
            }
            alignAll(Alignment.TOP_CENTER);
        }
    }
}
