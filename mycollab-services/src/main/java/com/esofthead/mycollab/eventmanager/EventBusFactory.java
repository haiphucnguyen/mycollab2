package com.esofthead.mycollab.eventmanager;

public interface EventBusFactory {
	EventBus getInstance();

	EventBus getInstanceSession(String sessionId);
}
