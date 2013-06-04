package com.esofthead.mycollab.vaadin.mvp;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

/** Holds data for one user session. */
@SuppressWarnings("serial")
public class AppData  implements TransactionListener, Serializable {
	
	private Locale locale;   // Current locale
    private transient ResourceBundle bundle;
    private String bundleName; // backup bundle name to reload ResourceBundle after deserializing
    
    private Application app; // For distinguishing between apps
    
    private String userData; // Trivial data model for the user

    private static ThreadLocal<AppData> instance = new ThreadLocal<AppData>();
    
    public AppData(Application app) {
        this.app = app;

        // It's usable from now on in the current request
        instance.set(this);
    }

    @Override
    public void transactionStart(Application application,
                                 Object transactionData) {
        // Set this data instance of this application
        // as the one active in the current thread. 
        if (this.app == application)
            instance.set(this);
    }

    @Override
    public void transactionEnd(Application application,
                               Object transactionData) {
        // Clear the reference to avoid potential problems
        if (this.app == application)
            instance.set(null);
    }

    public static void initLocale(Locale locale, String bundleName) {
    	
    	if (bundleName == null) {
			throw new NullPointerException("bundleName must not be null!");
		}
    	if (locale == null) {
			throw new NullPointerException("locale must not be null!");
		}
    	
    	instance.get().bundleName = bundleName;
        instance.get().locale = locale;
        instance.get().bundle = ResourceBundle.getBundle(bundleName, locale);
    }
    
    public static Locale getLocale() {
        return instance.get().locale;
    }
    
    public static String getMessage(String key) {
    	
    	// if session was serialized the transient ResourceBundle must be initialized again
    	if (instance.get().bundle == null) {
    		initLocale(instance.get().locale, instance.get().bundleName);
    	}
    	
    	return instance.get().bundle.getString(key);
    }

    public static String getUserData() {
        return instance.get().userData;
    }

    public static void setUserData(String userData) {
        instance.get().userData = userData;
    }
    public static Application getApplication() {
    	return instance.get().app;
    }
    
    public static <T> T getSpringBean(Class<T> requiredType) {
    	
    	WebApplicationContext context = (WebApplicationContext) instance.get().app.getContext();
    	
    	org.springframework.web.context.WebApplicationContext springContext = 
    			WebApplicationContextUtils.getRequiredWebApplicationContext(context.getHttpSession().getServletContext());
    	
    	return springContext.getBean(requiredType);
    }
}