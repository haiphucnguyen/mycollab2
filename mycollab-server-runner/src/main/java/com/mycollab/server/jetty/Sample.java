package com.mycollab.server.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

/**
 * @author Hai Nguyen
 */
public class Sample {
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setIdleTimeout(360000);
        serverConnector.setPort(8080);
        server.start();
        server.join();
    }
}
