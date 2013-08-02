/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 *
 * @author haiphucnguyen
 */
public interface MessageReadView extends IPreviewView<SimpleMessage> {

    HasPreviewFormHandlers<SimpleMessage> getPreviewFormHandlers();
}
