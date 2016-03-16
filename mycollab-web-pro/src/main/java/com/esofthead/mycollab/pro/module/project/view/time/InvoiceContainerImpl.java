package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.esofthead.mycollab.module.project.view.time.IInvoiceContainer;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class InvoiceContainerImpl extends AbstractPageView implements IInvoiceContainer {

    private InvoiceSearchCriteria searchCriteria;

    @Override
    public void display() {
        removeAllComponents();
        MHorizontalLayout header = new MHorizontalLayout().withSpacing(false).withStyleName("hdr-view").withWidth
                ("100%").withMargin(true);
        MHorizontalLayout bodyLayout = new MHorizontalLayout().withMargin(true);
        with(header, bodyLayout).expand(bodyLayout);

        InvoiceListComp invoiceListComp = new InvoiceListComp();
        InvoiceReadView invoiceReadView = new InvoiceReadView();
        bodyLayout.with(invoiceListComp, invoiceReadView).expand(invoiceReadView);
    }

    private static class InvoiceListComp extends VerticalLayout {
        InvoiceListComp() {
            setWidth("400px");
            addStyleName(UIConstants.BOX);
        }
    }

    private static class InvoiceReadView extends VerticalLayout {

    }
}
