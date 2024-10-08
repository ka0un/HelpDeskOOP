package project.core.service;

import project.core.api.CoreAPI;
import project.core.model.dao.User;
import project.core.service.interfaces.PermissionRegisterService;
import project.core.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionRegisterServiceImpl implements PermissionRegisterService {

    UserService userService;
    private static HashMap<String, String> permissions = new HashMap<>();

    static{
        //default permissions configuration
        permissions.put("admin", "admin");
    }

    public PermissionRegisterServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void addPermission(String role, String permission) {
        permissions.put(role, permission);
    }

    @Override
    public void addPermissions(String role, String... permissions) {
        for (String permission : permissions) {
            addPermission(role, permission);
        }
    }

    @Override
    public List<String> getPermissions(String role) {

        List<String> perms = new ArrayList<>();

        for (String key : permissions.keySet()) {
            if (key.equals(role)) {
                perms.add(permissions.get(key));
            }
        }

        return perms;
    }

    @Override
    public List<String> getRoles(String permission) {

        List<String> roles = new ArrayList<>();

        for (String key : permissions.keySet()) {
            if (permissions.get(key).equals(permission)) {
                roles.add(key);
            }
        }

        return roles;
    }

    @Override
    public boolean hasPermission(String role, String permission) {
        for (String key : permissions.keySet()) {
            if (key.equals(role) && permissions.get(key).equals(permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(User user, String permission) {
        return hasPermission(user.getRole(), permission);
    }

    @Override
    public boolean hasPermission(int userId, String permission) {

        try {
            User user = userService.getUser(userId);
            return hasPermission(user.getRole(), permission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
