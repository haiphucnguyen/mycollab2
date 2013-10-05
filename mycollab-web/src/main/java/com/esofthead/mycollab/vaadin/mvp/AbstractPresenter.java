package com.esofthead.mycollab.vaadin.mvp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.PermissionMap;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.security.PermissionChecker;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public abstract class AbstractPresenter<V extends View> implements Presenter<V> {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(AbstractPresenter.class);
	protected V view;

	public AbstractPresenter(Class<V> viewClass) {
		view = ViewManager.getView(viewClass);
	}

	@Override
	public V getView() {
		return view;
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		go(container, data, true);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data,
			boolean isHistoryTrack) {
		log.debug("Go to view: " + view);
		if (isHistoryTrack) {
			ViewState state = new ViewState(container, this, data);
			if (log.isDebugEnabled()) {
				StringBuilder str = new StringBuilder();
				str.append("----").append("\n");
				str.append("Add ").append(state).append("\n");
				str.append("to history with container ").append(container)
						.append("\n");
				str.append("----");
				log.debug(str.toString());
			}
			HistoryViewManager.addHistory(state);
		} else {
			log.debug("Disable history track for " + this);
		}

		if (checkPermissionAccessIfAny()) {
			onGo(container, data);
		} else {
			MessageConstants.showMessagePermissionAlert();
		}

	}

	protected abstract void onGo(ComponentContainer container,
			ScreenData<?> data);

	private boolean checkPermissionAccessIfAny() {
		ViewPermission viewPermission = this.getClass().getAnnotation(
				ViewPermission.class);
		if (viewPermission != null) {
			String permissionId = viewPermission.permissionId();
			int impliedPermissionVal = viewPermission.impliedPermissionVal();

			if (AppContext.isAdmin()) {
				return true;
			} else {
				PermissionMap permissionMap = AppContext.getPermissionMap();
				if (permissionMap == null) {
					return false;
				} else {
					Integer value = permissionMap.get(permissionId);
					if (value == null) {
						return false;
					} else {
						return PermissionChecker.isImplied(value,
								impliedPermissionVal);
					}
				}
			}
		} else {
			return true;
		}
	}

	@Override
	public void handleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		log.debug("Handle chain: " + BeanUtility.printBeanObj(pageActionChain)
				+ "---" + this);
		ScreenData pageAction = pageActionChain.pop();
		go(container, pageAction);

		if (pageActionChain.hasNext()) {
			onHandleChain(container, pageActionChain);
		} else {
			onDefaultStopChain();
		}
	}

	protected void onDefaultStopChain() {

	}

	protected void onHandleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		throw new UnsupportedOperationException("You need override this method");
	}
}
