package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.PrefixListSelect;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import java.util.Collection;

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
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource, propertyIds);
        }

        class FormLayoutFactory extends LeadFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super((lead.getId() == null) ? "Create Lead" : lead.getLastname());
            }

            private Layout createButtonControls() {
                return (new EditFormControlsGenerator<Lead>(EditForm.this))
                        .createButtonControls();
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

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("prefixname")) {
                    return new PrefixListSelect();
                } else if (propertyId.equals("primcountry")
                        || propertyId.equals("othercountry")) {
                    CountryComboBox countryComboBox = new CountryComboBox();
                    return countryComboBox;
                } else if (propertyId.equals("status")) {
                    LeadStatusComboBox statusComboBox = new LeadStatusComboBox();
                    return statusComboBox;
                } else if (propertyId.equals("industry")) {
                    IndustryComboBox industryComboBox = new IndustryComboBox();
                    return industryComboBox;
                } else if (propertyId.equals("assignuser")) {
                    UserComboBox userComboBox = new UserComboBox();
                    return userComboBox;
                } else if (propertyId.equals("source")) {
                    LeadSourceComboBox statusComboBox = new LeadSourceComboBox();
                    return statusComboBox;
                } else if (propertyId.equals("lastname")) {
                    TextField tf = new TextField();
                    tf.setNullRepresentation("");
                    tf.setRequired(true);
                    tf.setRequiredError("Last name must not be null");
                    return tf;
                } else if (propertyId.equals("description")) {
                    TextArea descArea = new TextArea();
                    descArea.setNullRepresentation("");
                    return descArea;
                }

                return null;
            }
        }
    }

    @Override
    public HasEditFormHandlers<Lead> getEditFormHandlers() {
        return editForm;
    }
}
