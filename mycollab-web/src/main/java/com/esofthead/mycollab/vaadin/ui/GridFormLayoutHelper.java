package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class GridFormLayoutHelper {
	private final GridLayout layout;

	public GridFormLayoutHelper(int columns, int rows) {
		layout = new GridLayout(2 * columns, rows + 1);
		layout.setMargin(true, false, false, false);
		layout.setSpacing(true);

		layout.setRowExpandRatio(0, 0);

		for (int i = 0; i < columns; i++) {
			Label captionL = new Label("");
			captionL.setWidth(UIConstants.DEFAULT_CAPTION_FORM_WIDTH);
			captionL.setHeight("1px");
			layout.addComponent(captionL, 2 * i, 0);

			Label captionS = new Label("");
			captionS.setWidth("5px");
			captionS.setHeight("1px");
			layout.addComponent(captionS, 2 * i + 1, 0);
		}

		layout.setRowExpandRatio(0, 0);
	}
	

	public Component addComponent(Component field, String caption, int columns,
			int rows, String width) {
		Label l = new Label(caption + ":");
		l.setSizeUndefined();
		layout.addComponent(l, 2 * columns, rows + 1);
		layout.setComponentAlignment(l, Alignment.TOP_RIGHT);

		layout.addComponent(field, 2 * columns + 1, rows + 1);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}
	
	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, String width, String height) {
		Label l = new Label(caption + ":");
		l.setSizeUndefined();
		layout.addComponent(l, 2 * columns, rows + 1);
		layout.setComponentAlignment(l, Alignment.TOP_RIGHT);
		
		layout.addComponent(field, 2 * columns + 1, rows + 1, 2*(columns +colspan -1) + 1, rows + 1);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}

	public Component addComponent(Component field, String caption, int columns, int rows) {
		return addComponent(field, caption, columns, rows,
				UIConstants.DEFAULT_CONTROL_WIDTH);
	}

	public Component addComponent(boolean condition, Field field, String caption,
			int columns, int rows) {
		if (condition) {
			return addComponent(field, caption, columns, rows,
					UIConstants.DEFAULT_CONTROL_WIDTH);
		} else {
			return null;
		}
	}

	public GridLayout getLayout() {
		return layout;
	}
}
