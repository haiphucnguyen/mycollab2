package com.mycollab.module.project.domain;

import com.mycollab.common.domain.SimpleRelayEmailNotification;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public class ProjectRelayEmailNotification extends SimpleRelayEmailNotification {
    private static final long serialVersionUID = 1L;

    private int projectId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
