package com.mycollab.vaadin.addon.sharedcomponents.client.shared;

import com.vaadin.shared.communication.ServerRpc;

public interface ScrollReachBottomRpc extends ServerRpc {
	public void onReachBottom();
}
