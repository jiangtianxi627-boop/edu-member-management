<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h2>欢迎来到主页</h2>
<form method="post" action="/logout">
    <security:csrfInput/>
    <input type="submit" value="注销">
</form>
</body>
</html>
