package com.esofthead.mycollab.module.project.view.file;

import com.esofthead.mycollab.vaadin.mvp.View;

public interface FileSearchResultView extends View {

	void displaySearchResult(String basePath, String name);
}
