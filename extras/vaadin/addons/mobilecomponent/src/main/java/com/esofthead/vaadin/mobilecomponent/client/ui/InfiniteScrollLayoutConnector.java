package com.esofthead.vaadin.mobilecomponent.client.ui;

import com.esofthead.vaadin.mobilecomponent.InfiniteScrollLayout;
import com.esofthead.vaadin.mobilecomponent.client.shared.ScrollReachBottomRpc;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.ui.VCssLayout;
import com.vaadin.shared.ui.Connect;

@Connect(InfiniteScrollLayout.class)
public class InfiniteScrollLayoutConnector extends AbstractExtensionConnector implements AttachEvent.Handler {
	private static final long serialVersionUID = -6280613841079001933L;

	private static final String CLASSNAME = "v-scrolllayout";

	private ScrollReachBottomRpc scrollReachBottomRpc = RpcProxy.create(ScrollReachBottomRpc.class, this);
	public JavaScriptObject scrollHandler;
	private VCssLayout layout;
	private Element contentEl;

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
		layout.addStyleName(CLASSNAME);
		contentEl = layout.getElement();
		layout.addAttachHandler(this);
	}

	@Override
	public void onAttachOrDetach(AttachEvent event) {
		if (event.isAttached()) {
			addScrollHandler(contentEl);
		} else {
			removeScrollHandler(contentEl);
		}
	}

	private native void removeScrollHandler(Element element) /*-{
		var self = this;
		element.removeEventListener('scroll', self.@com.esofthead.vaadin.mobilecomponent.client.ui.InfiniteScrollLayoutConnector::scrollHandler);
	}-*/;

	private native void addScrollHandler(Element element) /*-{
		var self = this;
		element.addEventListener('scroll', self.@com.esofthead.vaadin.mobilecomponent.client.ui.InfiniteScrollLayoutConnector::scrollHandler);
	}-*/;

	public void callRpc() {
		int scrollTop = contentEl.getScrollTop();
		if (scrollTop == contentEl.getScrollHeight() - contentEl.getOffsetHeight())
			scrollReachBottomRpc.onReachBottom();
	}

}
