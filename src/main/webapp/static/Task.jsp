<%@ page import="org.example.Models.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.Director" %>
<%@ page import="org.example.Models.Actor" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/19/2023
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 3px solid black;
            border-collapse: collapse;
        }
    </style>
    <title>Title</title>
</head>
<body>
<table id="example" class="table table-striped table-bordered">
<thead>
<tr>
    <th>Id</th>
    <th>name</th>
    <th>Issued at</th>
    <th>Full director name</th>

</tr>
</thead>
<tbody>
<%
    List<Film> films = (List) request.getAttribute("current_films");
    List<Actor> directors = (List) request.getAttribute("actors_directors");
    List<Actor> actors = (List) request.getAttribute("actors_n_films");
    for(Film film : films) {
%>
<tr>
    <td>
        <a href="http://localhost:8080/JspApi_war<%=WebConstants.prefix%>/film/<%=Integer.toString(film.getId()) %>">
            <%=Integer.toString(film.getId()) %>
        </a>
    </td>
    <td><%=film.getName() %></td>
    <td><%=film.getIssuedAt().toString() %></td>
    <td><%=film.getDirector().getFullName() %></td>
</tr>
<%
    }
%>
</tbody>
</table>
<br/><br/>
<table id="example2" class="table table-striped table-bordered">
<thead>
<tr>
    <th>Id</th>
    <th>Full name</th>
    <th>Date of birth</th>
</tr>
</thead>
<tbody>
<%
    for(Actor actor : directors) {
%>
<tr>
    <td>
        <a href=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix + "/actor/" + actor.getId() %>>
            <%=Integer.toString(actor.getId()) %>
        </a>
    </td>
    <td><%=actor.getFullName() %></td>
    <td><%=actor.getDateOfBirth().toString() %></td>
</tr>
<%
    }
%>
</tbody>
</table>
<br/><br/>
<%
    if (actors != null) {
%>
<table id="example3" class="table table-striped table-bordered">
<thead>
<tr>
    <th>Id</th>
    <th>Full name</th>
    <th>Date of birth</th>
</tr>
</thead>
<tbody>
<%
    for(Actor actor : directors) {
%>
<tr>
    <td>
        <a href=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix + "/actor/" + actor.getId() %>>
            <%=Integer.toString(actor.getId()) %>
        </a>
    </td>
    <td><%=actor.getFullName() %></td>
    <td><%=actor.getDateOfBirth().toString() %></td>
</tr>
<%
    }
%>
</tbody>
</table>
<br/><br/>
<%
    }
%>

<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/task" %>" method="post">
    Number of films: <input type="text" name="num">
    <input type="submit" value="Show" />
</form>
<br/><br/>
<form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/task" %>" method="post">
    <input type="hidden" name="_method" value="DELETE"><jsp:text /></input>
    Years: <input type="text" name="years">
    <input type="submit" value="Delete" />
</form>
</tbody>
</body>
</html>
