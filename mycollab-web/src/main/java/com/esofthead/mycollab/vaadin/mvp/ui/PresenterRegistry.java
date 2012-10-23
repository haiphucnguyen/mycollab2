package com.esofthead.mycollab.vaadin.mvp.ui;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class PresenterRegistry {

	private final Logger log = LoggerFactory.getLogger(PresenterRegistry.class);

	@Autowired
	private ApplicationContext context;
	
	@SuppressWarnings("rawtypes")
	public void registerPresenter() {

//		log.debug("Register presenter");
//		
//		Map<String, AbstractPresenter> presenterMap = context.getBeansOfType(AbstractPresenter.class, true, false);
//		
//		if (log.isDebugEnabled()) {
//			
//			for (Entry<String, AbstractPresenter> entry : presenterMap.entrySet()) {
//				log.debug("Registered Presenter: {}, {}", entry.getKey(), entry.getValue());				
//			}
//		}
	}
}
