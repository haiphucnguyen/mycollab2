/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.tokenfield.TokenField;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.EmailValidator;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectRoleComboBox;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberInviteViewImpl extends AbstractView implements
		ProjectMemberInviteView {

	private static final long serialVersionUID = 1L;
	private static final EmailValidator emailValidate = new EmailValidator();
	private final InviteMembersForm editForm;

	private PropertysetItem item;

	private List<String> inviteEmails = new ArrayList<String>();
	private Integer roleId = 0;

	public ProjectMemberInviteViewImpl() {
		super();
		this.setMargin(false, false, true, false);
		this.editForm = new InviteMembersForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void display() {
		item = new PropertysetItem();
		item.addItemProperty("inviteEmails", new ObjectProperty(inviteEmails));
		item.addItemProperty("roleId", new ObjectProperty(roleId));
		this.editForm.setItemDataSource(item);
	}

	private class InviteMembersForm extends AdvancedEditBeanForm<ProjectMember> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory implements IFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final HorizontalLayout controlButtons = new HorizontalLayout();
				controlButtons.setSpacing(true);

				Button inviteBtn = new Button("Invite Members",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								ProjectMemberInviteViewImpl.this
										.fireEvent(new ProjectMemberEvent.InviteProjectMembers(
												ProjectMemberInviteViewImpl.this,
												inviteEmails, roleId));

							}
						});
				inviteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				controlButtons.addComponent(inviteBtn);

				Button cancelBtn = new Button("Cancel",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								ViewState viewState = HistoryViewManager.back();
								if (viewState instanceof NullViewState) {
									EventBus.getInstance().fireEvent(
											new ProjectMemberEvent.GotoList(
													this, null));
								}

							}
						});
				cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				controlButtons.addComponent(cancelBtn);

				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			public Layout getLayout() {
				final AddViewLayout userAddLayout = new AddViewLayout(
						"Invite Members",
						MyCollabResource
								.newResource("icons/24/project/group.png"));

				userAddLayout.addTopControls(createButtonControls());

				this.informationLayout = new GridFormLayoutHelper(1, 2, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");

				userAddLayout.addBody(this.informationLayout.getLayout());
				return userAddLayout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("inviteEmails")) {
					this.informationLayout.addComponent(field, "User emails",
							0, 0);
				} else if (propertyId.equals("roleId")) {
					this.informationLayout.addComponent(field, "Role", 0, 1);
				}

			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("inviteEmails")) {
					final UserComboBoxWithInviteBtnCustomField userBoxCustomField = new UserComboBoxWithInviteBtnCustomField();
					return userBoxCustomField;
				} else if (propertyId.equals("roleId")) {
					return new ProjectRoleSelectionField();
				}
				return null;
			}
		}

		private class UserComboBoxWithInviteBtnCustomField extends CustomField {
			private static final long serialVersionUID = 1L;

			public UserComboBoxWithInviteBtnCustomField() {
				InviteUserTokenField inviteUserTokenField = new InviteUserTokenField();
				inviteUserTokenField
						.setFilteringMode(ComboBox.FILTERINGMODE_CONTAINS);

				final ProjectMemberService prjMemberService = ApplicationContextUtil
						.getSpringBean(ProjectMemberService.class);
				final List<SimpleUser> users = prjMemberService
						.getUsersNotInProject(
								CurrentProjectVariables.getProjectId(),
								AppContext.getAccountId());

				BeanItemContainer<SimpleUser> dsContainer = new BeanItemContainer<SimpleUser>(
						SimpleUser.class, users);
				inviteUserTokenField.setContainerDataSource(dsContainer);

				inviteUserTokenField
						.setTokenCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
				inviteUserTokenField.setTokenCaptionPropertyId("displayName");
				for (SimpleUser user : users) {
					inviteUserTokenField.setTokenIcon(
							user,
							UserAvatarControlFactory.createAvatarResource(
									user.getAvatarid(), 16));
				}

				this.setCompositionRoot(inviteUserTokenField);
			}

			@Override
			public Object getValue() {
				// return (String) userBox.getValue();
				return "";
			}

			@Override
			public Class<String> getType() {
				return String.class;
			}

		}
	}

	private class InviteUserTokenField extends TokenField {
		private static final long serialVersionUID = 1L;

		@Override
		public void addToken(Object tokenId) {
			String invitedEmail;

			if (tokenId instanceof SimpleUser) {
				invitedEmail = ((SimpleUser) tokenId).getEmail();
			} else if (tokenId instanceof String) {
				invitedEmail = (String) tokenId;
			} else {
				throw new MyCollabException("Do not support token field "
						+ tokenId);
			}

			if (emailValidate.validate(invitedEmail)) {
				if (!inviteEmails.contains(invitedEmail)) {
					inviteEmails.add(invitedEmail);
					super.addToken(tokenId);
				}
			} else {
				NotificationUtil
						.showNotification(
								LocalizationHelper
										.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.WARNING_NOT_VALID_EMAIL),
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
			}

		}

		@Override
		protected void onTokenDelete(Object tokenId) {
			String invitedEmail;

			if (tokenId instanceof SimpleUser) {
				invitedEmail = ((SimpleUser) tokenId).getEmail();
			} else if (tokenId instanceof String) {
				invitedEmail = (String) tokenId;
			} else {
				throw new MyCollabException("Do not support token field "
						+ tokenId);
			}

			inviteEmails.remove(invitedEmail);
			super.onTokenDelete(tokenId);
		}
	}

	private class ProjectRoleSelectionField extends CustomField {
		private static final long serialVersionUID = 1L;
		private final ProjectRoleComboBox roleComboBox;

		public ProjectRoleSelectionField() {
			this.roleComboBox = new ProjectRoleComboBox();
			this.roleComboBox.addListener(new Property.ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(final Property.ValueChangeEvent event) {
					getValue();

				}
			});

			this.setCompositionRoot(this.roleComboBox);
		}

		@Override
		public Object getValue() {
			roleId = (Integer) ProjectRoleSelectionField.this.roleComboBox
					.getValue();
			return roleId;
		}

		public void setRoleId(int projectRoleId) {
			roleComboBox.setValue(projectRoleId);
		}

		@Override
		public Class<Integer> getType() {
			return Integer.class;
		}
	}
}
