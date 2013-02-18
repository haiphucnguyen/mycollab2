/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends AbstractView implements
		ProjectMemberReadView {
	private static final long serialVersionUID = 1L;

	public ProjectMemberReadViewImpl() {
		this.addComponent(new Label("A"));
	}

	@Override
	public void previewItem(SimpleProjectMember item) {

	}

	@Override
	public SimpleProjectMember getItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
