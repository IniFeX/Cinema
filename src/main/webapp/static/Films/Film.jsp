<%@ page import="org.example.Models.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Models.Actor" %>
<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.User" %>
<%@ page import="org.example.Models.Role" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/13/2023
  Time: 6:17 PM
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
  Film film = (Film) request.getAttribute("film");
%>
<h2><%=film.getName() %></h2>
<br/>
Director: <%=film.getDirector().getFullName() %>
<br/>
Year of issue: <%=film.getIssuedAt().toString() %>
<br/>
Actors: <%
    for(Actor actor : film.getActors()) {
%>
<%=actor.getFullName()%>
<br/>
<%
    }
%>
<%
    String id = WebConstants.getUriId(request.getRequestURI());
%>
<%
    if (user.getRole() == Role.ADMIN) {
%>
<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/film/" + film.getId() %>" method="post">
    <input type="hidden" name="_method" value="DELETE"><jsp:text /></input>
    <input type="submit" value="Delete" />
</form>
<%
    }
%>

</body>
</html>
