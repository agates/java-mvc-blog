<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.List" %>
<%@ page import="agates.blog.Content" %>
<%@ page import="agates.blog.Constants" %>
<%@ page import="agates.blog.User" %>
<%
	User user = (User)request.getAttribute(Constants.USER_SESSION);
	String content = (String)request.getAttribute("content");
%>	
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel='stylesheet' href='./css/main.css'>
		<title>View Contact Info</title>
		<script src='./js/json2.js'></script>
		<script type="text/javascript">
			var content = <%=content %>;
			function printChildren(children) {
			    var out = "";
			    for (var i = 0; i < children.length; ++i) {
			        var child = children[i];
			        document.getElementById('comments').innerHTML += i;
			        out += "<div class='comment'><h6><a href='./ViewContent?id=" + child.id + "'>"
			        		+ child.user.username + " on " + child.date + "</a></h6><p>"
			        		+ child.content + "</p>" + printChildren(child.children);
			    }
			    
			    return out + "</div>";
			}

			function setContent() {
			    var out = "<h2 id='title'><a href='./ViewContent?id=" + content.id + "'>" + content.title + "</a></h2>";
			    out += "<p id='user'>" + content.user.username + " on " + content.date + "</h6>";
			    out += "<p id='text'>" + content.content + "</p>";
			    document.getElementById('content').innerHTML = out;
			    document.getElementById('comments').innerHTML = printChildren(content.children);
			}
		</script>
	</head>
	<body onload="setContent();">
		<div id="main">
			<% if (user == null) { %>
				<a href='./Login'>Login</a>
			<% } else { %>
				<a href='./Logout'>Logout</a>
			<% } %>
			<a href='./ViewContent'>Main Content List</a>
			<article>
				<section id="content"></section>
				<section id="comments"></section>
		 	</article>
		</div>
	</body>
</html>