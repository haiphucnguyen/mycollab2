package org.vaadin.addon.customfield.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.ApplicationServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

/**
 * A simple demonstration application for {@link CustomField}.
 * 
 * @author Henri Sara
 */
public class CustomFieldApplication extends Application {

    @Override
    public void init() {
        final Window mainWindow = new Window("Custom Field Example");

        TabSheet tabsheet = new TabSheet();

        tabsheet.addTab(new CityFieldExample(), "Wrapping a Field", null);
        tabsheet.addTab(new ConversionExample(), "Conversions", null);
        tabsheet.addTab(new AddressFormExample(), "Address Form", null);
        tabsheet.addTab(new NestedFormExample(false), "Nested Forms", null);
        tabsheet.addTab(new NestedFormExample(true), "Embedded Form", null);
        tabsheet.addTab(new BooleanFieldExample(), "Boolean Field", null);

        mainWindow.addComponent(tabsheet);

        setMainWindow(mainWindow);
    }
    
    
	public static void main(String[] args) throws Exception {
		startInEmbeddedJetty();
	}

	public static Server startInEmbeddedJetty() throws Exception {
		Server server = new Server(8888);
		ServletContextHandler handler = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		ServletHolder servletHolder = handler.addServlet(ApplicationServlet.class, "/*");
		servletHolder.setInitParameter("application", CustomFieldApplication.class.getName());
		server.setHandler(handler);
		server.start();
		return server;
	}

}
