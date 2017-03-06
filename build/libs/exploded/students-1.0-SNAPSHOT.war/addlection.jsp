<%--
  Created by IntelliJ IDEA.
  User: smoldyrev
  Date: 24.02.17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>добавляем лекцию</title>
</head>
<body>
<form action="/students/addlection" method="post">
    <label for="id">id:</label>
    <input type="text" name="id" id="id" value="${lection.id}" placeholder="id">

    <label for="name">name:</label>
    <input type="text" name="name" id="name" value="${lection.name}" placeholder="name">

    <label for="subject">subject:</label>
    <input type="text" name="subject" id="subject" value="${lection.subject}" placeholder="subject">

    <label for="textLection">textlection:</label>
    <input type="text" name="textLection" id="textLection" value="${lection.textLection}" placeholder="textLection">

    <label for="datetime">time:</label>
    <input type="date" name="datetime" id="datetime" value="${lection.date}" placeholder="datetime">

    <label for="groupid">Group:</label>
    <input type="text" name="groupid" id="groupid" value="${lection.groupid}" placeholder="groupid">

    <input type="submit" value="ok" formmethod="post">
</form>
</body>
</html>
