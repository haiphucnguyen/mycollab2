package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class FollowingTicketViewImpl extends AbstractView implements
		FollowingTicketView {
	private static final long serialVersionUID = 1L;

	public FollowingTicketViewImpl() {
		this.addComponent(new Label("Display tickets"));
	}

	@Override
	public void displayFollowingTicket() {
		// TODO Auto-generated method stub

	}
}
