package project.core.servlet;

import project.core.service.UserServiceImpl;
import project.core.service.interfaces.UserService;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	@Resource(name = "jdbc/helpdeskDB")
	private DataSource datasource;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userService = new UserServiceImpl();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theCommand = request.getParameter("command");
		switch (theCommand) {
			case "CHECK":
				try {
					checkUser(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "CREATE_ACCOUNT":
				try {
					createAccount(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
	}

	private void checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");

		boolean isValidUser = userService.validateUser(email, pw);

		if (isValidUser) {
			HttpSession session = request.getSession();
			session.setAttribute("username", email);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("errorMessage", "Invalid username or password.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int userId = userService.createAccount(userName, email, password);

		if (userId > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("username", email);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("errorMessage", "Account creation failed.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		}
	}
}
