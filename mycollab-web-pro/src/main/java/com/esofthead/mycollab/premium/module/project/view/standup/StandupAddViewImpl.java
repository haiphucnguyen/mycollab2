package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.form.field.RichTextEditField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

import java.util.GregorianCalendar;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class StandupAddViewImpl extends AbstractPageView implements
		StandupAddView {

	private static final long serialVersionUID = 1L;
	private final AdvancedEditBeanForm<StandupReportWithBLOBs> editForm;

	public StandupAddViewImpl() {
		super();
		this.editForm = new AdvancedEditBeanForm<>();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final StandupReportWithBLOBs standupReport) {
		this.editForm.setFormLayoutFactory(new FormLayoutFactory());
		this.editForm
				.setBeanFormFieldFactory(new EditFormFieldFactory(editForm));
		this.editForm.setBean(standupReport);
	}

	private class EditFormFieldFactory extends
			AbstractBeanFieldGroupEditFieldFactory<StandupReportWithBLOBs> {
		private static final long serialVersionUID = 1L;

		public EditFormFieldFactory(GenericBeanForm<StandupReportWithBLOBs> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {
			if (propertyId.equals("whatlastday")
					|| propertyId.equals("whattoday")
					|| propertyId.equals("whatproblem")) {
				final RichTextEditField richText = new RichTextEditField();
				richText.setWidth("100%");
				return richText;
			}
			return null;
		}
	}

	class FormLayoutFactory extends StandupReportFormLayoutFactory {

		private static final long serialVersionUID = 1L;

		public FormLayoutFactory() {
			super(AppContext.getMessage(StandupI18nEnum.FORM_EDIT_TITLE,
					AppContext.formatDate(new GregorianCalendar().getTime())));
		}

		private Layout createButtonControls() {
			return (new EditFormControlsGenerator<>(editForm)).createButtonControls(true, false, true);
		}

		@Override
		protected Layout createTopPanel() {
			return this.createButtonControls();
		}
	}

	@Override
	public HasEditFormHandlers<StandupReportWithBLOBs> getEditFormHandlers() {
		return this.editForm;
	}

}
