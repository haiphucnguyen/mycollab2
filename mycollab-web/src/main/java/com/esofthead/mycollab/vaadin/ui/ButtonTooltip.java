package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.ui.utils.LabelStringUtil;
import com.vaadin.ui.Button;

@SuppressWarnings("serial")
public class ButtonTooltip extends Button {

	public ButtonTooltip(String caption) {
		super(LabelStringUtil.subLabelString(caption));
		this.setDescription(caption);
	}
	
	public ButtonTooltip(String caption, ClickListener listenner) {
		super(LabelStringUtil.subLabelString(caption), listenner);
		this.setDescription(caption);
	}
}
