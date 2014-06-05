package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1.2
 * 
 */
public abstract class AbstractLazyPageView extends AbstractPageView implements
		LazyPageView {
	private static final long serialVersionUID = 1L;

	private static ProgressIndicator loadingProgressIndicator = new ProgressIndicator();

	private boolean isRunning = false;

	@Override
	public void lazyLoadView() {
		if (!isRunning) {
			isRunning = true;
			new InitializerThread().start();
			if (loadingProgressIndicator.isAttached())
				loadingProgressIndicator.setVisible(true);
			else {
				UI.getCurrent().addWindow(loadingProgressIndicator);
			}
		}
	}

	abstract protected void displayView();

	class InitializerThread extends Thread {
		@Override
		public void run() {
			UI.getCurrent().access(new Runnable() {

				@Override
				public void run() {
					displayView();
					// UI.getCurrent().removeWindow(loadingProgressIndicator);
					loadingProgressIndicator.close();
					UI.getCurrent().push();
					isRunning = false;
				}

			});
		};
	}

	private static class ProgressIndicator extends Window {
		private static final long serialVersionUID = -6157950150738214354L;

		public ProgressIndicator() {
			super();
			this.setDraggable(false);
			this.setClosable(false);
			this.setResizable(false);
			this.setStyleName("lazyload-progress");
			this.center();
			this.setModal(true);

			Image loadingIcon = new Image(null,
					MyCollabResource.newResource("icons/lazy-load-icon.gif"));
			this.setContent(loadingIcon);
		}
	}
}
