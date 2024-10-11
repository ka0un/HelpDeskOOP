<%@ page import="javax.servlet.http.*, javax.servlet.jsp.*" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">


<title>Create New Thread</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/forum.css" rel="stylesheet">
 <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body class="threadBody">
<%
String username = (String) session.getAttribute("username");

if(username == null){
	%>
	<script type="text/javascript">
        alert("Login First!");
        window.location.href = "login.jsp";  
    </script>
	<%
	
}
%>
	<h1>CREATE NEW THREAD</h1>
	<div class="addThreadDiv">
	<form action="threadControllerServlet" method="post">
	
	<input type="hidden" name="command" value="ADD">
	
  <div class="form-group">
    <label for="exampleInputEmail1">Title</label>
    <input type="text" name="threadName" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter title">
  </div>
  
  <div class="form-group">
      <label for="exampleInputEmail1">Thread Content</label><br>
      <textarea rows="10" cols="170" name="content"></textarea>
  </div>
  
  <div class="form-check">
    <input type="checkbox" class="form-check-input" id="exampleCheck1">
    <label class="form-check-label" for="exampleCheck1">I agree to terms and conditions</label>
  </div>
  
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<br><br>

<div style="clear:both;">
	<p>
		<a href="list-forum.jsp">Back to list</a>
	</p>
</div>
	
	
	</div>
	

</body>
</html>