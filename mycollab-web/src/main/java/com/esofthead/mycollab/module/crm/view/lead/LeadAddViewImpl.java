package com.esofthead.mycollab.module.crm.view.lead;

import java.util.Collection;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class LeadAddViewImpl extends AbstractView implements
        IFormAddView<Lead>, LeadAddView {

    private static final long serialVersionUID = 1L;
    private EditForm editForm;
    private Lead lead;

    public LeadAddViewImpl() {
        super();
        editForm = new EditForm();
        this.addComponent(editForm);
    }

    @Override
    public void editItem(Lead item) {
        this.lead = item;
        editForm.setItemDataSource(new BeanItem<Lead>(lead));
    }

    private class EditForm extends AdvancedEditBeanForm<Lead> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource,
                Collection<?> propertyIds) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new LeadEditFormFieldFactory(lead));
            super.setItemDataSource(newDataSource, propertyIds);
        }

        class FormLayoutFactory extends LeadFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((lead.getId() == null) ? "Create Lead" : lead.getLastname());
            }

            private Layout createButtonControls() {
            	final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Lead>(
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
    public HasEditFormHandlers<Lead> getEditFormHandlers() {
        return editForm;
    }
}
