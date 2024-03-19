<%@ page import="org.example.Models.Director" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.User" %>
<%@ page import="org.example.Models.Role" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/13/2023
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        <th>Full name</th>
        <th>Date of birth</th>
    </tr>
    </thead>
    <tbody>
    <%
        User user = (User) request.getAttribute("user");
        List<Director> directors = (List) request.getAttribute("directors");
        for(Director director : directors) {
    %>
    <tr>
        <td>
            <a href=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix + "/director/"  + director.getId() %>>
                <%=Integer.toString(director.getId()) %>
            </a>
        </td>
        <td><%=director.getFullName() %></td>
        <td><%=director.getDateOfBirth().toString() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
    <%
        if (user.getRole() == Role.ADMIN) {
    %>
    <form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/createDirector"%>">
        <input type="submit" value="Add" />
    </form>
    <%
        }
    %>
</table>
</body>
</html>
