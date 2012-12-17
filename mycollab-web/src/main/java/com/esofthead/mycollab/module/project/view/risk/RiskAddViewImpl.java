package com.esofthead.mycollab.module.project.view.risk;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.RichTextEditor;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class RiskAddViewImpl extends AbstractView implements RiskAddView,
		IFormAddView<Risk> {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	private Risk risk;
	
	private static Map<Integer, String> valueCaptions = new HashMap<Integer, String>(
            5);

	static {
        valueCaptions.put(1, "Epic Fail");
        valueCaptions.put(2, "Poor");
        valueCaptions.put(3, "OK");
        valueCaptions.put(4, "Good");
        valueCaptions.put(5, "Excellent");
    }

	public RiskAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Risk account) {
		this.risk = account;
		editForm.setItemDataSource(new BeanItem<Risk>(account));
	}

	private class EditForm extends AdvancedEditBeanForm<Risk> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends RiskFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<Risk>(EditForm.this))
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
					return new RichTextEditor();
				} else if (propertyId.equals("raisedbyuser")) {
					return new UserComboBox();
				} 
				else if (propertyId.equals("response")) {

					return new RichTextEditor();
				} else if (propertyId.equals("consequence")) {
					ValueComboBox box = new ValueComboBox();
					box.loadData(new String[] { "Catastrophic", "Critical",
							"Marginal", "Negligible" });
					return box;
				} else if (propertyId.equals("probalitity")) {
					ValueComboBox box = new ValueComboBox();
					box.loadData(new String[] { "Certain", "Likely",
							"Possible", "Unlikely", "Rare" });
				} else if (propertyId.equals("status")) {
					ValueComboBox box = new ValueComboBox();
					box.loadData(new String[] { "Open", "Closed" });
					return box;
				} else if (propertyId.equals("level")) {
					RatingStars ratingField = new RatingStars();
					ratingField.setMaxValue(5);
					ratingField.setImmediate(true);
					ratingField.setDescription("Risk level");
					ratingField.setValueCaption(valueCaptions.values()
		                    .toArray(new String[5]));
					ratingField.setValue(1f);
					
					ratingField.addListener(new Property.ValueChangeListener() {

		                private static final long serialVersionUID = -3277119031169194273L;

						public void valueChange(Property.ValueChangeEvent event) {
		                    
		                }
		            });
					return null;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Risk> getEditFormHandlers() {
		return editForm;
	}
}
