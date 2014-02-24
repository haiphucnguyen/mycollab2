package com.esofthead.vaadin.mobilecomponent;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.vaadin.mobilecomponent.client.shared.ScrollReachBottomRpc;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.CssLayout;

public class InfiniteScrollLayout extends AbstractExtension {
	private static final long serialVersionUID = -6811662900005441558L;

	public interface ScrollReachBottomListener {
		public void onReachBottom();
	}

	private List<ScrollReachBottomListener> listeners = new ArrayList<ScrollReachBottomListener>();

	private ScrollReachBottomRpc scrollReachBottomRpc = new ScrollReachBottomRpc() {
		private static final long serialVersionUID = 8200818665164309787L;

		@Override
		public void onReachBottom() {
			for (ScrollReachBottomListener listener : listeners) {
				listener.onReachBottom();
			}

		}
	};

	public InfiniteScrollLayout() {
		registerRpc(scrollReachBottomRpc);
	}

	public static InfiniteScrollLayout extend(CssLayout layout) {
		InfiniteScrollLayout scrollLayout = new InfiniteScrollLayout();
		scrollLayout.extend((AbstractClientConnector) layout);
		return scrollLayout;
	}

	public void addScrollListener(ScrollReachBottomListener listener) {
		listeners.add(listener);
	}

	public void removeScrollListener(ScrollReachBottomListener listener) {
		listeners.remove(listener);
	}
}
