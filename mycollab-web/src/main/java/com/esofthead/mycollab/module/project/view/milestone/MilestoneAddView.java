/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 *
 * @author haiphucnguyen
 */
public interface MilestoneAddView  extends IFormAddView<Milestone> {
	HasEditFormHandlers<Milestone> getEditFormHandlers();
    
}
