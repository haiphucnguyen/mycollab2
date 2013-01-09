package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.web.AppContext;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class AdvancedEditBeanForm<T> extends GenericForm implements
        HasEditFormHandlers<T> {

    private final Validator validation;
    private List<EditFormHandler<T>> editFormHandlers;

    public AdvancedEditBeanForm() {
        validation = AppContext.getSpringBean(LocalValidatorFactoryBean.class);
    }

    public AdvancedEditBeanForm(IFormLayoutFactory factory) {
        this();
        this.setFormLayoutFactory(factory);
    }

    protected boolean validateForm(Object data) {
        for (Object propertyId : this.getItemPropertyIds()) {
            this.getField(propertyId).removeStyleName("errorField");
        }
        Set<ConstraintViolation<Object>> violations = validation.validate(data);
        if (violations.size() > 0) {
            StringBuilder errorMsg = new StringBuilder();

            for (@SuppressWarnings("rawtypes") ConstraintViolation violation : violations) {
                errorMsg.append(violation.getMessage()).append("<br/>");
                this.getField(violation.getPropertyPath().toString())
                        .addStyleName("errorField");
            }

            MessageBox mb = new MessageBox(getWindow(), "Error!",
                    MessageBox.Icon.ERROR, errorMsg.toString(),
                    new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
            mb.show();

            return false;
        }

        return true;
    }

    @Override
    public void addFormHandler(EditFormHandler<T> editFormHandler) {
        if (editFormHandlers == null) {
            editFormHandlers = new ArrayList<EditFormHandler<T>>();
        }

        editFormHandlers.add(editFormHandler);
    }

    protected void fireSaveForm(T bean) {
        if (editFormHandlers != null) {
            for (EditFormHandler<T> editFormHandler : editFormHandlers) {
                editFormHandler.onSave(bean);
            }
        }
    }

    protected void fireSaveAndNewForm(T bean) {
        if (editFormHandlers != null) {
            for (EditFormHandler<T> editFormHandler : editFormHandlers) {
                editFormHandler.onSaveAndNew(bean);
            }
        }
    }

    protected void fireCancelForm() {
        if (editFormHandlers != null) {
            for (EditFormHandler<T> editFormHandler : editFormHandlers) {
                editFormHandler.onCancel();
            }
        }
    }
}
