package com.esofthead.mycollab.vaadin.mvp;

import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class WebServiceInitialize implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		ViewManager.init();
		PresenterResolver.init();
		BeanItemCustomExt.init();
	}

}
