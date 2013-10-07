/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import java.util.Collection;
import java.util.List;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.tokenfield.TokenField;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectRoleComboBox;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.ParsingUtils;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberAddViewImpl extends AbstractView implements
		ProjectMemberAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private ProjectMember user;

	public ProjectMemberAddViewImpl() {
		super();
		this.setMargin(false, false, true, false);
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final ProjectMember item) {
		this.user = item;
		this.editForm.setItemDataSource(new BeanItem<ProjectMember>(this.user));
	}

	private class EditForm extends AdvancedEditBeanForm<ProjectMember> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(ProjectMemberAddViewImpl.this.user.getId() == null) ? "Invite Member"
								: ((SimpleProjectMember) ProjectMemberAddViewImpl.this.user)
										.getMemberFullName(),
						UserAvatarControlFactory.createAvatarResource(null, 24));
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<ProjectMember>(
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
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("username")) {
					if (ProjectMemberAddViewImpl.this.user.getUsername() == null) {

						final UserComboBoxWithInviteBtnCustomField userBoxCustomField = new UserComboBoxWithInviteBtnCustomField();
						return userBoxCustomField;
					} else {
						if (ProjectMemberAddViewImpl.this.user instanceof SimpleProjectMember) {
							return new DefaultFormViewFieldFactory.FormViewField(
									((SimpleProjectMember) ProjectMemberAddViewImpl.this.user)
											.getMemberFullName());
						} else {
							return new DefaultFormViewFieldFactory.FormViewField(
									ProjectMemberAddViewImpl.this.user
											.getUsername());
						}
					}

				} else if (propertyId.equals("isadmin")) {
					AdminRoleSelectionField roleBox = new AdminRoleSelectionField();
					if (user.getProjectroleid() != null) {
						roleBox.setRoleId(user.getProjectroleid());
					} else if (user.getIsadmin() != null
							&& user.getIsadmin() == Boolean.TRUE) {
						roleBox.setRoleId(-1);
					}
					return roleBox;
				}
				return null;
			}
		}

		private class UserComboBoxWithInviteBtnCustomField extends CustomField {
			private static final long serialVersionUID = 1L;

			// private final ActiveUserComboBox userBox;
			// private final Button inviteOutSideUserBtn;

			public UserComboBoxWithInviteBtnCustomField() {
				TokenField inviteUserTokenField = new TokenField();
				inviteUserTokenField
						.setFilteringMode(ComboBox.FILTERINGMODE_CONTAINS);
				inviteUserTokenField.setNewTokensAllowed(true);

				final ProjectMemberService prjMemberService = ApplicationContextUtil
						.getSpringBean(ProjectMemberService.class);
				final List<SimpleUser> users = prjMemberService
						.getUsersNotInProject(
								CurrentProjectVariables.getProjectId(),
								AppContext.getAccountId());

				for (SimpleUser user : users) {
					inviteUserTokenField.addToken(user.getUsername());
					inviteUserTokenField.setTokenCaption(user.getUsername(),
							user.getDisplayName());
				}

				this.setCompositionRoot(inviteUserTokenField);

				// userBox = new ActiveUserComboBox(users);
				// userBox.setRequired(true);
				// userBox.setWidth("500px");
				// userBox.setImmediate(true);
				//
				// userBox.addListener(new ValueChangeListener() {
				// private static final long serialVersionUID = 1L;
				//
				// @Override
				// public void valueChange(
				// com.vaadin.data.Property.ValueChangeEvent event) {
				// if (user != null)
				// user.setUsername((String)
				// UserComboBoxWithInviteBtnCustomField.this
				// .getValue());
				// }
				// });
				// inviteOutSideUserBtn = new Button("Invite outside member",
				// new Button.ClickListener() {
				// private static final long serialVersionUID = 1L;
				//
				// @Override
				// public void buttonClick(ClickEvent event) {
				// ProjectMemberAddViewImpl.this
				// .getWindow()
				// .addWindow(
				// new InviteOutsideMemberWindow());
				// }
				// });
				// inviteOutSideUserBtn
				// .addStyleName(UIConstants.THEME_ROUND_BUTTON);
				//
				// HorizontalLayout layout = new HorizontalLayout();
				// layout.setSpacing(true);
				//
				// layout.addComponent(userBox);
				// layout.setComponentAlignment(userBox, Alignment.MIDDLE_LEFT);
				// layout.addComponent(inviteOutSideUserBtn);
				// layout.setComponentAlignment(inviteOutSideUserBtn,
				// Alignment.MIDDLE_LEFT);
				//
				// this.setCompositionRoot(layout);
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

		private class AdminRoleSelectionField extends CustomField {
			private static final long serialVersionUID = 1L;
			private final ProjectRoleComboBox roleComboBox;

			public AdminRoleSelectionField() {
				this.roleComboBox = new ProjectRoleComboBox();
				this.roleComboBox
						.addListener(new Property.ValueChangeListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void valueChange(
									final Property.ValueChangeEvent event) {
								getValue();

							}
						});

				this.setCompositionRoot(this.roleComboBox);
			}

			@Override
			public Object getValue() {
				Integer roleId = (Integer) AdminRoleSelectionField.this.roleComboBox
						.getValue();
				Boolean resultVal = null;
				if (roleId == -1) {
					ProjectMemberAddViewImpl.this.user.setIsadmin(Boolean.TRUE);
					ProjectMemberAddViewImpl.this.user.setProjectroleid(null);
					resultVal = Boolean.TRUE;
				} else {
					ProjectMemberAddViewImpl.this.user
							.setProjectroleid((Integer) AdminRoleSelectionField.this.roleComboBox
									.getValue());
					ProjectMemberAddViewImpl.this.user
							.setIsadmin(Boolean.FALSE);
					resultVal = Boolean.FALSE;
				}
				return resultVal;
			}

			public void setRoleId(int roleId) {
				roleComboBox.setValue(roleId);
			}

			@Override
			public Class<Integer> getType() {
				return Integer.class;
			}
		}
	}

	@Override
	public HasEditFormHandlers<ProjectMember> getEditFormHandlers() {
		return this.editForm;
	}

	private class InviteOutsideMemberWindow extends Window {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		public InviteOutsideMemberWindow() {
			super("Invite member window");
			this.center();
			this.setWidth("500px");

			initUI();
		}

		private void initUI() {
			VerticalLayout mainLayout = new VerticalLayout();
			mainLayout.setMargin(true);
			mainLayout.setSpacing(true);

			informationLayout = new GridFormLayoutHelper(1, 2, "100%", "167px",
					Alignment.MIDDLE_LEFT);

			final TextField emailTextField = new TextField();
			emailTextField.setWidth("250px");

			informationLayout.addComponentSupportFieldCaption(emailTextField,
					new Label("Email"), "80px", "250px", 0, 0,
					Alignment.MIDDLE_CENTER);

			final ProjectRoleComboBox projectRoleComboBox = new ProjectRoleComboBox();

			informationLayout.addComponentSupportFieldCaption(
					projectRoleComboBox, new Label("Role"), "80px", "250px", 0,
					1, Alignment.MIDDLE_CENTER);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			mainLayout.addComponent(informationLayout.getLayout());

			HorizontalLayout controllGroupBtn = new HorizontalLayout();
			controllGroupBtn.setSpacing(true);

			Button sendBtn = new Button("Send", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					String[] lstEmailArr = emailTextField.getValue().toString()
							.trim().split(";");
					// validate emails

					for (int i = 0; i < lstEmailArr.length; i++) {
						String email = lstEmailArr[i];
						if (!email.matches(ParsingUtils.EMAIL_PATTERN)) {
							ProjectMemberAddViewImpl.this
									.getWindow()
									.showNotification(
											"Please enter correct email format type.");
							return;
						}
					}

					int roleId = (Integer) projectRoleComboBox.getValue();
					ProjectMemberService projectMemberService = ApplicationContextUtil
							.getSpringBean(ProjectMemberService.class);
					projectMemberService.inviteProjectMember(lstEmailArr,
							CurrentProjectVariables.getProjectId(), roleId,
							AppContext.getUsername(), AppContext.getAccountId());

					InviteOutsideMemberWindow.this.close();
					ProjectMemberAddViewImpl.this.getWindow().showNotification(
							"Your invitation has sent to partner.");
				}
			});
			sendBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controllGroupBtn.addComponent(sendBtn);

			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					InviteOutsideMemberWindow.this.close();
				}
			});
			cancelBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controllGroupBtn.addComponent(cancelBtn);

			mainLayout.addComponent(controllGroupBtn);
			mainLayout.setComponentAlignment(controllGroupBtn,
					Alignment.MIDDLE_CENTER);

			Label noteLbl = new Label(
					"Note: You can add many emails addresses which be separated each others by semicolon (;)");
			mainLayout.addComponent(noteLbl);

			this.addComponent(mainLayout);
		}
	}
}
