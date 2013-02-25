/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.ActivityStream;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectActivityStream extends ActivityStream {
	private static final long serialVersionUID = 1L;
	private int projectId;
    private String projectName;
    private String createdUserFullName;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreatedUserFullName() {
        return createdUserFullName;
    }

    public void setCreatedUserFullName(String createdUserFullName) {
        this.createdUserFullName = createdUserFullName;
    }
}
