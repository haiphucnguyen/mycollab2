package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class CaseReadViewImpl extends AbstractView implements CaseReadView {
	private static final long serialVersionUID = 1L;

	private SimpleCase cases;

	private PreviewForm previewForm;

	public CaseReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleCase item) {
		cases = item;
		previewForm.setItemDataSource(new BeanItem<Case>(cases));
	}

	@Override
	public HasPreviewFormHandlers<Case> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Case> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("accountid")) {
						return new FormLinkViewField(cases.getAccountName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										EventBus.getInstance().fireEvent(
												new AccountEvent.GotoRead(this,
														cases.getAccountid()));

									}
								});
					} else if (propertyId.equals("email")) {
						return new FormEmailLinkViewField(cases.getEmail());
					}

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends CaseFormLayoutFactory {

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<Case>(PreviewForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();

				return relatedItemsPanel;
			}
		}
	}

	@Override
	public SimpleCase getItem() {
		return cases;
	}

}
