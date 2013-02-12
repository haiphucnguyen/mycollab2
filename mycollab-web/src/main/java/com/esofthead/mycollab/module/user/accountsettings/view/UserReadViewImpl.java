/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserReadViewImpl extends AbstractView implements UserReadView {

	private static final long serialVersionUID = 1L;
	private PreviewForm previewForm;
	private SimpleUser user;

	public UserReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleUser user) {
		this.user = user;
		previewForm.setItemDataSource(new BeanItem<User>(user));
	}

	@Override
	public HasPreviewFormHandlers<User> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<User> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {

					if (propertyId.equals("email")) {
						return new FormEmailLinkViewField(user.getEmail());
					} else if (propertyId.equals("isadmin")) {
						if (user.getIsadmin() != null
								&& user.getIsadmin() == Boolean.TRUE) {
							return new FormViewField("True");
						} else {
							FormContainerViewField formContainer = new FormContainerViewField();
							formContainer.addComponentField(new Label(
									"False. Role: "));
							Button roleLink = new Button(user.getRoleName(),
									new Button.ClickListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void buttonClick(ClickEvent event) {
											EventBus.getInstance()
													.fireEvent(
															new RoleEvent.GotoRead(
																	UserReadViewImpl.this,
																	user.getRoleid()));
										}
									});
							formContainer.addComponentField(roleLink);
							roleLink.setStyleName("link");
							return formContainer;
						}
					} else if (propertyId.equals("website")) {
						return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
								user.getWebsite());
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends UserFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(user.getDisplayName());
			}

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<User>(PreviewForm.this))
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
	public SimpleUser getItem() {
		return user;
	}
}
