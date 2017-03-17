<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%--<div>--%>
    <%--<a href="/students/registration">Регистрация</a>--%>
    <%--<form action="/students/login" method="post">--%>
        <%--<label for="login">Login:</label>--%>
        <%--<input type="text" name="login" id="login" value="" placeholder="логин">--%>
        <%--<label for="password">Password:</label>--%>
        <%--<input type="password" name="password" id="password" value="" placeholder="пароль">--%>

        <%--<input type="submit" value="Submit" formmethod="post">--%>
    <%--</form>--%>
<%--</div>--%>
<%--<div>--%>
<%--<form action="/students/login" method="post">--%>
    <%--<label for="login">Login:</label>--%>
    <%--<input type="text" name="login" id="login" value="" placeholder="логин">--%>
    <%--<label for="password">Password:</label>--%>
    <%--<input type="password" name="password" id="password" value="" placeholder="пароль">--%>

    <%--<input type="submit" value="Submit" formmethod="post">--%>
<%--</form></div>--%>

<div>
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
        <input type="text" name="j_username" placeholder="Login" value="">
        <input type="password"name="j_password" placeholder="Password" required value="">
        <button type="submit">Войти</button>
    </form>
</div>
${msg}

</body>
</html>
