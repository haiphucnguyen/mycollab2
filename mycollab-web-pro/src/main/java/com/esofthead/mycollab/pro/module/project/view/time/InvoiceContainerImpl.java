package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.domain.criteria.InvoiceSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.InvoiceService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.time.IInvoiceContainer;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
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
        ELabel invoiceIcon = ELabel.fontIcon(ProjectAssetsManager.getAsset(ProjectTypeConstants.INVOICE))
                .withStyleName(ValoTheme.LABEL_H2, ValoTheme.LABEL_NO_MARGIN);

        Component headerRightLayout = createHeaderRight();
        MHorizontalLayout header = new MHorizontalLayout(invoiceIcon, headerRightLayout).withSpacing(false).withStyleName("hdr-view")
                .withWidth("100%").withMargin(true).withAlign(headerRightLayout, Alignment.MIDDLE_RIGHT);
        MHorizontalLayout bodyLayout = new MHorizontalLayout().withMargin(true);
        with(header, bodyLayout).expand(bodyLayout);

        InvoiceListComp invoiceListComp = new InvoiceListComp();
        InvoiceReadView invoiceReadView = new InvoiceReadView();
        bodyLayout.with(invoiceListComp, invoiceReadView).expand(invoiceReadView);
    }

    private HorizontalLayout createHeaderRight() {
        MHorizontalLayout layout = new MHorizontalLayout();
        Button createBtn = new Button(AppContext.getMessage(InvoiceI18nEnum.BUTTON_NEW_INVOICE), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                SimpleInvoice invoice = new SimpleInvoice();
                invoice.setSaccountid(AppContext.getAccountId());
                invoice.setProjectid(CurrentProjectVariables.getProjectId());
                invoice.setStatus(OptionI18nEnum.InvoiceStatus.Scheduled.name());
                UI.getCurrent().addWindow(new InvoiceAddWindow(invoice));
            }
        });
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setStyleName(UIConstants.BUTTON_ACTION);
        createBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INVOICE));
        layout.with(createBtn);
        return layout;
    }

    private static class InvoiceListComp extends DefaultBeanPagedList<InvoiceService, InvoiceSearchCriteria,
            SimpleInvoice> {
        InvoiceListComp() {
            super(ApplicationContextUtil.getSpringBean(InvoiceService.class), new InvoiceRowDisplayHandler());
            setWidth("400px");
            addStyleName(UIConstants.BOX);
        }
    }

    private static class InvoiceRowDisplayHandler implements AbstractBeanPagedList.RowDisplayHandler<SimpleInvoice> {
        @Override
        public Component generateRow(AbstractBeanPagedList host, SimpleInvoice item, int rowIndex) {
            return new Label("Hello");
        }
    }

    private static class InvoiceReadView extends VerticalLayout {

    }
}
