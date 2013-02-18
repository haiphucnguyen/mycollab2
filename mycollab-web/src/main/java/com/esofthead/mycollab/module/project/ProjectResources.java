/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project;

import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectResources {

    private static final Map<String, Resource> sizeResources;

    static {
        sizeResources = new HashMap<String, Resource>();
        sizeResources.put(ProjectContants.PROJECT, new ThemeResource("icons/16/project/project.png"));
        sizeResources.put(ProjectContants.MESSAGE, new ThemeResource("icons/16/project/message.png"));
        sizeResources.put(ProjectContants.MILESTONE, new ThemeResource("icons/16/project/milestone.png"));
        sizeResources.put(ProjectContants.PROBLEM, new ThemeResource("icons/16/project/problem.png"));
        sizeResources.put(ProjectContants.RISK, new ThemeResource("icons/16/project/projectMember.png"));
        sizeResources.put(ProjectContants.TASK, new ThemeResource("icons/16/project/task.png"));
        sizeResources.put(ProjectContants.TASK_LIST, new ThemeResource("icons/16/project/tasklist.png"));
        sizeResources.put(ProjectContants.BUG, new ThemeResource("icons/16/project/bug.png"));
        sizeResources.put(ProjectContants.BUG_COMPONENT, new ThemeResource("icons/16/project/component.png"));
        sizeResources.put(ProjectContants.BUG_VERSION, new ThemeResource("icons/16/project/version.png"));
    }

    public static Resource getIconResource16size(String type) {
        return sizeResources.get(type);
    }
}
