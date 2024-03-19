<%@ page import="org.example.Constants.WebConstants" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/actors"%> method="POST">

    <input type="submit"  value="Create"/>
    <br/><br/>
    Full name: <input name="fullName" value="name" />
    <br><br>
    Year: <input name="year"  />
    Month: <input name="month"  />
    Day: <input name="day"  />
</form>
</body>
</html>