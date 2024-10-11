<%@ page import="javax.servlet.http.*, javax.servlet.jsp.*" %>
<% 

if(session != null){
session.invalidate();
response.sendRedirect("index.jsp");
}
%>