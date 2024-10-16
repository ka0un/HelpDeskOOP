package project.features.tickets.config;

import project.core.api.CoreAPI;
import project.core.service.interfaces.PermissionRegisterService;

public class PermissionConfig {

    public static void configurePermissions() {
        CoreAPI coreAPI = CoreAPI.getInstance();
        PermissionRegisterService permissionService = coreAPI.getPermissionRegisterService();

        // Add necessary permissions for tickets feature
        permissionService.addPermission("admin", "view_tickets");
        permissionService.addPermission("user", "view_tickets");
//kavindu
    }
}
