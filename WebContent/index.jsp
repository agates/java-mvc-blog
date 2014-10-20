<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="agates.blog.Content" %>
<%@ page import="agates.blog.Constants" %>
<%@ page import="agates.blog.User" %>
<%
	User user = (User)request.getAttribute(Constants.USER_SESSION);
	List<Content> content = (List<Content>)request.getAttribute("content");
%>	
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel='stylesheet' href='./css/main.css'>
		<title>View Contact Info</title>
	</head>
	<body>
		<div id="main">
			<nav id='user'>
				<ul>
					<% if (user == null) { %>
						<li><a href='./Login'>Login</a></li>
					<% } else { %>
						<li><a href='./Logout'>Logout</a></li>
					<% } %>
				</ul>
			</nav>
			<% if (content != null) { %>
				<article>
				<% for (Content c : content) { %>
					<section>
						<h2><a href='./ViewContent?id=<%= c.getId() %>'><%= c.getTitle() %></a></h2>
						<p><%= c.getContent() %>...</p>
					</section>
			 	<% } %>
			 	</article>
			<% }
			else { %>
				<%= "No content to display!" %>
			<% } %>
			<!-- <a href='./search.jsp'>Search cities</a> -->
			<!-- <a href='./add.jsp'>Add city</a> -->
		</div>
	</body>
</html>