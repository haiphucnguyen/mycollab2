package com.mycollab.module.crm.view.parameters;

import com.mycollab.module.crm.domain.CallWithBLOBs;
import com.mycollab.vaadin.mvp.ScreenData;

public class CallScreenData {
	public static class Add extends ScreenData<CallWithBLOBs> {

		public Add(CallWithBLOBs call) {
			super(call);
		}
	}

	public static class Edit extends ScreenData<CallWithBLOBs> {

		public Edit(CallWithBLOBs call) {
			super(call);
		}
	}
	
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}
}
