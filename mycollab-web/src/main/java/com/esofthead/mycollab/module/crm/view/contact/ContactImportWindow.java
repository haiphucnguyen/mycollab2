package com.esofthead.mycollab.module.crm.view.contact;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.SingleFileUploadField;

import au.com.bytecode.opencsv.CSVReader;

import com.esofthead.mycollab.common.ui.components.CSVBeanFieldComboBox;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.iexporter.CSVImportEntityProcess;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.ImportFieldDef;
import com.esofthead.mycollab.iexporter.csv.CSVBooleanFormatter;
import com.esofthead.mycollab.iexporter.csv.CSVDateFormatter;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.view.contact.iexport.ContactVCardObjectEntityConverter;
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

public class ContactImportWindow extends Window {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory
			.getLogger(ContactImportWindow.class);
	public static final String[] fileType = { "CSV", "VCard" };

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
																ContactVCardObjectEntityConverter converter = new ContactVCardObjectEntityConverter();
																Contact add = converter
																		.convert(
																				Contact.class,
																				vcard);
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
	}

	private class MappingCrmConfigurationLayout extends CssLayout {
		private static final long serialVersionUID = 1L;
		private VerticalLayout columnMappingCrmLayout;
		private GridFormLayoutHelper gridCrmMapping;
		private File uploadFile;
		public final List<FieldMapperDef> contactCrmFields = constructListFieldMapper();

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
				gridCrmMapping = new GridFormLayoutHelper(
						2,
						(new CSVReader(new FileReader(uploadFile))).readNext().length + 2,
						"100%", "200px");
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
			gridCrmMapping.getLayout().setMargin(true);
			gridCrmMapping.getLayout().setSpacing(true);

			Label header = new Label("Header");
			header.addStyleName("h3");

			Label crmLabel = new Label("CRM Fields");
			crmLabel.addStyleName("h3");
			Label colIndex = new Label("Colum Index");
			colIndex.addStyleName("h3");
			// IF has header
			if (checkboxChecked)
				gridCrmMapping.addComponentSupportFieldCaption(header,
						new Label(), "0px", "200px", 0, 0,
						Alignment.MIDDLE_CENTER);
			else {
				Label firstRowDataLabel = new Label("First Row Data");
				firstRowDataLabel.addStyleName("h3");
				gridCrmMapping.addComponentSupportFieldCaption(
						firstRowDataLabel, new Label(), "0px", "200px", 0, 0,
						Alignment.MIDDLE_CENTER);
			}
			gridCrmMapping.addComponentSupportFieldCaption(crmLabel,
					colIndex, "200px", "200px", 1, 0, Alignment.MIDDLE_CENTER);
			columnMappingCrmLayout.addComponent(gridCrmMapping
					.getLayout());

			HorizontalLayout controlGroupBtn = new HorizontalLayout();
			controlGroupBtn.setMargin(false, false, false, false);
			UiUtils.addComponent(columnMappingCrmLayout, controlGroupBtn,
					Alignment.MIDDLE_CENTER);

			Button saveBtn = new Button("Save", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {

						List<ImportFieldDef> listImportFieldDef = new ArrayList<ImportFieldDef>();
						for (int i = 0; i < gridCrmMapping.getLayout()
								.getRows(); i++) {
							Component componentOnGrid = gridCrmMapping
									.getComponent(1, i + 1);
							if (componentOnGrid instanceof HorizontalLayout) {
								Iterator<Component> lstComponentOnGrid = ((HorizontalLayout) componentOnGrid)
										.getComponentIterator();
								Component compent = lstComponentOnGrid.next();
								if (compent instanceof CSVBeanFieldComboBox) {
									ImportFieldDef importFieldDef = new ImportFieldDef(
											i,
											(FieldMapperDef) ((CSVBeanFieldComboBox) compent)
													.getValue());
									listImportFieldDef.add(importFieldDef);
								}
							}
						}

						CSVImportEntityProcess importProcess = new CSVImportEntityProcess();
						importProcess.doImport(uploadFile,
								AppContext.getSpringBean(ContactService.class),
								Contact.class, listImportFieldDef);
					} catch (Exception e) {
						throw new MyCollabException(e);
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

		private List<FieldMapperDef> constructListFieldMapper() {
			FieldMapperDef[] fields = {
					new FieldMapperDef("firstname", "First Name"),
					new FieldMapperDef("lastname", "Last Name"),
					new FieldMapperDef("account", "Account"),
					new FieldMapperDef("title", "Title"),
					new FieldMapperDef("department", "Department"),
					new FieldMapperDef("email", "Email"),
					new FieldMapperDef("assistant", "Assistant"),
					new FieldMapperDef("assistantphone", "Assistant Phone"),
					new FieldMapperDef("leadsource", "Leader Source"),
					new FieldMapperDef("officephone", "Phone Office"),
					new FieldMapperDef("mobile", "Mobile"),
					new FieldMapperDef("homephone", "Home phone"),
					new FieldMapperDef("otherphone", "Other Phone"),
					new FieldMapperDef("fax", "Fax"),
					new FieldMapperDef("birthday", "Birthday",
							new CSVDateFormatter()),
					new FieldMapperDef("iscallable", "Callable",
							new CSVBooleanFormatter()),
					new FieldMapperDef("assignuser", "Assign User"),
					new FieldMapperDef("primaddress", "Address"),
					new FieldMapperDef("primcity", "City"),
					new FieldMapperDef("primstate", "State"),
					new FieldMapperDef("primpostalcode", "Postal Code"),
					new FieldMapperDef("primcountry", "Country"),
					new FieldMapperDef("otheraddress", "Other Address"),
					new FieldMapperDef("othercity", "Other City"),
					new FieldMapperDef("otherstate", "Other State"),
					new FieldMapperDef("otherpostalcode", "Other Postal Code"),
					new FieldMapperDef("othercountry", "Other Country"),
					new FieldMapperDef("description", "Description") };
			return Arrays.asList(fields);
		}

		@SuppressWarnings({ "resource" })
		private void fillDataToGridLayout() {
			CSVReader csvReader;
			try {
				csvReader = new CSVReader(new FileReader(uploadFile));
				String[] stringHeader = csvReader.readNext();
				for (int i = 0; i < stringHeader.length; i++) {

					final CSVBeanFieldComboBox crmFieldComboBox = new CSVBeanFieldComboBox(
							contactCrmFields);

					String headerStr = stringHeader[i];
					gridCrmMapping.addComponentSupportFieldCaption(
							new Label(headerStr), new Label(), "0px", "200px",
							0, i + 1, Alignment.MIDDLE_CENTER);
					Label fieldCaptionColumnIndex = new Label("Column "
							+ (i + 1));
					gridCrmMapping.addComponentSupportFieldCaption(
							crmFieldComboBox, fieldCaptionColumnIndex, "200px",
							"200px", 1, i + 1, Alignment.MIDDLE_CENTER);
				}
			} catch (IOException e) {
				throw new MyCollabException(e);
			}
		}
	}
}
