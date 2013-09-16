package com.esofthead.mycollab.reporting;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;

import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

public class Templates {
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder underlineStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder bold22CenteredStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder columnStyle;
	static {
		rootStyle = stl.style().setPadding(2);
		boldStyle = stl.style(rootStyle).bold();
		italicStyle = stl.style(rootStyle).italic();
		underlineStyle = stl.style(rootStyle).underline();
		boldCenteredStyle = stl.style(boldStyle).setAlignment(
				HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
		bold18CenteredStyle = stl.style(boldCenteredStyle).setFontSize(18);
		bold22CenteredStyle = stl.style(boldCenteredStyle).setFontSize(22);
		columnStyle = stl.style(rootStyle).setVerticalAlignment(
				VerticalAlignment.MIDDLE);
		columnTitleStyle = stl.style(columnStyle).setBorder(stl.pen1Point())
				.setHorizontalAlignment(HorizontalAlignment.CENTER)
				.setBackgroundColor(Color.LIGHT_GRAY).bold();

	}

	/**
	 * Creates custom component which is possible to add to any report band
	 * component
	 */
	public static ComponentBuilder<?, ?> createTitleComponent(String label) {
		HyperLinkBuilder link = hyperLink("http://www.mycollab.com");
		ComponentBuilder<?, ?> dynamicReportsComponent = cmp.horizontalList(
				cmp.image(
						Templates.class.getClassLoader().getResourceAsStream(
								"images/logo.png")).setFixedDimension(150, 28),
				cmp.verticalList(cmp.text(label).setStyle(bold22CenteredStyle)
						.setHorizontalAlignment(HorizontalAlignment.LEFT), cmp
						.text("http://www.mycollab.com").setStyle(italicStyle)
						.setHyperLink(link)));

		return cmp.horizontalList().add(dynamicReportsComponent).newRow()
				.add(cmp.line()).newRow().add(cmp.verticalGap(10));
	}
}
