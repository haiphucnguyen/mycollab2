package com.esofthead.mycollab.module.crm.view.parameters;

import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

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
