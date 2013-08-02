package com.esofthead.mycollab.common.ui.components;

import java.util.List;

import com.esofthead.mycollab.iexporter.CSVObjectEntityConverter.FieldMapperDef;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;

public class CSVBeanFieldComboBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	public CSVBeanFieldComboBox(List<FieldMapperDef> fields) {
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		BeanItemContainer<FieldMapperDef> beanItemContainer = new BeanItemContainer(
				FieldMapperDef.class, fields);
		
		this.setContainerDataSource(beanItemContainer);
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
		this.setItemCaptionPropertyId("description");
	}
}
