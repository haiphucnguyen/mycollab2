package com.esofthead.mycollab.module.user.accountsettings.view.parameters;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

public class UserScreenData {
	public static class Read extends ScreenData<Object> {

		public Read(Object params) {
			super(params);
		}
	}

	public static class Add extends ScreenData<SimpleUser> {

		public Add(SimpleUser params) {
			super(params);
		}
	}

	public static class Edit extends ScreenData<User> {

		public Edit(User params) {
			super(params);
		}
	}

	public static class Search extends ScreenData<UserSearchCriteria> {

		public Search(UserSearchCriteria params) {
			super(params);
		}
	}
}
