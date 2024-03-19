<%@ page import="org.example.Constants.WebConstants" %>
<%@ page import="org.example.Models.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Models.Director" %>
<%@ page import="org.example.Models.Actor" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/19/2023
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/createFilm"%> method="POST">
  <input type="hidden" name="_method" value="POST"><jsp:text /></input>
  <input type="submit"  value="Create"/>
  <br/><br/>
  Name: <input name="name"  />
  <br><br>
  Year: <input name="year"  />
  Month: <input name="month"  />
  Day: <input name="day"  />
  Director:
  <select name="director">
    <%
  List<Director> directors = (List) request.getAttribute("directors");
  for(Director director : directors) {
  %>

      <option value=<%=Integer.toString(director.getId())%>><%=director.getFullName()%></option>
    <%
      }
    %>
  </select>
  <br/><br/>
  Selected actors: <select name="selected_actors" multiple>
  <%
    List<Actor> selectedActors = (List) request.getAttribute("chosen_actors");
    for(Actor actor : selectedActors) {
  %>

  <option value=<%=Integer.toString(actor.getId())%>><%=actor.getFullName()%></option>
  <%
    }
  %>
</select>
</form>
<br/>
  <form action=<%="http://localhost:8080/JspApi_war" + WebConstants.prefix +"/createFilm"%> method="POST">
    <input type="hidden" name="_method" value="PUT"><jsp:text /></input>
  Actors: <select name="actor" >
    <%
      List<Actor> actors = (List) request.getAttribute("actors");
      for(Actor actor : actors) {
    %>

    <option value=<%=Integer.toString(actor.getId())%>><%=actor.getFullName()%></option>
    <%
      }
    %>
  </select>
    <input type="submit"  value="Add"/>
  <form/>
</body>
</html>
