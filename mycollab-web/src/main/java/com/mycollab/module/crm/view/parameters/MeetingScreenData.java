package com.mycollab.module.crm.view.parameters;

import com.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.mycollab.vaadin.mvp.ScreenData;

public class MeetingScreenData {
	public static class Add extends ScreenData<MeetingWithBLOBs> {

		public Add(MeetingWithBLOBs meeting) {
			super(meeting);
		}
	}

	public static class Edit extends ScreenData<MeetingWithBLOBs> {

		public Edit(MeetingWithBLOBs meeting) {
			super(meeting);
		}
	}

	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
}
