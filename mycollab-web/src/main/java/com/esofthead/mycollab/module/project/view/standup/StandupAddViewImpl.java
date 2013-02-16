package com.esofthead.mycollab.module.project.view.standup;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.domain.StandupReport;
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

@ViewComponent
public class StandupAddViewImpl extends AbstractView implements StandupAddView {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;

	public StandupAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(StandupReport StandupReport) {
		editForm.setItemDataSource(new BeanItem<StandupReport>(StandupReport));
	}

	private class EditForm extends AdvancedEditBeanForm<StandupReport> {

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
				return (new EditFormControlsGenerator<StandupReport>(
						EditForm.this)).createButtonControls();
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
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<StandupReport> getEditFormHandlers() {
		return editForm;
	}

}
