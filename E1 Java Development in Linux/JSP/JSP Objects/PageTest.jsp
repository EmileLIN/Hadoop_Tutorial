<%@ page language = "java" %>
<%@ page import = "java.util.*" %>

<%@ include file = "./PageTest2.jsp" %>
<jsp:include page="./PageTest3.jsp"/>


<%
	ArrayList<String> arr = new ArrayList<String>();
	arr.add("apple");
	arr.add("banana");
	out.println(arr.toString());

	
%>

<jsp:forward page = "./PageTest4.jsp">
	<jsp:param name = "album" value="Shigatsu wa kimi no uso"/> 
</jsp:forward>

<%
	out.println("After Turn Up, A Little Sweet");
%>
