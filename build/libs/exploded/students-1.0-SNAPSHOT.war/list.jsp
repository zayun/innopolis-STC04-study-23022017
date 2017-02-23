<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List</h1>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>FIO</th>
        <th>BirthDay</th>
        <th>sex</th>
        <th>GroupID</th>
        <th>Actions</th>
    </tr>
    <a href="/students/addstudent">Добавить студентика</a>
<c:forEach items="${studentList}" var="student">

    <tr>
    <td><c:out value="${student.id}"></c:out></td>
    <td><c:out value="${student.name}"></c:out></td>
    <td><c:out value="${student.sex}"></c:out></td>
    <td><c:out value="${student.birthdate}"></c:out></td>
    <td><c:out value="${student.idGroup}"></c:out></td>

        <td><a href="/students/edit?id=${student.id}">edit</a>
            /
        <a href="/students/delete?id=${student.id}">del</a></td>
    </tr>

    <%--<c:out value="${userItem.name}"></c:out>--%>
    <%--<c:out value="${userItem.type}"></c:out>--%>
</c:forEach>
</table>
</body>
</html>
