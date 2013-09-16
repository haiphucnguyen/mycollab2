package com.esofthead.mycollab.reporting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;

public class SimpleColumnInjectionMap {
	private static Map<Class, List<? extends ColumnInjectionRenderer>> mapInjection = new HashMap<Class, List<? extends ColumnInjectionRenderer>>();

	static {
		mapInjection.put(SimpleAccount.class, Arrays
				.asList(new HyperLinkColumnInjectionRenderer("accountname",
						new AccountExpression())));
	}

	public static List<? extends ColumnInjectionRenderer> getRenderers(Class cls) {
		return mapInjection.get(cls);
	}

	private static class AccountExpression extends
			AbstractSimpleExpression<String> {

		@Override
		public String evaluate(ReportParameters reportParameters) {
			return "http://www.mycollab.com";
		}

	}
}
