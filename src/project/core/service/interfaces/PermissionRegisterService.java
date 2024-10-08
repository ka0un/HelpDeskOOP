package project.core.service.interfaces;

import project.core.model.dao.User;

import java.util.List;

public interface PermissionRegisterService {
    void addPermission(String role, String permission);
    void addPermissions(String role, String... permissions);
    List<String> getPermissions(String role);
    List<String> getRoles(String permission);
    boolean hasPermission(String role, String permission);
    boolean hasPermission(User user, String permission);
    boolean hasPermission(int userId, String permission);
}
