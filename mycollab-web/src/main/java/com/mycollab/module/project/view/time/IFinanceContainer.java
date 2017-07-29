package com.mycollab.module.project.view.time;

import com.mycollab.vaadin.web.ui.InitializingView;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface IFinanceContainer extends InitializingView {

    void initContent();

    Component gotoSubView(String name);

    void showTimeView();

    void showInvoiceView();
}
