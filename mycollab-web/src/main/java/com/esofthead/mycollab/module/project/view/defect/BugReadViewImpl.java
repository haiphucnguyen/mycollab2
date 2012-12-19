package com.esofthead.mycollab.module.project.view.defect;

import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
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
public class BugReadViewImpl extends AbstractView implements BugReadView {
	private static final long serialVersionUID = 1L;

	private SimpleBug problem;

	private PreviewForm previewForm;
	
	public BugReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleBug item) {
		problem = item;
		previewForm.setItemDataSource(new BeanItem<Bug>(item));
	}

	@Override
	public SimpleBug getItem() {
		return problem;
	}

	@Override
	public HasPreviewFormHandlers<Bug> getPreviewFormHandlers() {
		return previewForm;
	}
	
	private class PreviewForm extends AdvancedPreviewBeanForm<Bug> {
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
		
		class FormLayoutFactory extends BugReadFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<Bug>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();

				relatedItemsPanel.addComponent(new NoteListItems(
						"Notes", "Bug", problem.getId()));

				return relatedItemsPanel;
			}
		}
	}
}
