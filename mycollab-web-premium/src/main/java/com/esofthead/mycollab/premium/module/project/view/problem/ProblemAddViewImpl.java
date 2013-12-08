package com.esofthead.mycollab.premium.module.project.view.problem;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class ProblemAddViewImpl extends AbstractPageView implements ProblemAddView,
		IFormAddView<Problem> {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Problem problem;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);

	static {
		ProblemAddViewImpl.getValueCaptions().put(1, "Epic Fail");
		ProblemAddViewImpl.getValueCaptions().put(2, "Poor");
		ProblemAddViewImpl.getValueCaptions().put(3, "OK");
		ProblemAddViewImpl.getValueCaptions().put(4, "Good");
		ProblemAddViewImpl.getValueCaptions().put(5, "Excellent");
	}

	public ProblemAddViewImpl() {
		super();
		this.setMargin(true);
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final Problem problem) {
		this.problem = problem;
		this.editForm.setItemDataSource(new BeanItem<Problem>(problem));
	}

	private class EditForm extends AdvancedEditBeanForm<Problem> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new ProblemEditFormFieldFactory(problem));
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends ProblemFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super("Create Problem");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Problem>(
						EditForm.this)).createButtonControls();
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
	}

	@Override
	public HasEditFormHandlers<Problem> getEditFormHandlers() {
		return this.editForm;
	}

	public static Map<Integer, String> getValueCaptions() {
		return valueCaptions;
	}

	public static void setValueCaptions(Map<Integer, String> valueCaptions) {
		ProblemAddViewImpl.valueCaptions = valueCaptions;
	}
}
