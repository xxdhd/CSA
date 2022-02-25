<%--
  Created by IntelliJ IDEA.
  User: kongzhihao
  Date: 2022/2/23
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <input  type="text" name="age" ><br/>
    <input type="text" name="username"><br/>
    <input type="text" name="password"><br/>
    <input type="submit" name="提交">
</form>
</body>
</html>
