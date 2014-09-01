package com.esofthead.mycollab.module.project.view.bug.components;

import java.util.List;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.ui.ListSelect;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 *
 */
public class ComponentListSelect extends ListSelect {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public ComponentListSelect() {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		this.setMultiSelect(true);

		ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();
		searchCriteria.setStatus(new StringSearchField(StatusI18nEnum.Open
				.name()));

		searchCriteria.setProjectid(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));

		ComponentService componentService = ApplicationContextUtil
				.getSpringBean(ComponentService.class);
		List<Component> components = componentService
				.findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		for (Component component : components) {
			this.addItem(component.getId());
			this.setItemCaption(component.getId(), component.getComponentname());
		}

		this.setRows(4);
	}
}
