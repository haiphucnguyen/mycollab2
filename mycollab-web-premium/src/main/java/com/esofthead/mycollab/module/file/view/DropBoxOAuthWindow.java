package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class DropBoxOAuthWindow extends AbstractCloudDriveOAuthWindow {
	private static final long serialVersionUID = 1L;

	public DropBoxOAuthWindow() {
		super();
		this.setWidth("420px");
		this.setResizable(false);
		this.center();
		this.setContent(new Label("Do not support this feature in this edition"));
	}
}
