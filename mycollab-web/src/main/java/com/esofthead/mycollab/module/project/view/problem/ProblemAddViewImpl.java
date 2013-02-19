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
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class ProblemAddViewImpl extends AbstractView implements ProblemAddView,
		IFormAddView<Problem> {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
			5);

	static {
		valueCaptions.put(1, "Epic Fail");
		valueCaptions.put(2, "Poor");
		valueCaptions.put(3, "OK");
		valueCaptions.put(4, "Good");
		valueCaptions.put(5, "Excellent");
	}

	public ProblemAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Problem problem) {
		editForm.setItemDataSource(new BeanItem<Problem>(problem));
	}

	private class EditForm extends AdvancedEditBeanForm<Problem> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
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
				return (new EditFormControlsGenerator<Problem>(EditForm.this))
						.createButtonControls();
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

				if (propertyId.equals("description")) {
					RichTextArea risk = new RichTextArea();
					risk.setRequired(true);
					risk.setNullRepresentation("");
					risk.setRequiredError("Please enter a Desciption");
					return risk;
				} else if (propertyId.equals("raisedbyuser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("type")) {
				} else if (propertyId.equals("assigntouser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("priority")) {
					ValueComboBox box = new ValueComboBox();
					box.loadData(new String[] { "High", "Medium", "Low" });
					return box;
				} else if (propertyId.equals("status")) {
					ValueComboBox box = new ValueComboBox();
					box.loadData(new String[] { "Open", "Closed" });
					return box;
				} else if (propertyId.equals("level")) {
					final RatingStars ratingField = new RatingStars();
					ratingField.setMaxValue(5);
					ratingField.setImmediate(true);
					ratingField.setDescription("Problem level");
					ratingField.setValueCaption(valueCaptions.values().toArray(
							new String[5]));

					ratingField.addListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = -3277119031169194273L;

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							Double value = (Double) event.getProperty()
									.getValue();
							RatingStars changedRs = (RatingStars) event
									.getProperty();

							// reset value captions
							changedRs.setValueCaption(valueCaptions.values()
									.toArray(new String[5]));
							// set "Your Rating" caption
							if (value == null) {
								changedRs.setValue(1);
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
					TextField tf = new TextField();
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
		return editForm;
	}
}
