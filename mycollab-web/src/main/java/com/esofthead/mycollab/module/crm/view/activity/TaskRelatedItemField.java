package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionWindow;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.event.MouseEvents;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class TaskRelatedItemField extends CustomField implements FieldSelection {
	private static final long serialVersionUID = 1L;

	private RelatedItemComboBox relatedItemComboBox;

	private TextField itemField;
	private Embedded browseBtn;
	private Embedded clearBtn;

	private Task task;

	public TaskRelatedItemField(Task task) {
		this.task = task;

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
		browseBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
				String type = (String) relatedItemComboBox.getValue();
				if (type.equals("Account")) {
					AccountSelectionWindow accountWindow = new AccountSelectionWindow(
							TaskRelatedItemField.this);
					getWindow().addWindow(accountWindow);
					accountWindow.show();
				} else if (type.equals("Campaign")) {

				}
			}
		});

		layout.addComponent(browseBtn);
		layout.setComponentAlignment(browseBtn, Alignment.MIDDLE_LEFT);

		clearBtn = new Embedded(null, new ThemeResource(
				"icons/16/clearItem.png"));
		clearBtn.addListener(new MouseEvents.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void click(ClickEvent event) {
				TaskRelatedItemField.this.task.setType("");
				TaskRelatedItemField.this.task.setTypeid(null);
			}
		});

		layout.addComponent(clearBtn);
		layout.setComponentAlignment(clearBtn, Alignment.MIDDLE_LEFT);

		this.setCompositionRoot(layout);
	}

	@Override
	public Class<?> getType() {
		return (new String[2]).getClass();
	}

	private class RelatedItemComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public RelatedItemComboBox() {
			super();
			setCaption(null);
			this.setWidth("100px");
			this.loadData(new String[] { "Account", "Campaign", "Contact",
					"Lead", "Opportunity", "Case" });
		}
	}

	@Override
	public void fireValueChange(Object data) {
		if (data instanceof SimpleAccount) {
			task.setType("Account");
			task.setTypeid(((SimpleAccount) data).getId());
			itemField.setValue(((SimpleAccount) data).getAccountname());
		}
	}

}
