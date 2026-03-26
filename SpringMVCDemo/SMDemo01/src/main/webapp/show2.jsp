<%--
  Created by IntelliJ IDEA.
  User: AQvir
  Date: 2026/3/26
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="test6">
    <p>
        <label>
            名字：
            <input type="text" name="name"/>
        </label>
    </p>
    <p>
        <label>
            年龄：
            <input type="text" name="age"/>
        </label>
    </p>
    <p>
        <label>
            密码：
            <input type="text" name="pwd"/>
        </label>
    </p>
    <p>
        性别：
        <label>
            男：
            <input type="radio" name="sex" value="男"/>
        </label>
        <label>
            女：
            <input type="radio" name="sex" value="女"/>
        </label>
    </p>
    <p>
        爱好：
        <label>
            吃：
            <input type="checkbox" name="hobby" value="吃"/>
        </label>
        <label>
            喝：
            <input type="checkbox" name="hobby" value="喝"/>
        </label>
    </p>
    <p>
        <input type="submit" value="提交">
    </p>
</form>
</body>
</html>