package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.ProjectMemberTableFieldDef;
import com.esofthead.mycollab.reporting.CustomizeReportOutputWindow;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class ProjectMemberCustomizeReportOutputWindow extends CustomizeReportOutputWindow<ProjectMemberSearchCriteria, SimpleProjectMember> {
    public ProjectMemberCustomizeReportOutputWindow(VariableInjector<ProjectMemberSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.MEMBER, AppContext.getMessage(ProjectMemberI18nEnum.LIST), SimpleProjectMember.class,
                AppContextUtil.getSpringBean(ProjectMemberService.class), variableInjector);
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(ProjectMemberTableFieldDef.memberName(), ProjectMemberTableFieldDef.roleName(),
                ProjectMemberTableFieldDef.billingRate(), ProjectMemberTableFieldDef.overtimeRate());
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(ProjectMemberTableFieldDef.projectName(), ProjectMemberTableFieldDef.memberName(),
                ProjectMemberTableFieldDef.roleName(), ProjectMemberTableFieldDef.numOpenTasks(),
                ProjectMemberTableFieldDef.numOpenBugs(), ProjectMemberTableFieldDef.totalBillableLogTime(),
                ProjectMemberTableFieldDef.totalNonBillableLogTime(), ProjectMemberTableFieldDef.billingRate(),
                ProjectMemberTableFieldDef.overtimeRate());
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"MyCollab", "John Adams", "Administrator", "12", "3", "40", "8", "50", "60"};
    }
}
