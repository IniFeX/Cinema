<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/18/2023
  Time: 10:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/profile"%> method="POST">
    <input type="hidden" name="_method" value="PUT"><jsp:text /></input>
    <%
        User user = (User) request.getAttribute("user");
    %>
    Name: <input name="username" value=<%=user.getUsername()%> />
    <br><br>
    Password: <input  name="password" value=<%=user.getPassword()%>  />

    <input type="submit"  />
</form>
</body>
</html>
