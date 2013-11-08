/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.domain;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SimpleAuditLog extends AuditLog {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SimpleAuditLog.class);

	private List<AuditChangeItem> changeItems;

	private String postedUserFullName;

	private String postedUserAvatarId;

	public SimpleAuditLog() {
	}

	public List<AuditChangeItem> getChangeItems() {
		if (changeItems == null) {
			changeItems = parseChangeItems();
		}
		if (changeItems == null) {
			changeItems = new ArrayList<AuditChangeItem>();
		}
		return changeItems;
	}

	public String getPostedUserFullName() {
		return postedUserFullName;
	}

	public void setPostedUserFullName(String postedUserFullName) {
		this.postedUserFullName = postedUserFullName;
	}

	public String getPostedUserAvatarId() {
		return postedUserAvatarId;
	}

	public void setPostedUserAvatarId(String postedUserAvatarId) {
		this.postedUserAvatarId = postedUserAvatarId;
	}

	private List<AuditChangeItem> parseChangeItems() {
		List<AuditChangeItem> items = new ArrayList<AuditChangeItem>();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		builderFactory.setIgnoringComments(true);
		builderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(new InputSource(
					new StringReader(this.getChangeset())));
			NodeList changeElements = document
					.getElementsByTagName("changelog");

			for (int i = 0; i < changeElements.getLength(); i++) {
				Element element = (Element) changeElements.item(i);
				AuditChangeItem changeItem = new AuditChangeItem();
				changeItem.setField(element.getAttribute("field"));
				changeItem.setNewvalue(element.getAttribute("newvalue"));
				changeItem.setOldvalue(element.getAttribute("oldvalue"));
				items.add(changeItem);
			}
		} catch (Exception e) {
			log.error("Error while parse change log item", e);
		}

		return items;
	}
}
