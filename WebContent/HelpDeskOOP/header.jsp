<%@ page import="javax.servlet.http.*, javax.servlet.jsp.*" %>
<%
    HttpSession session1 = request.getSession(false); // Do not create a new session if none exists
    String username = null;
    
    if (session1 != null) {
        username = (String) session1.getAttribute("username");
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="CSS/styles.css" rel="stylesheet" />
</head>
<body>
    <!-- Navigation-->
    <nav class="navbar navbar-light bg-light static-top">
        <div class="container">
           
            
            <%
        if (username != null) {
            %>
            	<a class="btn btn-primary"  onclick="if(!(confirm('Are you sure you want to logOut?'))) return false" href="logOut.jsp">Log out</a>
            <% 
        } else {
            out.println("Hello, Guest!");
            %>
            <a class="btn btn-primary"  href="login.jsp">Sign Up</a>
            
            <%
        }
    %>
        </div>
    </nav>
</body>
</html>