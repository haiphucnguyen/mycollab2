package com.esofthead.mycollab.module.project.view.problem;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class ProblemAddViewImpl extends AbstractView implements ProblemAddView,
		IFormAddView<Problem> {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Problem problem;

	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);

	static {
		ProblemAddViewImpl.valueCaptions.put(1, "Epic Fail");
		ProblemAddViewImpl.valueCaptions.put(2, "Poor");
		ProblemAddViewImpl.valueCaptions.put(3, "OK");
		ProblemAddViewImpl.valueCaptions.put(4, "Good");
		ProblemAddViewImpl.valueCaptions.put(5, "Excellent");
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
			this.setFormFieldFactory(new EditFormFieldFactory());
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

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("description")) {
					final RichTextArea risk = new RichTextArea();
					risk.setRequired(true);
					risk.setNullRepresentation("");
					risk.setRequiredError("Please enter a Desciption");
					return risk;
				} else if (propertyId.equals("raisedbyuser")) {
					if (ProblemAddViewImpl.this.problem.getRaisedbyuser() == null) {
						ProblemAddViewImpl.this.problem
								.setRaisedbyuser(AppContext.getUsername());
					}
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("type")) {
				} else if (propertyId.equals("assigntouser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("priority")) {
					if (ProblemAddViewImpl.this.problem.getPriority() == null) {
						ProblemAddViewImpl.this.problem.setPriority("Medium");
					}
					final ValueComboBox box = new ValueComboBox(false, "High",
							"Medium", "Low");
					return box;
				} else if (propertyId.equals("status")) {
					if (ProblemAddViewImpl.this.problem.getStatus() == null) {
						ProblemAddViewImpl.this.problem.setStatus("Open");
					}
					final ValueComboBox box = new ValueComboBox(false, "Open",
							"Closed");
					return box;
				} else if (propertyId.equals("level")) {
					final RatingStars ratingField = new RatingStars();
					ratingField.setMaxValue(5);
					ratingField.setImmediate(true);
					ratingField.setDescription("Problem level");
					ratingField
							.setValueCaption(ProblemAddViewImpl.valueCaptions
									.values().toArray(new String[5]));

					ratingField.addListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = -3277119031169194273L;

						@Override
						public void valueChange(
								final Property.ValueChangeEvent event) {
							final Double value = (Double) event.getProperty()
									.getValue();
							final RatingStars changedRs = (RatingStars) event
									.getProperty();

							// reset value captions
							changedRs
									.setValueCaption(ProblemAddViewImpl.valueCaptions
											.values().toArray(new String[5]));
							// set "Your Rating" caption
							if (value == null) {
								changedRs.setValue(3);
							} else {
								changedRs.setValueCaption(
										(int) Math.round(value), "Your Rating");
							}

						}
					});
					return ratingField;
				} else if (propertyId.equals("resolution")) {
					return new RichTextArea();
				}

				if (propertyId.equals("issuename")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Problem> getEditFormHandlers() {
		return this.editForm;
	}
}
