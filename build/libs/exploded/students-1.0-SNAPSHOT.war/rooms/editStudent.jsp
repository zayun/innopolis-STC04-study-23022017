<%--
  Created by IntelliJ IDEA.
  User: smoldyrev
  Date: 23.02.17
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditStudentForm</title>
</head>
<body>

<form action="/students/editstudent" method="post">
    <label for="id">id:</label>
    <input type="text" name="id" id="id" value="${student.id}" placeholder="id">

    <label for="name">FIO:</label>
    <input type="text" name="name" id="name" value="${student.name}" placeholder="FIO">

    <label for="birthdate">dd.mm.yyyy:</label>
    <input type="date" name="birthdate" id="birthdate" value="${student.birthdate}" placeholder="Input">

    <label for="group">Group:</label>
    <input type="text" name="group" id="group" value="${student.getIdGroup()}" placeholder="group">

    <label for="sex">Sex:</label>
    <input type="text" name="sex" id="sex" value="${student.sex}" placeholder="sex">

    <input type="submit" value="ok" formmethod="post">
</form>
</body>
</html>
