package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class RiskReadViewImpl extends AbstractView implements RiskReadView {
	private static final long serialVersionUID = 1L;

	private SimpleRisk risk;

	private PreviewForm previewForm;
	
	public RiskReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleRisk item) {
		risk = item;
		previewForm.setItemDataSource(new BeanItem<Risk>(item));
	}

	@Override
	public SimpleRisk getItem() {
		return risk;
	}

	@Override
	public HasPreviewFormHandlers<Risk> getPreviewFormHandlers() {
		return previewForm;
	}
	
	private class PreviewForm extends AdvancedPreviewBeanForm<Risk> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}
		
		class FormLayoutFactory extends RiskFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<Risk>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();

				return relatedItemsPanel;
			}
		}
	}
}
