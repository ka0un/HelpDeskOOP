<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Helpdesk</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa; /* Light background */
        }
        .btn-custom {
            background-color: #0B60DB; /* Custom blue color */
            color: white; /* Text color */
            font-size: 1.5rem; /* Bigger font size */
            padding: 20px 40px; /* Bigger padding */
            border: none; /* Remove border */
            transition: background-color 0.3s;
        }
        .btn-custom:hover {
            background-color: black; /* Change color on hover */
        }
        .section {
            display: flex;
            align-items: center; /* Center content vertically */
            justify-content: center; /* Center content horizontally */
            height: 100vh; /* Full height */
            color: white; /* Text color */
        }
        .left-section {
            background-color: #0B60DB; /* Custom blue for left section */
        }
        .right-section {
            background-color: black; /* Black for right section */
        }
    </style>
</head>
<body>
<%
String username = (String) session.getAttribute("username");

if (username == null) {
%>
    <script type="text/javascript">
        alert("Login First!");
        window.location.href = "login.jsp";  
    </script>
<%}%>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 left-section section">
                <div>
                    <h1>Create New Thread</h1>
                       <button class="btn btn-custom" onclick="window.location.href='addThread.jsp'; return false;">Start New Thread</button>
                </div>
            </div>
            <div class="col-md-6 right-section section">
                <div>
                
                    <h1>View and Reply to Threads</h1>
                    <form action="threadControllerServlet" method="post">
<input type="submit" value="View and Reply"  class="btn btn-custom">
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
