package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.View;

public interface UserDashboardView extends View {
	void gotoMyProjectList();
	
	void gotoMyFeeds();
	
	void gotoMyTasks();
	
	void gotoMyBugs();
}
