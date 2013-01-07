package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CaseReadViewImpl extends AbstractView implements CaseReadView {

    private static final long serialVersionUID = 1L;

    private CasePreviewBuilder casePreview;
    
    public CaseReadViewImpl() {
        super();
        casePreview = new CasePreviewBuilder.ReadView();
        this.addComponent(casePreview);
    }

    @Override
    public void previewItem(SimpleCase item) {
    	casePreview.previewItem(item);
    }

    @Override
    public HasPreviewFormHandlers<Case> getPreviewFormHandlers() {
        return casePreview.getPreviewForm();
    }

    @Override
    public SimpleCase getItem() {
        return casePreview.getCase();
    }
}
