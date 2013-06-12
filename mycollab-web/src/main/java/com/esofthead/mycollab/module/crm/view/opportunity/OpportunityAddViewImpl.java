package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
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
public class OpportunityAddViewImpl extends AbstractView implements
		IFormAddView<Opportunity>, OpportunityAddView {

	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private Opportunity opportunity;

	public OpportunityAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(Opportunity item) {
		this.opportunity = item;
		editForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));
	}

	private class EditForm extends AdvancedEditBeanForm<Opportunity> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new OpportunityEditFormFieldFactory(opportunity));
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((opportunity.getId() == null) ? "Create Opportunity"
						: opportunity.getOpportunityname());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Opportunity>(
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
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}
		}
	}

	@Override
	public HasEditFormHandlers<Opportunity> getEditFormHandlers() {
		return editForm;
	}
}
