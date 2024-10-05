package project.core.service.interfaces;

import java.sql.Connection;

public interface DatabaseService {
    void init();
    Connection getConnection();
}
