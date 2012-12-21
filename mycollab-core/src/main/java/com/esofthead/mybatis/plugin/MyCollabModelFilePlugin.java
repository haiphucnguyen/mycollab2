package com.esofthead.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class MyCollabModelFilePlugin extends
		org.mybatis.generator.api.PluginAdapter {

	@Override
	public boolean validate(List<String> args) {
		System.out.println("AAA: " + args.size());
		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,
			IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {

		List<Element> elements = document.getRootElement().getElements();
		for (Element element : elements) {
			if (element instanceof XmlElement) {
				if ("cache".equals(((XmlElement) element).getName())) {
					System.out
							.println("WARNING: cach element is existed in document");
					return true;
				}
			}
		}

		XmlElement element = new XmlElement("cache");
		element.addAttribute(new Attribute("type",
				"org.mybatis.caches.ehcache.LoggingEhcache"));
		document.getRootElement().addElement(0, element);

		return true;
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}
}
