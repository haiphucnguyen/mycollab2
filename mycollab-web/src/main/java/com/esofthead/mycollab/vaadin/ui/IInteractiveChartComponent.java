package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public interface IInteractiveChartComponent extends PageView {
    void clickLegendItem(String key);
}
