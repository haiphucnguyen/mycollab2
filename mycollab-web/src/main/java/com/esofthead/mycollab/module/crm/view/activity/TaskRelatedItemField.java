package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class TaskRelatedItemField extends CustomField {
	private static final long serialVersionUID = 1L;

	private RelatedItemComboBox relatedItemComboBox;
	
	private TextField itemField;
	private Embedded browseBtn;
	private Embedded clearBtn;

	public TaskRelatedItemField() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		relatedItemComboBox = new RelatedItemComboBox();
		layout.addComponent(relatedItemComboBox);
		
		itemField = new TextField();
		itemField.setEnabled(true);
		layout.addComponent(itemField);
		layout.setComponentAlignment(itemField, Alignment.MIDDLE_LEFT);

		browseBtn = new Embedded(null, new ThemeResource(
				"icons/16/browseItem.png"));
		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);
		
		clearBtn = new Embedded(null, new ThemeResource(
				"icons/16/clearItem.png"));
		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);
		
		this.setCompositionRoot(layout);
	}

	@Override
	public Class<?> getType() {
		return (new String[2]).getClass();
	}

	private static class RelatedItemComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public RelatedItemComboBox() {
			super();
			setCaption(null);
			this.setWidth("100px");
			this.loadData(new String[] { "Account", "Campaign", "Contact",
					"Lead", "Opportunity", "Case" });
		}
	}

}
