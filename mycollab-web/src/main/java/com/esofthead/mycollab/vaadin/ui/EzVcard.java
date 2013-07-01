package com.esofthead.mycollab.vaadin.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.SingleFileUploadField;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import ezvcard.Ezvcard;
import ezvcard.Ezvcard.ParserChainTextReader;
import ezvcard.VCard;
import ezvcard.VCardVersion;

@SuppressWarnings("unchecked")
public abstract class EzVcard<T> extends Button{
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(EzVcard.class);
	private T object;
	
	public EzVcard(String purpose){
		super();
		if(purpose.equals("Download")){
			//Vcard Image
			this.setCaption("VcardDownload");
			this.setStyleName("link");
			this.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;
				@Override
				public void buttonClick(ClickEvent event) {
					object = createObjectForVcard();
					VCard vcard = createVcard(object);
					final File vCardFile = new File(vcard.getStructuredName().getGiven() + ".vcf");
					try {
					  Ezvcard.write(vcard).version(VCardVersion.V4_0).go(vCardFile);
					  StreamSource streamSource = new StreamSource() {
						private static final long serialVersionUID = 1L;

						@Override
						public InputStream getStream() {
							FileInputStream inputStrem = null;
							try {
								inputStrem = new FileInputStream(vCardFile);
							} catch (FileNotFoundException e) {
								log.debug("EzVcard file export not found");
							}
							return inputStrem;
						}
					  };
					  Resource res = new StreamResource(streamSource,
								vCardFile.getName(), getApplication());
					  getWindow().open(res, "_blank");
					} catch (IOException e){
					  log.debug("Error while write vCard file");
					}
				}
			});
		}else if(purpose.equals("Import")){
			//Vcard Image
			this.setCaption("VcardImport");
			this.setStyleName("link");
			this.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;
				@Override
				public void buttonClick(ClickEvent event) {
					VcardImportWindow vcardImportWindow = new VcardImportWindow("Import from Vcard file",object);
					getWindow().addWindow(vcardImportWindow);
				}
			});
		}
	}
	private class VcardImportWindow extends Window{
		private static final long serialVersionUID = 1L;
		private SingleFileUploadField uploadField;
		public VcardImportWindow(String title, T object){
			super();
			center();
			this.setWidth("300px");
			this.setCaption(title);
			
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			layout.setMargin(true);
			
			uploadField = new SingleFileUploadField();
			layout.addComponent(uploadField);
			
			HorizontalLayout controlGroupBtn = new HorizontalLayout();
			controlGroupBtn.setSpacing(true);
			Button importBtn = new Button("Import", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					final InputStream contentStream = VcardImportWindow.this.uploadField
							.getContentAsStream();
					if (contentStream != null) {
						ParserChainTextReader textReader = Ezvcard.parse(contentStream);
						try {
							VCard vcard = textReader.first();
							try{
								convertVcardToObjectAndSaveToDataBase(vcard);
							}catch(Exception e){
								log.debug("Import to database have any problem.");
							}
						} catch (IOException e) {
							log.debug("Import Vcard file not found.");
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
			importBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlGroupBtn.addComponent(importBtn);
			Button cancle = new Button("Cancle", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					VcardImportWindow.this.close();
				}
			});
			cancle.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlGroupBtn.addComponent(cancle);
			
			layout.addComponent(controlGroupBtn);
			
			this.addComponent(layout);
		}
	}
	public abstract T createObjectForVcard();
	public abstract VCard createVcard(T object);
	public abstract void convertVcardToObjectAndSaveToDataBase(VCard vcard);
}
