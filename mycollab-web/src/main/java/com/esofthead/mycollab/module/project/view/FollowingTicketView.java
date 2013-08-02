package com.esofthead.mycollab.module.project.view;

import java.util.List;

import com.esofthead.mycollab.vaadin.mvp.View;

public interface FollowingTicketView extends View {
	void displayFollowingTicket(List<Integer> prjKeys);
}
