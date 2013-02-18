package com.esofthead.mycollab.module.user.ui.components;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;
import java.util.List;

public class UserComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public UserComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

		UserSearchCriteria criteria = new UserSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		UserService userService = AppContext.getSpringBean(UserService.class);
		List<SimpleUser> userList = userService
				.findPagableListByCriteria(new SearchRequest<UserSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		loadUserList(userList);

	}

	public UserComboBox(List<SimpleUser> userList) {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
		loadUserList(userList);
	}

	private void loadUserList(List<SimpleUser> userList) {
		BeanContainer<String, SimpleUser> beanItem = new BeanContainer<String, SimpleUser>(
				SimpleUser.class);
		beanItem.setBeanIdProperty("username");

		for (SimpleUser user : userList) {
			beanItem.addBean(user);
		}

		this.setContainerDataSource(beanItem);
		this.setItemCaptionPropertyId("displayName");
	}
}
