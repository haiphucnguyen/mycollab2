package com.esofthead.mycollab.esb;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public class BeanProxyBuilder {

	public <S> S build(String endpoint, Class<S> buildCls) {
		try {
			CamelContext camelContext = ApplicationContextUtil
					.getBean(CamelContext.class);
			return new ProxyBuilder(camelContext).endpoint(endpoint).build(
					buildCls);
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}
