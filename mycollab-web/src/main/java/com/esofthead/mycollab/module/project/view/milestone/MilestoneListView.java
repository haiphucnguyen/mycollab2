/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.List;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.mvp.View;

/**
 * 
 * @author haiphucnguyen
 */
public interface MilestoneListView extends View {
	void displayMilestones(List<SimpleMilestone> milestones);
}
