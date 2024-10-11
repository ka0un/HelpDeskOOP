<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>bs5 forum list - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="CSS/forum.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">


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

  


    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content animated fadeInRight">

                    <div class="ibox-content m-b-sm border-bottom">
                        <div class="p-xs">
                            <div class="pull-left m-r-md">
                                <i class="fa fa-globe text-navy mid-icon"></i>
                            </div>
                            <h2>Welcome to our forum</h2>
                            <span>Feel free to choose the topic you're interested in.</span>
                        </div>
                    </div>

                    <div class="new-thread">
                        <button type="button" class="btn btn-primary btn-lg btn-block start-new-thread"
                                onclick="window.location.href='addThread.jsp'; return false;">Start New Thread</button>
                    </div>
                    
                    
                    <c:forEach var="tempThread" items="${THREAD_LIST}">
                    
                    <!-- set up a link for each thread -->
					<c:url var="tempLink" value="threadControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="threadId" value="${tempThread.id}" />
					</c:url>
					
					
                    <div class="ibox-content forum-container">
                        <div class="forum-item active">
                            <div class="row">
                                <div class="col-md-9">
                                    <div class="forum-icon">
                                        <i class="fa fa-shield"></i>
                                    </div>
                                    <a href="${tempLink}" class="forum-item-title" 
  >
   ${tempThread.title}
</a>
                                    <div class="forum-sub-title">${tempThread.content}</div>
                                </div>
                                
                                
                         
                                
                            </div>
                        </div>
                    </div>

                   </c:forEach>

                </div>
            </div>
        </div>
    </div>



<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
