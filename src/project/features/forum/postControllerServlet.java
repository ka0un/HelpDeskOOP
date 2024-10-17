package project.features.forum;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class postControllerServlet
 */
@WebServlet("/postControllerServlet")
public class postControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private postDbUtil postDbUtil1;
    
@Resource(name="jdbc/helpdeskDB") private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
        postDbUtil1 = new postDbUtil(datasource); 
			
			
			
		}
		catch(Exception e) {
			throw new ServletException();
		}
	}
	
    
    public postControllerServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String theCommand= request.getParameter("command");
		if(theCommand == null) {
			theCommand= "SHOW";
		}
		switch(theCommand) {
		case "SAVE":
			try {
				addPost(request, response);
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//case"SHOW":
			//try {
				//showPost(request, response, theCommand);
			//} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			//break;
		
		
		
	}}


	void showPost(HttpServletRequest request, HttpServletResponse response, String threadId)
		    throws Exception {

		
		
	    System.out.println("Retrieving posts...");
	    
	    String tid = threadId;
	    
		List<Post> posts = postDbUtil1.getPosts(tid);
	    System.out.println("Posts size: " + posts.size()); // Add this line

		
		request.setAttribute("POST_LIST", posts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("forumPost.jsp");
		dispatcher.forward(request, response);
	}


	private void addPost(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		HttpSession session = request.getSession();
		

		String user= (String) session.getAttribute("username");
		LocalDateTime created_at = LocalDateTime.now();
		String content = request.getParameter("reply");
		String t_id = request.getParameter("threadId");
		int ThreadId = Integer.parseInt(t_id);
		
		Post newPost = new Post(ThreadId, user, content, created_at);
		postDbUtil1.addPost(newPost);
		 
		response.sendRedirect("threadControllerServlet?command=LOAD&threadId="+t_id);
		
		
		
	}

}
