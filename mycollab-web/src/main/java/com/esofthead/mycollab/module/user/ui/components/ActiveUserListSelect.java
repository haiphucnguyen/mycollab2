package com.esofthead.mycollab.module.user.ui.components;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ListSelect;

public @SuppressWarnings("serial")
class ActiveUserListSelect extends ListSelect {
	private List<SimpleUser> userList;

	public ActiveUserListSelect() {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);
		this.setMultiSelect(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attach() {
		super.attach();
		this.removeAllItems();
		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setRegisterStatus(new StringSearchField(
				RegisterStatusConstants.ACTIVE));

		UserService userService = AppContext.getSpringBean(UserService.class);
		userList = userService
				.findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		loadData(userList);
	}

	public void loadData(List<SimpleUser> userList) {
		for (SimpleUser user : userList) {
			this.addItem(user.getUsername());
			this.setItemCaption(user.getUsername(), user.getDisplayName());
			this.setItemIcon(
					user.getUsername(),
					UserAvatarControlFactory.createAvatarResource(
							user.getAvatarid(), 16));
		}

		this.setRows(4);
	}
}
