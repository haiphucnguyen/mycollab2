package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CallReadViewImpl extends AbstractView implements CallReadView {

    private static final long serialVersionUID = 1L;
    
    private CallPreviewBuilder callPreview;

    public CallReadViewImpl() {
        super();
        callPreview = new CallPreviewBuilder.ReadView();
        this.addComponent(callPreview);
    }

    @Override
    public void previewItem(SimpleCall call) {
    	callPreview.call = call;
    	callPreview.previewItem(call);
    }

    @Override
    public HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers() {
        return callPreview.getPreviewForm();
    }

    @Override
    public SimpleCall getItem() {
        return callPreview.getCall();
    }
}
