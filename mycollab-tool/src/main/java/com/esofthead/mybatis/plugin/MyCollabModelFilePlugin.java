package com.esofthead.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
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
			generateInsertAndReturnKeySqlStatement(document, introspectedTable);
			generateRemoveMultipleKeysSqlStatement(document, introspectedTable);
			generateUpdateMultipleKeysSqlStatement(document, introspectedTable);
		}

		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {

		InnerEnum enumFieldClass = getEnumFieldClass(topLevelClass);
		enumFieldClass.addEnumConstant(field.getName());

		if ("VARCHAR".equals(introspectedColumn.getJdbcTypeName())
				|| "LONGVARCHAR".equals(introspectedColumn.getJdbcTypeName())) {
			String annotation = "@org.hibernate.validator.constraints.Length(max=%s, message=\"%s\")";
			annotation = String.format(annotation,
					introspectedColumn.getLength(), "Field value is too long");
			field.addAnnotation(annotation);
		}

		String columnAnnotation = "@com.esofthead.mycollab.core.db.metadata.Column(\"%s\")";
		columnAnnotation = String.format(columnAnnotation,
				introspectedColumn.getActualColumnName());
		field.addAnnotation(columnAnnotation);
		return true;
	}

	private static boolean isPrimaryKeyOfTable(
			IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable) {
		List<IntrospectedColumn> primaryKeyColumns = introspectedTable
				.getPrimaryKeyColumns();
		for (IntrospectedColumn priKey : primaryKeyColumns) {
			if (introspectedColumn.getActualColumnName().equals(
					priKey.getActualColumnName())) {
				return true;
			}
		}

		return false;
	}

	private void generateInsertAndReturnKeySqlStatement(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement element = new XmlElement("insert");
		String parameterType = !isBlobDomainGenerated(introspectedTable) ? introspectedTable
				.getBaseRecordType() : introspectedTable
				.getRecordWithBLOBsType();
		element.addAttribute(new Attribute("parameterType", parameterType));
		element.addAttribute(new Attribute("id", "insertAndReturnKey"));
		element.addAttribute(new Attribute("useGeneratedKeys", "true"));
		element.addAttribute(new Attribute("keyProperty", "id"));

		TextElement commentElement = new TextElement(
				"<!--WARNING - @mbggenerated-->");
		element.addElement(commentElement);

		StringBuffer sqlBuilder = new StringBuffer("insert into ").append(
				introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime())
				.append(" (");

		StringBuilder valueSt = new StringBuilder("values (");

		List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
		for (int i = 0; i < allColumns.size(); i++) {

			IntrospectedColumn column = allColumns.get(i);
			sqlBuilder.append(column.getActualColumnName());

			valueSt.append("#{").append(column.getJavaProperty())
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

	private void generateRemoveMultipleKeysSqlStatement(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement element = new XmlElement("delete");
		TextElement commentElement = new TextElement(
				"<!--WARNING - @mbggenerated-->");
		element.addElement(commentElement);
		element.addAttribute(new Attribute("id", "removeKeysWithSession"));
		element.addAttribute(new Attribute("parameterType", "java.util.List"));

		StringBuffer sqlBuilder = new StringBuffer("delete from ")
				.append(introspectedTable
						.getAliasedFullyQualifiedTableNameAtRuntime())
				.append(" where id IN <foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\"> #{item} </foreach>");

		element.addElement(new TextElement(sqlBuilder.toString()));
		document.getRootElement().addElement(element);
	}

	private void generateUpdateMultipleKeysSqlStatement(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement sqlElement = new XmlElement("sql");
		sqlElement
				.addAttribute(new Attribute("id", "massUpdateWithSessionSql"));
		sqlElement
				.addElement(new TextElement("<!--WARNING - @mbggenerated-->"));

		StringBuffer sqlBuilder = new StringBuffer("update ")
				.append(introspectedTable
						.getAliasedFullyQualifiedTableNameAtRuntime());
		sqlElement.addElement(new TextElement(sqlBuilder.toString()));
		XmlElement setElement = new XmlElement("set");

		// set every field of table
		List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
		for (IntrospectedColumn column : allColumns) {
			if (!isPrimaryKeyOfTable(column, introspectedTable)) {
				XmlElement ifElement = new XmlElement("if");
				String conditionStr = "record.%s != null";
				ifElement.addAttribute(new Attribute("test", String.format(
						conditionStr, column.getJavaProperty())));

				String setStr = "%s = #{record.%s,jdbcType=%s},";
				ifElement.addElement(new TextElement(String.format(setStr,
						column.getActualColumnName(), column.getJavaProperty(),
						column.getJdbcTypeName())));

				setElement.addElement(ifElement);
			}
		}
		sqlElement.addElement(setElement);
		document.getRootElement().addElement(sqlElement);

		XmlElement element = new XmlElement("update");
		element.addAttribute(new Attribute("id", "massUpdateWithSession"));
		element.addAttribute(new Attribute("parameterType", "map"));
		TextElement commentElement = new TextElement(
				"<!--WARNING - @mbggenerated-->");
		element.addElement(commentElement);

		XmlElement includeElement = new XmlElement("include");
		includeElement.addAttribute(new Attribute("refid",
				"massUpdateWithSessionSql"));

		element.addElement(includeElement);

		// generate query statement
		XmlElement queryElement = new XmlElement("if");
		queryElement.addAttribute(new Attribute("test", "_parameter != null"));
		queryElement
				.addElement(new TextElement(
						" where id IN <foreach item=\"item\" index=\"index\" collection=\"primaryKeys\" open=\"(\" separator=\",\" close=\")\"> #{item} </foreach>"));

		element.addElement(queryElement);
		document.getRootElement().addElement(element);
	}

	private boolean isBlobDomainGenerated(IntrospectedTable introspectedTable) {
		return !(introspectedTable.getBLOBColumns().size() == 0 || introspectedTable
				.getBLOBColumns().size() == 1);
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		String commentLine = "/*Domain class of table %s*/";
		topLevelClass
				.addFileCommentLine(String.format(commentLine,
						introspectedTable
								.getAliasedFullyQualifiedTableNameAtRuntime()));
		topLevelClass.addAnnotation("@SuppressWarnings(\"ucd\")");
		Field staticField = new Field("serialVersionUID",
				new FullyQualifiedJavaType("long"));
		staticField.setInitializationString("1");
		staticField.setStatic(true);
		staticField.setFinal(true);
		staticField.setVisibility(JavaVisibility.PRIVATE);
		topLevelClass.addField(staticField);

		if (!isBlobDomainGenerated(introspectedTable)) {
			topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		} else {
			topLevelClass.setVisibility(JavaVisibility.DEFAULT);
		}

		String tableAnnotation = "@com.esofthead.mycollab.core.db.metadata.Table(\"%s\")";
		tableAnnotation = String.format(tableAnnotation, introspectedTable
				.getTableConfiguration().getTableName());
		topLevelClass.addAnnotation(tableAnnotation);

		return super.modelBaseRecordClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addAnnotation("@SuppressWarnings(\"ucd\")");

		Field staticField = new Field("serialVersionUID",
				new FullyQualifiedJavaType("long"));
		staticField.setInitializationString("1");
		staticField.setStatic(true);
		staticField.setFinal(true);
		staticField.setVisibility(JavaVisibility.PRIVATE);
		topLevelClass.addField(staticField);

		return super.modelRecordWithBLOBsClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		topLevelClass.addAnnotation("@SuppressWarnings(\"ucd\")");
		List<InnerClass> innerClasses = topLevelClass.getInnerClasses();
		for (InnerClass innerClass : innerClasses) {
			innerClass.addAnnotation("@SuppressWarnings(\"ucd\")");
		}
		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		interfaze.addAnnotation("@SuppressWarnings({ \"ucd\", \"rawtypes\" })");

		if (isTableHasIdPrimaryKey(introspectedTable)) {
			generateInsertAndReturnKeyMethod(interfaze, introspectedTable);
			generateRemoveMultipleKeysMethod(interfaze, introspectedTable);
			generateMassUpdateMultipleKeysMethod(interfaze, introspectedTable);
		}

		return true;
	}

	private static InnerEnum getEnumFieldClass(TopLevelClass topLevelClass) {
		List<InnerEnum> innerEnums = topLevelClass.getInnerEnums();
		if (innerEnums == null || innerEnums.size() == 0) {
			InnerEnumEx enumFieldCls = new InnerEnumEx(
					new FullyQualifiedJavaType("Field"));
			enumFieldCls.setFinal(true);
			enumFieldCls.setStatic(true);
			enumFieldCls.setVisibility(JavaVisibility.PUBLIC);
			topLevelClass.addInnerEnum(enumFieldCls);
			Method equalToMethod = new Method("equalTo");
			equalToMethod.setReturnType(new FullyQualifiedJavaType("boolean"));
			equalToMethod.addParameter(new Parameter(
					new FullyQualifiedJavaType("Object"), "value"));
			equalToMethod.setVisibility(JavaVisibility.PUBLIC);
			equalToMethod.addBodyLine("return name().equals(value);");
			enumFieldCls.addMethod(equalToMethod);
			return enumFieldCls;
		} else {
			return innerEnums.get(0);
		}
	}

	private void generateInsertAndReturnKeyMethod(Interface interfaze,
			IntrospectedTable introspectedTable) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		context.getCommentGenerator().addGeneralMethodComment(method,
				introspectedTable);
		method.setName("insertAndReturnKey");
		String paramterType = !isBlobDomainGenerated(introspectedTable) ? introspectedTable
				.getBaseRecordType() : introspectedTable
				.getRecordWithBLOBsType();
		method.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				paramterType), "value"));
		interfaze.addMethod(method);
	}

	private void generateRemoveMultipleKeysMethod(Interface interfaze,
			IntrospectedTable introspectedTable) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		context.getCommentGenerator().addGeneralMethodComment(method,
				introspectedTable);
		method.setName("removeKeysWithSession");
		method.setReturnType(new FullyQualifiedJavaType("void"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				"java.util.List"), "primaryKeys"));
		interfaze.addMethod(method);
	}

	private void generateMassUpdateMultipleKeysMethod(Interface interfaze,
			IntrospectedTable introspectedTable) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		context.getCommentGenerator().addGeneralMethodComment(method,
				introspectedTable);
		method.setName("massUpdateWithSession");
		method.setReturnType(new FullyQualifiedJavaType("void"));

		String paramterType = !isBlobDomainGenerated(introspectedTable) ? introspectedTable
				.getBaseRecordType() : introspectedTable
				.getRecordWithBLOBsType();

		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				paramterType), "record", "@Param(\"record\")"));
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				"java.util.List"), "primaryKeys", "@Param(\"primaryKeys\")"));
		interfaze.addMethod(method);
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(
			Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		return true;
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
			Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean result = isBlobDomainGenerated(introspectedTable);
		return !result;
	}

	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
			Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean result = isBlobDomainGenerated(introspectedTable);
		return !result;
	}

	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(
			Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		boolean result = isBlobDomainGenerated(introspectedTable);
		return !result;
	}

	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(
			Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		boolean result = isBlobDomainGenerated(introspectedTable);
		return !result;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return super.modelSetterMethodGenerated(method, topLevelClass,
				introspectedColumn, introspectedTable, modelClassType);
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
