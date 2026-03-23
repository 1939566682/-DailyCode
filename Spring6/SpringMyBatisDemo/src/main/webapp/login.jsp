<%--
  Created by IntelliJ IDEA.
  User: zhaoss-msb
  Date: 2023/3/2
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--定义表单--%>
<form action="loginServlet" method="post">
    用户名：<input type="text" name="uname"> <br>
    密码：<input type="password" name="pwd"> <br>
    <input type="submit" value="登录">
</form>
</body>
</html>