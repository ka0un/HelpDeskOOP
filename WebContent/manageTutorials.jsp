<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="project.features.VideoTutorials.VideoTutorialDButill" %>
<%@ page import="project.features.VideoTutorials.VideoTutorial" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Input and Display</title>

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
            margin: 0; /* Reset margin */
            padding: 10px; /* Add some padding */
            background-color: #111;
            color: #f4f4f9;
            display: flex;
            flex-direction: column; /* Change to column direction */
            min-height: 100vh; /* Minimum height */
        }

        .container {
            display: flex;
            align-items: flex-start;
            justify-content: center;
            gap: 30px;
            padding: 30px;
            max-width: 1200px;
            background-color: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            flex-grow: 1; /* Allow container to grow */
            overflow: auto; /* Enable scrolling if content overflows */
        }

        .input-form, .display-form {
            background-color: #2a2a2a; /* Darker background for forms */
            padding: 30px; /* Increased padding */
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
            transition: transform 0.2s; /* Animation for hover effect */
        }
        .display-form {
            margin-bottom: 20px;
        }

        .input-form:hover, .display-form:hover {
            transform: translateY(-5px); /* Subtle lift on hover */
        }

        .input-title, .display-title {
            font-size: 1.8rem; /* Increased font size for titles */
            margin-bottom: 20px; /* Increased bottom margin */
            color: #fff; /* Title color */
        }

        .input-field, .dropdown {
            width: 100%;
            padding: 12px; /* Increased padding */
            margin-bottom: 15px; /* Increased bottom margin */
            border-radius: 5px; /* Rounded corners */
            border: 1px solid #ccc; /* Light border */
            background-color: #3b3b3b; /* Input background */
            color: #f4f4f9; /* Input text color */
        }

        .btn-submit, .btn-update, .btn-delete {
            background-color: #007bff; /* Bootstrap primary color */
            color: white;
            border: none;
            padding: 12px; /* Increased padding */
            text-align: center;
            border-radius: 5px;
            transition: background-color 0.3s ease, transform 0.2s; /* Added transition */
            width: 100%;
            margin-top: 15px; /* Increased top margin */
        }

        .btn-submit:hover, .btn-update:hover, .btn-delete:hover {
            background-color: #0056b3; /* Darker on hover */
            transform: translateY(-2px); /* Lift on hover */
        }

        .btn-update {
            background-color: #28a745; /* Success color */
        }

        .btn-delete {
            background-color: #dc3545; /* Danger color */
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse; /* Collapse borders */
        }

        th, td {
            text-align: left;
            padding: 12px; /* Increased padding */
        }

        th {
            background-color: #00509e; /* Header background color */
            color: white;
            border-bottom: 2px solid #004085; /* Added bottom border */
        }

        tr:nth-child(even) {
            background-color: #333; /* Alternate row color */
        }

        .radio-group {
            display: flex;
            gap: 15px; /* Increased gap */
            align-items: center;
        }

        /* Flex container for layout */
        .form-container {
            display: flex;
            gap: 30px; /* Increased gap */
            width: 100%;
        }

        .left-section {
            flex: 0.4; /* 40% width */
        }

        .right-section {
            flex: 0.6; /* 60% width */
        }

        /* Add New Video Tutorial button */
        .btn-add-video {
            background-color: #007bff; /* Bootstrap primary color */
            color: white;
            border: none;
            padding: 15px; /* Increased padding */
            border-radius: 5px;
            width: 100%;
            text-align: center;
            font-size: 1.2rem;
            margin-bottom: 20px; /* Added bottom margin */
            transition: background-color 0.3s ease, transform 0.2s; /* Added transition */
        }

        .btn-add-video:hover {
            background-color: #0056b3; /* Darker on hover */
            transform: translateY(-2px); /* Lift on hover */
        }

        .video-info {
            display: flex; 
            justify-content: space-between; 
            color: #f4f4f9; /* Color to match the theme */
            margin-bottom: 15px; /* Space below the info line */
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="form-container">
            <!-- Left Section for Category and Video List -->
            <div class="left-section">
                <!-- Select Category Form -->
                <a href = "insertVideoTutorials.jsp"> <button class="btn-add-video">Add New Video Tutorial</button></a>
                <div class="display-form">
                    <div class="display-title">Select Video Tutorial Category</div>
                    <form action="manageTutorials.jsp" method="get">
                        <select id="category" class="dropdown" name="category" required>
                            <option value="">Select a category</option>
                            <option value="Career Development">Career Development</option>
                            <option value="IT Security and Best Practices">IT Security and Best Practices</option>
                            <option value="Technical Skills">Technical Skills</option>
                            <option value="Creative Skills">Creative Skills</option>
                            <option value="Professional Development">Professional Development</option>
                        </select>
                        <button type="submit" class="btn-submit">Submit</button>
                    </form>
                </div>

                <!-- Video List Display -->
                <div class="display-form">
                    <div class="display-title">Video Tutorials</div>

                    <%
                        String selectedCategory = request.getParameter("category");
                        HashMap<String, Integer> filtered = VideoTutorialDButill.videosOfCategory(selectedCategory);
                        
                        if (filtered != null && !filtered.isEmpty()) {
                    %>
                        <table class="table table-dark">
                            <thead>
                                <tr>
                                    <th>Tutorial Name</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Map.Entry<String, Integer> entry : filtered.entrySet()) {
                                        String videoTitle = entry.getKey();
                                        int id = entry.getValue();
                                %>
                                    <tr>
                                        <td><strong><%= videoTitle %></strong></td>
                                        <td>
                                            <a href="manageTutorials.jsp?listid=<%= id %>" class="btn btn-primary">Check</a>
                                        </td>
                                    </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    <%
                        } else {
                    %>
                        <div class="alert alert-warning">No videos available for this category.</div>
                    <%
                        }
                    %>
                </div>
            </div>
                
                 <%
                 
                       if (request.getParameter("listid") != null) {
                    	   
                       
                    	   String idString = request.getParameter("listid"); 
                           int vid = Integer.parseInt(idString);
                       
                           List<VideoTutorial> tutorials = VideoTutorialDButill.getvideoDetailFromId(vid); // Get all tutorials
                       
                           for (VideoTutorial vt : tutorials) {
                            
                    %>
            <!-- Right Section for Add Video Tutorial -->
            <div class="right-section">
                <!-- Add Video Tutorial Form -->
                <div class="input-form">
                    <div class="input-title">Update or Delete Video Tutorial</div>
                    <form action="updateVideo" method="post">
                        <input type="hidden" name="id" value="<%= vid %>">
                        <input type="text" name="title" class="input-field" value= "<%= vt.getTitle() %>">
                        <input type="url" name="url" class="input-field" value = "https://www.youtube.com/watch?v=<%= vt.getVideoID() %>"  >

                        
                        <select id="category" class="dropdown" name="category" required>
                            <option value="<%= vt.getCategory() %>"><%= vt.getCategory() %></option>
                            <option value="Career Development">Career Development</option>
                            <option value="IT Security and Best Practices">IT Security and Best Practices</option>
                            <option value="Technical Skills">Technical Skills</option>
                            <option value="Creative Skills">Creative Skills</option>
                            <option value="Professional Development">Professional Development</option>
                        </select>

                        <!-- Add the line with views and date here -->
                        <div class="video-info">
                            <span><%= vt.getTimeSinceUpload() %></span>
                            <span style="margin-left: auto;"><%= vt.getViews() %> views <i class="fas fa-eye"></i></span>
                        </div>
                        
                        <div class="radio-group">
                   <%  if( vt.isEnabled() == true ){ %>
                              <label><input type="radio" name="videoType" value="enable" checked> Enabled</label>
                              <label><input type="radio" name="videoType" value="disable" > Disabled</label>
                        
                   <%  }      
                        else{
                        	
                    %>
                            <label><input type="radio" name="videoType" value="enable" > Enabled</label>
                            <label><input type="radio" name="videoType" value="disable" checked> Disabled</label>
                   <% } %>  
                        

                       
                       
                        </div>
                        <input type = hidden name ="id"  value =<%= vid %>   >
                        <input type="submit" class="btn-update" name = "submit" value = "Update">
                       
                    </form>
                    
                    <form action="deleteVideo" method="post">
	                    <input type = hidden name ="id"  value =<%= vid %>   >
	                    <input type="submit" class="btn-delete" name = "submit" value = "Delete" onclick="confirm('Are you sure you want to delete this tutorial?')">
                   </form>
                    
              <%
                           }
                     }
              %>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies (Popper.js) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
</body>
</html>
