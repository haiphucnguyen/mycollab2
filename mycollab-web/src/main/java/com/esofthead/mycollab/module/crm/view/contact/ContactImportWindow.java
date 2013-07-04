package com.esofthead.mycollab.module.crm.view.contact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.easyuploads.SingleFileUploadField;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.types.AddressType;
import ezvcard.types.EmailType;
import ezvcard.types.TelephoneType;

public class ContactImportWindow extends Window {
	private static final long serialVersionUID = 1L;
	public static final String[] fileType = { "CSV", "Vcard" };

	private SingleFileUploadField uploadFile;

	private List<SimpleContact> lstContact;

	public ContactImportWindow() {
		super("Import Contact");
		center();
		this.setWidth("950px");

		VerticalLayout layout = constructBody();
		this.addComponent(layout);
	}

	private VerticalLayout constructBody() {
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
				final InputStream contentStream = uploadFile
						.getContentAsStream();
				if (contentStream != null) {
					try {
						List<VCard> lstVcard = Ezvcard.parse(contentStream)
								.all();
						lstContact = new ArrayList<SimpleContact>();
						for (VCard vcard : lstVcard) {
							SimpleContact add = convertVcardToContact(vcard);
							lstContact.add(add);
						}
						// Here we go lstContact here ----------
						System.out.print("");
					} catch (IOException e) {
						throw new MyCollabException(e);
					}
				} else {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									"It seems you did not attach file yet!");
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

	private SimpleContact convertVcardToContact(VCard vcard) {
		SimpleContact contact = new SimpleContact();
		if (vcard != null) {

			// NAME
			if (vcard.getStructuredName() != null) {
				contact.setFirstname(vcard.getStructuredName().getFamily());
				contact.setLastname(vcard.getStructuredName().getGiven());
			}
			// ADDRESS
			if (vcard.getAddresses() != null) {
				List<AddressType> lstAddress = vcard.getAddresses();
				for (AddressType address : lstAddress) {
					String temp = "";
					if (address != null
							&& address.getTypes().toString() != null
							&& address.getTypes().toString().length() > 6)
						temp = address.getTypes().toString().substring(6);
					String addressType = (temp.length() > 0) ? temp.substring(
							0, temp.length() - 1) : "";
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
					String temp = "";
					if (phone != null && phone.getTypes().toString() != null
							&& phone.getTypes().toString().length() > 6)
						temp = phone.getTypes().toString().substring(6);
					String phoneType = (temp.length() > 0) ? temp.substring(0,
							temp.length() - 1) : "";
					if (phoneType.equals("home")) { // HOME
						contact.setHomephone(phone.getText());
					} else if (phoneType.equals("work")) { // Office
						contact.setOfficephone(phone.getText());
					} else if (phoneType.equals("cell")) { // Mobie
						contact.setMobile(phone.getText());
					} else if (phoneType.equals("pager")) { // OtherPhone
						contact.setOtherphone(phone.getText());
					} else if (phoneType.equals("fax")) { // FAX
						contact.setFax(phone.getText());
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

		ComboBox fileformat = new ComboBox();
		fileformat.addItem("CSV");
		fileformat.addItem("VCF");
		fileformat.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_ITEM);
		gridLayout.addComponent(fileformat, "File Type", 0, 1);

		ComboBox encodingCombobox = new ComboBox();
		encodingCombobox.setItemCaptionMode(1);
		encodingCombobox.addItem("UTF-8");
		gridLayout.addComponent(encodingCombobox, "Character Encoding", 0, 2);

		ComboBox delimiterComboBox = new ComboBox();
		delimiterComboBox.setItemCaptionMode(2);
		delimiterComboBox.addItem(",(comma)");
		gridLayout.addComponent(delimiterComboBox, "Delimiter", 0, 3);

		CheckBox checkbox = new CheckBox();
		gridLayout.addComponent(checkbox, "Has header", 0, 4);
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

		uploadFile = new SingleFileUploadField();
		informationStep1.addComponent(uploadFile);

		informationStep1.addComponent(new Label(
				"Supported Files Type : VCF, CSV"));

		layoutStep1.addComponent(titleStep1);
		layoutStep1.addComponent(informationStep1);
		step1bodyWapper.addComponent(layoutStep1);

		return step1bodyWapper;
	}
}
