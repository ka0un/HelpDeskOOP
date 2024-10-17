<%@ page import="project.features.VideoTutorials.VideoTutorial" %>
<%@ page import="project.features.VideoTutorials.VideoTutorialDButill" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Play Video</title>
    
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome for Icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #111; /* Dark background to match the theme */
            color: #f4f4f9; /* Light text for readability */
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #3a7bd5; /* Gradient color to match the primary theme */
            padding: 20px;
            color: white;
            text-align: center;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .container {
            display: flex;
            max-width: 1200px;
            margin: 30px auto;
            padding: 20px;
            gap: 20px;
        }

        .video-player {
            flex: 2;
            padding-right: 20px;
        }

        iframe {
            width: 100%;
            height: 450px;
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        .related-videos {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .video-box {
            background-color: #1a1a1a; /* Dark gray background for cards */
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2); /* Consistent box shadow with the main page */
            transition: box-shadow 0.3s ease, transform 0.3s ease;
        }

        .video-box:hover {
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.4);
            transform: translateY(-5px); /* Smooth hover effect */
        }

        .video-box img {
            width: 100%;
            height: 150px;
            object-fit: cover;
        }

        .video-info {
            padding: 15px;
        }

        .video-title {
            font-size: 1.1rem;
            font-weight: bold;
            color: #f4f4f9; /* Light text */
            margin-bottom: 8px;
        }

        .video-views {
            font-size: 0.85rem;
            color: #bbb; /* Subtle gray for views */
        }

        /* Buttons */
        .button {
            display: block;
            background-color: #3a7bd5; /* Primary blue color for buttons */
            color: white;
            text-align: center;
            padding: 10px;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 10px;
            font-size: 0.9rem;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #2e62aa; /* Darker blue for hover effect */
        }
    </style>
</head>
<body>

    <!-- Header Section -->
    <div class="header">Help Desk Learning Videos - Video Player</div>

    <!-- Main Content Section -->
    <div class="container">
        <!-- Video Player Section -->
       <div class="video-player">
    <% 
        String videoId = request.getParameter("videoId");
        
        // Ensure id is not null or empty before using it
        if (videoId != null && !videoId.isEmpty()) { 
    %>
        <iframe
            src="https://www.youtube.com/embed/<%= videoId %>?controls=1" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowfullscreen>
        </iframe>
        <%
            // Call updateViews method only if id is valid
            VideoTutorialDButill.updateViews(videoId);
        } else {
            out.println("Error: videoId is required."); // Error handling for missing videoId
        }
        %>
</div>


        <!-- Related Videos Section -->
        <div class="related-videos">
            <!-- Related Video 1 -->
            
        
            
       <%
           String idString = request.getParameter("id"); 
           int id = Integer.parseInt(idString);
       
           
           
           List<VideoTutorial> tutorials = VideoTutorialDButill.getSampleVideoDetails(id); // Get all tutorials
           for (VideoTutorial vt : tutorials) {
        	   
           String thumbnailUrl = "https://img.youtube.com/vi/" + vt.getVideoID() + "/0.jpg";
           
           
      %>
            
            <div class="video-box">
                <a href="play.jsp?videoId=sample1">
                     <img src="<%= thumbnailUrl %>" alt="Video Thumbnail">
                </a>
                <div class="video-info">
                    <a href="play.jsp?videoId=sample1">
                        <div class="video-title"> <%= vt.getTitle() %> </div>
                    </a>
                    <div class="video-views"><%= vt.getViews() %> Views</div>
                    <a class="button" href="play.jsp?videoId=<%= vt.getVideoID() %>&id=<%= vt.getId() %>">Watch Now</a>
                </div>
            </div>
            
       <%
           }
       %>
            
            
            
            

        </div>
    </div>

</body>
</html>
