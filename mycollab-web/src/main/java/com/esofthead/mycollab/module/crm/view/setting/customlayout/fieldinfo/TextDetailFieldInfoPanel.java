package com.esofthead.mycollab.module.crm.view.setting.customlayout.fieldinfo;

import java.util.List;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.TextDynaField;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.vaadin.ui.TextField;

public class TextDetailFieldInfoPanel extends
		DetailFieldInfoPanel<TextDynaField> {
	private static final long serialVersionUID = 1L;

	private TextField labelField = new TextField();
	private TextField lengthField = new TextField();
	private SectionSelectList sectionList;

	public TextDetailFieldInfoPanel(List<DynaSection> activeSections) {
		super(activeSections);

		GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 3);
		layoutHelper.addComponent(labelField, "Label", 0, 0);
		sectionList = new SectionSelectList(activeSections);
		layoutHelper.addComponent(sectionList, "Section", 0, 1);
		layoutHelper.addComponent(lengthField, "Length", 0, 2);
		this.addComponent(layoutHelper.getLayout());
	}

	@Override
	public void updateCustomField() {
		String displayName = (String) labelField.getValue();
		DynaSection ownSection = (DynaSection) sectionList.getValue();
		TextDynaField customField = new TextDynaField();
		customField.setCustom(true);
	}
}
