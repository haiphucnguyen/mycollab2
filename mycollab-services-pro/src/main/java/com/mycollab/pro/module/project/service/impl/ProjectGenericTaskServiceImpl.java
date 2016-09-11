package com.mycollab.pro.module.project.service.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.dao.TaskMapper;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.service.impl.AbstractProjectGenericTaskServiceImpl;
import com.mycollab.module.tracker.dao.BugMapper;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import com.mycollab.pro.module.project.dao.RiskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@Service
public class ProjectGenericTaskServiceImpl extends AbstractProjectGenericTaskServiceImpl {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private BugMapper bugMapper;

    @Autowired
    private RiskMapper riskMapper;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public void updateAssignmentValue(ProjectGenericTask assignment) {
        if (assignment.isTask()) {
            Task task = new Task();
            task.setId(assignment.getTypeId());
            task.setMilestoneid(assignment.getMilestoneId());
            taskMapper.updateByPrimaryKeySelective(task);
        } else if (assignment.isBug()) {
            BugWithBLOBs bug = new BugWithBLOBs();
            bug.setId(assignment.getTypeId());
            bug.setMilestoneid(assignment.getMilestoneId());
            bugMapper.updateByPrimaryKeySelective(bug);
        } else if (assignment.isRisk()) {
            Risk risk = new Risk();
            risk.setId(assignment.getTypeId());
            risk.setMilestoneid(assignment.getMilestoneId());
            riskMapper.updateByPrimaryKeySelective(risk);
        } else {
            throw new MyCollabException("Not support");
        }

        asyncEventBus.post(new CleanCacheEvent(assignment.getsAccountId(), new Class[]{ProjectService.class}));
    }

    @Override
    public void closeSubAssignmentOfMilestone(Integer milestoneId) {

    }
}
