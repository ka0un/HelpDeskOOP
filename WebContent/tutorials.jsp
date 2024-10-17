<%@ page import="project.features.VideoTutorials.VideoTutorial" %>
<%@ page import="project.features.VideoTutorials.VideoTutorialDButill" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Help Desk Learning Videos</title>

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
            margin: 0;
            background-color: #111; /* Dark background */
            color: #f4f4f9; /* Light text */
        }

        .hero-section {
            background: linear-gradient(to right, rgba(0, 80, 158, 0.8), rgba(0, 63, 125, 0.8)), url('https://example.com/your-hero-image.jpg');
            height: 400px;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }

        .hero-section .play-button {
            font-size: 60px;
            color: white;
            background-color: rgba(255, 255, 255, 0.2);
            border-radius: 50%;
            padding: 20px;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .hero-section .play-button:hover {
            transform: scale(1.1);
        }

        .hero-text {
            position: absolute;
            bottom: 20px;
            left: 40px;
            font-size: 2rem;
        }

        .nav-menu {
            background-color: #1a1a1a; /* Dark gray for menu */
            padding: 10px 20px;
            display: flex;
            justify-content: center;
            gap: 30px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .nav-menu a {
            text-decoration: none;
            color: #f4f4f9; /* Light text */
            font-weight: 500;
        }

        .container {
            margin-top: 40px;
        }

        .video-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }

        .video-card {
            background-color: #1a1a1a; /* Dark gray for video cards */
            border-radius: 10px;
            overflow: hidden;
            transition: transform 0.3s ease;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .video-card:hover {
            transform: translateY(-10px);
        }

        .video-card img {
            width: 100%;
            height: 180px;
            object-fit: cover;
        }

        .video-info {
            padding: 15px;
        }

        .video-title {
            font-size: 1.2rem;
            font-weight: bold;
            color: #f4f4f9; /* Light text */
        }

        .video-meta {
            display: flex;
            justify-content: space-between;
            font-size: 0.9rem;
            color: #bbb; /* Light gray for the meta info */
            margin-bottom: 15px;
        }

        .ranking-sidebar {
            background-color: #1a1a1a; /* Dark gray for sidebar */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .ranking-sidebar h5 {
            font-weight: bold;
            margin-bottom: 15px;
            color: #f4f4f9; /* Light text */
        }

        .ranking-list {
            list-style-type: none;
            padding: 0;
        }

        .ranking-list li {
            padding: 10px 0;
            font-size: 1rem;
            color: #bbb; /* Light gray for the ranking items */
        }

        .ranking-list li span {
            font-weight: bold;
            color: #f4f4f9; /* Light text for the rank numbers */
        }

        .btn-watch {
            background-color: #00509e; /* Consistent blue button */
            color: white;
            border: none;
            width: 100%;
            padding: 10px;
            text-align: center;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        .btn-watch:hover {
            background-color: #003f7d; /* Darker blue hover */
        }
    </style>
</head>

<body>
    <!-- Hero Section -->
    <div class="hero-section">
        <i class="fas fa-play-circle play-button"></i>
        <div class="hero-text">Explore Video Tutorials to Enhance Your Support Experience</div>
    </div>

    <!-- Navigation Menu -->
    <div class="nav-menu">
        <a href="tutorials.jsp">Home</a>
        <a href="tutorials.jsp?cat=Career Development">Career Development</a>
        <a href="tutorials.jsp?cat=IT Security and Best Practices">IT Security</a>
        <a href="tutorials.jsp?cat=Technical Skills">Technical Skills</a>
        <a href="tutorials.jsp?cat=Creative Skills">Creative Skills</a>
        <a href="tutorials.jsp?cat=Professional Development">Professional Development</a>
        
    </div>

    <!-- Content -->
    <div class="container">
        <div class="row">
            <!-- Video Gallery Section -->
            <div class="col-lg-9">
                <div class="video-grid">
                    <%
                        String category = request.getParameter("cat");
                        if(category == null){
                        	category ="Home";
                        }
                    
                        List<VideoTutorial> tutorials = VideoTutorialDButill.getvideosFromCategory(category); // Get all tutorials
                        for (VideoTutorial vt : tutorials) {
                           
                            String thumbnailUrl = "https://img.youtube.com/vi/" + vt.getVideoID() + "/0.jpg";
                    %>
                        <div class="video-card">
                            <img src="<%= thumbnailUrl %>" alt="Video Thumbnail">
                            <div class="video-info">
                                <div class="video-title"><%= vt.getTitle() %></div>
                                <div class="video-meta">
                                    <div><i class="fas fa-eye"></i> <%= vt.getViews() %> views</div>
                                    <div> <%= vt.getTimeSinceUpload() %> </div> <!-- Example rating -->
                                </div>
                                <a href="play.jsp?videoId=<%= vt.getVideoID() %>&id=<%= vt.getId() %>" class="btn-watch">Watch Now</a>
                            </div>
                        </div>
                    <%
  
                        }
                    %>
                </div>
            </div>

            <!-- Ranking Sidebar -->
            <div class="col-lg-3">
                <div class="ranking-sidebar">
                    <h5>Top Ranked Videos</h5>
                    <ul class="ranking-list">
                    <%
					    int r = 1;
					    List<String> ranked = VideoTutorialDButill.getTopRankedVideos();
					    if (ranked != null && !ranked.isEmpty()) {
					        for (String list : ranked) {
					%>
					            <li><span><%= r %>.</span> <%= list %></li>
					<%
					            r++;
					        }
					    } else {
					%>
					        <li>No videos ranked.</li>
					<%
					    }
					%>

                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
