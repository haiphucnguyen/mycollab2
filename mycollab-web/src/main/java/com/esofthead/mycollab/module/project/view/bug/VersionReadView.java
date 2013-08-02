/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 *
 * @author haiphucnguyen
 */
public interface VersionReadView extends IPreviewView<Version> {

    HasPreviewFormHandlers<Version> getPreviewFormHandlers();
    
}
