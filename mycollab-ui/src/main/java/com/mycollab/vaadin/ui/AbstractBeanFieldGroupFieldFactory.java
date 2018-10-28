/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.ui;

import com.mycollab.core.arguments.NotBindable;
import com.mycollab.core.utils.ClassUtils;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.field.DefaultViewField;
import com.vaadin.data.*;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import org.apache.commons.beanutils.BeanUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public abstract class AbstractBeanFieldGroupFieldFactory<B> implements IBeanFieldGroupFieldFactory<B> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBeanFieldGroupFieldFactory.class);

    protected GenericBeanForm<B> attachForm;
    private boolean isReadOnlyGroup;
    private BeanValidationBinder<B> binder;

    public AbstractBeanFieldGroupFieldFactory(GenericBeanForm<B> form, boolean isValidateForm, boolean isReadOnlyGroup) {
        this.attachForm = form;
        this.isReadOnlyGroup = isReadOnlyGroup;
    }

    @Override
    public void setBean(B bean) {
        binder = new BeanValidationBinder<>((Class<B>) bean.getClass());
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
                        formField = new TextField();
                    }
                } else {
                    if (formField instanceof DummyCustomField) {
                        continue;
                    } else if (!(formField instanceof CompoundCustomField)) {
                        binder.bind(formField, bindField);
                    }
                }

                if (formField instanceof DateField && !(formField instanceof PopupDateFieldExt)) {
                    ((DateField) formField).setZoneId(UserUIContext.getUserTimeZone().toZoneId());
                    ((DateField) formField).setDateFormat(AppUI.getDateFormat());
                }
                attachForm.attachField(bindField, formField);
            }
        } else {
            Class<?> beanClass = bean.getClass();
            java.lang.reflect.Field[] fields = ClassUtils.getAllFields(beanClass);
            for (java.lang.reflect.Field field : fields) {
                AbstractField<?> formField = (AbstractField<?>) onCreateField(field.getName());
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
//                            formField = fieldGroup.buildAndBind(field.getName());
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
                    ((DateField) formField).setZoneId(UserUIContext.getUserTimeZone().toZoneId());
                    ((DateField) formField).setDateFormat(AppUI.getDateFormat());
                }

                attachForm.attachField(field.getName(), formField);
            }
        }
    }

    @Override
    public boolean commit() {
        try {
            binder.writeBean(attachForm.getBean());
        } catch (ValidationException e) {
            List<ValidationResult> beanValidationErrors = e.getBeanValidationErrors();
            return false;
        }
        BinderValidationStatus<B> validate = binder.validate();
        BinderValidationStatusHandler<B> validationStatusHandler = binder.getValidationStatusHandler();
        binder.setValidationStatusHandler((BinderValidationStatusHandler<B>) status -> {
            // create an error message on failed bean level validations
            List<ValidationResult> errors = status.getBeanValidationErrors();

            // collect all bean level error messages into a single string,
            // separating each message with a <br> tag
            String errorMessage = errors.stream().map(ValidationResult::getErrorMessage)
                    // sanitize the individual error strings to avoid code injection
                    // since we are displaying the resulting string as HTML
                    .map(errorString -> Jsoup.clean(errorString, Whitelist.simpleText()))
                    .collect(Collectors.joining("<br>"));
        });
        return true;
//        try {
//            fieldGroup.commit();
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
    }

    abstract protected HasValue<?> onCreateField(Object propertyId);
}
