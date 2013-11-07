package com.esofthead.mycollab.premium.module.project.view.file;

import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface FileSearchResultView extends View {

	void displaySearchResult(FileSearchCriteria searchCriteria);
}
