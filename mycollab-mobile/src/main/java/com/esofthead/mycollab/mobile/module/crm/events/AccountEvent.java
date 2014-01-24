package com.esofthead.mycollab.mobile.module.crm.events;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

public class AccountEvent {
	public static class Save extends ApplicationEvent {
		private static final long serialVersionUID = -8233913139722949767L;

		public Save(Object source, Object data) {
            super(source, data);
        }
    }

    public static class Search extends ApplicationEvent {
		private static final long serialVersionUID = 1753078348208999466L;

		public Search(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = -4100336975752268823L;

		public GotoList(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoAdd extends ApplicationEvent {
		private static final long serialVersionUID = 5537133797081861218L;

		public GotoAdd(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoRead extends ApplicationEvent {
		private static final long serialVersionUID = 2024499664273723194L;

		public GotoRead(Object source, Object data) {
            super(source, data);
        }
    }

    public static class GotoEdit extends ApplicationEvent {
		private static final long serialVersionUID = 28575528879169859L;

		public GotoEdit(Object source, Object data) {
            super(source, data);
        }
    }
}
