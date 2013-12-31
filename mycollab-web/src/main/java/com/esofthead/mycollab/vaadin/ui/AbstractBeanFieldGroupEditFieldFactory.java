package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NotBindable;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.validator.constraints.DateComparision;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 
 * @author MyCollab Ltd
 * @since 3.0
 * 
 * @param <B>
 */
public abstract class AbstractBeanFieldGroupEditFieldFactory<B> implements
		IBeanFieldGroupFieldFactory<B>, CommitHandler {

	private static final long serialVersionUID = 1L;
	protected GenericBeanForm<B> attachForm;
	protected FieldGroup fieldGroup;
	private final Validator validation;

	public AbstractBeanFieldGroupEditFieldFactory(GenericBeanForm<B> form) {
		this.attachForm = form;
		this.fieldGroup = new FieldGroup();
		this.fieldGroup.setBuffered(true);
		this.fieldGroup.addCommitHandler(this);
		validation = ApplicationContextUtil
				.getSpringBean(LocalValidatorFactoryBean.class);
	}

	@Override
	public void setBean(B bean) {
		fieldGroup.setItemDataSource(new BeanItem<B>(bean));

		Class<?> beanClass = bean.getClass();
		java.lang.reflect.Field[] fields = ClassUtils.getAllFields(beanClass);
		for (java.lang.reflect.Field field : fields) {
			Field<?> formField = onCreateField(field.getName());
			if (formField == null) {
				if (field.getAnnotation(NotBindable.class) != null) {
					continue;
				} else {
					formField = fieldGroup.buildAndBind(field.getName());
				}
			} else {
				if (!(formField instanceof CompoundCustomField)) {
					fieldGroup.bind(formField, field.getName());
				}
			}

			if (formField instanceof AbstractTextField) {
				((AbstractTextField) formField).setNullRepresentation("");
			} else if (formField instanceof RichTextArea) {
				((RichTextArea) formField).setNullRepresentation("");
			}

			attachForm.attachField(field.getName(), formField);
		}
	}

	@Override
	public void commit() {
		try {
			fieldGroup.commit();
			attachForm.setValid(true);
		} catch (CommitException e) {
			e.printStackTrace();
			attachForm.setValid(false);
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	public void preCommit(FieldGroup.CommitEvent commitEvent)
			throws CommitException {

	}

	@Override
	public void postCommit(FieldGroup.CommitEvent commitEvent)
			throws CommitException {
		for (Object propertyId : fieldGroup.getBoundPropertyIds()) {
			fieldGroup.getField(propertyId).removeStyleName("errorField");
		}
		Set<ConstraintViolation<B>> violations = validation.validate(attachForm
				.getBean());
		if (violations.size() > 0) {
			StringBuilder errorMsg = new StringBuilder();

			for (@SuppressWarnings("rawtypes")
			ConstraintViolation violation : violations) {
				errorMsg.append(violation.getMessage()).append("<br/>");

				if (violation.getPropertyPath() != null
						&& !violation.getPropertyPath().toString().equals("")) {
					fieldGroup.getField(violation.getPropertyPath().toString())
							.addStyleName("errorField");
				} else {
					Annotation validateAnno = violation
							.getConstraintDescriptor().getAnnotation();
					if (validateAnno instanceof DateComparision) {
						String firstDateField = ((DateComparision) validateAnno)
								.firstDateField();
						String lastDateField = ((DateComparision) validateAnno)
								.lastDateField();

						fieldGroup.getField(firstDateField).addStyleName(
								"errorField");
						fieldGroup.getField(lastDateField).addStyleName(
								"errorField");
					}
				}

			}

			NotificationUtil.showErrorNotification(errorMsg.toString());
			attachForm.setValid(false);
			throw new CommitException(errorMsg.toString());
		}
	}

	abstract protected Field<?> onCreateField(Object propertyId);
}
