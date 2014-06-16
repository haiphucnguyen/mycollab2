package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class StandupAddViewImpl extends AbstractPageView implements
		StandupAddView {

	private static final long serialVersionUID = 1L;
	private final AdvancedEditBeanForm<StandupReportWithBLOBs> editForm;

	public StandupAddViewImpl() {
		super();
		this.editForm = new AdvancedEditBeanForm<StandupReportWithBLOBs>();
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
				final RichTextArea richText = new RichTextArea();
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
			final Layout controlButtons = (new EditFormControlsGenerator<StandupReportWithBLOBs>(
					editForm)).createButtonControls(true, false, true);
			return controlButtons;
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
