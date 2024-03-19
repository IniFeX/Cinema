<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.Director" %><%--
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
    Director director = (Director) request.getAttribute("director");
%>
<form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/director/" + director.getId()%> method="POST">
    <input type="hidden" name="_method" value="PUT"><jsp:text /></input>
    <input type="submit"  value="Create"/>
    <br/><br/>
    Full name: <input name="fullName" value=<%=director.getFullName()%> />
    <br><br>
    Year: <input name="year" value=<%=director.getDateOfBirth().getYear()%> />
    Month: <input name="month" value=<%=director.getDateOfBirth().getMonth()%> />
    Day: <input name="day" value=<%=director.getDateOfBirth().getDay()%> />
</form>
</body>
</html>
