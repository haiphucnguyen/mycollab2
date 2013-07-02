package com.esofthead.mycollab.vaadin.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.easyuploads.SingleFileUploadField;

import au.com.bytecode.opencsv.CSVReader;

import com.esofthead.mycollab.web.AppContext;
import com.thoughtworks.xstream.XStream;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("unchecked")
public abstract class CsvImportButton<T> extends Button{
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(EzVcard.class);
	public CsvImportButton(){
		super();
		this.addStyleName(UIConstants.THEME_BLUE_LINK);
		this.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ImportCsvWindow importCsvWindow = new ImportCsvWindow();
				getWindow().addWindow(importCsvWindow);
			}
		});
	}
	private class ImportCsvWindow extends Window{
		private static final long serialVersionUID = 1L;
		public ImportCsvWindow(){
			super("Import from CSV file");
			center();
			this.setWidth("300px");
			
			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);
			layout.setMargin(true);
			final SingleFileUploadField uploadField = new SingleFileUploadField();
			layout.addComponent(uploadField);
			
			HorizontalLayout controlGroup = new HorizontalLayout();
			Button importBtn = new Button("Import", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					final InputStream contentStream = uploadField.getContentAsStream();
					if (contentStream != null) {
						// write the inputStream to a FileOutputStream
						File upload = new File("upload.cvs");
						OutputStream outputStream = null;
						try {
							outputStream = new FileOutputStream(upload);
						} catch (FileNotFoundException e) {
							
						}
						int read = 0;
						byte[] bytes = new byte[1024];
						try {
							while ((read = contentStream.read(bytes)) != -1) {
								outputStream.write(bytes, 0, read);
							}
						} catch (IOException e) {
						}
						// We got upload file csv here  --------------
						try {
				            CSVReader reader = new CSVReader(new FileReader(upload));
				            String[] line = null;

				            String[] header = reader.readNext();

				            List out = new ArrayList();

				            while((line = reader.readNext())!=null){
				                List<String[]> item = new ArrayList<String[]>();
				                    for (int i = 0; i < header.length; i++) {
				                    String[] keyVal = new String[2];
				                    String string = header[i];
				                    String val = line[i];
				                    keyVal[0] = string;
				                    keyVal[1] = val;
				                    item.add(keyVal);
				                }
				                out.add(item);
				            }

				            XStream xstream = new XStream();

				            File xmlfile = new File("Xml.xml");

							xstream.toXML(out, new FileWriter(xmlfile,false));
							
							T value = (T) xstream.fromXML(xmlfile);

							importToDB(value);
							// Problem remove all file created -------
							// TODO : more code here
						
				        } catch (Exception e) {
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
			controlGroup.addComponent(importBtn);
			
			Button cancleBtn = new Button("Cancel", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					ImportCsvWindow.this.close();
				}
			});
			cancleBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlGroup.addComponent(cancleBtn);
			layout.addComponent(controlGroup);
			
			this.addComponent(layout);
		}
	}
	protected abstract void importToDB(T value);
}
