<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.Actor" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/19/2023
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  Actor actor = (Actor) request.getAttribute("actor");
%>
<form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/actor/" + actor.getId()%> method="POST">
  <input type="hidden" name="_method" value="PUT"><jsp:text /></input>
  <input type="submit"  value="Create"/>
  <br/><br/>
  Full name: <input name="fullName" value=<%=actor.getFullName()%> />
  <br><br>
  Year: <input name="year" value=<%=actor.getDateOfBirth().getYear()%> />
  Month: <input name="month" value=<%=actor.getDateOfBirth().getMonth()%> />
  Day: <input name="day" value=<%=actor.getDateOfBirth().getDay()%> />
</form>
</body>
</html>
