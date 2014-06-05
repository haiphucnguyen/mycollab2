package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1.2
 * 
 */
public abstract class AbstractLazyPageView extends AbstractPageView implements
		LazyPageView {
	private static final long serialVersionUID = 1L;

	public void lazyLoadView() {
		new InitializerThread().start();
	}

	abstract protected void displayView();

	class InitializerThread extends Thread {
		@Override
		public void run() {
			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {
					displayView();
					UI.getCurrent().push();
				}

			});
		};
	}
}
