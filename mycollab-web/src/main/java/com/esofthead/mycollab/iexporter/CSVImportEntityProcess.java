package com.esofthead.mycollab.iexporter;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import au.com.bytecode.opencsv.CSVReader;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.CSVItemMapperDef;
import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.ImportFieldDef;
import com.esofthead.mycollab.web.AppContext;

public class CSVImportEntityProcess<S extends ICrudService, E> {
	private Validator validation;

	public CSVImportEntityProcess() {
		validation = AppContext.getSpringBean(LocalValidatorFactoryBean.class);
	}

	/**
	 * @throw this method throw IllegalArgumentException. You should catch it.
	 */
	public void doImport(File file, boolean isHasHeader, S service,
			Class<E> beanCls, List<ImportFieldDef> fieldDef) {
		try {
			// TODO: miss header
			CSVReader csvReader = new CSVReader(new FileReader(file));
			CSVObjectEntityConverter<E> converter = new CSVObjectEntityConverter<E>();
			String[] rowData = csvReader.readNext();
			if (isHasHeader)
				rowData = csvReader.readNext();
			int rowIndex = (isHasHeader) ? 2 : 1;
			int numRowSuccess = 0, numRowError = 0;
			StringBuffer errMsg = new StringBuffer("");

			while (rowData != null) {
				E bean = converter.convert(
						beanCls,
						new CSVItemMapperDef(rowData, fieldDef
								.toArray(new ImportFieldDef[fieldDef.size()])));
				try {
					validate(bean);
					service.saveWithSession(bean, AppContext.getUsername());
					numRowSuccess++;
				} catch (IllegalArgumentException e1) {
					errMsg.append("Row " + rowIndex).append("_");
					errMsg.append(e1.getMessage());
					numRowError++;
				} finally {
					rowData = csvReader.readNext();
					rowIndex++;
				}
			}
			csvReader.close();
			throw new IllegalArgumentException("numRowSuccess:" + numRowSuccess
					+ "numRowError:" + numRowError + "Detail:"
					+ errMsg.toString());
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 
	 * @param bean
	 * @throws InValidDataException
	 */
	private void validate(E bean) throws IllegalArgumentException {
		Set<ConstraintViolation<E>> violations = validation.validate(bean);
		if (violations.size() > 0) {
			StringBuilder errorMsg = new StringBuilder();

			for (@SuppressWarnings("rawtypes")
			ConstraintViolation violation : violations) {
				errorMsg.append(violation.getPropertyPath().toString()).append(
						": ");
				errorMsg.append(violation.getMessage()).append(".");
			}
			errorMsg.append("//");
			throw new IllegalArgumentException(errorMsg.toString());
		}
	}
}
