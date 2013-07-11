package com.esofthead.mycollab.iexporter;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.CSVItemMapperDef;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.ImportFieldDef;
import com.esofthead.mycollab.web.AppContext;

public class CSVImportEntityProcess<S extends ICrudService, E> {
	public void doImport(File file, S service, Class<E> beanCls,
			List<ImportFieldDef> fieldDef) {
		try {
			// TODO: miss header
			CSVReader csvReader = new CSVReader(new FileReader(file));
			CSVObjectEntityConverter<E> converter = new CSVObjectEntityConverter<E>();
			String[] rowData = csvReader.readNext();
			while (rowData != null) {
				E bean = converter.convert(
						beanCls,
						new CSVItemMapperDef(rowData, fieldDef
								.toArray(new ImportFieldDef[fieldDef.size()])));
				service.saveWithSession(bean, AppContext.getUsername());
				rowData = csvReader.readNext();
			}
			csvReader.close();
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}
