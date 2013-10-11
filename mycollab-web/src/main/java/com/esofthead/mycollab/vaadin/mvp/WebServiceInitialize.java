package com.esofthead.mycollab.vaadin.mvp;

import org.springframework.beans.factory.InitializingBean;

public class WebServiceInitialize implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		ViewManager.init();

	}

}
