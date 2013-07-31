/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import com.esofthead.mycollab.common.domain.SimpleActivityStream;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectActivityStream extends SimpleActivityStream {
	private static final long serialVersionUID = 1L;
	private int projectId;
	private String projectName;

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
}
