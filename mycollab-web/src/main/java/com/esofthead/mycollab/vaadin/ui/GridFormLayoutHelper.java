package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class GridFormLayoutHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	private final GridLayout layout;

	private final String fieldControlWidth;
	private final Alignment captionAlignment;

	public GridFormLayoutHelper(int columns, int rows) {
		this(columns, rows, UIConstants.DEFAULT_CAPTION_FORM_WIDTH);
	}

	public GridFormLayoutHelper(int columns, int rows,
			String fieldControlWidth, String defaultCaptionWidth,
			Alignment captionAlignment) {
		this.fieldControlWidth = fieldControlWidth;
		this.captionAlignment = captionAlignment;

		layout = new GridLayout(2 * columns, rows + 1);
		layout.setMargin(true, false, false, false);
		layout.setSpacing(true);

		layout.setRowExpandRatio(0, 0);

		for (int i = 0; i < columns; i++) {
			Label captionL = new Label("");
			captionL.setWidth(defaultCaptionWidth);
			captionL.setHeight("1px");
			layout.addComponent(captionL, 2 * i, 0);

			Label captionS = new Label("");
			captionS.setWidth("5px");
			captionS.setHeight("1px");
			layout.addComponent(captionS, 2 * i + 1, 0);
		}

		layout.setRowExpandRatio(0, 0);
	}

	public GridFormLayoutHelper(int columns, int rows,
			String defaultCaptionWidth) {
		this(columns, rows, UIConstants.DEFAULT_CONTROL_WIDTH,
				defaultCaptionWidth, Alignment.TOP_RIGHT);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, String width) {
		return addComponent(field, caption, columns, rows, width,
				captionAlignment);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, String width, Alignment alignment) {
		if (caption != null) {
			Label l = new Label(caption + ":");
			l.setSizeUndefined();
			layout.addComponent(l, 2 * columns, rows + 1);
			layout.setComponentAlignment(l, alignment);
		}
		layout.addComponent(field, 2 * columns + 1, rows + 1);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}

	public Component getComponent(int column, int row) {
		return layout.getComponent(2 * column + 1, row + 1);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, String width, String height) {
		return addComponent(field, caption, columns, rows, colspan, width,
				height, captionAlignment);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, String width, String height,
			Alignment alignment) {
		Label l = new Label(caption + ":");
		l.setSizeUndefined();
		layout.addComponent(l, 2 * columns, rows + 1);
		layout.setComponentAlignment(l, alignment);

		layout.addComponent(field, 2 * columns + 1, rows + 1, 2 * (columns
				+ colspan - 1) + 1, rows + 1);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, String width) {
		return addComponent(field, caption, columns, rows, colspan, width,
				captionAlignment);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, String width, Alignment alignment) {
		Label l = new Label(caption + ":");
		l.setSizeUndefined();
		layout.addComponent(l, 2 * columns, rows + 1);
		layout.setComponentAlignment(l, alignment);

		layout.addComponent(field, 2 * columns + 1, rows + 1, 2 * (columns
				+ colspan - 1) + 1, rows + 1);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, int rowspan) {
		return addComponent(field, caption, columns, rows, colspan, rowspan,
				captionAlignment);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, int colspan, int rowspan, Alignment alignment) {
		if (caption != null) {
			Label l = new Label(caption + ":");
			l.setSizeUndefined();
			layout.addComponent(l, 2 * columns, rows + 1);
			// layout.setComponentAlignment(l, alignment);

			layout.addComponent(field, 2 * columns + 1, rows + 1, 2 * (columns
					+ colspan - 1) + 1, rows + rowspan);
			layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
			field.setCaption(null);
			return field;
		} else {
			layout.addComponent(field, 2 * columns, rows + 1, 2 * (columns
					+ colspan - 1) + 1, rows + rowspan);
			layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
			field.setCaption(null);
			field.setWidth("100%");
			return field;
		}

	}

	public Component addComponent(Component field, String caption, int columns,
			int rows) {
		return addComponent(field, caption, columns, rows,
				this.fieldControlWidth);
	}

	public Component addComponent(Component field, String caption, int columns,
			int rows, Alignment alignment) {
		return addComponent(field, caption, columns, rows,
				this.fieldControlWidth, alignment);
	}

	public Component addComponent(boolean condition, Field field,
			String caption, int columns, int rows) {
		if (condition) {
			return addComponent(field, caption, columns, rows,
					this.fieldControlWidth);
		} else {
			return null;
		}
	}

	public Component addComponent(boolean condition, Field field,
			String caption, int columns, int rows, Alignment alignment) {
		if (condition) {
			return addComponent(field, caption, columns, rows,
					this.fieldControlWidth, alignment);
		} else {
			return null;
		}
	}

	public GridLayout getLayout() {
		return layout;
	}
}
