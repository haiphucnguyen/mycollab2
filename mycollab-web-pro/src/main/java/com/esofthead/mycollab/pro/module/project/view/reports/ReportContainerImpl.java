package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class ReportContainerImpl extends AbstractPageView implements IReportContainer {
    public ReportContainerImpl() {
        this.withStyleName(UIConstants.FLEX_DISPLAY, "hdr-view");

        MVerticalLayout standupConsole = new MVerticalLayout().withWidth("300px").withStyleName(UIConstants.BOX,
                UIConstants.HOVER_EFFECT_NOT_BOX);
        standupConsole.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        standupConsole.addComponent(ELabel.fontIcon(FontAwesome.LEGAL).withStyleName("icon-38px"));
        A standupReportLink = new A(ProjectLinkBuilder.generateStandupDashboardLink(CurrentProjectVariables.getProjectId()))
                .appendText("Standup " + "Report");
        standupConsole.addComponent(ELabel.h3(standupReportLink.write()).withWidthUndefined());
        standupConsole.addComponent(new ELabel("Your daily scrum which asks three questions What you did yesterday?, " +
                "What you will do today? and Do you have roadblocks? in the StandUp meeting which should not " +
                "exceed 15 minutes.").withWidth("100%"));
        this.addComponent(standupConsole);
    }
}
