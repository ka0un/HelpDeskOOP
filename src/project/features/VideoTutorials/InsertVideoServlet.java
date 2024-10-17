package project.features.VideoTutorials;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertVideoServlet")
public class InsertVideoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String url = request.getParameter("youtubeUrl");
        String category = request.getParameter("category");
        
        boolean isTrue;
        
        // Insert the video using the extracted video ID
        isTrue = VideoTutorialDButill.insertVideo(title, extractVideoId(url),category);
        
        // Forward to the appropriate JSP based on success or failure
        if (isTrue == true) {
            RequestDispatcher dis = request.getRequestDispatcher("tutorials.jsp");
            dis.forward(request, response);
        } else {
            RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
            dis2.forward(request, response);
        }
    }

    // Improved method to extract video ID from different YouTube URL formats
    private String extractVideoId(String youtubeUrl) {
        String videoId = null;

        // Check if the URL contains "watch?v="
        if (youtubeUrl.contains("watch?v=")) {
            int start = youtubeUrl.indexOf("v=") + 2;
            int end = youtubeUrl.indexOf("&", start);  // Check if there's a parameter after the video ID
            if (end == -1) {
                // No additional parameters, take the rest of the URL after "v="
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
