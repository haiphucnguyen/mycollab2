package com.mycollab.pro.module.project.view.assignments;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.vaadin.web.ui.field.PopupBeanFieldBuilder;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.vaadin.ui.AbstractComponent;
import org.vaadin.teemu.VaadinIcons;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@ViewComponent
public class AssignmentPopupFieldFactoryImpl implements AssignmentPopupFieldFactory {
    @Override
    public AbstractComponent createStartDatePopupField(ProjectGenericTask assignment) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder<ProjectGenericTask>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (assignment.getStartDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_FORWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_FORWARD.getHtml(), UserUIContext.formatDate(assignment.getStartDate()));
                }

            }

            @Override
            public boolean isPermission() {
                if (bean.isTask()) {
                    return CurrentProjectVariables.canWrite(ProjectTypeConstants.TASK);
                } else if (bean.isBug()) {
                    return CurrentProjectVariables.canWrite(ProjectTypeConstants.BUG);
                } else if (bean.isRisk()) {
                    return CurrentProjectVariables.canWrite(ProjectTypeConstants.RISK);
                } else {
                    return false;
                }
            }

            @Override
            public void save() {
                if (bean.isTask()) {
                    Task task = new Task();
                    task.setId(bean.getTypeId());
                    task.setTaskname(bean.getName());
                    task.setStartdate(bean.getEndDate());
                    task.setSaccountid(bean.getsAccountId());
                    AppContextUtil.getSpringBean(ProjectTaskService.class).updateSelectiveWithSession(task, UserUIContext.getUsername());
                } else if (bean.isBug()) {
                    BugWithBLOBs bug = new BugWithBLOBs();
                    bug.setId(bean.getTypeId());
                    bug.setSummary(bean.getName());
                    bug.setStartdate(bean.getEndDate());
                    bug.setSaccountid(bean.getsAccountId());
                    AppContextUtil.getSpringBean(BugService.class).updateSelectiveWithSession(bug, UserUIContext.getUsername());
                } else if (bean.isRisk()) {
                    Risk risk = new Risk();
                    risk.setId(bean.getTypeId());
                    risk.setRiskname(bean.getName());
                    risk.setStartdate(bean.getEndDate());
                    risk.setSaccountid(bean.getsAccountId());
                    AppContextUtil.getSpringBean(RiskService.class).updateSelectiveWithSession(risk, UserUIContext.getUsername());
                }
            }
        };
        builder.withBean(assignment).withBindProperty("startDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE))
                .withField(new PopupDateFieldExt())
                .withValue(assignment.getStartDate());
        return builder.build();
    }

    @Override
    public AbstractComponent createEndDatePopupField(ProjectGenericTask assignment) {
        PopupBeanFieldBuilder<ProjectGenericTask> builder = new PopupBeanFieldBuilder<ProjectGenericTask>() {
            @Override
            protected String generateSmallContentAsHtml() {
                if (assignment.getEndDate() == null) {
                    Div divHint = new Div().setCSSClass("nonValue");
                    divHint.appendText(VaadinIcons.TIME_BACKWARD.getHtml());
                    divHint.appendChild(new Span().appendText(" " + UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT))
                            .setCSSClass("hide"));
                    return divHint.write();
                } else {
                    return String.format(" %s %s", VaadinIcons.TIME_BACKWARD.getHtml(), UserUIContext.formatDate(assignment.getEndDate()));
                }
            }

            @Override
            public boolean isPermission() {
                if (bean.isTask()) {
                    return CurrentProjectVariables.canWrite(ProjectTypeConstants.TASK);
                } else if (bean.isBug()) {
                    return CurrentProjectVariables.canWrite(ProjectTypeConstants.BUG);
                } else if (bean.isRisk()) {
                    return CurrentProjectVariables.canWrite(ProjectTypeConstants.RISK);
                } else {
                    return false;
                }
            }

            @Override
            public void save() {
                if (bean.isTask()) {
                    Task task = new Task();
                    task.setId(bean.getTypeId());
                    task.setTaskname(bean.getName());
                    task.setEnddate(bean.getEndDate());
                    task.setSaccountid(bean.getsAccountId());
                    AppContextUtil.getSpringBean(ProjectTaskService.class).updateSelectiveWithSession(task, UserUIContext.getUsername());
                } else if (bean.isBug()) {
                    BugWithBLOBs bug = new BugWithBLOBs();
                    bug.setId(bean.getTypeId());
                    bug.setSummary(bean.getName());
                    bug.setEnddate(bean.getEndDate());
                    bug.setSaccountid(bean.getsAccountId());
                    AppContextUtil.getSpringBean(BugService.class).updateSelectiveWithSession(bug, UserUIContext.getUsername());
                } else if (bean.isRisk()) {
                    Risk risk = new Risk();
                    risk.setId(bean.getTypeId());
                    risk.setRiskname(bean.getName());
                    risk.setEnddate(bean.getEndDate());
                    risk.setSaccountid(bean.getsAccountId());
                    AppContextUtil.getSpringBean(RiskService.class).updateSelectiveWithSession(risk, UserUIContext.getUsername());
                }
            }
        };
        builder.withBean(assignment).withBindProperty("endDate").withCaption(UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE))
                .withField(new PopupDateFieldExt())
                .withValue(assignment.getEndDate());
        return builder.build();
    }
}
