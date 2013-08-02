package com.esofthead.mycollab.web;

import com.vaadin.ui.CustomLayout;

public class CustomLayoutExt extends CustomLayout {
	private static final long serialVersionUID = 1L;

	public CustomLayoutExt(String layoutId) {
		super();

		try {
			initTemplateContentsFromInputStream(CustomLayoutExt.class
					.getClassLoader().getResourceAsStream(
							"layouts/" + layoutId + ".html"));
		} catch (Exception e) {
			this.setTemplateName(layoutId);
		}
	}
}
