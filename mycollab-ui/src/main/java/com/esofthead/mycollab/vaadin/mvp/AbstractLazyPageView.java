package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.vaadin.AppContext;
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

	private boolean isRunning = false;

	public void lazyLoadView() {
		if (!isRunning) {
			isRunning = true;
			new InitializerThread().start();
		}
	}

	abstract protected void displayView();

	class InitializerThread extends Thread {
		@Override
		public void run() {
			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {
					if (AppContext.getInstance().isClosed()) {
						return;
					}
					displayView();
					UI.getCurrent().push();
					isRunning = false;
				}

			});
		};
	}
}
