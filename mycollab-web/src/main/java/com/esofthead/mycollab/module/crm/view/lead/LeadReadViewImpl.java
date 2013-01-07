package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class LeadReadViewImpl extends AbstractView implements LeadReadView {

    private static final long serialVersionUID = 1L;
    private LeadPreviewBuilder _leadPreview;

    public LeadReadViewImpl() {
        super();
        _leadPreview = new LeadPreviewBuilder.ReadView();
        this.addComponent(_leadPreview);
    }

    @Override
    public void previewItem(SimpleLead lead) {
        _leadPreview.previewItem(lead);
    }

    @Override
    public HasPreviewFormHandlers<Lead> getPreviewFormHandlers() {
        return _leadPreview.getPreviewForm();
    }

    @Override
    public SimpleLead getItem() {
        return _leadPreview.getLead();
    }
}
