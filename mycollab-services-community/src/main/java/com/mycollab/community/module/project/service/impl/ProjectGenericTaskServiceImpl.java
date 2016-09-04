package com.mycollab.community.module.project.service.impl;

import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.service.impl.AbstractProjectGenericTaskServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@Service
public class ProjectGenericTaskServiceImpl extends AbstractProjectGenericTaskServiceImpl{
    @Override
    public void updateAssignmentValue(ProjectGenericTask assignment) {
        throw new MyCollabException("Not support this operation in the community edition");
    }
}
