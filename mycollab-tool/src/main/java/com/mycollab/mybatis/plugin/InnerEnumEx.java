package com.mycollab.mybatis.plugin;

import java.util.Iterator;

import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;

public class InnerEnumEx extends InnerEnum {

	public InnerEnumEx(FullyQualifiedJavaType type) {
		super(type);
	}

	public String getFormattedContent(int indentLevel) {
		StringBuilder sb = new StringBuilder();

		addFormattedJavadoc(sb, indentLevel);
		addFormattedAnnotations(sb, indentLevel);

		OutputUtilities.javaIndent(sb, indentLevel);
		if (getVisibility() == JavaVisibility.PUBLIC) {
			sb.append(getVisibility().getValue());
		}

		if (isStatic()) {
			sb.append("static ");
		}

		sb.append("enum "); //$NON-NLS-1$
		sb.append(getType().getShortName());

		if (getSuperInterfaceTypes().size() > 0) {
			sb.append(" implements "); //$NON-NLS-1$

			boolean comma = false;
			for (FullyQualifiedJavaType fqjt : getSuperInterfaceTypes()) {
				if (comma) {
					sb.append(", "); //$NON-NLS-1$
				} else {
					comma = true;
				}

				sb.append(fqjt.getShortName());
			}
		}

		sb.append(" {"); //$NON-NLS-1$
		indentLevel++;

		Iterator<String> strIter = getEnumConstants().iterator();
		while (strIter.hasNext()) {
			OutputUtilities.newLine(sb);
			OutputUtilities.javaIndent(sb, indentLevel);
			String enumConstant = strIter.next();
			sb.append(enumConstant);

			if (strIter.hasNext()) {
				sb.append(',');
			} else {
				sb.append(';');
			}
		}

		if (getFields().size() > 0) {
			OutputUtilities.newLine(sb);
		}

		Iterator<Field> fldIter = getFields().iterator();
		while (fldIter.hasNext()) {
			OutputUtilities.newLine(sb);
			Field field = fldIter.next();
			sb.append(field.getFormattedContent(indentLevel, null));
			if (fldIter.hasNext()) {
				OutputUtilities.newLine(sb);
			}
		}

		if (getMethods().size() > 0) {
			OutputUtilities.newLine(sb);
		}

		Iterator<Method> mtdIter = getMethods().iterator();
		while (mtdIter.hasNext()) {
			OutputUtilities.newLine(sb);
			Method method = mtdIter.next();
			sb.append(method.getFormattedContent(indentLevel, false, null));
			if (mtdIter.hasNext()) {
				OutputUtilities.newLine(sb);
			}
		}

		if (getInnerClasses().size() > 0) {
			OutputUtilities.newLine(sb);
		}

		Iterator<InnerClass> icIter = getInnerClasses().iterator();
		while (icIter.hasNext()) {
			OutputUtilities.newLine(sb);
			InnerClass innerClass = icIter.next();
			sb.append(innerClass.getFormattedContent(indentLevel, null));
			if (icIter.hasNext()) {
				OutputUtilities.newLine(sb);
			}
		}

		if (getInnerEnums().size() > 0) {
			OutputUtilities.newLine(sb);
		}

		Iterator<InnerEnum> ieIter = getInnerEnums().iterator();
		while (ieIter.hasNext()) {
			OutputUtilities.newLine(sb);
			InnerEnum innerEnum = ieIter.next();
			sb.append(innerEnum.getFormattedContent(indentLevel, null));
			if (ieIter.hasNext()) {
				OutputUtilities.newLine(sb);
			}
		}

		indentLevel--;
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append('}');

		return sb.toString();
	}
}
