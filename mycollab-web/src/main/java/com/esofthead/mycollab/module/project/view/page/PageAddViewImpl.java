package com.esofthead.mycollab.module.project.view.page;

import com.esofthead.mycollab.module.wiki.domain.Page;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
@ViewComponent
public class PageAddViewImpl extends AbstractPageView implements PageAddView {
	private static final long serialVersionUID = 1L;

	public PageAddViewImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void editItem(Page item) {
		// TODO Auto-generated method stub

	}

	@Override
	public HasEditFormHandlers<Page> getEditFormHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
