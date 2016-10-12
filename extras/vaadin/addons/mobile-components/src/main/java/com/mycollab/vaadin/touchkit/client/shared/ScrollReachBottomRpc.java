package com.mycollab.vaadin.touchkit.client.shared;

import com.vaadin.shared.communication.ServerRpc;

public interface ScrollReachBottomRpc extends ServerRpc {
	void onReachBottom();
}
