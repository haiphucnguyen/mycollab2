package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.module.file.view.IFileModule;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class FileModule extends AbstractPageView implements IFileModule {
	private static final long serialVersionUID = 1L;

	public FileModule() {
		ControllerRegistry.addController(new FileController(this));
	}
}
