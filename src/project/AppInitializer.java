package project;


import project.core.util.Logger;
import project.features.tickets.config.PermissionConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.log("Starting Open Help Desk application...");
        PermissionConfig.configurePermissions();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}