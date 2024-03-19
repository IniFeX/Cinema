<%@ page import="org.example.Constants.WebConstants" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script>
        $(document).on("click", "#somebutton", function() {
            $.get("someservlet", function(responseText) {
                $("#somediv").text(responseText);
            });
        });
    </script>
</head>
<body>
    <h3>Menu</h3>

    <form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/directors" %>>
        <input type="submit" value="Show directors" />
    </form>
    <form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/actors" %>>
        <input type="submit" value="Show actors" />
    </form>
    <form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/films" %>>
        <input type="submit" value="Show films" />
    </form>
    <form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/task" %>>
        <input type="submit" value="Task" />
    </form>
    <form action="<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/profile" %>">
        <input type="submit" value="Profile" />
    </form>
</body>
</html>