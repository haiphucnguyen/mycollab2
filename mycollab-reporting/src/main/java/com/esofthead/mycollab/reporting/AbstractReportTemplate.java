/**
 * This file is part of mycollab-reporting.
 *
 * mycollab-reporting is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-reporting is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-reporting.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.reporting.expression.CompBuilderValue;
import com.esofthead.mycollab.reporting.expression.HyperlinkValue;
import com.esofthead.mycollab.reporting.expression.MValue;
import com.esofthead.mycollab.reporting.expression.SimpleFieldExpression;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.PaddingBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import java.awt.*;
import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */
public abstract class AbstractReportTemplate {
    private StyleBuilder rootStyle;
    private StyleBuilder boldStyle;
    private StyleBuilder italicStyle;
    private StyleBuilder underlineStyle;
    private StyleBuilder boldCenteredStyle;
    private StyleBuilder columnTitleStyle;
    private StyleBuilder columnStyle;
    private StyleBuilder borderStyle;
    private StyleBuilder formCaptionStyle;
    private StyleBuilder metaInfoStyle;

    public AbstractReportTemplate() {
        rootStyle = stl.style().setPadding(4);
        boldStyle = stl.style(rootStyle).bold();
        italicStyle = stl.style(rootStyle).italic();
        underlineStyle = stl.style(rootStyle).underline();
        boldCenteredStyle = stl.style(boldStyle).setTextAlignment(HorizontalTextAlignment.LEFT, VerticalTextAlignment.MIDDLE);
        borderStyle = stl.style(rootStyle).setBorder(stl.pen1Point().setLineColor(new Color(233, 233, 233)));
        metaInfoStyle = stl.style(rootStyle).setForegroundColor(new Color(153, 153, 153));
        formCaptionStyle = stl.style(rootStyle).setForegroundColor(new Color(153, 153, 153))
                .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);

        PaddingBuilder padding = stl.padding().setLeft(8);
        columnStyle = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
        columnTitleStyle = stl.style(columnStyle).setBorder(stl.pen1Point())
                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT)
                .setBackgroundColor(Color.LIGHT_GRAY).setPadding(padding).bold();
    }

    public StyleBuilder getUnderlineStyle() {
        return underlineStyle;
    }

    public StyleBuilder getBoldCenteredStyle() {
        return boldCenteredStyle;
    }

    public StyleBuilder getItalicStyle() {
        return italicStyle;
    }

    public StyleBuilder getBorderStyle() {
        return borderStyle;
    }

    public StyleBuilder getMetaInfoStyle() {
        return metaInfoStyle;
    }

    public StyleBuilder getFormCaptionStyle() {
        return formCaptionStyle;
    }

    public StyleBuilder getColumnTitleStyle() {
        return columnTitleStyle;
    }

    public ComponentBuilder buildCompBuilder(MValue value) {
        if (value instanceof HyperlinkValue) {
            return buildHyperLink((HyperlinkValue) value);
        } else if (value instanceof CompBuilderValue) {
            return buildComp((CompBuilderValue) value);
        } else if (value instanceof SimpleFieldExpression) {
            return buildText((SimpleFieldExpression) value);
        } else {
            throw new MyCollabException("Do not support mvalue type " + value);
        }
    }

    private ComponentBuilder buildHyperLink(HyperlinkValue hyperlink) {
        ComponentBuilder compBuilder;

        compBuilder = cmp.text(hyperlink.getTitle()).setHyperLink(hyperLink(hyperlink.getHref())).setStyle(underlineStyle);

        if (hyperlink.getStyle() != null) {
            compBuilder.setStyle(hyperlink.getStyle());
        }
        return compBuilder;
    }

    private ComponentBuilder buildComp(CompBuilderValue compBuilder) {
        return compBuilder.getCompBuilder();
    }

    private ComponentBuilder buildText(DRIExpression<String> expr) {
        return cmp.text(expr).setStyle(rootStyle);
    }
}
