/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 *
 * @author haiphucnguyen
 */
public interface MilestoneReadView  extends IPreviewView<SimpleMilestone> {

    HasPreviewFormHandlers<Milestone> getPreviewFormHandlers();
    
}
