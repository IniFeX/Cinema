<%--
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
<form action="http://localhost:8080/JspApi_war/auth" method="POST">
    Name: <input name="username" />
    <br><br>
    Password: <input  name="password"  />

    <input type="submit"  />
</form>
<form action="<%="http://localhost:8080/JspApi_war/register" %>">
    <input type="submit" value="Registration" />
</form>
</body>
</html>
