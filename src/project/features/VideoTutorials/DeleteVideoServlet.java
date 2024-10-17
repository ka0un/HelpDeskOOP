package project.features.VideoTutorials;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteVideoServlet
 */
@WebServlet("/DeleteVideoServlet")
public class DeleteVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String idString = request.getParameter("id"); 
         int id = Integer.parseInt(idString);
         
         boolean isTrue = VideoTutorialDButill.deleteVideo(id);
         
         if (isTrue == true) {
	            RequestDispatcher dis = request.getRequestDispatcher("tutorials.jsp");
	            dis.forward(request, response);
	     } else {
	            RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
	            dis2.forward(request, response);
	     }
	}

}
