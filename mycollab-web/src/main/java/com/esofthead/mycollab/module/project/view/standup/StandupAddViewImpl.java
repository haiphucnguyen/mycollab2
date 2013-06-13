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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;

@ViewComponent
public class StandupAddViewImpl extends AbstractView implements StandupAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;

	public StandupAddViewImpl() {
		super();
		this.setMargin(true);
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final StandupReportWithBLOBs StandupReport) {
		this.editForm.setItemDataSource(new BeanItem<StandupReportWithBLOBs>(
				StandupReport));
	}

	private class EditForm extends AdvancedEditBeanForm<StandupReportWithBLOBs> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
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
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<StandupReportWithBLOBs>(
						EditForm.this)).createButtonControls(true, false, true);
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("whatlastday")
						|| propertyId.equals("whattoday")
						|| propertyId.equals("whatproblem")) {
					final RichTextArea richText = new RichTextArea();
					richText.setWidth("500px");
					return richText;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<StandupReportWithBLOBs> getEditFormHandlers() {
		return this.editForm;
	}

}
