package com.esofthead.mycollab.module.project.view.file;

import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Tree;

@ViewComponent
public class FileManagerViewImpl extends AbstractView implements
		FileManagerView {
	private static final long serialVersionUID = 1L;

	private Tree rootFolder;

	private ResourceService resourceService;

	public FileManagerViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);

		HorizontalLayout menuBar = new HorizontalLayout();
		this.addComponent(menuBar);

		rootFolder = new Tree();

		Item addItem = rootFolder.addItem("Documents");
		this.addComponent(rootFolder);
	}
}
