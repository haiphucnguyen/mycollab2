package com.mycollab.module.project.view.page;

import com.mycollab.module.page.domain.PageResource;
import com.mycollab.vaadin.mvp.PageView;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public interface PageListView extends PageView {
    /**
     * @param resources
     */
    void displayDefaultPages(List<PageResource> resources);

    void showNoItemView();
}
