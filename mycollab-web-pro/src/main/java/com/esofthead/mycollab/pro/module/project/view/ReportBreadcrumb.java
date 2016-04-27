package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.events.ReportEvent;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.i18n.BreadcrumbI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.breadcrumb.Breadcrumb;
import com.vaadin.ui.Button;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class ReportBreadcrumb extends Breadcrumb implements CacheableComponent {
    private Button homeBtn;

    public ReportBreadcrumb() {
        this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
        this.setUseDefaultClickBehaviour(false);
        homeBtn = new Button(null, new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this));
            }
        });
        this.addLink(homeBtn);
    }

    public void gotoStandupList(Date onDate) {
        this.select(0);
        this.addLink(new Button(AppContext.getMessage(BreadcrumbI18nEnum.REPORTS), new GotoReportsListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(AppContext.getMessage(BreadcrumbI18nEnum.STANDUP)));
        if (onDate == null) {
            AppContext.addFragment("project/reports/standup/list/",
                    AppContext.getMessage(BreadcrumbI18nEnum.FRA_STANDUP));
        } else {
            AppContext.addFragment("project/reports/standup/list/" + AppContext.formatDate(onDate).replace('/', '-'),
                    AppContext.getMessage(BreadcrumbI18nEnum.FRA_STANDUP));
        }
    }

    public void gotoStandupAdd(Date date) {
        this.select(0);
        this.addLink(new Button(AppContext.getMessage(BreadcrumbI18nEnum.REPORTS), new GotoReportsListener()));
        this.setLinkEnabled(true, 1);
        this.addLink(new Button(AppContext.getMessage(BreadcrumbI18nEnum.STANDUP), new GotoStandupListener()));
        this.setLinkEnabled(true, 2);
        this.addLink(new Button(AppContext.getMessage(GenericI18Enum.BUTTON_ADD)));

        AppContext.addFragment("project/reports/standup/add/" + UrlEncodeDecoder.encode(CurrentProjectVariables.getProjectId()
                + "/" + AppContext.formatDate(date)), AppContext.getMessage(BreadcrumbI18nEnum.FRA_STANDUP_FOR_DAY,
                AppContext.formatDate(date)));
    }

    private static class GotoReportsListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(Button.ClickEvent event) {
            EventBusFactory.getInstance().post(new ProjectEvent.GotoReportConsole(this));
        }
    }

    private static class GotoStandupListener implements Button.ClickListener {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(Button.ClickEvent event) {
            EventBusFactory.getInstance().post(new StandUpEvent.GotoList(this, null));
        }
    }
}
