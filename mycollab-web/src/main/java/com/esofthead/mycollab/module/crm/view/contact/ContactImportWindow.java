package com.esofthead.mycollab.module.crm.view.contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.SingleFileUploadField;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.file.FileStreamResource;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.parameters.AddressTypeParameter;
import ezvcard.parameters.TelephoneTypeParameter;
import ezvcard.types.AddressType;
import ezvcard.types.EmailType;
import ezvcard.types.TelephoneType;

public class ContactImportWindow extends Window {
	private static final long serialVersionUID = 1L;
	public static final String[] fileType = { "CSV", "Vcard" };
	public static final String[] contactCrmFields = { "First Name",
			"Last Name", "Account", "Title", "Department", "Email",
			"Assistant", "Assistant Phone", "Leader Source", "Phone Office",
			"Mobile", "Home phone", "Other Phone", "Fax", "Birthday",
			"Callable", "Assign User", "Address", "City", "State",
			"Postal Code", "Country", "Other Address", "Other City",
			"Other State", "Other Postal Code", "Other Country", "Description" };

	private SingleFileUploadField uploadField;

	private File fileupload;
	private ComboBox fileformat;
	private VerticalLayout layoutStep123;
	private CssLayout layoutStep4;
	private InputStream contentStream;
	private GridFormLayoutHelper gridWithHeaderLayout;
	private CheckBox checkboxHasHeader;
	private int xPosition, yPosition, numberOfColumn = 0;
	private List<String> listStringFromCombox;
	private VerticalLayout informationLayoutStep4;
	private HorizontalLayout errorImportLayout;

	public ContactImportWindow() {
		super("Import Contact");
		center();
		this.setWidth("950px");

		layoutStep123 = constructBodyStep123();
		this.addComponent(layoutStep123);
	}

	private String mapValueToContact(SimpleContact contact, String label,
			String value) {
		String errorStr = "";
		switch (label) {
		case "First Name":
			contact.setFirstname(value);
			break;
		case "Last Name":
			contact.setLastname(value);
			break;
		case "Title":
			contact.setTitle(value);
			break;
		case "Department":
			contact.setDepartment(value);
			break;
		case "Email":
			InternetAddress emailAddr;
			try {
				emailAddr = new InternetAddress(value);
				emailAddr.validate();
				ContactSearchCriteria criteria = new ContactSearchCriteria();
				criteria.setSaccountid(new NumberSearchField(AppContext
						.getAccountId()));
				criteria.setAnyEmail(new StringSearchField(value));
				ContactService service = AppContext
						.getSpringBean(ContactService.class);
				int count = service.getTotalCount(criteria);
				if (count > 0) {
					errorStr += "This email has already exist on system.";
				} else
					contact.setEmail(value);
			} catch (AddressException e1) {
				errorStr += "Email-address: '" + value
						+ "' is invalid on Internet.";
			}
			break;
		case "Assistant":
			contact.setAssistant(value);
			break;
		case "Assistant Phone":
			contact.setAssistantphone(value);
			break;
		case "Leader Source":
			contact.setLeadsource(value);
			break;
		case "Phone Office":
			contact.setOfficephone(value);
			break;
		case "Mobile":
			contact.setMobile(value);
			break;
		case "Home phone":
			contact.setHomephone(value);
			break;
		case "Other Phone":
			contact.setOfficephone(value);
			break;
		case "Fax":
			contact.setFax(value);
			break;
		case "Birthday":
			try {
				DateFormat formatter = new SimpleDateFormat(
						AppContext.getDateFormat());
				formatter.setLenient(false);
				Date date = formatter.parse(value);
				contact.setBirthday(date);
			} catch (Exception e) {
				errorStr += "Can't parse value = \'" + value
						+ "\' to DateType, please input follow mm/dd/yyyy.";
			}
			break;
		case "Callable":
			boolean val = (value.equals("true")) ? true : false;
			contact.setIscallable(val);
			break;
		case "Assign User":
			contact.setAssignuser(value);
			break;
		case "Address":
			contact.setPrimaddress(value);
			break;
		case "City":
			contact.setPrimcity(value);
			break;
		case "State":
			contact.setPrimstate(value);
			break;
		case "Postal Code":
			contact.setPrimpostalcode(value);
			break;
		case "Country":
			contact.setPrimcountry(value);
			break;
		case "Other Address":
			contact.setOtheraddress(value);
			break;
		case "Other City":
			contact.setOthercity(value);
			break;
		case "Other State":
			contact.setOtherstate(value);
			break;
		case "Other Postal Code":
			contact.setOtherpostalcode(value);
			break;
		case "Other Country":
			contact.setOthercountry(value);
			break;
		case "Description":
			contact.setDescription(value);
			break;
		}
		return errorStr;
	}

	private VerticalLayout constructBodyStep123() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		final HorizontalLayout informationLayout = new HorizontalLayout();
		informationLayout.setWidth("100%");
		informationLayout.setSpacing(true);

		CssLayout step1Layout = constructBodyStep1();
		CssLayout step2Layout = constructBodyStep2();
		CssLayout step3Layout = constructBodyStep3();

		informationLayout.addComponent(step1Layout);
		informationLayout.addComponent(step2Layout);
		layout.addComponent(informationLayout);
		layout.addComponent(step3Layout);

		HorizontalLayout controlGroupBtn = new HorizontalLayout();
		controlGroupBtn.setSpacing(true);
		Button nextBtn = new Button("Next");

		nextBtn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					contentStream = uploadField.getContentAsStream();
				} catch (Exception e) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									"It seems you did not attach file yet!");
				}
				if (contentStream != null) {
					String filename = uploadField.getFileName();
					String fileuploadType = filename.substring(
							filename.indexOf(".") + 1, filename.length());
					if (fileuploadType.equals("vcf")) {
						ConfirmDialog
								.show(ContactImportWindow.this.getParent()
										.getWindow(),
										"Message information",
										"You choose a vcf file. This step will import to database. Do you want to do it?",
										"Import", "Cancel",
										new ConfirmDialog.Listener() {
											private static final long serialVersionUID = 1L;

											@Override
											public void onClose(
													ConfirmDialog dialog) {
												if (dialog.isConfirmed()) {
													try {
														ContactService contactService = AppContext
																.getSpringBean(ContactService.class);
														List<VCard> lstVcard = Ezvcard
																.parse(contentStream)
																.all();
														for (VCard vcard : lstVcard) {
															SimpleContact add = convertVcardToContact(vcard);
															add.setCreatedtime(new Date());
															add.setSaccountid(AppContext
																	.getAccountId());
															contactService
																	.saveWithSession(
																			add,
																			AppContext
																					.getUsername());
														}
														ContactImportWindow.this
																.getParent()
																.showNotification(
																		"Import successfully.");
														ContactImportWindow.this
																.close();
														ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria();
														contactSearchCriteria
																.setSaccountid(new NumberSearchField(
																		AppContext
																				.getAccountId()));
														contactSearchCriteria
																.setContactName(new StringSearchField(
																		""));
														EventBus.getInstance()
																.fireEvent(
																		new ContactEvent.GotoList(
																				ContactListView.class,
																				new ContactSearchCriteria()));
													} catch (IOException e) {
														throw new MyCollabException(
																e);
													}
												}
											}
										});
					} else if (fileuploadType.equals("csv")) {
						try {
							fileupload = new File("upload.csv");
							OutputStream outputStream = new FileOutputStream(
									fileupload);

							int read = 0;
							byte[] bytes = new byte[1024];

							while ((read = contentStream.read(bytes)) != -1) {
								outputStream.write(bytes, 0, read);
							}
							layoutStep4 = constructBodyStep4();
							xPosition = ContactImportWindow.this.getPositionX();
							yPosition = ContactImportWindow.this.getPositionY();
							ContactImportWindow.this
									.removeComponent(layoutStep123);
							if ((Boolean) checkboxHasHeader.getValue())
								ContactImportWindow.this.setWidth("800px");
							else
								ContactImportWindow.this.setWidth("600px");

							ContactImportWindow.this.setPositionX(380);
							ContactImportWindow.this.setPositionY(150);

							ContactImportWindow.this.addComponent(layoutStep4);
						} catch (FileNotFoundException e) {
							throw new MyCollabException(e);
						} catch (IOException e) {
							throw new MyCollabException(e);
						}
					} else {
						getWindow().showNotification(
								"Please choose supported files.");
					}
				}
			}
		});
		nextBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		UiUtils.addComponent(controlGroupBtn, nextBtn, Alignment.MIDDLE_CENTER);

		Button cancleBtn = new Button("Cancel");
		cancleBtn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ContactImportWindow.this.close();
			}
		});
		cancleBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		UiUtils.addComponent(controlGroupBtn, cancleBtn,
				Alignment.MIDDLE_CENTER);

		UiUtils.addComponent(layout, controlGroupBtn, Alignment.MIDDLE_CENTER);
		return layout;
	}

	private CssLayout constructBodyStep4() {
		CssLayout step4Wapper = new CssLayout();
		step4Wapper.setWidth("100%");
		step4Wapper.addStyleName(UIConstants.BORDER_BOX_2);

		final HorizontalLayout step4BodyLayout = new HorizontalLayout();
		step4BodyLayout.setMargin(false, false, false, true);
		step4BodyLayout.setSpacing(true);

		final HorizontalLayout step4Title = new HorizontalLayout();
		Label step4TitleLabel = new Label("Step 4:");
		step4TitleLabel.addStyleName("h2");
		step4Title.addComponent(step4TitleLabel);
		step4BodyLayout.addComponent(step4Title);

		informationLayoutStep4 = new VerticalLayout();
		informationLayoutStep4.setWidth("100%");
		informationLayoutStep4.setMargin(true);
		Label infoLabel = new Label("Map the columns to Module fields");
		infoLabel.addStyleName("h3");
		informationLayoutStep4.addComponent(infoLabel);
		try {
			gridWithHeaderLayout = new GridFormLayoutHelper(2, (new CSVReader(
					new FileReader(fileupload))).readNext().length + 2, "100%",
					"200px");
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
		gridWithHeaderLayout.getLayout().setMargin(true);
		gridWithHeaderLayout.getLayout().setSpacing(true);

		Label header = new Label("Header");
		header.addStyleName("h3");

		Label crmLabel = new Label("CRM Fields");
		crmLabel.addStyleName("h3");
		Label colIndex = new Label("Colum Index");
		colIndex.addStyleName("h3");
		// IF has header
		if ((Boolean) checkboxHasHeader.getValue()) {
			gridWithHeaderLayout.addComponentSupportFieldCaption(header,
					new Label(), "0px", "200px", 0, 0, Alignment.MIDDLE_CENTER);

			gridWithHeaderLayout.addComponentSupportFieldCaption(crmLabel,
					colIndex, "200px", "200px", 1, 0, Alignment.MIDDLE_CENTER);
		} else {
			try {
				gridWithHeaderLayout = new GridFormLayoutHelper(
						1,
						(new CSVReader(new FileReader(fileupload))).readNext().length + 2,
						"100%", "200px");
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
			gridWithHeaderLayout.getLayout().setMargin(true);
			gridWithHeaderLayout.getLayout().setSpacing(true);
			gridWithHeaderLayout.addComponentSupportFieldCaption(crmLabel,
					colIndex, "200px", "200px", 0, 0, Alignment.MIDDLE_CENTER);
		}
		informationLayoutStep4.addComponent(gridWithHeaderLayout.getLayout());

		HorizontalLayout controlGroupBtn = new HorizontalLayout();
		controlGroupBtn.setMargin(false, false, false, false);
		UiUtils.addComponent(informationLayoutStep4, controlGroupBtn,
				Alignment.MIDDLE_CENTER);

		Button saveBtn = new Button("Save", new ClickListener() {
			private static final long serialVersionUID = 1L;
			private boolean checkValidInput = true;

			@Override
			public void buttonClick(ClickEvent event) {
				listStringFromCombox = new ArrayList<String>();
				checkValidInput = true;
				for (int i = 0; i < numberOfColumn; i++) {
					Component compentOnGrid;
					if ((Boolean) checkboxHasHeader.getValue())
						compentOnGrid = gridWithHeaderLayout.getComponent(1,
								i + 1);
					else
						compentOnGrid = gridWithHeaderLayout.getComponent(0,
								i + 1);
					if (compentOnGrid instanceof HorizontalLayout) {
						Iterator<Component> setComponentOnGrid = ((HorizontalLayout) compentOnGrid)
								.getComponentIterator();
						Component compent = setComponentOnGrid.next();
						while (compent != null) {
							if (compent instanceof ComboBox) {
								String str = ((ComboBox) compent).getValue()
										.toString();
								if (listStringFromCombox.size() == 0)
									listStringFromCombox.add(str);
								else if (listStringFromCombox.indexOf(str) != -1) {
									checkValidInput = false;
									ContactImportWindow.this
											.getParent()
											.getWindow()
											.showNotification(
													"You should map one column to one crm field");
								} else {
									listStringFromCombox.add(str);
								}
							}
							try {
								compent = setComponentOnGrid.next();
							} catch (Exception e) {
								break;
							}
						}
					}
				}
				if (checkValidInput) { // success in input Data from User
					try {
						// NOw we have lstStringCovert show field order we
						CSVReader csvReader = new CSVReader(new FileReader(
								fileupload));
						String[] rowIndex = csvReader.readNext();
						int rowCount = 1;
						if ((Boolean) checkboxHasHeader.getValue()) {
							rowIndex = csvReader.readNext();
							rowCount++;
						}
						int rowCountSuccess = 0, rowError = 0;

						ContactService contactService = AppContext
								.getSpringBean(ContactService.class);
						final List<String> lstErrorString = new ArrayList<String>();
						while (rowIndex != null && rowIndex.length > 0) {
							// checking and log error
							// row is rowCount , column is i
							SimpleContact contact = new SimpleContact();
							contact.setSaccountid(AppContext.getAccountId());
							String errorStr = "";
							for (int i = 0; i < listStringFromCombox.size(); i++) {
								errorStr += mapValueToContact(contact,
										listStringFromCombox.get(i),
										rowIndex[i]);
								if ((i == listStringFromCombox.size() - 1)
										&& errorStr.length() > 0) {
									lstErrorString.add("row " + rowCount + ": "
											+ errorStr);
									rowError++;
								}
							}
							if (errorStr.length() == 0) {
								contactService.saveWithSession(contact,
										AppContext.getUsername());
								rowCountSuccess++;
							}
							rowIndex = csvReader.readNext();
							rowCount++;
						}
						// Show success , error message
						String message = "Import success " + rowCountSuccess
								+ " rows, fail " + rowError + " rows";
						Button btnLink = new Button(
								"download CSV error file for more informations",
								new ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										File reportErrorFile = new File(
												"reportErrorFile.csv");
										try {
											CSVWriter writer = new CSVWriter(
													new FileWriter(
															reportErrorFile));
											String[] header = { "RowIndex : errorMessage" };
											writer.writeNext(header);

											for (int i = 0; i < lstErrorString
													.size(); i++) {
												String[] body = { lstErrorString
														.get(i) };
												writer.writeNext(body);
											}
											writer.close();
											ContactImportWindow.this
													.getWindow()
													.open(new FileStreamResource(
															new FileInputStream(
																	reportErrorFile),
															reportErrorFile
																	.getName(),
															ContactImportWindow.this
																	.getApplication()),
															"_blank");
										} catch (IOException e) {
											throw new MyCollabException(e);
										}

									}
								});
						btnLink.addStyleName("link");
						Label messageImportLabel = new Label(message);

						errorImportLayout = new HorizontalLayout();
						errorImportLayout.setSpacing(true);
						errorImportLayout.addComponent(messageImportLabel);
						if (rowError > 0)
							errorImportLayout.addComponent(btnLink);
						if (errorImportLayout != null) {
							informationLayoutStep4
									.removeComponent(errorImportLayout);
						}
						informationLayoutStep4.addComponent(errorImportLayout);

					} catch (IOException e) {
						throw new MyCollabException(e);
					}
				} else {
					ContactImportWindow.this
							.getParent()
							.getWindow()
							.showNotification(
									"You should map one column to one crm field");
				}
			}
		});
		saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlGroupBtn.addComponent(saveBtn);
		Button btnClose = new Button("Close", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ContactImportWindow.this.close();
				ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria();
				contactSearchCriteria.setSaccountid(new NumberSearchField(
						AppContext.getAccountId()));
				contactSearchCriteria.setContactName(new StringSearchField(""));
				EventBus.getInstance().fireEvent(
						new ContactEvent.GotoList(ContactListView.class,
								new ContactSearchCriteria()));
			}
		});
		btnClose.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlGroupBtn.addComponent(btnClose);
		step4BodyLayout.addComponent(informationLayoutStep4);
		step4Wapper.addComponent(step4BodyLayout);

		fillDataToGridLayout();

		return step4Wapper;
	}

	@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
	private void fillDataToGridLayout() {
		// // IF Has Header ------ fill to CrM fields and disable
		CSVReader csvReader;
		try {
			csvReader = new CSVReader(new FileReader(fileupload));
			String[] stringHeader = csvReader.readNext();
			numberOfColumn = stringHeader.length;
			for (int i = 0; i < stringHeader.length; i++) {

				final ComboBox addcomboBox = new ComboBox();

				BeanItemContainer<String> crmFileValue = new BeanItemContainer(
						String.class, Arrays.asList(contactCrmFields));

				addcomboBox.setContainerDataSource(crmFileValue);

				String headerStr = stringHeader[i];
				if ((Boolean) checkboxHasHeader.getValue()) {
					gridWithHeaderLayout.addComponentSupportFieldCaption(
							new Label(headerStr), new Label(), "0px", "200px",
							0, i + 1, Alignment.MIDDLE_CENTER);
					Label fieldCaptionColumnIndex = new Label("Column "
							+ (i + 1));
					gridWithHeaderLayout.addComponentSupportFieldCaption(
							addcomboBox, fieldCaptionColumnIndex, "200px",
							"200px", 1, i + 1, Alignment.MIDDLE_CENTER);
				} else {
					Label fieldCaptionColumnIndex = new Label("Column "
							+ (i + 1));
					gridWithHeaderLayout.addComponentSupportFieldCaption(
							addcomboBox, fieldCaptionColumnIndex, "200px",
							"200px", 0, i + 1, Alignment.MIDDLE_CENTER);
				}
			}
		} catch (FileNotFoundException e) {
			throw new MyCollabException(e);
		} catch (IOException e) {
			throw new MyCollabException(e);
		}
	}

	private SimpleContact convertVcardToContact(VCard vcard) {
		SimpleContact contact = new SimpleContact();
		if (vcard != null) {

			// NAME
			if (vcard.getStructuredName() != null) {
				if (vcard.getStructuredName().getFamily() != null)
					contact.setFirstname(vcard.getStructuredName().getFamily());
				else
					contact.setFirstname("");
				if (vcard.getStructuredName().getGiven() != null)
					contact.setLastname(vcard.getStructuredName().getGiven());
				else
					contact.setLastname("");
				String contactName = "";
				if (contact.getFirstname() != null)
					contactName += contact.getFirstname();
				if (contact.getLastname() != null)
					contactName += contact.getLastname();
				contact.setContactName(contactName);
			}
			// ADDRESS
			if (vcard.getAddresses() != null) {
				List<AddressType> lstAddress = vcard.getAddresses();
				for (AddressType address : lstAddress) {
					Set<AddressTypeParameter> setPhoneType = address.getTypes();
					for (Object object : setPhoneType.toArray()) {
						int index = object.toString().indexOf("=");
						String addressType = object.toString().substring(
								index + 1, object.toString().length());
						if (addressType.equals("home")) {
							contact.setPrimaddress(address.getStreetAddress());
							contact.setPrimcountry(address.getCountry());
							contact.setPrimpostalcode(address.getPostalCode());
							contact.setPrimstate(address.getLocality());
						} else {
							contact.setOtheraddress(address.getStreetAddress());
							contact.setOthercountry(address.getCountry());
							contact.setOtherpostalcode(address.getPostalCode());
							contact.setOtherstate(address.getLocality());
						}
					}
				}
			}
			// EMAIL
			if (vcard.getEmails() != null) {
				for (EmailType email : vcard.getEmails()) {
					contact.setEmail(email.getValue());
				}
			}
			// BRITHDAY
			if (vcard.getBirthday() != null) {
				contact.setBirthday(vcard.getBirthday().getDate());
			}
			// Description
			if (vcard.getNotes() != null && vcard.getNotes().size() > 0) {
				contact.setDescription(vcard.getNotes().get(0).toString());
			}
			// Department
			if (vcard.getOrganization() != null) {
				contact.setDepartment((vcard.getOrganization().getValues() != null && vcard
						.getOrganization().getValues().size() > 0) ? vcard
						.getOrganization().getValues().get(0) : "");
			}
			// PHone
			if (vcard.getTelephoneNumbers() != null) {
				for (TelephoneType phone : vcard.getTelephoneNumbers()) {
					Set<TelephoneTypeParameter> setPhoneType = phone.getTypes();
					for (Object object : setPhoneType.toArray()) {
						int index = object.toString().indexOf("=");
						String phoneType = object.toString().substring(
								index + 1, object.toString().length());
						if (phoneType.equals("home")) { // HOME
							contact.setHomephone((phone.getText() != null) ? phone
									.getText() : phone.getUri().getNumber());
						} else if (phoneType.equals("work")) { // Office
							contact.setOfficephone((phone.getText() != null) ? phone
									.getText() : phone.getUri().getNumber());
						} else if (phoneType.equals("cell")) { // Mobie
							contact.setMobile((phone.getText() != null) ? phone
									.getText() : phone.getUri().getNumber());
						} else if (phoneType.equals("pager")) { // OtherPhone
							contact.setOtherphone((phone.getText() != null) ? phone
									.getText() : phone.getUri().getNumber());
						} else if (phoneType.equals("fax")) { // FAX
							contact.setFax((phone.getText() != null) ? phone
									.getText() : phone.getUri().getNumber());
						}
					}
				}
			}
			// Leadsource
			if (vcard.getExtendedType("leadsource") != null
					&& vcard.getExtendedType("leadsource").size() > 0) {
				String leadsource = (vcard.getExtendedType("leadsource").get(0) != null) ? vcard
						.getExtendedType("leadsource").get(0).getValue()
						: "";
				contact.setLeadsource(leadsource);
			}
			// Assistant
			if (vcard.getExtendedType("Assistant") != null
					&& vcard.getExtendedType("Assistant").size() > 0) {
				String assistant = vcard.getExtendedType("Assistant").get(0)
						.getValue();
				contact.setAssistant(assistant);
			}
			// AssistantPhone
			if (vcard.getExtendedType("AssistantPhone") != null
					&& vcard.getExtendedType("AssistantPhone").size() > 0) {
				String assisPhone = vcard.getExtendedType("AssistantPhone")
						.get(0).getValue();
				contact.setAssistantphone(assisPhone);
			}
			// AssignUser
			if (vcard.getExtendedType("AssignUser") != null
					&& vcard.getExtendedType("AssignUser").size() > 0) {
				String assignUser = vcard.getExtendedType("AssignUser").get(0)
						.getValue();
				contact.setAssignuser(assignUser);
			}
			// Callable
			if (vcard.getExtendedType("Callable") != null
					&& vcard.getExtendedType("Callable").size() > 0) {
				String bool = vcard.getExtendedType("Callable").get(0)
						.getValue();
				if (bool.equals("true"))
					contact.setIscallable(true);
				else
					contact.setIscallable(false);
			}
			// Tittle
			if (vcard.getTitles() != null && vcard.getTitles().size() > 0) {
				String title = vcard.getTitles().get(0).getValue();
				contact.setTitle(title);
			}
		}
		return contact;
	}

	private CssLayout constructBodyStep3() {
		final CssLayout bodyStep3Wapper = new CssLayout();
		bodyStep3Wapper.addStyleName(UIConstants.BORDER_BOX_2);
		bodyStep3Wapper.setWidth("100%");

		final HorizontalLayout layoutStep3 = new HorizontalLayout();

		HorizontalLayout titleStep3 = new HorizontalLayout();
		Label labelStep3 = new Label("Step 3:");
		labelStep3.addStyleName("h3");
		UiUtils.addComponent(titleStep3, labelStep3, Alignment.TOP_LEFT);
		layoutStep3.addComponent(titleStep3);

		VerticalLayout infoLayoutStep3 = new VerticalLayout();
		infoLayoutStep3.setMargin(true);

		HorizontalLayout infoLayout = new HorizontalLayout();
		infoLayout.setSpacing(true);

		infoLayoutStep3.addComponent(infoLayout);
		layoutStep3.addComponent(infoLayoutStep3);

		Label labelInfo = new Label("Duplicate Record Handling");
		infoLayout.addComponent(labelInfo);

		CheckBox checkbox = new CheckBox();
		infoLayout.addComponent(checkbox);

		bodyStep3Wapper.addComponent(layoutStep3);

		return bodyStep3Wapper;
	}

	@SuppressWarnings("unchecked")
	private CssLayout constructBodyStep2() {
		final CssLayout bodyStep2Wapper = new CssLayout();
		bodyStep2Wapper.addStyleName(UIConstants.BORDER_BOX_2);
		bodyStep2Wapper.setWidth("100%");

		final HorizontalLayout layoutStep2 = new HorizontalLayout();

		HorizontalLayout titleStep2 = new HorizontalLayout();
		Label labelStep2 = new Label("Step 2:");
		labelStep2.addStyleName("h3");
		UiUtils.addComponent(titleStep2, labelStep2, Alignment.TOP_LEFT);
		layoutStep2.addComponent(titleStep2);

		VerticalLayout informationStep2 = new VerticalLayout();

		GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(1, 5,
				"100%", "200px", Alignment.MIDDLE_LEFT);

		gridLayout.getLayout().setSpacing(true);
		gridLayout.getLayout().setMargin(true);

		gridLayout.addComponent(new Label(), "Specify Format", 0, 0);

		@SuppressWarnings("rawtypes")
		BeanItemContainer<String> fileformatType = new BeanItemContainer(
				String.class, Arrays.asList(fileType));

		fileformat = new ComboBox();
		fileformat.setContainerDataSource(fileformatType);
		fileformat
				.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		fileformat.setValue("Vcard");
		gridLayout.addComponent(fileformat, "File Type", 0, 1);

		ComboBox encodingCombobox = new ComboBox();
		encodingCombobox
				.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		encodingCombobox.addItem("UTF-8");
		encodingCombobox.setValue("UTF-8");
		gridLayout.addComponent(encodingCombobox, "Character Encoding", 0, 2);

		ComboBox delimiterComboBox = new ComboBox();
		delimiterComboBox
				.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		delimiterComboBox.addItem(",(comma)");
		delimiterComboBox.addItem("#,(sharp)");

		delimiterComboBox.setValue(",(comma)");
		gridLayout.addComponent(delimiterComboBox, "Delimiter", 0, 3);

		checkboxHasHeader = new CheckBox();
		gridLayout.addComponent(checkboxHasHeader, "Has header", 0, 4);
		informationStep2.addComponent(gridLayout.getLayout());

		layoutStep2.addComponent(titleStep2);
		layoutStep2.addComponent(informationStep2);

		bodyStep2Wapper.addComponent(layoutStep2);
		return bodyStep2Wapper;
	}

	private CssLayout constructBodyStep1() {
		final CssLayout step1bodyWapper = new CssLayout();
		step1bodyWapper.setWidth("100%");
		step1bodyWapper.setHeight("100%");
		step1bodyWapper.addStyleName(UIConstants.BORDER_BOX_2);

		final HorizontalLayout layoutStep1 = new HorizontalLayout();
		layoutStep1.setSpacing(true);
		layoutStep1.setHeight("100%");

		HorizontalLayout titleStep1 = new HorizontalLayout();
		Label labelStep1 = new Label("Step 1:");
		labelStep1.addStyleName("h3");

		UiUtils.addComponent(titleStep1, labelStep1, Alignment.TOP_LEFT);
		layoutStep1.addComponent(titleStep1);

		VerticalLayout informationStep1 = new VerticalLayout();
		informationStep1.setSpacing(true);
		informationStep1.setMargin(true);

		informationStep1.addComponent(new Label("Select File"));

		uploadField = new SingleFileUploadField();
		informationStep1.addComponent(uploadField);

		informationStep1.addComponent(new Label(
				"Supported Files Type : VCF, CSV"));

		layoutStep1.addComponent(titleStep1);
		layoutStep1.addComponent(informationStep1);
		step1bodyWapper.addComponent(layoutStep1);

		return step1bodyWapper;
	}
}
