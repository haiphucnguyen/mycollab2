package com.esofthead.mycollab.reporting;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanDataSource<T> implements JRDataSource {

	private List<T> data;
	private int currentIndex = 0;
	private T currentRecord;

	public BeanDataSource() {
		data = new ArrayList<T>();
	}

	public BeanDataSource(List<T> data) {
		this.data = data;
	}

	public void add(T... values) {
		for (T value : values) {
			data.add(value);
		}
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		try {
			String fieldName = jrField.getName();
			return PropertyUtils.getProperty(currentRecord, fieldName);
		} catch (Exception e) {
			throw new JRException(e);
		}
	}

	@Override
	public boolean next() throws JRException {
		boolean result = (currentIndex < data.size());
		if (result) {
			currentRecord = data.get(currentIndex);
			currentIndex++;
		}

		return result;
	}

}
