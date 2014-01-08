package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.resource.ui.GenericBeanForm;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
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
			super("Create Standup Report for "
					+ AppContext.formatDate(new GregorianCalendar().getTime()));
		}

		private Layout createButtonControls() {
			final HorizontalLayout controlPanel = new HorizontalLayout();
			final Layout controlButtons = (new EditFormControlsGenerator<StandupReportWithBLOBs>(
					editForm)).createButtonControls(true, false, true);
			controlButtons.setSizeUndefined();
			controlPanel.addComponent(controlButtons);
			controlPanel.setWidth("100%");
			controlPanel.setComponentAlignment(controlButtons,
					Alignment.MIDDLE_CENTER);
			controlPanel.setMargin(true);
			return controlPanel;
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
