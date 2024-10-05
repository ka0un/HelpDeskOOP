package project.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbUtils {
	private DataSource dataSource;

	public DbUtils(DataSource dataSource) {
		this.dataSource = dataSource;
        try {
            initializeDatabase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public void close(Connection myConn, Statement mystmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (mystmt != null) {
				mystmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validateUser(String email, String pw) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		boolean isValid = false;

		try {
			myConn = dataSource.getConnection();
			String sql = "SELECT * FROM users WHERE email=? AND password=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, email);
			myStmt.setString(2, pw);
			myRs = myStmt.executeQuery();

			if (myRs.next()) {
				isValid = true; // User exists
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}

		return isValid;
	}

	public void initializeDatabase() throws Exception {
		String sql = "CREATE TABLE IF NOT EXISTS users ("
				+ "id INT AUTO_INCREMENT PRIMARY KEY, "
				+ "email VARCHAR(255) NOT NULL, "
				+ "password VARCHAR(255) NOT NULL"
				+ ");" +
				"INSERT INTO users (email, password) VALUES ('test@example.com', 'password');";

		try (Connection connection = dataSource.getConnection();
			 Statement statement = connection.createStatement()) {

			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
