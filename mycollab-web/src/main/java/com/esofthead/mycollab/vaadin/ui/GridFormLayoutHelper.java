package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class GridFormLayoutHelper {
	private GridLayout layout;

	public GridFormLayoutHelper(int columns, int rows) {
		layout = new GridLayout(3 * columns, rows + 1);
		layout.setMargin(true, false, false, true);
		layout.setSpacing(true);

		layout.setRowExpandRatio(0, 0);

		for (int i = 0; i < columns; i++) {
			Label captionL = new Label("");
			captionL.setWidth(UIConstants.DEFAULT_CAPTION_FORM_WIDTH);
			captionL.setHeight("1px");
			layout.addComponent(captionL, 3 * i, 0);

			Label capLabelE = new Label();
			capLabelE.setHeight("1px");
			layout.addComponent(capLabelE, 3 * i + 1, 0);

			Label captionS = new Label("");
			captionS.setWidth("5px");
			captionS.setHeight("1px");
			layout.addComponent(captionS, 3 * i + 2, 0);
		}

		layout.setRowExpandRatio(0, 0);
	}

	public Field addComponent(Field field, String caption, int columns,
			int rows, String width) {
		Label l = new Label(caption);
		l.setSizeUndefined();
		layout.addComponent(l, 3 * columns, rows + 1);
		layout.setComponentAlignment(l, Alignment.TOP_RIGHT);

		layout.addComponent(field, 3 * columns + 1, rows + 1);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}

	public Field addComponent(Field field, String caption, int column1,
			int row1, int column2, int row2) {
		Label l = new Label(caption);
		l.setSizeUndefined();
		layout.addComponent(l, 3 * column1, row1 + 1);
		layout.setComponentAlignment(l, Alignment.TOP_RIGHT);

		layout.addComponent(field, 3 * column1 + 1, row1 + 1, 3 * column2 + 1,
				row2 + 1);
		field.setCaption(null);
		return field;
	}

	public Field addComponent(boolean condition, Field field, String caption,
			int column1, int row1, int column2, int row2) {
		if (condition) {
			return addComponent(field, caption, column1, row1, column2, row2);
		} else {
			return null;
		}
	}

	public Field addComponent(Field field, String caption, int columns, int rows) {
		return addComponent(field, caption, columns, rows,
				UIConstants.DEFAULT_CONTROL_WIDTH);
	}

	public Field addComponent(boolean condition, Field field, String caption,
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
