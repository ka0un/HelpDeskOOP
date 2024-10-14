package project.core.api;


import project.core.service.DatabaseServiceImpl;
import project.core.service.PermissionRegisterServiceImpl;
import project.core.service.UserServiceImpl;
import project.core.service.interfaces.DatabaseService;
import project.core.service.interfaces.PermissionRegisterService;
import project.core.service.interfaces.UserService;


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

    public CoreAPI getInstance() {
        if (instance == null) {
            instance = new CoreAPI();
        }
        return instance;
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
