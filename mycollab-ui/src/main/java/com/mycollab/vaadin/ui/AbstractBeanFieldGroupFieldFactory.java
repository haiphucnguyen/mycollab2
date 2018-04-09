/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.ui;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.arguments.NotBindable;
import com.mycollab.core.utils.ClassUtils;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ui.field.DefaultViewField;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.touchkit.gwt.client.vcom.DatePickerState;
import org.vaadin.touchkit.ui.DatePicker;

import javax.validation.Validator;
import java.util.Set;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public abstract class AbstractBeanFieldGroupFieldFactory<B> implements IBeanFieldGroupFieldFactory<B> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBeanFieldGroupFieldFactory.class);

    protected GenericBeanForm<B> attachForm;
    protected Binder<B> binder;
    protected boolean isValidateForm;
    protected boolean isReadOnlyGroup;
    protected Validator validation;

    public AbstractBeanFieldGroupFieldFactory(GenericBeanForm<B> form, boolean isValidateForm, boolean isReadOnlyGroup) {
        this.attachForm = form;
        this.binder = new Binder<>();
        this.isValidateForm = isValidateForm;
        this.isReadOnlyGroup = isReadOnlyGroup;

        if (isValidateForm) {
            validation = AppContextUtil.getValidator();
        }
    }

    @Override
    public void setBean(B bean) {
        IFormLayoutFactory layoutFactory = attachForm.getLayoutFactory();
        if (layoutFactory instanceof WrappedFormLayoutFactory) {
            layoutFactory = ((WrappedFormLayoutFactory) layoutFactory).getWrappedFactory();
        }
        if (layoutFactory instanceof IDynaFormLayout) {
            IDynaFormLayout dynaFormLayout = (IDynaFormLayout) layoutFactory;
            Set<String> bindFields = dynaFormLayout.bindFields();
            for (String bindField : bindFields) {
                HasValue<?> formField = onCreateField(bindField);
                if (formField == null) {
                    if (isReadOnlyGroup) {
                        try {
                            String propertyValue = BeanUtils.getProperty(attachForm.getBean(), bindField);
                            formField = new DefaultViewField(propertyValue);
                        } catch (Exception e) {
                            LOG.error("Error while get field value", e);
                            formField = new DefaultViewField("Error");
                        }
                    } else {
                        binder.bind(formField, bindField);
                    }
                } else {
                    if (formField instanceof DummyCustomField) {
                        continue;
                    } else if (!(formField instanceof CompoundCustomField)) {
                        binder.bind(formField, bindField);
                    }
                }

                if (formField instanceof DateField && !(formField instanceof PopupDateFieldExt)) {
                    // TODO: do we need to check timezone
//                    ((DateField) formField).setTimeZone(UserUIContext.getUserTimeZone());
                    ((DateField) formField).setDateFormat(AppUI.getDateFormat());
                }
                postCreateField(bindField, formField);
                attachForm.attachField(bindField, (Component) formField);
            }
        } else {
            Class<?> beanClass = bean.getClass();
            java.lang.reflect.Field[] fields = ClassUtils.getAllFields(beanClass);
            for (java.lang.reflect.Field field : fields) {
                HasValue<?> formField = onCreateField(field.getName());
                if (formField == null) {
                    if (field.getAnnotation(NotBindable.class) != null) {
                        continue;
                    } else {
                        if (isReadOnlyGroup) {
                            try {
                                String propertyValue = BeanUtils.getProperty(attachForm.getBean(), field.getName());
                                formField = new DefaultViewField(propertyValue);
                            } catch (Exception e) {
                                LOG.error("Error while get field value", e);
                                formField = new DefaultViewField("Error");
                            }
                        } else {
                            // TODO: check this case
                            throw new MyCollabException("Check this case");
                        }
                    }
                } else {
                    if (formField instanceof DummyCustomField) {
                        continue;
                    } else if (!(formField instanceof CompoundCustomField)) {
                        if (!isReadOnlyGroup) {
                            binder.bind(formField, field.getName());
                        }
                    }
                }

                if (formField instanceof DateField) {
                    //TODO: check whether we need to set timezone
//                    ((DateField) formField).setTimeZone(UserUIContext.getUserTimeZone());
                    ((DateField) formField).setDateFormat(AppUI.getDateFormat());
                } else if (formField instanceof DatePicker) {
                    ((DatePicker) formField).setResolution(DatePickerState.Resolution.DAY);
                    ((DatePicker) formField).setWidth("100px");
                }
                postCreateField(field.getName(), formField);
                attachForm.attachField(field.getName(), (Component) formField);
            }
        }
    }

    @Override
    public boolean commit() {
//        try {
//            binder.commit();
//            attachForm.setValid(true);
//            return true;
//        } catch (FieldGroup.CommitException e) {
//            attachForm.setValid(false);
//            Map<Field<?>, com.vaadin.data.Validator.InvalidValueException> invalidFields = e.getInvalidFields();
//            if (invalidFields != null && invalidFields.size() > 0) {
//                StringBuilder errorMsg = new StringBuilder();
//                for (Map.Entry<Field<?>, com.vaadin.data.Validator.InvalidValueException> entry : invalidFields.entrySet()) {
//                    com.vaadin.data.Validator.InvalidValueException invalidEx = entry.getValue();
//                    errorMsg.append(invalidEx.getHtmlMessage()).append("<br/>");
//                    entry.getKey().addStyleName("errorField");
//                }
//                NotificationUtil.showErrorNotification(errorMsg.toString());
//                return false;
//            } else {
//                NotificationUtil.showErrorNotification(e.getCause().getMessage());
//                return false;
//            }
//        } catch (Exception e) {
//            throw new MyCollabException(e);
//        }
        return true;
    }

//    @Override
//    public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
//        for (Object propertyId : binder.getBoundPropertyIds()) {
//            binder.getField(propertyId).removeStyleName("errorField");
//        }
//        StringBuilder errorMsg = new StringBuilder();
//        int violationCount = 0;
//        for (Field<?> f : commitEvent.getFieldBinder().getFields()) {
//            try {
//                if (f instanceof CustomField) {
//                    continue;
//                }
//                f.validate();
//            } catch (com.vaadin.data.Validator.InvalidValueException e) {
//                violationCount++;
//                errorMsg.append(e.getHtmlMessage()).append("<br/>");
//                f.addStyleName("errorField");
//            }
//        }
//        if (violationCount > 0) {
//            throw new FieldGroup.CommitException(errorMsg.toString());
//        }
//    }

//    @Override
//    public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
//        Set<ConstraintViolation<B>> violations = validation.validate(attachForm.getBean());
//        if (violations.size() > 0) {
//            StringBuilder errorMsg = new StringBuilder();
//
//            for (ConstraintViolation violation : violations) {
//                errorMsg.append(violation.getMessage()).append("<br/>");
//                Path propertyPath = violation.getPropertyPath();
//                if (propertyPath != null && !propertyPath.toString().equals("")) {
//                    binder.getField(propertyPath.toString()).addStyleName("errorField");
//                } else {
//                    Annotation validateAnno = violation.getConstraintDescriptor().getAnnotation();
//                    if (validateAnno instanceof DateComparison) {
//                        String firstDateField = ((DateComparison) validateAnno).firstDateField();
//                        String lastDateField = ((DateComparison) validateAnno).lastDateField();
//
//                        binder.getField(firstDateField).addStyleName("errorField");
//                        binder.getField(lastDateField).addStyleName("errorField");
//                    }
//                }
//
//            }
//            throw new FieldGroup.CommitException(errorMsg.toString());
//        }
//    }

    @Override
    public void setBuffered(boolean isBuffered) {
//        if (binder != null) {
//            binder.setBuffered(isBuffered);
//        }
    }

    abstract protected HasValue<?> onCreateField(Object propertyId);

    protected void postCreateField(Object propertyId, HasValue<?> field) {
    }
}
