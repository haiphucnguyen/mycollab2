package com.esofthead.mycollab.module.user.ui.components;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ListSelect;

public @SuppressWarnings("serial")
class UserListSelect extends ListSelect {
	private List<SimpleUser> userList;
	public UserListSelect() {
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

		UserService userService = AppContext.getSpringBean(UserService.class);
		 userList = userService
				.findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		loadData(userList);
	}
	
	public void loadData(List<SimpleUser> userList){
		for (SimpleUser user : userList) {
			this.addItem(user.getUsername());
			this.setItemCaption(user.getUsername(), user.getDisplayName());
			this.setItemIcon(user.getUsername(), UserAvatarControlFactory
					.getResource(user.getUsername(), 16));
		}

		this.setRows(4);
	}
	
	public List<SimpleUser> getListUser(){
		return userList;
	}
}
