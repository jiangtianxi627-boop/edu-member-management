<%--
  Created by IntelliJ IDEA.
  User: kriss
  Date: 2025-05-24
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <form action="/login.do" method="post">
    用户名: <input type="text" name="username"><br>
    密  码: <input type="password" name="password"><br>
    <%--动态生成token--%>
    <security:csrfInput/>
    <input type="submit" value="登录">
  </form>
</body>
</html>
