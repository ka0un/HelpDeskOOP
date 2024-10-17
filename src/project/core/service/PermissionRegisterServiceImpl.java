package project.core.service;

import project.core.api.CoreAPI;
import project.core.model.dao.User;
import project.core.service.interfaces.PermissionRegisterService;
import project.core.service.interfaces.UserService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PermissionRegisterServiceImpl implements PermissionRegisterService {

    UserService userService;
    private static ConcurrentHashMap<String, List<String>> permissions = new ConcurrentHashMap<>();

    static{
        //default permissions configuration
        permissions.put("admin", List.of("admin"));
    }

    public PermissionRegisterServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void addPermission(String role, String permission) {
        if (permissions.containsKey(role)) {
            List<String> perms = permissions.get(role);
            perms = new ArrayList<>(perms); // Ensure the list is mutable
            perms.add(permission);
            permissions.put(role, perms);
            return;
        }

        permissions.put(role, new ArrayList<>(List.of(permission))); // Use mutable list
    }

    @Override
    public void addPermissions(String role, String... permissions) {
        for (String permission : permissions) {
            addPermission(role, permission);
        }
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
            for (String perm : permissions.get(key)) {
                if (key.equals(role) && perm.equalsIgnoreCase(permission)) {
                    return true;
                }
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
