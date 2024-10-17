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
 * Servlet implementation class threadControllerServlet
 */
@WebServlet("/threadControllerServlet")
public class threadControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private threadDbUtil threadDbUtil1;
	private postDbUtil postDbUtil1;
	
	@Resource(name="jdbc/helpdeskDB") private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			threadDbUtil1 = new threadDbUtil(datasource);
			postDbUtil1 = new postDbUtil(datasource);
			
		}
		catch(Exception e) {
			throw new ServletException();
		}
	}
	
       
    
    public threadControllerServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//System.out.print("3");
		try {
			String theCommand= request.getParameter("command");
			switch(theCommand) {
			case "LIST":
				listThreads(request, response);
				break;
		
			case "LOAD":
				loadThread(request,response);
				break;
		
			
			
			
			}} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	



	
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String theCommand= request.getParameter("command");
		
		if(theCommand == null) {
			theCommand= "LIST";
		}
		switch(theCommand) {
		case "LIST":
			listThreads(request, response);
			break;
		case "ADD":
			addThread(request,response);
			break;
		case "LOAD":
			loadThread(request,response);
			break;
		case "MY_VIEW":
			ViewThread(request,response);
			break;
		case "UPDATE":
			updateThread(request,response);
			break;
		case "UPDATE2":
			System.out.println(4333);
			update2Thread(request,response);
			break;
		case "DELETE":
			System.out.println(432);
			deleteThread(request,response);
			break;
		
		default:
			listThreads(request, response);
			break;
		
	}
		}
		catch(Exception e) {
			throw new ServletException(e);}
		}



	private void deleteThread(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		String theThreadId = request.getParameter("threadId");
		threadDbUtil1.deleteThread(theThreadId);
		
		response.sendRedirect("threadOptions.jsp");
		
	}



	private void update2Thread(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String theThreadId = request.getParameter("threadId");
		String title=request.getParameter("threadName");
		System.out.print("ssd");
		HttpSession session = request.getSession();
		
		String user= (String) session.getAttribute("username");
		LocalDateTime created_at = LocalDateTime.now();
		String content = request.getParameter("content");
		String status = "updated";
		System.out.print("sd678");
		int ThreadId = Integer.parseInt(theThreadId);
		thread thread1 = new thread(ThreadId,title,user, created_at, status,content);
				
				
		threadDbUtil1.updateThread(thread1);
		response.sendRedirect("threadOptions.jsp");
		
		
		
		
		
	}



	private void updateThread(HttpServletRequest request, HttpServletResponse response) throws Exception {
String theThreadId = request.getParameter("threadId");
		
		thread thread1 = threadDbUtil1.getThread(theThreadId);
		
		System.out.print("sd");
		request.setAttribute("THE_THREAD", thread1);
		request.setAttribute(theThreadId, theThreadId);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateThread.jsp");
		dispatcher.forward(request, response);
		
		
		
	}



	private void listThreads(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//System.out.println("Retrieving threads...");
		List<thread> threads = threadDbUtil1.getThreads();
		System.out.println("Number of threads retrieved: " + threads.size());
		//for (thread t : threads) {
		 //   System.out.println("ID: " + t.getId() + ", Title: " + t.getTitle() + ", Content: " + t.getContent());
		//}
		
		
		
		request.setAttribute("THREAD_LIST", threads);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("list-forum.jsp");
		
		dispatcher.forward(request, response);
		
	}


	private void addThread(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		HttpSession session = request.getSession();
		
		String title=request.getParameter("threadName");
		
		String user= (String) session.getAttribute("username");
		LocalDateTime created_at = LocalDateTime.now();
		String content = request.getParameter("content");
		String status = "new";
		
		
		
		thread theThread = new thread(title, user, created_at, status,
				content);
		
		threadDbUtil1.addThread(theThread);
		
        response.sendRedirect("list-forum.jsp");

		
		
		
	}
	
	private void loadThread(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.print("3");
		String theThreadId = request.getParameter("threadId");
		
		thread thread1 = threadDbUtil1.getThread(theThreadId);
		
		
		request.setAttribute("THE_THREAD", thread1);
		request.setAttribute(theThreadId, theThreadId);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/forumPost.jsp");
		dispatcher.forward(request, response);
		
		//postControllerServlet pc = (postControllerServlet) getServletContext().getAttribute("postControllerServlet");
		//if (pc == null) {
		  //  pc = new postControllerServlet();
		    //pc.init(); // Manually call init
		 //   getServletContext().setAttribute("postControllerServlet", pc); // Store it in context
		//}
		//pc.showPost(request, response, theThreadId);
		
		
		
		
	}
	

private void ViewThread(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	HttpSession session = request.getSession();
	
	
	
	String user= (String) session.getAttribute("username");

	
	
	
	thread theThread = new thread(user);
	
	List<thread> threads = threadDbUtil1.viewThread(user);
	System.out.println(threads.size());
	
	request.setAttribute("THREAD_LIST", threads);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/myThreads.jsp");
	dispatcher.forward(request, response);

}}
