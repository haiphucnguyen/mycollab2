package com.esofthead.mycollab.module.user.accountsettings.view.parameters;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class RoleScreenData {
	public static class Read extends ScreenData<Integer> {

		public Read(Integer params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<Role> {

		public Add(Role params) {
			super(params);
		}
	}

	public static class Edit extends ScreenData<Role> {

		public Edit(Role params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<RoleSearchCriteria> {

		public Search(RoleSearchCriteria params) {
			super(params);
		}
	}
}
