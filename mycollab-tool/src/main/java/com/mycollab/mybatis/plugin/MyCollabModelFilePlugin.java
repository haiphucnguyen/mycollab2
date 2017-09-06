package com.mycollab.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class MyCollabModelFilePlugin extends org.mybatis.generator.api.PluginAdapter {

    private static Map<String, InnerEnumEx> enumOfBlobDomains = new HashMap<>();

    @Override
    public boolean validate(List<String> args) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        if (isTableHasIdPrimaryKey(introspectedTable)) {
            generateInsertAndReturnKeySqlStatement(document, introspectedTable);
            generateRemoveMultipleKeysSqlStatement(document, introspectedTable);
            generateUpdateMultipleKeysSqlStatement(document, introspectedTable);
        }

        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        InnerEnumEx enumFieldClass = enumOfBlobDomains.get(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        if (enumFieldClass == null) {
            enumFieldClass = buildEnumFieldClass();
            enumOfBlobDomains.put(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime(), enumFieldClass);
        }

        enumFieldClass.addEnumConstant(field.getName());

        if ("VARCHAR".equals(introspectedColumn.getJdbcTypeName())
                || "LONGVARCHAR".equals(introspectedColumn.getJdbcTypeName())) {
            topLevelClass.addImportedType("org.hibernate.validator.constraints.Length");
            String annotation = "@Length(max=%s, message=\"%s\")";
            annotation = String.format(annotation, introspectedColumn.getLength(), "Field value is too long");
            field.addAnnotation(annotation);
        }

        String columnAnnotation = String.format("@Column(\"%s\")", introspectedColumn.getActualColumnName());
        field.addAnnotation(columnAnnotation);

        if (isPrimaryKeyOfTable(introspectedColumn, introspectedTable)) {
            generateEqualsMethod(field, topLevelClass);
            generateHashCodeMethod(field, topLevelClass);
        }
        return true;
    }

    private void generateEqualsMethod(Field field, TopLevelClass topLevelClass) {
        topLevelClass.addImportedType("org.apache.commons.lang3.builder.EqualsBuilder");
        Method m = new Method();
        m.setName("equals");
        m.setReturnType(new FullyQualifiedJavaType("boolean"));
        m.setVisibility(JavaVisibility.PUBLIC);
        m.setFinal(true);
        m.addParameter(new Parameter(new FullyQualifiedJavaType("Object"), "obj"));
        m.addBodyLine("if (obj == null) { return false;}");
        m.addBodyLine("if (obj == this) { return true;}");
        m.addBodyLine("if (!obj.getClass().isAssignableFrom(getClass())) { return false;}");
        m.addBodyLine(String.format("%s item = (%s)obj;", topLevelClass.getType().getShortName(), topLevelClass.getType().getShortName()));
        m.addBodyLine(String.format("return new EqualsBuilder().append(%s, %s).build();", field.getName(), "item" +
                "." + field.getName()));
        topLevelClass.addMethod(m);
    }

    private void generateHashCodeMethod(Field field, TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(" org.apache.commons.lang3.builder.HashCodeBuilder");
        Method m = new Method();
        m.setName("hashCode");
        m.setReturnType(new FullyQualifiedJavaType("int"));
        m.setVisibility(JavaVisibility.PUBLIC);
        m.setFinal(true);
        m.addBodyLine(String.format("return new HashCodeBuilder(%d, %d).append(%s).build();", Math.abs(new Random()
                .nextInt(1000) * 2 + 1), Math.abs(new Random().nextInt(1000) * 2 + 1), field.getName()));
        topLevelClass.addMethod(m);
    }

    private static InnerEnumEx buildEnumFieldClass() {
        InnerEnumEx enumFieldCls = new InnerEnumEx(new FullyQualifiedJavaType("Field"));
        enumFieldCls.setFinal(true);
        enumFieldCls.setVisibility(JavaVisibility.PUBLIC);
        Method equalToMethod = new Method("equalTo");
        equalToMethod.setReturnType(new FullyQualifiedJavaType("boolean"));
        equalToMethod.addParameter(new Parameter(new FullyQualifiedJavaType("Object"), "value"));
        equalToMethod.setVisibility(JavaVisibility.PUBLIC);
        equalToMethod.addBodyLine("return name().equals(value);");
        enumFieldCls.addMethod(equalToMethod);
        return enumFieldCls;
    }

    private static boolean isPrimaryKeyOfTable(IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
        for (IntrospectedColumn priKey : primaryKeyColumns) {
            if (introspectedColumn.getActualColumnName().equals(priKey.getActualColumnName())) {
                return true;
            }
        }

        return false;
    }

    private void generateInsertAndReturnKeySqlStatement(Document document, IntrospectedTable introspectedTable) {
        XmlElement element = new XmlElement("insert");
        String parameterType = !isBlobDomainGenerated(introspectedTable) ? introspectedTable
                .getBaseRecordType() : introspectedTable.getRecordWithBLOBsType();
        element.addAttribute(new Attribute("parameterType", parameterType));
        element.addAttribute(new Attribute("id", "insertAndReturnKey"));
        element.addAttribute(new Attribute("useGeneratedKeys", "true"));
        element.addAttribute(new Attribute("keyProperty", "id"));

        TextElement commentElement = new TextElement("<!--WARNING - @mbggenerated-->");
        element.addElement(commentElement);

        StringBuilder sqlBuilder = new StringBuilder("insert into ").append(
                introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()).append(" (");

        StringBuilder valueSt = new StringBuilder("values (");

        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (int i = 0; i < allColumns.size(); i++) {
            IntrospectedColumn column = allColumns.get(i);
            sqlBuilder.append(column.getActualColumnName());

            valueSt.append("#{").append(column.getJavaProperty())
                    .append(",jdbcType=").append(column.getJdbcTypeName()).append("}");

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

    private void generateRemoveMultipleKeysSqlStatement(Document document, IntrospectedTable introspectedTable) {
        XmlElement element = new XmlElement("delete");
        TextElement commentElement = new TextElement(
                "<!--WARNING - @mbggenerated-->");
        element.addElement(commentElement);
        element.addAttribute(new Attribute("id", "removeKeysWithSession"));
        element.addAttribute(new Attribute("parameterType", "java.util.List"));

        StringBuilder sqlBuilder = new StringBuilder("delete from ").append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime())
                .append(" where id IN <foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\"> #{item} </foreach>");

        element.addElement(new TextElement(sqlBuilder.toString()));
        document.getRootElement().addElement(element);
    }

    private void generateUpdateMultipleKeysSqlStatement(Document document, IntrospectedTable introspectedTable) {
        XmlElement sqlElement = new XmlElement("sql");
        sqlElement.addAttribute(new Attribute("id", "massUpdateWithSessionSql"));
        sqlElement.addElement(new TextElement("<!--WARNING - @mbggenerated-->"));
        StringBuilder sqlBuilder = new StringBuilder("#set($record = $_parameter.record)");
        sqlBuilder.append("\nupdate ")
                .append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        sqlElement.addElement(new TextElement(sqlBuilder.toString()));
        StringBuilder setElement = new StringBuilder("#mset()").append("\n");

        // set every field of table
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : allColumns) {
            if (!isPrimaryKeyOfTable(column, introspectedTable)) {
                setElement.append("        #ifnotnull(").append(String.format("$record.%s", column
                        .getJavaProperty())).append(")").append("\n");

                String setStr = "            %s = @{record.%s,jdbcType=%s},";
                setElement.append(String.format(setStr, column.getActualColumnName(), column.getJavaProperty(), column.getJdbcTypeName()));

                setElement.append("\n").append("        #end").append("\n");
            }
        }
        setElement.append("    #end");
        sqlElement.addElement(new TextElement(setElement.toString()));
        document.getRootElement().addElement(sqlElement);

        XmlElement element = new XmlElement("update");
        element.addAttribute(new Attribute("id", "massUpdateWithSession"));
        element.addAttribute(new Attribute("parameterType", "map"));
        element.addAttribute(new Attribute("lang", "velocity"));
        TextElement commentElement = new TextElement("<!--WARNING - @mbggenerated-->");
        element.addElement(commentElement);

        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", "massUpdateWithSessionSql"));

        element.addElement(includeElement);

        // generate query statement
        StringBuilder queryElement = new StringBuilder("#ifnotnull($_parameter)");
        queryElement.append(
                "\n    where id IN #repeat($_parameter.primaryKeys $item \",\" \"(\", \")\")").append("\n    @{item}")
                .append("\n     #end");
        queryElement.append("\n    #end");
        element.addElement(new TextElement(queryElement.toString()));
        document.getRootElement().addElement(element);
    }

    private boolean isBlobDomainGenerated(IntrospectedTable introspectedTable) {
        return !(introspectedTable.getBLOBColumns().size() == 0 || introspectedTable
                .getBLOBColumns().size() == 1);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("com.mycollab.db.metadata.Column");
        topLevelClass.addImportedType("com.mycollab.db.metadata.Table");
        topLevelClass.addImportedType("org.apache.ibatis.type.Alias");
        String commentLine = "/*Domain class of table %s*/";
        topLevelClass.addFileCommentLine(String.format(commentLine,
                introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
        topLevelClass.addAnnotation("@SuppressWarnings(\"ucd\")");
        Field staticField = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
        staticField.setInitializationString("1");
        staticField.setStatic(true);
        staticField.setFinal(true);
        staticField.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.addField(staticField);

        if (!isBlobDomainGenerated(introspectedTable)) {
            topLevelClass.setVisibility(JavaVisibility.PUBLIC);

            InnerEnumEx enumFieldClass = enumOfBlobDomains.get(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
            if (enumFieldClass == null) {
                enumFieldClass = buildEnumFieldClass();
                enumOfBlobDomains.put(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime(), enumFieldClass);
            }
            topLevelClass.addInnerEnum(enumFieldClass);

        } else {
            topLevelClass.setVisibility(JavaVisibility.DEFAULT);
        }

        String tableAnnotation = String.format("@Table(\"%s\")", introspectedTable.getTableConfiguration().getTableName());
        topLevelClass.addAnnotation(tableAnnotation);

        String aliasAnnotation = String.format("@Alias(\"%s\")", topLevelClass.getType().getShortName());
        topLevelClass.addAnnotation(aliasAnnotation);

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("com.mycollab.db.metadata.Column");
        topLevelClass.addImportedType("com.mycollab.db.metadata.Table");
        topLevelClass.addAnnotation("@SuppressWarnings(\"ucd\")");
        Field staticField = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
        staticField.setInitializationString("1");
        staticField.setStatic(true);
        staticField.setFinal(true);
        staticField.setVisibility(JavaVisibility.PRIVATE);
        topLevelClass.addField(staticField);

        InnerEnumEx enumFieldClass = enumOfBlobDomains.get(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        if (enumFieldClass == null) {
            enumFieldClass = buildEnumFieldClass();
            enumOfBlobDomains.put(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime(), enumFieldClass);
        }
        topLevelClass.addInnerEnum(enumFieldClass);

        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addAnnotation("@SuppressWarnings(\"ucd\")");
        List<InnerClass> innerClasses = topLevelClass.getInnerClasses();
        for (InnerClass innerClass : innerClasses) {
            innerClass.addAnnotation("@SuppressWarnings(\"ucd\")");
        }
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addAnnotation("@SuppressWarnings({ \"ucd\", \"rawtypes\" })");

        if (isTableHasIdPrimaryKey(introspectedTable)) {
            generateInsertAndReturnKeyMethod(interfaze, introspectedTable);
            generateRemoveMultipleKeysMethod(interfaze, introspectedTable);
            generateMassUpdateMultipleKeysMethod(interfaze, introspectedTable);
        }

        return true;
    }

    private void generateInsertAndReturnKeyMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        method.setName("insertAndReturnKey");
        String paramterType = !isBlobDomainGenerated(introspectedTable) ? introspectedTable
                .getBaseRecordType() : introspectedTable.getRecordWithBLOBsType();
        method.setReturnType(new FullyQualifiedJavaType("java.lang.Integer"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(paramterType), "value"));
        interfaze.addMethod(method);
    }

    private void generateRemoveMultipleKeysMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        method.setName("removeKeysWithSession");
        method.setReturnType(new FullyQualifiedJavaType("void"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.List"), "primaryKeys"));
        interfaze.addMethod(method);
    }

    private void generateMassUpdateMultipleKeysMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        method.setName("massUpdateWithSession");
        method.setReturnType(new FullyQualifiedJavaType("void"));

        String paramterType = !isBlobDomainGenerated(introspectedTable) ? introspectedTable
                .getBaseRecordType() : introspectedTable.getRecordWithBLOBsType();

        method.addParameter(new Parameter(new FullyQualifiedJavaType(paramterType), "record", "@Param(\"record\")"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("java.util.List"), "primaryKeys", "@Param(\"primaryKeys\")"));
        interfaze.addMethod(method);
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                       IntrospectedTable introspectedTable) {
        boolean result = isBlobDomainGenerated(introspectedTable);
        return !result;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        boolean result = isBlobDomainGenerated(introspectedTable);
        return !result;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
                                                                    IntrospectedTable introspectedTable) {
        boolean result = isBlobDomainGenerated(introspectedTable);
        return !result;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
                                                                    IntrospectedTable introspectedTable) {
        boolean result = isBlobDomainGenerated(introspectedTable);
        return !result;
    }

    private boolean isTableHasIdPrimaryKey(IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> primaryKeys = introspectedTable.getPrimaryKeyColumns();
        if ((primaryKeys != null) && (primaryKeys.size() == 1)) {
            IntrospectedColumn column = primaryKeys.get(0);
            return (column.getFullyQualifiedJavaType().getFullyQualifiedName())
                    .equals("java.lang.Integer");
        } else {
            return false;
        }
    }
}
