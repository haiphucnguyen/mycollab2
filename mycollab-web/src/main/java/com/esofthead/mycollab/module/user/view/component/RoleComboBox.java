/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view.component;

import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author haiphucnguyen
 */
public class RoleComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public RoleComboBox() {
		super();
		this.setNullSelectionAllowed(false);
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

		RoleSearchCriteria criteria = new RoleSearchCriteria();

		RoleService roleService = ApplicationContextUtil.getSpringBean(RoleService.class);
		List<SimpleRole> roleList = roleService
				.findPagableListByCriteria(new SearchRequest<RoleSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		BeanContainer<String, SimpleRole> beanItem = new BeanContainer<String, SimpleRole>(
				SimpleRole.class);
		beanItem.setBeanIdProperty("id");

		for (SimpleRole role : roleList) {
			beanItem.addBean(role);
		}

		SimpleRole ownerRole = new SimpleRole();
		ownerRole.setId(-1);
		ownerRole.setRolename("Account Owner");
		beanItem.addBean(ownerRole);

		this.setContainerDataSource(beanItem);
		this.setItemCaptionPropertyId("rolename");
		if (roleList.size() > 0) {
			SimpleRole role = roleList.get(0);
			this.setValue(role.getId());
		} else {
			this.setValue(-1);
		}
	}

}
