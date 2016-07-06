package com.mycollab.pro.module.project.view.reports;

import com.mycollab.vaadin.mvp.PageView;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface StandupListView extends PageView {
    /**
     *
     * @param projectKeys
     */
    void display(List<Integer> projectKeys, Date date);
}
