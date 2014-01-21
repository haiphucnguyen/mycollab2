package com.esofthead.mycollab.vaadin;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabSession {

	private static Logger log = LoggerFactory.getLogger(MyCollabSession.class);

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void putVariable(String key, Object value) {
		log.debug("Put variable {}, value {} to session {}", new Object[] {
				"mycollabapp" + key, value,
				VaadinSession.getCurrent().getSession().getId() });
		VaadinSession.getCurrent().getSession()
				.setAttribute("mycollabapp-" + key, value);
	}

	/**
	 * 
	 * @param key
	 */
	public static void removeVariable(String key) {
		try {
			VaadinSession.getCurrent().getSession()
					.removeAttribute("mycollabapp-" + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clearVariables() {
		WrappedSession session = VaadinSession.getCurrent().getSession();
		Set<String> attributeNames = session.getAttributeNames();
		for (String attributeName : attributeNames) {
			if (attributeName.startsWith("mycollabapp-")) {
				session.removeAttribute(attributeName);
			}
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Object getVariable(String key) {
		log.debug("Get variable {}", "mycollabapp-" + key);
		return VaadinSession.getCurrent().getSession()
				.getAttribute("mycollabapp-" + key);
	}

}
