package project.features.VideoTutorials;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateVideoTutorial")
public class UpdateVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
	        String title = request.getParameter("title");
	        String url = request.getParameter("url");
	        String category = request.getParameter("category");
	        String type = request.getParameter("videoType");
	        
	        String idString = request.getParameter("id"); 
            int id = Integer.parseInt(idString);
        
	        
		    boolean isTrue = VideoTutorialDButill.updateVideo( id, title , extractVideoId(url) , category , type);
	       
	        if (isTrue == true) {
	            RequestDispatcher dis = request.getRequestDispatcher("tutorials.jsp");
	            dis.forward(request, response);
	        } else {
	            RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
	            dis2.forward(request, response);
	        }
	        
	 }
	        
	        
	        private String extractVideoId(String youtubeUrl) {
	            String videoId = null;

	            // Check if the URL contains "watch?v="
	            if (youtubeUrl.contains("watch?v=")) {
	                int start = youtubeUrl.indexOf("v=") + 2;
	                int end = youtubeUrl.indexOf("&", start);  // Check if there's a parameter after the video ID
	                if (end == -1) {
	                  
	                    videoId = youtubeUrl.substring(start);
	                } else {
	                    // Extract the video ID part only
	                    videoId = youtubeUrl.substring(start, end);
	                }
	            }
	            // Handle shortened URLs like https://youtu.be/VIDEO_ID
	            else if (youtubeUrl.contains("youtu.be/")) {
	                int start = youtubeUrl.indexOf("youtu.be/") + 9;
	                int end = youtubeUrl.indexOf("?", start);  // Check if there are additional parameters
	                if (end == -1) {
	                    // No additional parameters, take the rest of the URL after "youtu.be/"
	                    videoId = youtubeUrl.substring(start);
	                } else {
	                    // Extract the video ID part only
	                    videoId = youtubeUrl.substring(start, end);
	                }
	            }

	            return videoId;
	        }
       
   
 

}
