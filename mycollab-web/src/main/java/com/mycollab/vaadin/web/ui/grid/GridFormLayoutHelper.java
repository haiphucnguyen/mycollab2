/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui.grid;

import com.hp.gagawa.java.elements.Span;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class GridFormLayoutHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    private final GridLayout layout;

    private Map<String, GridCellWrapper> fieldCaptionMappings = new HashMap<>();

    public GridFormLayoutHelper(int columns, int rows) {

        layout = new GridLayout(columns, rows);
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setRowExpandRatio(0, 0);
    }

    public static GridFormLayoutHelper defaultFormLayoutHelper(int columns, int rows) {
        GridFormLayoutHelper helper = new GridFormLayoutHelper(columns, rows);
        helper.getLayout().setWidth("100%");
        helper.getLayout().setSpacing(false);
        helper.getLayout().setMargin(false);
        helper.getLayout().addStyleName(WebThemes.GRIDFORM_STANDARD);
        return helper;
    }

    public void appendRow() {
        layout.setRows(layout.getRows() + 1);
    }

    public int getRows() {
        return layout.getRows();
    }

    public <T> T addComponent(T field, String caption, int columns, int rows, int colSpan, String width) {
        return this.addComponent(field, caption, null, columns, rows, colSpan, width);
    }

    public <T> T addComponent(T field, String caption, int columns, int rows) {
        return this.addComponent(field, caption, null, columns, rows, 1, "100%");
    }

    public <T> T addComponent(T field, String caption, String contextHelp, int columns, int rows) {
        return this.addComponent(field, caption, contextHelp, columns, rows, 1, "100%");
    }

    public <T> T addComponent(T field, String caption, String contextHelp, int columns, int rows, int colSpan, String controlWidth) {
        GridCellWrapper cell = buildCell(caption, contextHelp, columns, rows, colSpan, controlWidth);
        cell.addField((Component) field);
        return field;
    }

    public GridCellWrapper buildCell(String caption, String contextHelp, int columns, int rows) {
        return buildCell(caption, contextHelp, columns, rows, 1, "100%");
    }

    public GridCellWrapper buildCell(String caption, String contextHelp, int columns, int rows, int colSpan, String controlWidth) {
        GridCellWrapper cell = new GridCellWrapper();
        cell.setFieldWidth(controlWidth);

        if (StringUtils.isNotBlank(caption)) {
            Span captionSpan = new Span().appendText(caption);
            if (StringUtils.isNotBlank(contextHelp)) {

            }
            ELabel captionLbl = new ELabel(caption).withStyleName(UIConstants.LABEL_WORD_WRAP).withDescription(caption);
            MHorizontalLayout captionWrapper = new MHorizontalLayout().withSpacing(false).withMargin(true)
                    .withFullHeight().withStyleName("gridform-caption").with(captionLbl).expand(captionLbl);
            if (StringUtils.isNotBlank(contextHelp)) {
                ELabel contextHelpLbl = ELabel.html("&nbsp;" + VaadinIcons.QUESTION_CIRCLE.getHtml())
                        .withStyleName(WebThemes.INLINE_HELP).withDescription(contextHelp).withUndefinedWidth();
                captionWrapper.with(contextHelpLbl);
            }

            cell.addCaption(ELabel.html(captionSpan.write()).withStyleName("gridform-caption"));
        }

        layout.addComponent(cell, columns, rows, columns + colSpan - 1, rows);

        if (StringUtils.isNotBlank(caption)) {
            fieldCaptionMappings.put(caption, cell);
        }
        return cell;
    }

    /**
     * @param caption
     * @return null if it can not find the component wrapper associates with
     * <code>caption</code>
     */
    public GridCellWrapper getComponentWrapper(String caption) {
        return fieldCaptionMappings.get(caption);
    }

    public GridLayout getLayout() {
        return layout;
    }
}
