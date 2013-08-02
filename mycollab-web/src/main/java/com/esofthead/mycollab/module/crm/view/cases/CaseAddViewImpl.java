package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.CaseWithBLOBs;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class CaseAddViewImpl extends AbstractView implements CaseAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private CaseWithBLOBs cases;

    public CaseAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(CaseWithBLOBs cases) {
        this.cases = cases;
        editForm.setItemDataSource(new BeanItem<CaseWithBLOBs>(cases));
    }

    private class EditForm extends AdvancedEditBeanForm<CaseWithBLOBs> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new CaseEditFormFieldFactory(cases));
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends CaseFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((cases.getId() == null) ? "Create Case" : cases.getSubject());
            }

            private Layout createButtonControls() {
            	final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<CaseWithBLOBs>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
            }

            @Override
            protected Layout createTopPanel() {
                return createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return createButtonControls();
            }
        }
    }

    @Override
    public HasEditFormHandlers<CaseWithBLOBs> getEditFormHandlers() {
        return editForm;
    }
}
