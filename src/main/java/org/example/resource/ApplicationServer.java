package org.example.resource;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ApplicationServer {
    public static void main(String[] args) throws Exception {
        // Port du serveur
        Server server = new Server(8181);

        // Configurer Jersey
        ResourceConfig config = new ResourceConfig();
        config.register(UserResource.class);
        config.register(JacksonFeature.class); // pour JSON

        // Attacher Jersey à Jetty
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        context.addServlet(servlet, "/*");

        server.setHandler(context);

        server.start();
        System.out.println("Server started at http://localhost:8181/");
        server.join();
    }
}
