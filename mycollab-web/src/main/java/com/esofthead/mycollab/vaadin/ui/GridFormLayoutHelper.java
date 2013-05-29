package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class GridFormLayoutHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	private final GridLayout layout;

	private final String fieldControlWidth;
	private final String defaultCaptionWidth;
	private final Alignment captionAlignment;

	public GridFormLayoutHelper(final int columns, final int rows) {
		this(columns, rows, UIConstants.DEFAULT_CAPTION_FORM_WIDTH);
	}

	public GridFormLayoutHelper(final int columns, final int rows,
			final String defaultCaptionWidth) {
		this(columns, rows, UIConstants.DEFAULT_CONTROL_WIDTH,
				defaultCaptionWidth, Alignment.TOP_RIGHT);
	}

	public GridFormLayoutHelper(final int columns, final int rows,
			final String fieldControlWidth, final String defaultCaptionWidth) {
		this(columns, rows, fieldControlWidth, defaultCaptionWidth,
				Alignment.TOP_RIGHT);
	}

	public GridFormLayoutHelper(final int columns, final int rows,
			final String fieldControlWidth, final String defaultCaptionWidth,
			final Alignment captionAlignment) {
		this.fieldControlWidth = fieldControlWidth;
		this.defaultCaptionWidth = defaultCaptionWidth;
		this.captionAlignment = captionAlignment;

		layout = new GridLayout(2 * columns, rows);
		layout.setMargin(true, false, false, false);
		layout.setSpacing(false);

		// layout.setRowExpandRatio(0, 0);

		// for (int i = 0; i < columns; i++) {
		// Label captionL = new Label("");
		// captionL.setWidth(defaultCaptionWidth);
		// captionL.setHeight("1px");
		// layout.addComponent(captionL, 2 * i, 0);
		//
		// Label captionS = new Label("");
		// captionS.setWidth("5px");
		// captionS.setHeight("1px");
		// layout.addComponent(captionS, 2 * i + 1, 0);
		// }

		layout.setRowExpandRatio(0, 0);
	}

	public Component addComponent(final boolean condition, final Field field,
			final String caption, final int columns, final int rows) {
		if (condition) {
			return addComponent(field, caption, columns, rows,
					fieldControlWidth);
		} else {
			return null;
		}
	}

	public Component addComponent(final boolean condition, final Field field,
			final String caption, final int columns, final int rows,
			final Alignment alignment) {
		if (condition) {
			return addComponent(field, caption, columns, rows,
					fieldControlWidth, alignment);
		} else {
			return null;
		}
	}

	public Component addComponent(final Boolean condition,
			final Component field, final String caption, final int columns,
			final int rows, final int colspan) {
		if (condition) {
			return addComponent(field, caption, columns, rows, colspan);
		} else {
			return null;
		}
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows) {
		return addComponent(field, caption, columns, rows, fieldControlWidth);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final Alignment alignment) {
		return addComponent(field, caption, columns, rows, fieldControlWidth,
				alignment);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan) {
		return addComponent(field, caption, columns, rows, colspan,
				fieldControlWidth, captionAlignment);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan,
			final int rowspan) {
		return addComponent(field, caption, columns, rows, colspan, rowspan,
				captionAlignment);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan,
			final int rowspan, final Alignment alignment) {
		if (caption != null) {
			final Label l = new Label(caption + ":");
			l.setSizeUndefined();
			layout.addComponent(l, 2 * columns, rows);
			layout.setComponentAlignment(l, alignment);

			layout.addComponent(field, 2 * columns + 1, rows, 2 * (columns
					+ colspan - 1) + 1, rows + rowspan);
			layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
			field.setCaption(null);
			return field;
		}
		layout.addComponent(field, 2 * columns, rows,
				2 * (columns + colspan - 1) + 1, rows + rowspan);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		field.setCaption(null);
		field.setWidth("100%");
		return field;
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan,
			final String width) {
		return addComponent(field, caption, columns, rows, colspan, width,
				captionAlignment);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan,
			final String width, final Alignment alignment) {
		if (caption != null) {
			final Label l = new Label(caption + ":");
			l.setHeight("100%");
			final HorizontalLayout captionWrapper = new HorizontalLayout();
			captionWrapper.addComponent(l);
			captionWrapper.setStyleName("gridform-caption");
			captionWrapper.setWidth(defaultCaptionWidth);
			if (columns == 0) {
				captionWrapper.addStyleName("first-col");
			}
			if (rows == 0) {
				captionWrapper.addStyleName("first-row");
			}
			layout.addComponent(captionWrapper, 2 * columns, rows);
			captionWrapper.setHeight("100%");
		}
		final HorizontalLayout fieldWrapper = new HorizontalLayout();
		fieldWrapper.setStyleName("gridform-field");
		fieldWrapper.addComponent(field);
		field.setCaption(null);
		field.setWidth(width);
		field.setHeight("100%");
		fieldWrapper.setWidth("100%");
		if (rows == 0) {
			fieldWrapper.addStyleName("first-row");
		}
		layout.addComponent(fieldWrapper, 2 * columns + 1, rows, 2 * (columns
				+ colspan - 1) + 1, rows);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		return field;
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan,
			final String width, final String height) {
		return addComponent(field, caption, columns, rows, colspan, width,
				height, captionAlignment);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final int colspan,
			final String width, final String height, final Alignment alignment) {
		final Label l = new Label(caption + ":");
		l.setSizeUndefined();
		layout.addComponent(l, 2 * columns, rows);
		layout.setComponentAlignment(l, alignment);

		layout.addComponent(field, 2 * columns + 1, rows, 2 * (columns
				+ colspan - 1) + 1, rows);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		field.setCaption(null);
		field.setWidth(width);
		return field;
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final String width) {
		return addComponent(field, caption, columns, rows, width,
				captionAlignment);
	}

	public Component addComponent(final Component field, final String caption,
			final int columns, final int rows, final String width,
			final Alignment alignment) {
		if (caption != null) {
			final Label l = new Label(caption + ":");
			l.setHeight("100%");
			final HorizontalLayout captionWrapper = new HorizontalLayout();
			captionWrapper.addComponent(l);
			captionWrapper.setComponentAlignment(l, alignment);
			captionWrapper.setWidth(defaultCaptionWidth);
			captionWrapper.setHeight("100%");
			captionWrapper.setStyleName("gridform-caption");
			if (columns == 0) {
				captionWrapper.addStyleName("first-col");
			}
			if (rows == 0) {
				captionWrapper.addStyleName("first-row");
			}
			layout.addComponent(captionWrapper, 2 * columns, rows);
		}
		final HorizontalLayout fieldWrapper = new HorizontalLayout();
		fieldWrapper.setStyleName("gridform-field");
		field.setCaption(null);
		fieldWrapper.addComponent(field);
		field.setWidth(width);
		fieldWrapper.setWidth("100%");
		if (rows == 0) {
			fieldWrapper.addStyleName("first-row");
		}
		layout.addComponent(fieldWrapper, 2 * columns + 1, rows);
		layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
		return field;
	}

	public Component getComponent(final int column, final int row) {
		return layout.getComponent(2 * column + 1, row);
	}

	public GridLayout getLayout() {
		return layout;
	}
}
