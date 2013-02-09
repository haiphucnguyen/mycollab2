/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneReadViewImpl extends AbstractView implements MilestoneReadView {
    
    private static final long serialVersionUID = 1L;
    private MilestonePreviewBuilder.ReadView milestonePreview;
    
    public MilestoneReadViewImpl() {
        super();
        milestonePreview = new MilestonePreviewBuilder.ReadView();
        this.addComponent(milestonePreview);
    }
    
    @Override
    public void previewItem(SimpleMilestone item) {
    	milestonePreview.previewItem(item);
    }
    
    @Override
    public SimpleMilestone getItem() {
        return milestonePreview.getMilestone();
    }
    
    @Override
    public HasPreviewFormHandlers<Milestone> getPreviewFormHandlers() {
        return milestonePreview.getPreviewForm();
    }
}
