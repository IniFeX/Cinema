<%@ page import="org.example.Models.User" %>
<%@ page import="org.example.Constants.WebConstants" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/17/2023
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  User user = (User) request.getAttribute("user");
%>
<h2><%=user.getUsername() %></h2>

<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/profile" %>" method="post">
    <input type="hidden" name="_method" value="DELETE"><jsp:text /></input>
    <input type="submit" value="Delete" />
</form>
<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/redactUser" %>">
    <input type="submit" value="Redact" />
</form>
<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/profile" %>" method="post">
    <input type="submit" value="Sing out" />
</form>
</body>
</html>
