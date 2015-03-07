package de.codefest8.gamification;

import de.codefest8.gamification.controller.RestfulController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by torsten on 08/02/15.
 */
public class App {
    public static void main(String[] args) throws Exception {

        Server server = configureServer();

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }


    }

    private static Server configureServer() {

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(RestfulController.class.getPackage().getName());
        resourceConfig.register(JacksonFeature.class);

        ServletContainer servletContainer = new ServletContainer(resourceConfig);

        ServletHolder servletHolder = new ServletHolder(servletContainer);
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.classnames", RestfulController.class.getCanonicalName());

        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.addServlet(servletHolder, "/*");
        server.setHandler(contextHandler);

        return server;
    }
}
