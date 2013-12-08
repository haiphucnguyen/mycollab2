package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

public interface FileSearchResultView extends PageView {

	void displaySearchResult(FileSearchCriteria searchCriteria);
}
