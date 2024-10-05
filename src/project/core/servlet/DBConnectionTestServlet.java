package project.core.servlet;

import project.core.service.DatabaseServiceImpl;
import project.core.service.interfaces.DatabaseService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBConnectionTestServlet extends HttpServlet {

    DatabaseService databaseService = DatabaseServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        databaseService.init();

        // Step 1: Set the content type for the response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = databaseService.getConnection()) {
            // Step 2: Check if connection is valid
            if (conn != null && !conn.isClosed()) {
                out.println("<h3>Database connected successfully!</h3>");
            } else {
                out.println("<h3>Failed to connect to the database.</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
