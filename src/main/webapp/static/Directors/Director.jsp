<%@ page import="org.example.Models.Director" %>
<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.User" %>
<%@ page import="org.example.Models.Role" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/19/2023
  Time: 7:18 PM
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
  Director director = (Director) request.getAttribute("director");
%>
<h2><%=director.getFullName() %></h2>
<br/>
Date of birth: <%=director.getDateOfBirth().toString() %>
<br/>

<%
    if (user.getRole() == Role.ADMIN) {
%>
<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/director/" + director.getId() %>" method="POST">
    <input type="hidden" name="_method" value="DELETE"><jsp:text /></input>
    <input type="submit" value="Delete" />
</form>
<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/redactDirector/" + director.getId()%>">
    <input type="submit" value="Redact" />
</form>
<%
    }
%>
</body>
</html>
