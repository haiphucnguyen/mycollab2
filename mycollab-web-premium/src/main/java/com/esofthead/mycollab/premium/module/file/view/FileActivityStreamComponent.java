package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.module.ecm.domain.criteria.ContentActivityLogSearchCriteria;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
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
		this.bodyContent.addComponent(this.activityStreamList);
		final ContentActivityLogSearchCriteria searchCriteria = new ContentActivityLogSearchCriteria();

		this.activityStreamList.setSearchCriteria(searchCriteria);
	}

	public void addSelectedHandlerToPageList(
			SearchHandler<FileSearchCriteria> handler) {
		activityStreamList.addSearchHandler(handler);
	}

}
