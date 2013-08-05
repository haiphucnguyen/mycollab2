/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.module.ecm.domain.criteria.ContentActivityLogSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class FileActivityStreamComponent extends Depot {
	private static final long serialVersionUID = 1L;

	private final FileActivityStreamPagedList activityStreamList;

	public FileActivityStreamComponent() {
		super("File Feeds", new VerticalLayout());
		this.activityStreamList = new FileActivityStreamPagedList();
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showContentFeeds() {
		this.bodyContent.removeAllComponents();
		this.bodyContent.addComponent(new LazyLoadWrapper(
				this.activityStreamList));
		final ContentActivityLogSearchCriteria searchCriteria = new ContentActivityLogSearchCriteria();

		this.activityStreamList.setSearchCriteria(searchCriteria);
	}
}
