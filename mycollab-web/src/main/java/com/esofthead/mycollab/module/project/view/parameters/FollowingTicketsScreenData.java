package com.esofthead.mycollab.module.project.view.parameters;

import java.util.List;

import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class FollowingTicketsScreenData {
	public static class GotoMyFollowingItems extends ScreenData<List<Integer>> {

		public GotoMyFollowingItems(List<Integer> prjKeys) {
			super(prjKeys);
		}
	}
}
