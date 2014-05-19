package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H3;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1.1
 * 
 */
public class TooltipBuilder {

	private Div div;
	private Table table;

	public TooltipBuilder() {
		div = new Div();
		table = new Table();
		table.setStyle("padding-left:10px; width :500px; color: #5a5a5a; font-size:11px;");
		div.appendChild(table);
	}

	public TooltipBuilder setTitle(String title) {
		H3 htmlTitle = new H3();
		htmlTitle.appendText(title);
		div.appendChild(0, htmlTitle);
		return this;
	}

	public TooltipBuilder appendRow(Tr tr) {
		table.appendChild(tr);
		return this;
	}

	public Div create() {
		return div;
	}

	public static class TdUtil {
		public static Td buildCellName(String name) {
			return new Td().setStyle(
					"width: 70px; vertical-align: top; text-align: right;")
					.appendText(name + ": ");
		}

		public static Td buildCellValue(String value) {
			String cutNameVal = StringUtils.getStringFieldValue(value);
			return new Td()
					.setStyle(
							"width:200px;word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendText(cutNameVal);
		}

		public static Td buildCellLink(String href, String name) {
			String cutNameVal = StringUtils.getStringFieldValue(name);
			return new Td()
					.setStyle(
							"word-wrap: break-word; white-space: normal;vertical-align: top; word-break: break-all;")
					.appendChild(new A().setHref(href).appendText(cutNameVal));
		}
	}
}
