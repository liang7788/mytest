<%--
  Created by IntelliJ IDEA.
  User: 31953
  Date: 2021/11/30
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/param1/index22<%--/param2/reg--%>">
    账号<input name="username"/><br>
    密码<input name="pwd"/><br>
    <%--生日<input name="birthday"/><br>--%>
    城市<input name="address.city"/><br>
    街道<input name="address.street"/><br>
    电话<input name="address.phone"/><br>
    <input type="submit" value="reg"/>
</form>
</body>
</html>
