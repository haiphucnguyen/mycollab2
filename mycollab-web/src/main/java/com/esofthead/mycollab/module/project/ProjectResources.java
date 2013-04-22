/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.vaadin.ui.ResourceResolver;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectResources {

	private static final Map<String, String> resourceLinks;

	static {
		resourceLinks = new HashMap<String, String>();
		resourceLinks.put(ProjectContants.PROJECT, ResourceResolver
				.getResourceLink("icons/16/project/project.png"));
		resourceLinks.put(ProjectContants.MESSAGE, ResourceResolver
				.getResourceLink("icons/16/project/message.png"));
		resourceLinks.put(ProjectContants.MILESTONE, ResourceResolver
				.getResourceLink("icons/16/project/milestone.png"));
		resourceLinks.put(ProjectContants.PROBLEM, ResourceResolver
				.getResourceLink("icons/16/project/problem.png"));
		resourceLinks.put(ProjectContants.RISK,
				ResourceResolver.getResourceLink("icons/16/project/risk.png"));
		resourceLinks.put(ProjectContants.TASK,
				ResourceResolver.getResourceLink("icons/16/project/task.png"));
		resourceLinks.put(ProjectContants.TASK_LIST, ResourceResolver
				.getResourceLink("icons/16/project/tasklist.png"));
		resourceLinks.put(ProjectContants.BUG,
				ResourceResolver.getResourceLink("icons/16/project/bug.png"));
		resourceLinks.put(ProjectContants.BUG_COMPONENT, ResourceResolver
				.getResourceLink("icons/16/project/component.png"));
		resourceLinks.put(ProjectContants.BUG_VERSION, ResourceResolver
				.getResourceLink("icons/16/project/version.png"));
	}

	public static String getResourceLink(String type) {
		return resourceLinks.get(type);
	}
}
