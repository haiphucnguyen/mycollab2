package com.esofthead.mycollab.module.project.view.standup;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;

@ViewComponent
public class StandupAddViewImpl extends AbstractView implements StandupAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;

	public StandupAddViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(StandupReportWithBLOBs standupReport) {
		editForm.setItemDataSource(new BeanItem<StandupReportWithBLOBs>(
				standupReport));
	}

	private class EditForm extends AdvancedEditBeanForm<StandupReportWithBLOBs> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends StandupReportFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Standup Report for "
						+ AppContext.formatDate(new GregorianCalendar()
								.getTime()));
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<StandupReportWithBLOBs>(
						EditForm.this)).createButtonControls(true, false, true);
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
				if (propertyId.equals("whatlastday")
						|| propertyId.equals("whattoday")
						|| propertyId.equals("whatproblem")) {
					RichTextArea richText = new RichTextArea();
					richText.setWidth("500px");
					return richText;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<StandupReportWithBLOBs> getEditFormHandlers() {
		return editForm;
	}

}
