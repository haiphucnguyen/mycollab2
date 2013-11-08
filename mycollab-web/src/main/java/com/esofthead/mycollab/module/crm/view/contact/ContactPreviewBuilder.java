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
package com.esofthead.mycollab.module.crm.view.contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.file.resource.FileStreamResource;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameters.AddressTypeParameter;
import ezvcard.parameters.TelephoneTypeParameter;
import ezvcard.types.AddressType;
import ezvcard.types.BirthdayType;
import ezvcard.types.EmailType;
import ezvcard.types.NoteType;
import ezvcard.types.OrganizationType;
import ezvcard.types.ProfileType;
import ezvcard.types.RawType;
import ezvcard.types.StructuredNameType;
import ezvcard.types.TelephoneType;
import ezvcard.types.TitleType;

@SuppressWarnings("serial")
public abstract class ContactPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<Contact> previewForm;
	protected SimpleContact contact;
	protected ContactOpportunityListComp associateOpportunityList;
	protected EventRelatedItemListComp associateActivityList;
	protected NoteListItems noteListItems;

	protected void initRelatedComponent() {
		this.associateOpportunityList = new ContactOpportunityListComp();
		this.associateActivityList = new EventRelatedItemListComp(true);
		this.noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(final SimpleContact item) {
		this.contact = item;
		this.previewForm.setItemDataSource(new BeanItem<SimpleContact>(
				this.contact));
		this.displayNotes();
		this.displayActivities();
		this.displayAssociateOpportunityList();
	}

	public ContactOpportunityListComp getAssociateOpportunityList() {
		return this.associateOpportunityList;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return this.associateActivityList;
	}

	public SimpleContact getContact() {
		return this.contact;
	}

	public AdvancedPreviewBeanForm<Contact> getPreviewForm() {
		return this.previewForm;
	}

	private void displayNotes() {
		this.noteListItems.showNotes(CrmTypeConstants.CONTACT,
				this.contact.getId());
	}

	public void displayActivities() {
		final EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CONTACT));
		criteria.setTypeid(new NumberSearchField(this.contact.getId()));
		this.associateActivityList.setSearchCriteria(criteria);
	}

	private void displayAssociateOpportunityList() {
		final OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND,
				this.contact.getId()));
		this.associateOpportunityList.displayOpportunities(this.contact);
	}

	protected class ContactFormFieldFactory extends DefaultFormViewFieldFactory {

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("accountid")) {
				return new FormLinkViewField(contact.getAccountName(),
						new Button.ClickListener() {
							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new AccountEvent.GotoRead(this, contact
												.getAccountid()));
							}
						});
			} else if (propertyId.equals("email")) {
				return new FormEmailLinkViewField(
						ContactPreviewBuilder.this.contact.getEmail());
			} else if (propertyId.equals("assignuser")) {
				return new UserLinkViewField(
						ContactPreviewBuilder.this.contact.getAssignuser(),
						ContactPreviewBuilder.this.contact
								.getAssignUserAvatarId(),
						ContactPreviewBuilder.this.contact
								.getAssignUserFullName());
			} else if (propertyId.equals("iscallable")) {
				if (ContactPreviewBuilder.this.contact.getIscallable() == null
						|| Boolean.FALSE == ContactPreviewBuilder.this.contact
								.getIscallable()) {
					return new FormViewField("No");
				} else {
					return new FormViewField("Yes");
				}
			} else if (propertyId.equals("birthday")) {
				return new FormViewField(AppContext.formatDate(contact
						.getBirthday()));
			} else if (propertyId.equals("firstname")) {
				final FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
				final Label nameLbl = new Label(
						ContactPreviewBuilder.this.contact.getFirstname());
				containerField.addComponentField(nameLbl);
				containerField.getLayout().setExpandRatio(nameLbl, 1.0f);
				final Button vcardDownloadBtn = new Button("",
						new Button.ClickListener() {

							@Override
							public void buttonClick(final ClickEvent event) {
								final VCard vcard = new VCard();

								// Given is name
								final StructuredNameType name = new StructuredNameType();
								if (ContactPreviewBuilder.this.contact
										.getFirstname() != null) {
									name.setFamily(ContactPreviewBuilder.this.contact
											.getLastname());
								}
								if (ContactPreviewBuilder.this.contact
										.getLastname() != null) {
									name.setGiven(ContactPreviewBuilder.this.contact
											.getFirstname());
								}
								vcard.setStructuredName(name);

								if (ContactPreviewBuilder.this.contact
										.getEmail() != null) {
									final EmailType email = new EmailType();
									email.setValue(ContactPreviewBuilder.this.contact
											.getEmail());
									vcard.addEmail(email);
								}
								// Mapping Address ---------------------
								final AddressType otherAddress = new AddressType();
								otherAddress.addType(AddressTypeParameter.WORK);
								// Street address map to OtherAddress
								if (ContactPreviewBuilder.this.contact
										.getOtheraddress() != null) {
									otherAddress
											.setStreetAddress(ContactPreviewBuilder.this.contact
													.getOtheraddress());
								}
								if (ContactPreviewBuilder.this.contact
										.getOthercountry() != null) {
									otherAddress
											.setCountry(ContactPreviewBuilder.this.contact
													.getOthercountry());
								}
								// city map to Region ----------------
								if (ContactPreviewBuilder.this.contact
										.getOthercity() != null) {
									otherAddress
											.setRegion(ContactPreviewBuilder.this.contact
													.getOthercity());
								}
								if (ContactPreviewBuilder.this.contact
										.getOtherpostalcode() != null) {
									otherAddress
											.setPostalCode(ContactPreviewBuilder.this.contact
													.getOtherpostalcode());
								}
								// Sate map to Locality
								if (ContactPreviewBuilder.this.contact
										.getOtherstate() != null) {
									otherAddress
											.setLocality(ContactPreviewBuilder.this.contact
													.getOtherstate());
								}
								vcard.addAddress(otherAddress);

								final AddressType primAddress = new AddressType();
								primAddress.addType(AddressTypeParameter.HOME);

								// StreetAddress map to PrimAddress
								if (ContactPreviewBuilder.this.contact
										.getPrimaddress() != null) {
									primAddress
											.setStreetAddress(ContactPreviewBuilder.this.contact
													.getPrimaddress());
								}

								if (ContactPreviewBuilder.this.contact
										.getPrimcountry() != null) {
									primAddress
											.setCountry(ContactPreviewBuilder.this.contact
													.getPrimcountry());
								}

								if (ContactPreviewBuilder.this.contact
										.getPrimcity() != null) {
									primAddress
											.setRegion(ContactPreviewBuilder.this.contact
													.getPrimcity());
								}

								if (ContactPreviewBuilder.this.contact
										.getPrimpostalcode() != null) {
									primAddress
											.setPostalCode(ContactPreviewBuilder.this.contact
													.getPrimpostalcode());
								}

								if (ContactPreviewBuilder.this.contact
										.getPrimstate() != null) {
									primAddress
											.setLocality(ContactPreviewBuilder.this.contact
													.getPrimstate());
								}

								vcard.addAddress(primAddress);

								// Mapping Phone --------------------
								if (ContactPreviewBuilder.this.contact
										.getHomephone() != null) {
									vcard.addTelephoneNumber(
											ContactPreviewBuilder.this.contact
													.getHomephone(),
											TelephoneTypeParameter.HOME);
								}
								// OFFICE PHONE
								if (ContactPreviewBuilder.this.contact
										.getOfficephone() != null) {
									vcard.addTelephoneNumber(
											ContactPreviewBuilder.this.contact
													.getOfficephone(),
											TelephoneTypeParameter.WORK);
								}
								// MOBIE
								if (ContactPreviewBuilder.this.contact
										.getMobile() != null) {
									vcard.addTelephoneNumber(
											ContactPreviewBuilder.this.contact
													.getMobile(),
											TelephoneTypeParameter.CELL);
								}

								if (ContactPreviewBuilder.this.contact
										.getOtherphone() != null) {
									vcard.addTelephoneNumber(
											ContactPreviewBuilder.this.contact
													.getOtherphone(),
											TelephoneTypeParameter.PAGER);
								}

								// FAX
								if (ContactPreviewBuilder.this.contact.getFax() != null) {
									final TelephoneType fax = new TelephoneType();
									fax.addType(TelephoneTypeParameter.FAX);
									fax.setText(ContactPreviewBuilder.this.contact
											.getFax());
									vcard.addTelephoneNumber(fax);
								}
								// Map department -----------
								if (ContactPreviewBuilder.this.contact
										.getDepartment() != null) {
									final OrganizationType department = new OrganizationType();
									department
											.addValue(ContactPreviewBuilder.this.contact
													.getDepartment());
									vcard.addOrganization(department);
								}
								// Map leadsource to Profile
								if (ContactPreviewBuilder.this.contact
										.getLeadsource() != null) {
									final ProfileType profile = new ProfileType();
									profile.setValue(ContactPreviewBuilder.this.contact
											.getLeadsource());
									vcard.setProfile(profile);
								}
								// Map birthday ----------
								if (ContactPreviewBuilder.this.contact
										.getBirthday() != null) {
									final BirthdayType birthday = new BirthdayType();
									birthday.setDate(
											ContactPreviewBuilder.this.contact
													.getBirthday(), false);
									vcard.setBirthday(birthday);
								}
								if (ContactPreviewBuilder.this.contact
										.getDescription() != null) {
									final NoteType noteType = new NoteType();
									noteType.setValue(ContactPreviewBuilder.this.contact
											.getDescription());
									vcard.addNote(noteType);
								}
								// Map leadsource
								if (ContactPreviewBuilder.this.contact
										.getLeadsource() != null) {
									final RawType leadsource = new RawType(
											"leadsource");
									leadsource
											.setValue(ContactPreviewBuilder.this.contact
													.getLeadsource());
									vcard.addExtendedType(leadsource);
								}
								// Map assitance ---
								if (ContactPreviewBuilder.this.contact
										.getAssistant() != null) {
									final RawType assistant = new RawType(
											"Assistant");
									assistant
											.setValue(ContactPreviewBuilder.this.contact
													.getAssistant());
									vcard.addExtendedType(assistant);
								}
								// Map AssistantPhone
								if (ContactPreviewBuilder.this.contact
										.getAssistantphone() != null) {
									final RawType assistantPhone = new RawType(
											"AssistantPhone");
									assistantPhone
											.setValue(ContactPreviewBuilder.this.contact
													.getAssistantphone());
									vcard.addExtendedType(assistantPhone);
								}
								// Map AssignUser
								if (ContactPreviewBuilder.this.contact
										.getAssignuser() != null) {
									final RawType assignuser = new RawType(
											"AssignUser");
									assignuser
											.setValue(ContactPreviewBuilder.this.contact
													.getAssignuser());
									vcard.addExtendedType(assignuser);
								}
								// Map Callable true/false
								if (ContactPreviewBuilder.this.contact
										.getIscallable() != null) {
									final RawType bool = new RawType("Callable");
									bool.setValue(ContactPreviewBuilder.this.contact
											.getIscallable().toString());
									vcard.addExtendedType(bool);
								}
								// Map Tittle ----
								if (ContactPreviewBuilder.this.contact
										.getTitle() != null) {
									final TitleType title = new TitleType(
											ContactPreviewBuilder.this.contact
													.getTitle());
									vcard.addExtendedType(title);
								}

								final File vCardFile = new File(vcard
										.getStructuredName().getGiven()
										+ ".vcf");
								try {
									Ezvcard.write(vcard)
											.version(VCardVersion.V4_0)
											.go(vCardFile);
									ContactPreviewBuilder.this.getWindow()
											.open(new FileStreamResource(
													new FileInputStream(
															vCardFile),
													vCardFile.getName(),
													ContactPreviewBuilder.this
															.getApplication()),
													"_blank");
								} catch (final IOException e) {
									throw new MyCollabException(e);
								}
							}
						});
				vcardDownloadBtn.setIcon(MyCollabResource
						.newResource("icons/12/vcard.png"));
				containerField.addComponentField(vcardDownloadBtn);
				containerField.getLayout().setComponentAlignment(
						vcardDownloadBtn, Alignment.TOP_RIGHT);
				return containerField;
			}

			return null;
		}
	}

	public static class ReadView extends ContactPreviewBuilder {
		private final VerticalLayout contactInformation;
		private final VerticalLayout relatedItemsContainer;
		private final ReadViewLayout contactAddLayout;

		public ReadView() {
			this.contactAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/contact.png"));
			this.addComponent(this.contactAddLayout);

			this.initRelatedComponent();

			this.previewForm = new AdvancedPreviewBeanForm<Contact>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.CONTACT,
							ContactDefaultDynaFormLayoutFactory.getForm()));
					this.setFormFieldFactory(new ContactFormFieldFactory());
					super.setItemDataSource(newDataSource);
					ReadView.this.contactAddLayout
							.setTitle(ReadView.this.contact.getContactName());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final ContactPreviewBuilder printView = new ContactPreviewBuilder.PrintView();
					printView.previewItem(ReadView.this.contact);
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					this.getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					this.getWindow().open(
							new ExternalResource(window.getURL()), "_blank",
							1100, 200, // Width and height
							Window.BORDER_NONE); // No decorations

					// Print automatically when the window opens.
					// This call will block until the print dialog exits!
					window.executeJavaScript("print();");

					// Close the window automatically after printing
					window.executeJavaScript("self.close();");
				}

				@Override
				protected void showHistory() {
					final ContactHistoryLogWindow historyLog = new ContactHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CONTACT,
							ReadView.this.contact.getId());
					this.getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(this.previewForm,
							RolePermissionCollections.CRM_CONTACT);

			this.contactAddLayout.addControlButtons(optionalActionControls);

			this.contactInformation = new VerticalLayout();
			this.contactInformation.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(this.previewForm,
							RolePermissionCollections.CRM_CONTACT);
			actionControls.addStyleName("control-buttons");
			this.contactInformation.addComponent(actionControls);

			this.contactInformation.addComponent(this.previewForm);

			this.contactInformation.addComponent(this.noteListItems);

			this.contactAddLayout.addTab(this.contactInformation,
					"Contact Information");

			this.relatedItemsContainer = new VerticalLayout();
			this.relatedItemsContainer.setMargin(true);

			this.contactAddLayout.addTab(this.relatedItemsContainer,
					"More Information");

			this.addComponent(this.contactAddLayout);

			this.contactAddLayout
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {

						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
							if ("Contact Information".equals(caption)) {

							} else if ("More Information".equals(caption)) {
								ReadView.this.relatedItemsContainer
										.addComponent(ReadView.this.associateActivityList);
								ReadView.this.relatedItemsContainer
										.addComponent(ReadView.this.associateOpportunityList);
							}
							ReadView.this.contactAddLayout.selectTab(caption);
						}
					});
		}
	}

	public static class PrintView extends ContactPreviewBuilder {

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<Contact>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new ContactFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			this.initRelatedComponent();

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends ContactFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.contact.getContactName());
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(PrintView.this.noteListItems);
				relatedItemsPanel
						.addComponent(PrintView.this.associateActivityList);
				relatedItemsPanel
						.addComponent(PrintView.this.associateOpportunityList);

				return relatedItemsPanel;
			}
		}
	}
}
