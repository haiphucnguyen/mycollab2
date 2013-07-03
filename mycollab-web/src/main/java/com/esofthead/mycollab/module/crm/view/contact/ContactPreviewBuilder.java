package com.esofthead.mycollab.module.crm.view.contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.file.FileStreamResource;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
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
import ezvcard.types.KindType;
import ezvcard.types.NoteType;
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
		associateOpportunityList = new ContactOpportunityListComp();
		associateActivityList = new EventRelatedItemListComp(true);
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleContact item) {
		contact = item;
		previewForm.setItemDataSource(new BeanItem<SimpleContact>(contact));
		displayNotes();
		displayActivities();
		displayAssociateOpportunityList();
	}

	public ContactOpportunityListComp getAssociateOpportunityList() {
		return associateOpportunityList;
	}

	public EventRelatedItemListComp getAssociateActivityList() {
		return associateActivityList;
	}

	public SimpleContact getContact() {
		return contact;
	}

	public AdvancedPreviewBeanForm<Contact> getPreviewForm() {
		return previewForm;
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CONTACT, contact.getId());
	}

	public void displayActivities() {
		EventSearchCriteria criteria = new EventSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
		criteria.setType(new StringSearchField(SearchField.AND,
				CrmTypeConstants.CONTACT));
		criteria.setTypeid(new NumberSearchField(contact.getId()));
		associateActivityList.setSearchCriteria(criteria);
	}

	private void displayAssociateOpportunityList() {
		OpportunitySearchCriteria criteria = new OpportunitySearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setContactId(new NumberSearchField(SearchField.AND, contact
				.getId()));
		associateOpportunityList.displayOpportunities(contact);
	}

	protected class ContactFormFieldFactory extends DefaultFormViewFieldFactory {

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			// if (propertyId.equals("accountId")) {
			// return new FormLinkViewField(contact.getAccountName(),
			// new Button.ClickListener() {
			// @Override
			// public void buttonClick(ClickEvent event) {
			// EventBus.getInstance()
			// .fireEvent(
			// new AccountEvent.GotoRead(
			// this,
			// contact.getAccountId()));
			//
			// }
			// });
			if (propertyId.equals("email")) {
				return new FormEmailLinkViewField(contact.getEmail());
			} else if (propertyId.equals("assignuser")) {
				return new FormLinkViewField(contact.getAssignUserFullName(),
						new Button.ClickListener() {
							@Override
							public void buttonClick(ClickEvent event) {
								// TODO Auto-generated method stub
							}
						});
			} else if (propertyId.equals("iscallable")) {
				if (contact.getIscallable() == null
						|| Boolean.FALSE == contact.getIscallable()) {
					return new FormViewField("No");
				} else {
					return new FormViewField("Yes");
				}
			} else if (propertyId.equals("firstname")) {
				FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
				Label nameLbl = new Label(contact.getFirstname());
				containerField.addComponentField(nameLbl);
				Button vcardDownloadBtn = new Button("",
						new Button.ClickListener() {

							@Override
							public void buttonClick(ClickEvent event) {
								VCard vcard = new VCard();

								// Given is name
								StructuredNameType name = new StructuredNameType();
								if (contact.getFirstname() != null)
									name.setFamily(contact.getLastname());
								if (contact.getLastname() != null)
									name.setGiven(contact.getFirstname());
								vcard.setStructuredName(name);

								if (contact.getEmail() != null) {
									EmailType email = new EmailType();
									email.setValue(contact.getEmail());
									vcard.addEmail(email);
								}
								// Mapping Address ---------------------
								AddressType otherAddress = new AddressType();
								otherAddress.addType(AddressTypeParameter.WORK);
								// Street address map to OtherAddress
								if (contact.getOtheraddress() != null)
									otherAddress.setStreetAddress(contact
											.getOtheraddress());
								if (contact.getOthercountry() != null)
									otherAddress.setCountry(contact
											.getOthercountry());
								// city map to Region ----------------
								if (contact.getOthercity() != null)
									otherAddress.setRegion(contact
											.getOthercity());
								if (contact.getOtherpostalcode() != null)
									otherAddress.setPostalCode(contact
											.getOtherpostalcode());
								// Sate map to Locality
								if (contact.getOtherstate() != null)
									otherAddress.setLocality(contact
											.getOtherstate());
								vcard.addAddress(otherAddress);

								AddressType primAddress = new AddressType();
								primAddress.addType(AddressTypeParameter.HOME);
								// StreetAddress map to PrimAddress
								if (contact.getPrimaddress() != null)
									primAddress.setStreetAddress(contact
											.getPrimaddress());
								if (contact.getPrimcountry() != null)
									primAddress.setCountry(contact
											.getPrimcountry());
								if (contact.getPrimcity() != null)
									primAddress.setRegion(contact.getPrimcity());
								if (contact.getPrimpostalcode() != null)
									primAddress.setPostalCode(contact
											.getPrimpostalcode());
								if (contact.getPrimstate() != null)
									primAddress.setLocality(contact
											.getPrimstate());
								vcard.addAddress(primAddress);

								// Mapping Phone --------------------
								if (contact.getHomephone() != null) {
									TelephoneType homePhone = new TelephoneType();
									homePhone
											.addType(TelephoneTypeParameter.HOME);
									homePhone.setPref(1);
									homePhone.setText(contact.getHomephone());
									vcard.addTelephoneNumber(homePhone);
								}
								// OFFICE PHONE
								if (contact.getOfficephone() != null) {
									TelephoneType workPhone = new TelephoneType();
									workPhone
											.addType(TelephoneTypeParameter.WORK);
									workPhone.setText(contact.getOfficephone());
									vcard.addTelephoneNumber(workPhone);
								}
								// MOBIE
								if (contact.getMobile() != null) {
									TelephoneType mobiePhone = new TelephoneType();
									mobiePhone
											.addType(TelephoneTypeParameter.MODEM);
									vcard.addTelephoneNumber(contact
											.getMobile());
								}
								// FAX
								if (contact.getFax() != null) {
									TelephoneType fax = new TelephoneType();
									fax.addType(TelephoneTypeParameter.FAX);
									fax.setText(contact.getFax());
									vcard.addTelephoneNumber(fax);
								}
								// Map department -----------
								if (contact.getDepartment() != null) {
									KindType department = new KindType();
									department.setValue(contact.getDepartment());
									department.isOrg();
									vcard.setKind(department);
								}
								// Map leadsource to Profile
								if (contact.getLeadsource() != null) {
									ProfileType profile = new ProfileType();
									profile.setValue(contact.getLeadsource());
									vcard.setProfile(profile);
								}
								// Map brithday ----------
								if (contact.getBirthday() != null) {
									BirthdayType birthday = new BirthdayType();
									birthday.setDate(contact.getBirthday(),
											false);
									vcard.setBirthday(birthday);
								}
								if (contact.getDescription() != null) {
									NoteType noteType = new NoteType();
									noteType.setValue(contact.getDescription());
									vcard.addNote(noteType);
								}
								// Map leadsource
								if (contact.getLeadsource() != null) {
									RawType leadsource = new RawType(
											"leadsource");
									leadsource.setValue(contact.getLeadsource());
									vcard.addExtendedType(leadsource);
								}
								// Map assitance ---
								if (contact.getAssistant() != null) {
									RawType assistant = new RawType("Assistant");
									assistant.setValue(contact.getAssistant());
									vcard.addExtendedType(assistant);
								}
								// Map AssistantPhone
								if (contact.getAssistantphone() != null) {
									RawType assistantPhone = new RawType(
											"AssistantPhone");
									assistantPhone.setValue(contact
											.getAssistantphone());
									vcard.addExtendedType(assistantPhone);
								}
								// Map AssignUser
								if (contact.getAssignuser() != null) {
									RawType assignuser = new RawType(
											"AssignUser");
									assignuser.setValue(contact.getAssignuser());
									vcard.addExtendedType(assignuser);
								}
								// Map Callable true/false
								if (contact.getIscallable() != null) {
									RawType bool = new RawType("Callable");
									bool.setValue(contact.getIscallable()
											.toString());
									vcard.addExtendedType(bool);
								}
								// Map Tittle ----
								if (contact.getTitle() != null) {
									TitleType title = new TitleType(contact
											.getTitle());
									vcard.addExtendedType(title);
								}

								final File vCardFile = new File(vcard
										.getStructuredName().getGiven()
										+ ".vcf");
								try {
									Ezvcard.write(vcard)
											.version(VCardVersion.V3_0)
											.go(vCardFile);
									getWindow()
											.open(new FileStreamResource(
													new FileInputStream(
															vCardFile),
													vCardFile.getName(),
													getApplication()), "_blank");
								} catch (IOException e) {
									throw new MyCollabException(e);
								}
							}
						});
				vcardDownloadBtn.setIcon(MyCollabResource
						.newResource("icons/12/vcard.png"));
				containerField.addComponentField(vcardDownloadBtn);
				containerField.getLayout().setComponentAlignment(
						vcardDownloadBtn, Alignment.MIDDLE_RIGHT);
				return containerField;
			}

			return null;
		}
	}

	public static class ReadView extends ContactPreviewBuilder {
		private VerticalLayout contactInformation;
		private VerticalLayout relatedItemsContainer;
		private ReadViewLayout contactAddLayout;

		public ReadView() {
			contactAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/contact.png"));
			this.addComponent(contactAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<Contact>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new ContactFormLayoutFactory.ContactInformationLayout(
							true));
					this.setFormFieldFactory(new ContactFormFieldFactory());
					super.setItemDataSource(newDataSource);
					contactAddLayout.setTitle(contact.getContactName());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					ContactPreviewBuilder printView = new ContactPreviewBuilder.PrintView();
					printView.previewItem(contact);
					window.addComponent(printView);

					// Add the printing window as a new application-level window
					getApplication().addWindow(window);

					// Open it as a popup window with no decorations
					getWindow().open(new ExternalResource(window.getURL()),
							"_blank", 1100, 200, // Width and height
							Window.BORDER_NONE); // No decorations

					// Print automatically when the window opens.
					// This call will block until the print dialog exits!
					window.executeJavaScript("print();");

					// Close the window automatically after printing
					window.executeJavaScript("self.close();");
				}

				@Override
				protected void showHistory() {
					ContactHistoryLogWindow historyLog = new ContactHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CONTACT,
							contact.getId());
					getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_CONTACT);

			contactAddLayout.addControlButtons(optionalActionControls);

			contactInformation = new VerticalLayout();
			contactInformation.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_CONTACT);
			actionControls.addStyleName("control-buttons");
			contactInformation.addComponent(actionControls);

			contactInformation.addComponent(previewForm);

			contactInformation.addComponent(noteListItems);

			contactAddLayout.addTab(contactInformation, "Contact Information");

			relatedItemsContainer = new VerticalLayout();
			relatedItemsContainer.setMargin(true);

			contactAddLayout.addTab(relatedItemsContainer, "More Information");

			this.addComponent(contactAddLayout);

			contactAddLayout
					.addTabChangedListener(new DetachedTabs.TabChangedListener() {

						@Override
						public void tabChanged(final TabChangedEvent event) {
							final Button btn = event.getSource();
							final String caption = btn.getCaption();
							if ("Contact Information".equals(caption)) {

							} else if ("More Information".equals(caption)) {
								relatedItemsContainer
										.addComponent(associateActivityList);
								relatedItemsContainer
										.addComponent(associateOpportunityList);
							}
							contactAddLayout.selectTab(caption);
						}
					});
		}
	}

	public static class PrintView extends ContactPreviewBuilder {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Contact>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new ContactFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};
			initRelatedComponent();

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends ContactFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(contact.getContactName());
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(noteListItems);
				relatedItemsPanel.addComponent(associateActivityList);
				relatedItemsPanel.addComponent(associateOpportunityList);

				return relatedItemsPanel;
			}
		}
	}
}
