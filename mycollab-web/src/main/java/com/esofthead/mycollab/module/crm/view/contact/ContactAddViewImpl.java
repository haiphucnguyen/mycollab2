package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;

public class ContactAddViewImpl extends AbstractView implements ContactAddView {

	@Override
	public void addNewItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editItem(Contact item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HasEditFormHandlers<Contact> getEditFormHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
//	private static final long serialVersionUID = 1L;
//	
//	private EditForm editForm;
//
//	public ContactAddViewImpl() {
//		super();
//		editForm = new EditForm();
//		this.addComponent(editForm);
//	}
//	
//	@Override
//	public void addNewItem() {
//		this.removeAllComponents();
//		Form formItem = AppContext.getSpringBean(EditForm.class);
//		formItem.setItemDataSource(new BeanItem<Contact>(new Contact()));
//		this.addComponent(formItem);
//	}
//
//	@Override
//	public void editItem(Contact item) {
//		this.removeAllComponents();
//		Form formItem = AppContext.getSpringBean(EditForm.class);
//		formItem.setItemDataSource(new BeanItem<Contact>(item));
//		this.addComponent(formItem);
//	}
//
//	
//	public void viewItem(Contact item) {
//		this.removeAllComponents();
//		Form formItem = AppContext.getSpringBean(ViewForm.class);
//		formItem.setItemDataSource(new BeanItem<Contact>(item));
//		this.addComponent(formItem);
//	}
//
//
//	public static class EditForm extends AdvancedEditBeanForm<Contact> {
//		private static final long serialVersionUID = 1L;
//		
//		public EditForm() {
//			this.setFormLayoutFactory(new FormLayoutFactory());
//			this.setFormFieldFactory(new EditFormFieldFactory());
//		}
//
//		@Override
//		protected HorizontalLayout createButtonControls() {
//			HorizontalLayout layout = new HorizontalLayout();
//			layout.setSpacing(true);
//			layout.setStyleName("addNewControl");
//			FormActionListener formActionListener = new FormActionListener();
//			Button saveBtn = new Button(SAVE_ACTION, formActionListener);
//			Button cancelBtn = new Button(CANCEL_ACTION, formActionListener);
//
//			layout.addComponent(saveBtn);
//			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);
//
//			layout.addComponent(cancelBtn);
//			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
//			return layout;
//		}
//
//		@PostConstruct
//		private void initFieldFactory() {
//			this.setFormFieldFactory(new EditFormFieldFactory());
//		}
//
//		private class EditFormFieldFactory extends DefaultFieldFactory {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Field createField(Item item, Object propertyId,
//					com.vaadin.ui.Component uiContext) {
//
//				Field field = super.createField(item, propertyId, uiContext);
//
//				if (propertyId.equals("leadsource")) {
//					LeadSourceComboBox leadSource = AppContext
//							.getSpringBean(LeadSourceComboBox.class);
//					return leadSource;
//				} else if (propertyId.equals("accountid")) {
//					AccountSelectionField accountField = AppContext
//							.getSpringBean(AccountSelectionField.class);
//					return accountField;
//				}
//
//				if (field instanceof TextField) {
//					((TextField) field).setNullRepresentation("");
//					((TextField) field)
//							.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
//					((TextField) field).setCaption(null);
//				}
//
//				return field;
//			}
//		}
//
//		private class FormActionListener implements Button.ClickListener {
//			private static final long serialVersionUID = 1L;
//
//			// ==================================================
//			// Actions
//			// ==================================================
//			@Override
//			public void buttonClick(ClickEvent event) {
//				String caption = event.getButton().getCaption();
//				@SuppressWarnings("unchecked")
//				Contact contact = ((BeanItem<Contact>) EditForm.this
//						.getItemDataSource()).getBean();
//				if (caption.equals(SAVE_ACTION)) {
//					if (validateForm(contact)) {
//						
//					}
//				} else if (caption.equals(CANCEL_ACTION)) {
//					
//				}
//			}
//		}
//	}

}
