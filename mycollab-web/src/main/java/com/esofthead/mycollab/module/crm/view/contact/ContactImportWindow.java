package com.esofthead.mycollab.module.crm.view.contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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
	public static final String[] fileType = { "CSV", "VCard" };
	public static final String[] contactCrmFields = { "First Name",
			"Last Name", "Account", "Title", "Department", "Email",
			"Assistant", "Assistant Phone", "Leader Source", "Phone Office",
			"Mobile", "Home phone", "Other Phone", "Fax", "Birthday",
			"Callable", "Assign User", "Address", "City", "State",
			"Postal Code", "Country", "Other Address", "Other City",
			"Other State", "Other Postal Code", "Other Country", "Description" };

	private FileConfigurationLayout fileConfigurationLayout;
	private MappingCrmConfigurationLayout mappingCrmFieldLayout;

	public ContactImportWindow() {
		super("Import Contact");
		center();
		this.setWidth("1000px");

		fileConfigurationLayout = new FileConfigurationLayout();
		this.addComponent(fileConfigurationLayout);
	}

	private class FileConfigurationLayout extends VerticalLayout {
		private static final long serialVersionUID = 1L;
		private InputStream contentStream;
		private CheckBox hasHeaderCheckBox;
		private SingleFileUploadField uploadField;

		private ComboBox fileformatComboBox;

		public FileConfigurationLayout() {
			final VerticalLayout layout = new VerticalLayout();
			layout.setWidth("100%");
			layout.setSpacing(true);

			final HorizontalLayout informationLayout = new HorizontalLayout();
			informationLayout.setWidth("100%");
			informationLayout.setSpacing(true);

			CssLayout fileUploadLayout = fileUploadLayout();
			CssLayout fileInfomationLayout = fileConfigurationLayout();
			CssLayout handleDuplicationLayout = handelDuplicateRecordLayout();

			informationLayout.addComponent(fileUploadLayout);
			informationLayout.addComponent(fileInfomationLayout);
			layout.addComponent(informationLayout);
			layout.addComponent(handleDuplicationLayout);

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
							File uploadFile = uploadField.getContentAsFile();
							if (uploadFile != null) {
								mappingCrmFieldLayout = new MappingCrmConfigurationLayout(
										(Boolean) hasHeaderCheckBox.getValue(),
										uploadFile);
								ContactImportWindow.this
										.removeComponent(fileConfigurationLayout);

								ContactImportWindow.this.setWidth("800px");

								ContactImportWindow.this.center();

								ContactImportWindow.this
										.addComponent(mappingCrmFieldLayout);
							}

						} else {
							getWindow().showNotification(
									"Please choose supported files.");
						}
					}
				}
			});
			nextBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			UiUtils.addComponent(controlGroupBtn, nextBtn,
					Alignment.MIDDLE_CENTER);

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

			UiUtils.addComponent(layout, controlGroupBtn,
					Alignment.MIDDLE_CENTER);
			this.addComponent(layout);
		}

		private CssLayout handelDuplicateRecordLayout() {
			final CssLayout bodyLayoutWapper = new CssLayout();
			bodyLayoutWapper.addStyleName(UIConstants.BORDER_BOX_2);
			bodyLayoutWapper.setWidth("100%");

			final HorizontalLayout bodyLayout = new HorizontalLayout();

			HorizontalLayout titleHorizontal = new HorizontalLayout();
			Label title = new Label("Step 3:");
			title.addStyleName("h3");
			UiUtils.addComponent(titleHorizontal, title, Alignment.TOP_LEFT);
			bodyLayout.addComponent(titleHorizontal);

			VerticalLayout informationLayout = new VerticalLayout();
			informationLayout.setMargin(true);

			HorizontalLayout infoLayout = new HorizontalLayout();
			infoLayout.setSpacing(true);

			informationLayout.addComponent(infoLayout);
			bodyLayout.addComponent(informationLayout);

			Label labelInfo = new Label("Duplicate Record Handling");
			infoLayout.addComponent(labelInfo);

			CheckBox checkbox = new CheckBox();
			checkbox.setValue(true);
			infoLayout.addComponent(checkbox);

			bodyLayoutWapper.addComponent(bodyLayout);

			return bodyLayoutWapper;
		}

		@SuppressWarnings("unchecked")
		private CssLayout fileConfigurationLayout() {
			final CssLayout bodyLayoutWapper = new CssLayout();
			bodyLayoutWapper.addStyleName(UIConstants.BORDER_BOX_2);
			bodyLayoutWapper.setWidth("100%");

			final HorizontalLayout bodyLayout = new HorizontalLayout();

			HorizontalLayout titleHorizontal = new HorizontalLayout();
			Label title = new Label("Step 2:");
			title.addStyleName("h3");
			UiUtils.addComponent(titleHorizontal, title, Alignment.TOP_LEFT);
			bodyLayout.addComponent(titleHorizontal);

			VerticalLayout informationLayout = new VerticalLayout();

			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(1, 5,
					"100%", "200px", Alignment.MIDDLE_LEFT);

			gridLayout.getLayout().setSpacing(true);
			gridLayout.getLayout().setMargin(true);

			gridLayout.addComponent(new Label(), "Specify Format", 0, 0);

			@SuppressWarnings("rawtypes")
			BeanItemContainer<String> fileformatType = new BeanItemContainer(
					String.class, Arrays.asList(fileType));

			fileformatComboBox = new ComboBox();
			fileformatComboBox.setContainerDataSource(fileformatType);
			fileformatComboBox
					.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
			fileformatComboBox.setValue("VCard");
			gridLayout.addComponent(fileformatComboBox, "File Type", 0, 1);

			ComboBox encodingCombobox = new ComboBox();
			encodingCombobox
					.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
			encodingCombobox.addItem("UTF-8");
			encodingCombobox.setValue("UTF-8");
			encodingCombobox.setEnabled(false);
			gridLayout.addComponent(encodingCombobox, "Character Encoding", 0,
					2);

			ComboBox delimiterComboBox = new ComboBox();
			delimiterComboBox
					.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
			delimiterComboBox.addItem(",(comma)");
			delimiterComboBox.addItem("#,(sharp)");

			delimiterComboBox.setValue(",(comma)");
			delimiterComboBox.setEnabled(false);
			gridLayout.addComponent(delimiterComboBox, "Delimiter", 0, 3);

			HorizontalLayout checkboxHorizontalLayout = new HorizontalLayout();
			hasHeaderCheckBox = new CheckBox();
			checkboxHorizontalLayout.addComponent(hasHeaderCheckBox);

			Label checkboxMessageLabel = new Label(
					"(Has header at first-line?)");
			checkboxHorizontalLayout.addComponent(checkboxMessageLabel);

			gridLayout.addComponent(checkboxHorizontalLayout, "Has header", 0,
					4);
			informationLayout.addComponent(gridLayout.getLayout());

			bodyLayout.addComponent(titleHorizontal);
			bodyLayout.addComponent(informationLayout);

			bodyLayoutWapper.addComponent(bodyLayout);
			return bodyLayoutWapper;
		}

		private CssLayout fileUploadLayout() {
			final CssLayout bodyLayoutWapper = new CssLayout();
			bodyLayoutWapper.setWidth("100%");
			bodyLayoutWapper.setHeight("100%");
			bodyLayoutWapper.addStyleName(UIConstants.BORDER_BOX_2);

			final HorizontalLayout bodyLayout = new HorizontalLayout();
			bodyLayout.setSpacing(true);
			bodyLayout.setHeight("100%");

			HorizontalLayout titleHorizontalLayout = new HorizontalLayout();
			Label title = new Label("Step 1:");
			title.addStyleName("h3");

			UiUtils.addComponent(titleHorizontalLayout, title,
					Alignment.TOP_LEFT);
			bodyLayout.addComponent(titleHorizontalLayout);

			VerticalLayout informationLayout = new VerticalLayout();
			informationLayout.setSpacing(true);
			informationLayout.setMargin(true);

			informationLayout.addComponent(new Label("Select File"));

			uploadField = new SingleFileUploadField();
			uploadField.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(ValueChangeEvent event) {
					String filename = uploadField.getFileName();
					String fileuploadType = filename.substring(
							filename.indexOf(".") + 1, filename.length());
					if (fileuploadType.equals("vcf")) {
						fileformatComboBox.setValue("VCard");
						fileformatComboBox.setEnabled(false);
					} else if (fileuploadType.equals("csv")) {
						fileformatComboBox.setValue("CSV");
						fileformatComboBox.setEnabled(false);
					}
				}
			});
			informationLayout.addComponent(uploadField);

			informationLayout.addComponent(new Label(
					"Supported Files Type : VCF, CSV"));

			bodyLayout.addComponent(titleHorizontalLayout);
			bodyLayout.addComponent(informationLayout);
			bodyLayoutWapper.addComponent(bodyLayout);

			return bodyLayoutWapper;
		}

		private SimpleContact convertVcardToContact(VCard vcard) {
			SimpleContact contact = new SimpleContact();
			if (vcard != null) {

				// NAME
				if (vcard.getStructuredName() != null) {
					if (vcard.getStructuredName().getFamily() != null)
						contact.setFirstname(vcard.getStructuredName()
								.getFamily());
					else
						contact.setFirstname("");
					if (vcard.getStructuredName().getGiven() != null)
						contact.setLastname(vcard.getStructuredName()
								.getGiven());
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
						Set<AddressTypeParameter> setPhoneType = address
								.getTypes();
						for (Object object : setPhoneType.toArray()) {
							int index = object.toString().indexOf("=");
							String addressType = object.toString().substring(
									index + 1, object.toString().length());
							if (addressType.equals("home")) {
								contact.setPrimaddress(address
										.getStreetAddress());
								contact.setPrimcountry(address.getCountry());
								contact.setPrimpostalcode(address
										.getPostalCode());
								contact.setPrimstate(address.getLocality());
							} else {
								contact.setOtheraddress(address
										.getStreetAddress());
								contact.setOthercountry(address.getCountry());
								contact.setOtherpostalcode(address
										.getPostalCode());
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
						Set<TelephoneTypeParameter> setPhoneType = phone
								.getTypes();
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
					String leadsource = (vcard.getExtendedType("leadsource")
							.get(0) != null) ? vcard
							.getExtendedType("leadsource").get(0).getValue()
							: "";
					contact.setLeadsource(leadsource);
				}
				// Assistant
				if (vcard.getExtendedType("Assistant") != null
						&& vcard.getExtendedType("Assistant").size() > 0) {
					String assistant = vcard.getExtendedType("Assistant")
							.get(0).getValue();
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
					String assignUser = vcard.getExtendedType("AssignUser")
							.get(0).getValue();
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
	}

	private class MappingCrmConfigurationLayout extends CssLayout {
		private static final long serialVersionUID = 1L;
		private VerticalLayout columnMappingCrmLayout;
		private GridFormLayoutHelper gridWithHeaderLayout;
		private List<String> listStringFromCombox;
		private HorizontalLayout messageHorizontal;
		private File uploadFile;
		private int numberOfColumn = 0;

		public MappingCrmConfigurationLayout(final boolean checkboxChecked,
				final File uploadFile) {
			this.uploadFile = uploadFile;
			this.setWidth("100%");
			this.addStyleName(UIConstants.BORDER_BOX_2);

			final HorizontalLayout bodyLayout = new HorizontalLayout();
			bodyLayout.setMargin(false, false, false, true);
			bodyLayout.setSpacing(true);

			final HorizontalLayout titleHorizontal = new HorizontalLayout();
			Label title = new Label("Step 4:");
			title.addStyleName("h2");
			titleHorizontal.addComponent(title);
			bodyLayout.addComponent(titleHorizontal);

			columnMappingCrmLayout = new VerticalLayout();
			columnMappingCrmLayout.setWidth("100%");
			columnMappingCrmLayout.setMargin(true);
			Label infoLabel = new Label("Map the columns to Module fields");
			infoLabel.addStyleName("h3");
			columnMappingCrmLayout.addComponent(infoLabel);
			try {
				gridWithHeaderLayout = new GridFormLayoutHelper(
						2,
						(new CSVReader(new FileReader(uploadFile))).readNext().length + 2,
						"100%", "200px");
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
			if (checkboxChecked)
				gridWithHeaderLayout.addComponentSupportFieldCaption(header,
						new Label(), "0px", "200px", 0, 0,
						Alignment.MIDDLE_CENTER);
			else {
				Label firstRowDataLabel = new Label("First Row Data");
				firstRowDataLabel.addStyleName("h3");
				gridWithHeaderLayout.addComponentSupportFieldCaption(
						firstRowDataLabel, new Label(), "0px", "200px", 0, 0,
						Alignment.MIDDLE_CENTER);
			}
			gridWithHeaderLayout.addComponentSupportFieldCaption(crmLabel,
					colIndex, "200px", "200px", 1, 0, Alignment.MIDDLE_CENTER);
			columnMappingCrmLayout.addComponent(gridWithHeaderLayout
					.getLayout());

			HorizontalLayout controlGroupBtn = new HorizontalLayout();
			controlGroupBtn.setMargin(false, false, false, false);
			UiUtils.addComponent(columnMappingCrmLayout, controlGroupBtn,
					Alignment.MIDDLE_CENTER);

			Button saveBtn = new Button("Save", new ClickListener() {
				private static final long serialVersionUID = 1L;
				private boolean checkValidInput = true;

				@Override
				public void buttonClick(ClickEvent event) {
					listStringFromCombox = new ArrayList<String>();
					checkValidInput = true;
					for (int i = 0; i < numberOfColumn; i++) {
						Component componentOnGrid = gridWithHeaderLayout
								.getComponent(1, i + 1);
						if (componentOnGrid instanceof HorizontalLayout) {
							Iterator<Component> setComponentOnGrid = ((HorizontalLayout) componentOnGrid)
									.getComponentIterator();
							Component compent = setComponentOnGrid.next();
							while (compent != null) {
								if (compent instanceof ComboBox) {
									String str = (((ComboBox) compent)
											.getValue() != null) ? ((ComboBox) compent)
											.getValue().toString() : "";
									if (str.length() == 0) {
										checkValidInput = false;
										ContactImportWindow.this
												.getParent()
												.getWindow()
												.showNotification(
														"Please choose crm field to map.");
										break;
									} else if (listStringFromCombox.size() == 0)
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
					// success in input Data from User
					if (checkValidInput
							&& (listStringFromCombox.indexOf("Last Name") != -1)) {
						try {
							// NOw we have lstStringCovert show field order we
							CSVReader csvReader = new CSVReader(new FileReader(
									uploadFile));
							String[] rowIndex = csvReader.readNext();
							int rowCount = 1;
							if (checkboxChecked) {
								rowIndex = csvReader.readNext();
								rowCount++;
							}
							int numRowSuccess = 0;
							int numRowError = 0;

							ContactService contactService = AppContext
									.getSpringBean(ContactService.class);
							final List<String> lstRowFailDetail = new ArrayList<String>();
							// we limit error row is 100

							while (rowIndex != null && rowIndex.length > 0) {
								// checking and log error
								StringBuffer errorStr = new StringBuffer("");
								SimpleContact contact = new SimpleContact();
								contact.setSaccountid(AppContext.getAccountId());
								for (int i = 0; i < listStringFromCombox.size(); i++) {
									if (i < rowIndex.length) {
										errorStr.append(mapValueToContact(
												contact,
												listStringFromCombox.get(i),
												rowIndex[i]));
									} else {
										errorStr.append(mapValueToContact(
												contact,
												listStringFromCombox.get(i), ""));
									}
									if ((i == listStringFromCombox.size() - 1)
											&& errorStr.length() > 0) {
										if (numRowError <= 100)
											lstRowFailDetail.add("row "
													+ rowCount + ": "
													+ errorStr.toString());
										numRowError++;
									}
								}
								if (errorStr.length() == 0) {
									contactService.saveWithSession(contact,
											AppContext.getUsername());
									numRowSuccess++;
								}
								rowIndex = csvReader.readNext();
								rowCount++;
							}
							csvReader.close();
							// Show success , error message
							String message = "Import success " + numRowSuccess
									+ " rows, fail " + numRowError + " rows";
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

												for (int i = 0; i < lstRowFailDetail
														.size() && i <= 100; i++) {
													String[] body = { lstRowFailDetail
															.get(i) };
													writer.writeNext(body);

													if (i == 100) {
														String[] endBody = { "And more" };
														writer.writeNext(endBody);
														break;
													}
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

							if (messageHorizontal != null)
								messageHorizontal.removeAllComponents();
							else
								messageHorizontal = new HorizontalLayout();
							messageHorizontal.setSpacing(true);
							messageHorizontal.addComponent(messageImportLabel);
							if (numRowError > 0)
								messageHorizontal.addComponent(btnLink);
							if (messageHorizontal != null) {
								columnMappingCrmLayout
										.removeComponent(messageHorizontal);
							}
							columnMappingCrmLayout
									.addComponent(messageHorizontal);

						} catch (IOException e) {
							throw new MyCollabException(e);
						}
					} else {
						ContactImportWindow.this
								.getParent()
								.getWindow()
								.showNotification(
										"'Last Name' is required field, please choose one column.");
					}
				}
			});
			saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlGroupBtn.addComponent(saveBtn);

			Button previousBtn = new Button("Previous", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ContactImportWindow.this.removeAllComponents();
					ContactImportWindow.this.setWidth("950px");
					ContactImportWindow.this
							.addComponent(fileConfigurationLayout);
					ContactImportWindow.this.center();
				}
			});
			previousBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlGroupBtn.addComponent(previousBtn);

			Button btnClose = new Button("Close", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ContactImportWindow.this.close();
					ContactSearchCriteria contactSearchCriteria = new ContactSearchCriteria();
					contactSearchCriteria.setSaccountid(new NumberSearchField(
							AppContext.getAccountId()));
					contactSearchCriteria.setContactName(new StringSearchField(
							""));
					EventBus.getInstance().fireEvent(
							new ContactEvent.GotoList(ContactListView.class,
									new ContactSearchCriteria()));
				}
			});
			btnClose.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlGroupBtn.addComponent(btnClose);
			bodyLayout.addComponent(columnMappingCrmLayout);
			this.addComponent(bodyLayout);

			fillDataToGridLayout();
		}

		@SuppressWarnings({ "resource", "rawtypes", "unchecked" })
		private void fillDataToGridLayout() {
			// // IF Has Header ------ fill to CrM fields and disable
			CSVReader csvReader;
			try {
				csvReader = new CSVReader(new FileReader(uploadFile));
				String[] stringHeader = csvReader.readNext();
				numberOfColumn = stringHeader.length;
				for (int i = 0; i < stringHeader.length; i++) {

					final ComboBox addcomboBox = new ComboBox();

					BeanItemContainer<String> crmFileValue = new BeanItemContainer(
							String.class, Arrays.asList(contactCrmFields));

					addcomboBox.setContainerDataSource(crmFileValue);

					String headerStr = stringHeader[i];
					gridWithHeaderLayout.addComponentSupportFieldCaption(
							new Label(headerStr), new Label(), "0px", "200px",
							0, i + 1, Alignment.MIDDLE_CENTER);
					Label fieldCaptionColumnIndex = new Label("Column "
							+ (i + 1));
					gridWithHeaderLayout.addComponentSupportFieldCaption(
							addcomboBox, fieldCaptionColumnIndex, "200px",
							"200px", 1, i + 1, Alignment.MIDDLE_CENTER);
				}
			} catch (IOException e) {
				throw new MyCollabException(e);
			}
		}

		private StringBuffer mapValueToContact(SimpleContact contact,
				String label, String value) {
			StringBuffer errorStr = new StringBuffer("");
			if (label.equals("First Name")) {
				contact.setFirstname(value);
			} else if (label.equals("Last Name")) {
				contact.setLastname(value);
			} else if (label.equals("Title")) {
				contact.setTitle(value);
			} else if (label.equals("Department")) {
				contact.setDepartment(value);
			} else if (label.equals("Email")) {
				if (value.length() == 0) {
					contact.setEmail(value);
				} else {
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
							errorStr.append("This email has already exist on system.");
						} else
							contact.setEmail(value);
					} catch (AddressException e1) {
						errorStr.append("Email-address: '" + value
								+ "' is invalid on Internet.");
					}
				}
			} else if (label.equals("Assistant")) {
				contact.setAssistant(value);
			} else if (label.equals("Assistant Phone")) {
				contact.setAssistantphone(value);
			} else if (label.equals("Leader Source")) {
				contact.setLeadsource(value);
			} else if (label.equals("Phone Office")) {
				contact.setOfficephone(value);
			} else if (label.equals("Mobile")) {
				contact.setMobile(value);
			} else if (label.equals("Home phone")) {
				contact.setHomephone(value);
			} else if (label.equals("Other Phone")) {
				contact.setOfficephone(value);
			} else if (label.equals("Fax")) {
				contact.setFax(value);
			} else if (label.equals("Birthday")) {
				try {
					DateFormat formatter = new SimpleDateFormat(
							AppContext.getDateFormat());
					formatter.setLenient(false);
					Date date = formatter.parse(value);
					contact.setBirthday(date);
				} catch (Exception e) {
					errorStr.append("Can't parse value = \'" + value
							+ "\' to DateType, please input follow mm/dd/yyyy.");
				}
			} else if (label.equals("Callable")) {
				boolean val = (value.equals("true")) ? true : false;
				contact.setIscallable(val);
			} else if (label.equals("Assign User")) {
				contact.setAssignuser(value);
			} else if (label.equals("Address")) {
				contact.setPrimaddress(value);
			} else if (label.equals("City")) {
				contact.setPrimcity(value);
			} else if (label.equals("State")) {
				contact.setPrimstate(value);
			} else if (label.equals("Postal Code")) {
				contact.setPrimpostalcode(value);
			} else if (label.equals("Country")) {
				contact.setPrimcountry(value);
			} else if (label.equals("Other Address")) {
				contact.setOtheraddress(value);
			} else if (label.equals("Other City")) {
				contact.setOthercity(value);
			} else if (label.equals("Other State")) {
				contact.setOtherstate(value);
			} else if (label.equals("Other Postal Code")) {
				contact.setOtherpostalcode(value);
			} else if (label.equals("Other Country")) {
				contact.setOthercountry(value);
			} else if (label.equals("Description")) {
				contact.setDescription(value);
			}
			return errorStr;
		}
	}
}
