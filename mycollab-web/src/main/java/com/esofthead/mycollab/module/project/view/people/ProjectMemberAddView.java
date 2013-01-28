/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 *
 * @author haiphucnguyen
 */
public interface ProjectMemberAddView  extends IFormAddView<ProjectMember> {

    HasEditFormHandlers<ProjectMember> getEditFormHandlers();
    
}
