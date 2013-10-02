package com.esofthead.mycollab.eventmanager;

import com.esofthead.mycollab.core.MyCollabException;

public abstract class EventBus {

	private static String eventbusFactoryImplClsName = "com.esofthead.mycollab.eventmanager.EventBusFactoryImpl";

	private static EventBusFactory eventbusFactory;

	static {
		try {
			Class<EventBusFactory> cls = (Class<EventBusFactory>) Class
					.forName(eventbusFactoryImplClsName);
			eventbusFactory = cls.newInstance();
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	public static EventBus getInstance() {
		return eventbusFactory.getInstance();
	}

	public static EventBus getInstanceSession(String sessionId) {
		return eventbusFactory.getInstanceSession(sessionId);
	}

	abstract public void addListener(ApplicationEventListener<?> listener);

	abstract public void removeListener(ApplicationEventListener<?> listener);

	abstract public void clear();

	abstract public void fireEvent(ApplicationEvent event);
}
