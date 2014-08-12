package com.esofthead.mycollab.mobile.module.project.ui;

import com.esofthead.mycollab.mobile.ui.AbstractNavigationMenu;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class ProjectModuleNavigationMenu extends AbstractNavigationMenu {

	private static final long serialVersionUID = -4087918174690391176L;

	public ProjectModuleNavigationMenu() {
		super();

		final MenuButton prjListBtn = new MenuButton("Projects", "&#xe614;");
		addMenu(prjListBtn);

		final MenuButton activityBtn = new MenuButton("Activities", "&#xe610;");
		addMenu(activityBtn);

		final MenuButton ticketBtn = new MenuButton("Following Tickets",
				"&#xe613;");
		addMenu(ticketBtn);
	}

	@Override
	protected ClickListener createDefaultButtonClickListener() {
		// TODO Auto-generated method stub
		return null;
	}

}
