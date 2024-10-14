package project.core.api;


import project.core.service.DatabaseServiceImpl;
import project.core.service.PermissionRegisterServiceImpl;
import project.core.service.UserServiceImpl;
import project.core.service.interfaces.DatabaseService;
import project.core.service.interfaces.PermissionRegisterService;
import project.core.service.interfaces.UserService;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;


public class CoreAPI {


    private DatabaseService databaseService;

    private UserService userService;

    private PermissionRegisterService permissionRegisterService;


    private static CoreAPI instance;

    private CoreAPI() {
        databaseService = new DatabaseServiceImpl();
        userService = new UserServiceImpl();
        permissionRegisterService = new PermissionRegisterServiceImpl(userService);
    }

    public static CoreAPI getInstance() {
        if (instance == null) {
            instance = new CoreAPI();
        }
        return instance;
    }

    public boolean isUserLoggedInAndHasPermission(HttpSession session, String permission) {

        if (!isUserLoggedIn(session)) {
            return false;
        }

        String userId = session.getAttribute("userId").toString();
        UserService userService = new UserServiceImpl();
        return permissionRegisterService.hasPermission(Integer.parseInt(userId), permission);
    }

    public boolean isUserLoggedIn(HttpSession session) {
        if (session == null || session.getAttribute("userId") == null) {
            return false;
        }
        return true;
    }

	public DatabaseService getDatabaseService() {
		return databaseService;
	}

	

	public UserService getUserService() {
		return userService;
	}

	

	public PermissionRegisterService getPermissionRegisterService() {
		return permissionRegisterService;
	}

	


}
