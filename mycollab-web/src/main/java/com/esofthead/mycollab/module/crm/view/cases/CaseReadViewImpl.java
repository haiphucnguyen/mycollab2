package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CaseReadViewImpl extends AbstractView implements CaseReadView {

    private static final long serialVersionUID = 1L;

    private CasePreview casePreview;
    
    public CaseReadViewImpl() {
        super();
        casePreview = new CasePreview(true);
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
