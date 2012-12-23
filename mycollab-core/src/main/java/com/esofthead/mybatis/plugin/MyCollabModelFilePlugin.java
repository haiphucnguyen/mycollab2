package com.esofthead.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class MyCollabModelFilePlugin extends
		org.mybatis.generator.api.PluginAdapter {

	@Override
	public boolean validate(List<String> args) {
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
		if (isTableHasIdPrimaryKey(introspectedTable)) {
			XmlElement element = new XmlElement("insert");
			element.addAttribute(new Attribute("id", "insertAndReturnKey"));
			element.addAttribute(new Attribute("type", introspectedTable
					.getBaseRecordType()));
			element.addAttribute(new Attribute("useGeneratedKeys", "true"));
			element.addAttribute(new Attribute("keyProperty", "id"));

			TextElement commentElement = new TextElement(
					"<!--WARNING - @mbggenerated-->");
			element.addElement(commentElement);

			StringBuffer sqlBuilder = new StringBuffer("insert into ").append(
					introspectedTable
							.getAliasedFullyQualifiedTableNameAtRuntime())
					.append(" (");

			StringBuffer valueSt = new StringBuffer("values (");

			List<IntrospectedColumn> allColumns = introspectedTable
					.getAllColumns();
			for (int i = 0; i < allColumns.size(); i++) {

				IntrospectedColumn column = allColumns.get(i);
				sqlBuilder.append(column.getActualColumnName());

				valueSt.append("#{").append(column.getActualColumnName())
						.append(",jdbcType=").append(column.getJdbcTypeName())
						.append("}");

				if (i < allColumns.size() - 1) {
					sqlBuilder.append(", ");
					valueSt.append(", ");
				}
			}
			sqlBuilder.append(") ");
			sqlBuilder.append(valueSt.toString()).append(")");

			element.addElement(new TextElement(sqlBuilder.toString()));

			document.getRootElement().addElement(element);
		}

		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		if (isTableHasIdPrimaryKey(introspectedTable)) {
			Method method = new Method();
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setName("insertAndReturnKey");
			method.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
			method.addParameter(new Parameter(new FullyQualifiedJavaType(
					introspectedTable.getBaseRecordType()), "value"));
			interfaze.addMethod(method);
		}

		return true;
	}

	private boolean isTableHasIdPrimaryKey(IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> primaryKeys = introspectedTable
				.getPrimaryKeyColumns();
		if ((primaryKeys != null) && (primaryKeys.size() == 1)) {
			IntrospectedColumn column = primaryKeys.get(0);
			return (column.getFullyQualifiedJavaType().getFullyQualifiedName())
					.equals("java.lang.Integer");
		} else {
			return false;
		}
	}
}
