<%@ page language = "java" %>

<%
	String username = (String) request.getParameter("username");
	out.println("User is "+username);

%>
