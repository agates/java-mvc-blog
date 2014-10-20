<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@ page import="agates.blog.CustomExceptionSet" %>
<%@ page import="agates.blog.CustomException" %>
<%
	CustomExceptionSet errors = (CustomExceptionSet)request.getAttribute("error");
%>	
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel='stylesheet' href='./css/main.css'>
		<title>View Contact Info</title>
	</head>
	<body>
		<div id='contactInfo'>
			<% if (errors != null)
				for (CustomException e : errors.get()) { %>
				<%= e.getType() %>: <%= e.getMessage() %><br />
			 <% }
			   else { %>
			    <%= "No errors to display" %>
			 <% } %>
		</div>
	</body>
</html>