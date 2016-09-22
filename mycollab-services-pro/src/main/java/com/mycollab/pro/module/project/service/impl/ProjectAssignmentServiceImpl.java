package com.mycollab.pro.module.project.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.arguments.SearchField;
import com.mycollab.module.project.domain.ProjectAssignment;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.service.impl.AbstractProjectAssignmentServiceImpl;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.mycollab.module.tracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@Service
public class ProjectAssignmentServiceImpl extends AbstractProjectAssignmentServiceImpl {

    @Autowired
    private ProjectTaskService taskService;

    @Autowired
    private BugService bugService;

    @Autowired
    private RiskService riskService;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public void updateAssignmentValue(ProjectAssignment assignment, String username) {
        if (assignment.isTask()) {
            Task task = new Task();
            task.setName(assignment.getName());
            task.setId(assignment.getTypeId());
            task.setMilestoneid(assignment.getMilestoneId());
            task.setSaccountid(assignment.getsAccountId());
            taskService.updateSelectiveWithSession(task, username);
        } else if (assignment.isBug()) {
            BugWithBLOBs bug = new BugWithBLOBs();
            bug.setName(assignment.getName());
            bug.setId(assignment.getTypeId());
            bug.setMilestoneid(assignment.getMilestoneId());
            bug.setSaccountid(assignment.getsAccountId());
            bugService.updateSelectiveWithSession(bug, username);
        } else if (assignment.isRisk()) {
            Risk risk = new Risk();
            risk.setName(assignment.getName());
            risk.setId(assignment.getTypeId());
            risk.setMilestoneid(assignment.getMilestoneId());
            risk.setSaccountid(assignment.getsAccountId());
            riskService.updateSelectiveWithSession(risk, username);
        } else {
            throw new MyCollabException("Not support");
        }

        asyncEventBus.post(new CleanCacheEvent(assignment.getsAccountId(), new Class[]{ProjectService.class}));
    }

    @Override
    public void closeSubAssignmentOfMilestone(Integer milestoneId) {
        BugWithBLOBs bug = new BugWithBLOBs();
        bug.setStatus(BugStatus.Resolved.name());
        BugSearchCriteria bugSearchCriteria = new BugSearchCriteria();
        bugSearchCriteria.addExtraField(BugSearchCriteria.p_milestones.buildPropertyParamInList(SearchField.AND,
                Collections.singleton(milestoneId)));
        bugService.updateBySearchCriteria(bug, bugSearchCriteria);

        Risk risk = new Risk();
        risk.setStatus(StatusI18nEnum.Closed.name());
        RiskSearchCriteria riskSearchCriteria = new RiskSearchCriteria();
        riskSearchCriteria.addExtraField(RiskSearchCriteria.p_milestones.buildPropertyParamInList(SearchField.AND,
                Collections.singleton(milestoneId)));
        riskService.updateBySearchCriteria(risk, riskSearchCriteria);

        Task task = new Task();
        task.setStatus(StatusI18nEnum.Closed.name());
        TaskSearchCriteria taskSearchCriteria = new TaskSearchCriteria();
        taskSearchCriteria.addExtraField(TaskSearchCriteria.p_milestoneId.buildPropertyParamInList(SearchField.AND,
                Collections.singleton(milestoneId)));
        taskService.updateBySearchCriteria(task, taskSearchCriteria);

    }
}
