<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="agates.blog.CustomExceptionSet" %>
<%@ page import="agates.blog.CustomException" %>
<%@ page import="agates.blog.CustomErrorType" %>
<%
Set<CustomErrorType> errors = new HashSet<CustomErrorType>();
if (request.getAttribute("error") != null) {
	for (CustomException e : ((CustomExceptionSet)request.getAttribute("error")).get()) { 
		errors.add(e.getType());
	}
}
%>	
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="./css/main.css">
	<title>Contact Info</title>
</head>
<body>
	<form id="main" method="post" action="./Login">
		<fieldset id="login">
			<legend>Login</legend>
			<% if (errors.contains(CustomErrorType.invalidCredentials)){%><div class='error'><%=CustomErrorType.invalidCredentials %></div> <%} %>
			<div class="labels">
				<div class='field'><label for="username">Username:</label><input type='text' id='username'
					<% if (errors.contains(CustomErrorType.invalidUsername)){%>class='error' <%} %>
					name='username' /></div><br />
				<div class='field'><label for="password">Password:</label><input type='text' id='password'  
					<% if (errors.contains(CustomErrorType.invalidPassword)){%>class='error' <%} %>
					name='password' /></div>
			</div>
		</fieldset>
		<div>
			<input type='submit' id='submit' value='Submit' />
		</div>
	</form>
</body>
</html>