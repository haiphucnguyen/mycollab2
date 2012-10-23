package com.esofthead.mycollab.shared.audit.domain;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SimpleAuditLog extends AuditLog {
	private List<AuditChangeItem> changeItems;

	public SimpleAuditLog(AuditLog auditLog) {
		this.setChangeset(auditLog.getChangeset());
		this.setId(auditLog.getId());
		this.setObjectClass(auditLog.getObjectClass());
		this.setPosteddate(auditLog.getPosteddate());
		this.setPosteduser(auditLog.getPosteduser());
		this.setRefid(auditLog.getRefid());

		setChangeItems();
	}

	public List<AuditChangeItem> getChangeItems() {
		return changeItems;
	}

	public void setChangeItems(List<AuditChangeItem> changeItems) {
		this.changeItems = changeItems;
	}

	private void setChangeItems() {
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

			changeItems = new ArrayList<AuditChangeItem>();

			for (int i = 0; i < changeElements.getLength(); i++) {
				Element element = (Element) changeElements.item(i);
				AuditChangeItem changeItem = new AuditChangeItem();
				changeItem.setField(element.getAttribute("field"));
				changeItem.setNewvalue(element.getAttribute("newvalue"));
				changeItem.setOldvalue(element.getAttribute("oldvalue"));
				changeItems.add(changeItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
