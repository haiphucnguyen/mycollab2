package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.esofthead.vaadin.mobilecomponent.InfiniteScrollLayout;
import com.esofthead.vaadin.mobilecomponent.client.shared.ScrollReachBottomRpc;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Element;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VCssLayout;
import com.vaadin.shared.ui.Connect;

@Connect(InfiniteScrollLayout.class)
public class InfiniteScrollLayoutConnector extends AbstractExtensionConnector implements AttachEvent.Handler {
	private static final long serialVersionUID = -6280613841079001933L;

	private ScrollReachBottomRpc scrollReachBottomRpc = RpcProxy.create(ScrollReachBottomRpc.class, this);
	private JavaScriptObject scrollHandler;
	private VCssLayout layout;



	@Override
	protected void init() {
		super.init();

		initHandler();
	}

	private native void initHandler() /*-{
		var self = this;
		this.@com.esofthead.vaadin.mobilecomponent.client.ui.InfiniteScrollLayoutConnector::scrollHandler = function() {
			self.@com.esofthead.vaadin.mobilecomponent.client.ui.InfiniteScrollLayoutConnector::callRpc()();
		};
	}-*/;

	@Override
	protected void extend(ServerConnector target) {
		layout = (VCssLayout) ((ComponentConnector) target).getWidget();

		layout.addAttachHandler(this);
	}

	@Override
	public void onAttachOrDetach(AttachEvent event) {
		if (event.isAttached()) {
			addScrollHandler(layout.getElement());
		} else {
			removeScrollHandler(layout.getElement());
		}
	}

	private native void removeScrollHandler(Element element) /*-{
		var self = this;
		element.addEventListener('scroll', self.@com.esofthead.vaadin.mobilecomponent.client.ui.InfiniteScrollLayoutConnector::scrollHandler);
	}-*/;

	private native void addScrollHandler(Element element) /*-{
		var self = this;
		element.removeEventListener('scroll', self.@com.esofthead.vaadin.mobilecomponent.client.ui.InfiniteScrollLayoutConnector::scrollHandler);
	}-*/;

	private void callRpc() {
		scrollReachBottomRpc.onReachBottom();
	}

}
