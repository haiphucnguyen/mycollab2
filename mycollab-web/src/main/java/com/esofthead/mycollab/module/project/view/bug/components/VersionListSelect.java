package com.esofthead.mycollab.module.project.view.bug.components;

import java.util.List;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.ui.ListSelect;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 *
 */
public class VersionListSelect extends ListSelect {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public VersionListSelect() {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		this.setMultiSelect(true);

		VersionSearchCriteria searchCriteria = new VersionSearchCriteria();
		searchCriteria.setStatus(new StringSearchField(StatusI18nEnum.Open
				.name()));

		searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
				CurrentProjectVariables.getProjectId()));

		VersionService versionService = ApplicationContextUtil
				.getSpringBean(VersionService.class);
		List<Version> versions = versionService
				.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
						searchCriteria, 0, Integer.MAX_VALUE));
		for (Version version : versions) {
			this.addItem(version.getId());
			this.setItemCaption(version.getId(), version.getVersionname());
		}

		this.setRows(4);
	}
}
