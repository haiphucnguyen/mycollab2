package com.esofthead.mycollab.mobile.module.project.view.parameters;

import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class BugScreenData {

	public static class Search extends ScreenData<BugFilterParameter> {

		public Search(BugFilterParameter params) {
			super(params);
		}
	}

	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<BugWithBLOBs> {

		public Add(BugWithBLOBs params) {
			super(params);
		}
	}

	public static class Edit extends ScreenData<BugWithBLOBs> {

		public Edit(BugWithBLOBs params) {
			super(params);
		}
	}
}
