/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.AttachmentDisplayComponent;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MessageReadViewImpl extends AbstractView implements
		MessageReadView {
	private static final long serialVersionUID = 1L;

	private final PreviewForm previewForm;
	private SimpleMessage message;

	public MessageReadViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public HasPreviewFormHandlers<SimpleMessage> getPreviewFormHandlers() {
		return previewForm;
	}

	@Override
	public void previewItem(SimpleMessage item) {
		this.message = item;
		previewForm.setItemDataSource(new BeanItem<SimpleMessage>(item));
	}

	@Override
	public SimpleMessage getItem() {
		return message;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleMessage> {
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

		class FormLayoutFactory implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;
			private GridLayout bodyLayout;

			@Override
			public Layout getLayout() {
				AddViewLayout riskAddLayout = new AddViewLayout(
						message.getTitle(), new ThemeResource(
								"icons/48/project/message.png"));

				riskAddLayout.addTopControls(createTopPanel());

				VerticalLayout layout = new VerticalLayout();

				bodyLayout = new GridLayout(1, 2);
				bodyLayout.setWidth("100%");
				layout.addComponent(bodyLayout);
				layout.setComponentAlignment(bodyLayout,
						Alignment.BOTTOM_CENTER);

				riskAddLayout.addBottomControls(createBottomPanel());

				riskAddLayout.addBody(layout);

				return riskAddLayout;
			}

			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			protected Layout createBottomPanel() {
				VerticalLayout bottomPanel = new VerticalLayout();
				Component attachmentDisplayComp = AttachmentDisplayComponent
						.getAttachmentDisplayComponent(
								AttachmentConstants.PROJECT_MESSAGE,
								message.getId());
				bottomPanel.addComponent(attachmentDisplayComp);
				bottomPanel.addComponent(new CommentListDepot(
						CommentTypeConstants.PRJ_MESSAGE, message.getId()));
				return bottomPanel;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("message")) {
					bodyLayout.addComponent(field, 0, 0);
				} else if (propertyId.equals("posteddate")) {
					bodyLayout.addComponent(field, 0, 1);
				}
			}
		}
	}
}
