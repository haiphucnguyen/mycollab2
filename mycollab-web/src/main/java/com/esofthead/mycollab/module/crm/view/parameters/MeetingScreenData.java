package com.esofthead.mycollab.module.crm.view.parameters;

import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class MeetingScreenData {
	public static class Add extends ScreenData<Meeting> {

		public Add(Meeting meeting) {
			super(meeting);
		}
	}

	public static class Edit extends ScreenData<Meeting> {

		public Edit(Meeting meeting) {
			super(meeting);
		}
	}
	
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
}
