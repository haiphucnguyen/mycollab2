/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.vaadin.ui.ResourceResolver;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.Resource;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectResources {

	private static final Map<String, String> resourceLinks;

	private static final Map<String, Resource> resources;

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

		resources = new HashMap<String, Resource>();
		resources.put(ProjectContants.PROJECT,
				MyCollabResource.newResource("icons/16/project/project.png"));
		resources.put(ProjectContants.MESSAGE,
				MyCollabResource.newResource("icons/16/project/message.png"));
		resources.put(ProjectContants.MILESTONE,
				MyCollabResource.newResource("icons/16/project/milestone.png"));
		resources.put(ProjectContants.PROBLEM,
				MyCollabResource.newResource("icons/16/project/problem.png"));
		resources.put(ProjectContants.RISK,
				MyCollabResource.newResource("icons/16/project/risk.png"));
		resources.put(ProjectContants.TASK,
				MyCollabResource.newResource("icons/16/project/task.png"));
		resources.put(ProjectContants.TASK_LIST,
				MyCollabResource.newResource("icons/16/project/tasklist.png"));
		resources.put(ProjectContants.BUG,
				MyCollabResource.newResource("icons/16/project/bug.png"));
		resources.put(ProjectContants.BUG_COMPONENT,
				MyCollabResource.newResource("icons/16/project/component.png"));
		resources.put(ProjectContants.BUG_VERSION,
				MyCollabResource.newResource("icons/16/project/version.png"));
	}

	public static String getResourceLink(String type) {
		return resourceLinks.get(type);
	}

	public static Resource getResource(String type) {
		return resources.get(type);
	}
}
