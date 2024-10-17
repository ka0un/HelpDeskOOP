<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="javax.servlet.http.HttpSession" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light background */
        }
        .post-container, .reply-container {
            background-color: white; /* White background for posts */
            border: 1px solid #ddd; /* Light border */
            border-radius: 8px; /* Rounded corners */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            margin-bottom: 20px; /* Spacing between posts */
            padding: 20px; /* Padding inside posts */
        }
        .post-title {
            color: #0B60DB; /* Custom blue color for titles */
        }
        .post-content {
            color: black; /* Black text for content */
        }
        .user-info {
            font-size: 0.9rem; /* Smaller font size for user info */
            color: gray; /* Gray for user info text */
        }
        .action-buttons {
            margin-top: 15px; /* Spacing for buttons */
        }
        .btn-custom {
            background-color: #0B60DB; /* Custom blue color */
            color: white; /* Text color */
            border: none; /* Remove border */
            transition: background-color 0.3s;
        }
        .btn-custom:hover {
            background-color: black; /* Change color on hover */
        }
        .replies-title {
            margin-top: 30px; /* Space above replies title */
            font-size: 1.5rem; /* Larger font for replies section */
            color: #0B60DB; /* Custom blue color */
        }
    </style>
</head>
<body>

	
	
	
    <div class="container mt-5">
    <div>
<form action="threadControllerServlet" method="post">
<input type="submit" value="Go Back"  class="btn btn-custom">
</form>

</div><br>
        <div class="post-container">
            <h2 class="post-title">${THE_THREAD.title}</h2>
            <p class="user-info">Posted by <strong>${THE_THREAD.user}</strong> on ${THE_THREAD.created_at}</p>
            <div class="post-content">
                <p>${THE_THREAD.content}</p>
            </div>
            
        </div>
       

        <div class="replies-title">Reply</div>
        
        <div class="reply-container mt-4">
    <h5>Leave a Reply:</h5>
    <form action="postControllerServlet" method="post">
        <div class="mb-3">
        <input type="hidden" name="command" value="SAVE">
        	<input type="hidden" name="threadId" value="${THE_THREAD.id}"/>
            <textarea class="form-control" rows="3" placeholder="Type your reply here..." name="reply"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit Reply</button>
    </form>
</div>

		<div>
		<form method="post" action="postControllerServlet.java">
		<input type="hidden" value="SHOW"/>
		<input type="submit" class="btn btn-primary btn-lg"value="View Previous replies">
		</form>
		
		</div>
		
		
        <c:forEach var="tempPost" items="${POST_LIST}">
        
        
        <!-- set up a link for each thread -->
					<c:url var="tempLink" value="threadControllerServlet">
						<c:param name="command" value="EDIT" />
						<c:param name="postId" value="${tempPost.id}" />
					</c:url>
        
        <div class="reply-container">
            <p class="user-info">Reply by <strong>${tempPost.user_email}</strong> on October 12, 2024</p>
            <div class="post-content">
                <p>This is a reply to the original post. Thank you for sharing your thoughts!</p>
            </div>
        </div>
        
        
        
        </c:forEach>

        <!-- Add more replies as needed -->
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
