package com.esofthead.mycollab.vaadin.mvp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;

public class UrlResolver {
	private static Logger log = LoggerFactory.getLogger(UrlResolver.class);

	private Map<String, UrlResolver> subResolvers;

	public void addSubResolver(String key, UrlResolver subResolver) {
		if (subResolvers == null) {
			subResolvers = new HashMap<String, UrlResolver>();
		}
		subResolvers.put(key, subResolver);
	}

	public void handle(String... params) {
		try {
			if (params != null && params.length > 0) {
				String key = params[0];
				if (subResolvers == null) {
					handlePage(params);
				} else {
					UrlResolver urlResolver = subResolvers.get(key);
					if (urlResolver == null) {
						throw new MyCollabException(
								"Can not register resolver key " + key
										+ " for Resolver: " + this);
					} else {
						preHandle();
						List<String> paramList = Arrays.asList(params).subList(
								1, params.length);
						log.debug("Handle url in resolver: " + urlResolver);
						urlResolver.handle(paramList.toArray(new String[0]));
					}
				}
			} else {
				handlePage(new String[0]);
			}
		} catch (Exception e) {
			log.error("Error while navigation", e);
			AppContext
					.getApplication()
					.getMainWindow()
					.showNotification("Information",
							"The record is not existed",
							Window.Notification.TYPE_HUMANIZED_MESSAGE);
		}
	}

	protected void preHandle() {
	}

	protected void handlePage(String... params) {
		log.debug("Handle page: " + this + " with params: "
				+ BeanUtility.printBeanObj(params));
	}
}
