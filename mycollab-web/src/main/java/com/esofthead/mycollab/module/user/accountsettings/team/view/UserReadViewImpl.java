/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfileFormLayoutFactory;
import com.esofthead.mycollab.module.user.accountsettings.view.UserFormLayoutFactory;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 */
@ViewComponent
public class UserReadViewImpl extends AbstractPageView implements UserReadView {

	private static final long serialVersionUID = 1L;
	protected AdvancedPreviewBeanForm<User> previewForm;
	protected SimpleUser user;

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
					} else if (propertyId.equals("roleid")) {
						if (user.getIsAccountOwner() != null
								&& user.getIsAccountOwner() == Boolean.TRUE) {
							return new FormViewField("Account Owner");
						} else {
							FormLinkViewField roleLink = new FormLinkViewField(
									user.getRoleName(),
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
							return roleLink;
						}
					} else if (propertyId.equals("website")) {
						return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
								user.getWebsite());
					} else if (propertyId.equals("dateofbirth")) {
						return new DefaultFormViewFieldFactory.FormViewField(
								AppContext.formatDate(user.getDateofbirth()));
					} else if (propertyId.equals("timezone")) {
						return new DefaultFormViewFieldFactory.FormViewField(
								TimezoneMapper.getTimezone(user.getTimezone())
										.getDisplayName());
					} else if (propertyId.equals("facebookaccount")) {
						return new DefaultFormViewFieldFactory.FormUrlSocialNetworkLinkViewField(
								user.getFacebookaccount(),
								"https://www.facebook.com/"
										+ user.getFacebookaccount());
					} else if (propertyId.equals("twitteraccount")) {
						return new DefaultFormViewFieldFactory.FormUrlSocialNetworkLinkViewField(
								user.getTwitteraccount(),
								"https://www.twitter.com/"
										+ user.getTwitteraccount());
					} else if (propertyId.equals("skypecontact")) {
						return new DefaultFormViewFieldFactory.FormUrlSocialNetworkLinkViewField(
								user.getSkypecontact(), "skype:"
										+ user.getSkypecontact() + "?chat");
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			UserReadViewImpl printView = new UserReadViewImpl.PrintView();
			printView.previewItem(user);
			window.setContent(printView);

			UI.getCurrent().addWindow(window);

			// Print automatically when the window opens
			JavaScript.getCurrent().execute(
					"setTimeout(function() {"
							+ "  print(); self.close();}, 0);");
		}

		@Override
		protected void showHistory() {
		}

		class FormLayoutFactory extends ProfileFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(user.getDisplayName(), true);
				this.setAvatarLink(user.getAvatarid());
			}

			@Override
			protected Layout createTopPanel() {
				PreviewFormControlsGenerator<User> previewForm = new PreviewFormControlsGenerator<User>(
						PreviewForm.this);
				previewForm
						.createButtonControls(RolePermissionCollections.ACCOUNT_USER);
				previewForm.removeCloneButton();
				return previewForm.getLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();

				return relatedItemsPanel;
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends UserReadViewImpl {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<User>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(Item item,
								Object propertyId, Component uiContext) {
							if (propertyId.equals("email")) {
								return new FormEmailLinkViewField(user
										.getEmail());
							} else if (propertyId.equals("roleid")) {
								if (user.getIsAccountOwner() != null
										&& user.getIsAccountOwner() == Boolean.TRUE) {
									return new FormViewField("Account Owner");
								} else {
									FormContainerViewField formContainer = new FormContainerViewField();
									formContainer.addComponentField(new Label(
											user.getRoleName()));
									return formContainer;
								}
							} else if (propertyId.equals("website")) {
								return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
										user.getWebsite());
							} else if (propertyId.equals("dateofbirth")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext.formatDate(user
												.getDateofbirth()));
							} else if (propertyId.equals("timezone")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										TimezoneMapper.getTimezone(
												user.getTimezone())
												.getDisplayName());
							}
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends UserFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(user.getDisplayName());
				this.setAvatarLink(user.getAvatarid());
			}

			@Override
			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				return new HorizontalLayout();
			}
		}
	}

	@Override
	public SimpleUser getItem() {
		return user;
	}
}
