/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.ui;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.io.Serializable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MobileGridFormLayoutHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private final GridLayout layout;

    private final String fieldControlWidth;
    private final String defaultCaptionWidth;
    private final Alignment captionAlignment;

    public MobileGridFormLayoutHelper(final int columns, final int rows, final String fieldControlWidth, final String defaultCaptionWidth, final Alignment captionAlignment) {
        this.fieldControlWidth = fieldControlWidth;
        this.defaultCaptionWidth = defaultCaptionWidth;
        this.captionAlignment = captionAlignment;

        this.layout = new GridLayout(2 * columns, rows);
        this.layout.setMargin(new MarginInfo(true, false, false, false));
        this.layout.setSpacing(false);

        this.layout.setRowExpandRatio(0, 0);
    }

    public static MobileGridFormLayoutHelper defaultFormLayoutHelper(int columns, int rows) {
        MobileGridFormLayoutHelper helper = new MobileGridFormLayoutHelper(columns, rows, "100%", "150px", Alignment.TOP_LEFT);
        helper.getLayout().setWidth("100%");
        helper.getLayout().addStyleName(UIConstants.GRIDFORM_STANDARD);
        helper.getLayout().setMargin(false);
        return helper;
    }

    public Component addComponent(final Component field, final String caption, final int columns, final int rows) {
        return this.addComponent(field, caption, columns, rows, fieldControlWidth, captionAlignment);
    }

    public Component addComponent(final Component field, final String caption, final int columns, final int rows, final int colspan, final String width, final Alignment alignment) {
        if (caption != null) {
            final Label l = new Label(caption);
            final HorizontalLayout captionWrapper = new HorizontalLayout();
            captionWrapper.addComponent(l);
            captionWrapper.setComponentAlignment(l, alignment);
            captionWrapper.setStyleName("gridform-caption");
            captionWrapper.setMargin(true);
            captionWrapper.setWidth(this.defaultCaptionWidth);
            if (columns == 0) {
                captionWrapper.addStyleName("first-col");
            }
            if (rows == 0) {
                captionWrapper.addStyleName("first-row");
            }
            if ((rows + 1) % 2 == 0)
                captionWrapper.addStyleName("even-row");

            this.layout.addComponent(captionWrapper, 2 * columns, rows);
            captionWrapper.setHeight("100%");
        }
        final MHorizontalLayout fieldWrapper = new MHorizontalLayout().withStyleName("gridform-field");
        fieldWrapper.addComponent(field);

        if (!(field instanceof Button))
            field.setCaption(null);

        field.setWidth(width);

        fieldWrapper.setWidth("100%");
        if (rows == 0) {
            fieldWrapper.addStyleName("first-row");
        }
        if ((rows + 1) % 2 == 0) {
            fieldWrapper.addStyleName("even-row");
        }
        this.layout.addComponent(fieldWrapper, 2 * columns + 1, rows, 2 * (columns + colspan - 1) + 1, rows);
        this.layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
        return field;
    }

    public Component addComponent(final Component field, final String caption,
                                  final int columns, final int rows, final String width,
                                  final Alignment alignment) {
        if (caption != null) {
            final Label l = new Label(caption);
            // l.setHeight("100%");
            final HorizontalLayout captionWrapper = new HorizontalLayout();
            captionWrapper.addComponent(l);
            captionWrapper.setComponentAlignment(l, alignment);
            captionWrapper.setWidth(this.defaultCaptionWidth);
            captionWrapper.setHeight("100%");
            captionWrapper.setStyleName("gridform-caption");
            captionWrapper.setMargin(true);
            if (columns == 0) {
                captionWrapper.addStyleName("first-col");
            }
            if (rows == 0) {
                captionWrapper.addStyleName("first-row");
            }
            this.layout.addComponent(captionWrapper, 2 * columns, rows);
        }
        final HorizontalLayout fieldWrapper = new HorizontalLayout();
        fieldWrapper.setStyleName("gridform-field");
        if (!(field instanceof Button))
            field.setCaption(null);
        fieldWrapper.addComponent(field);

        field.setWidth(width);

        fieldWrapper.setWidth("100%");
        fieldWrapper.setMargin(true);
        if (rows == 0) {
            fieldWrapper.addStyleName("first-row");
        }
        this.layout.addComponent(fieldWrapper, 2 * columns + 1, rows);
        this.layout.setColumnExpandRatio(2 * columns + 1, 1.0f);
        return field;
    }

    public Component getComponent(final int column, final int row) {
        return this.layout.getComponent(2 * column + 1, row);
    }

    public GridLayout getLayout() {
        return this.layout;
    }
}
