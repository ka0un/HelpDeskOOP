package project.core.service.interfaces;

import project.core.model.dao.User;

import java.util.List;

public interface UserService {
    int createAccount(String userName, String email, String password) throws Exception;
    boolean validateUser(String email, String pw) throws Exception;
    String getUserName(int id) throws Exception;
    int getUserId(String email) throws Exception;
    String getUserEmail(int id) throws Exception;
    User getUser(int id) throws Exception;
    List<User> getAllUsers() throws Exception;
}
