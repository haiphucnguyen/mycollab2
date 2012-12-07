package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class CallReadViewImpl extends AbstractView implements CallReadView {
	private static final long serialVersionUID = 1L;

	private PreviewForm previewForm;

	private SimpleCall call;

	public CallReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleCall call) {
		this.call = call;
		previewForm.setItemDataSource(new BeanItem<Call>(call));
	}

	@Override
	public HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleCall> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("assignuser")) {
						return new FormLinkViewField(call
								.getAssignUserFullName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
									}
								});
					} else if (propertyId.equals("type")) {
						return new RelatedReadItemField(call);
					} 
					
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends CallFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<SimpleCall>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return new VerticalLayout();
			}
		}
	}

	@Override
	public SimpleCall getItem() {
		return call;
	}

}
