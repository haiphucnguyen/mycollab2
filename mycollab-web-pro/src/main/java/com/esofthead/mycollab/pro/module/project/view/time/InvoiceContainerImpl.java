package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.view.time.IInvoiceContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class InvoiceContainerImpl extends AbstractPageView implements IInvoiceContainer {

    @Override
    public void display() {
        removeAllComponents();
        MHorizontalLayout headerLayout = new MHorizontalLayout();

    }

    private static class InvoiceListComp extends VerticalLayout {

    }
}
