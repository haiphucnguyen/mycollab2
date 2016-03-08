package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.domain.Tag;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ITagListView extends PageView {
    void displayTags(Tag tag);
}
