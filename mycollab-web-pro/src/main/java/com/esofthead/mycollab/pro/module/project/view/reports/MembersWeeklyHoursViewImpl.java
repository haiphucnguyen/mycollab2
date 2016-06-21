package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
@ViewComponent
public class MembersWeeklyHoursViewImpl extends AbstractPageView implements MembersWeeklyHoursView {
    @Override
    public void display() {
        removeAllComponents();
        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        List<SimpleProject> projects = projectService.getProjectsUserInvolved(AppContext.getUsername(), AppContext.getAccountId());
        if (CollectionUtils.isNotEmpty(projects)) {
            for (SimpleProject project : projects) {
                buildHourlyProjectReport(project);
            }
        } else {

        }
    }

    private void buildHourlyProjectReport(SimpleProject project) {
        MVerticalLayout contentLayout = new MVerticalLayout();
        addComponent(contentLayout);
        contentLayout.with(ELabel.h3(project.getName()));
    }
}
