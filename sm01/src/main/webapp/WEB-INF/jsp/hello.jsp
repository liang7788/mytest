<%--
  Created by IntelliJ IDEA.
  User: 31953
  Date: 2021/12/2
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <p>${CRUDmsg}<p/>
    <a href="${pageContext.request.contextPath}/user/welcome">欢迎回到页面</a>
</div>

<div>
    <form action="${pageContext.request.contextPath}/p" enctype="multipart/form-data" method="post">
        上传图片1:<input type="file" name="images"><br>
        上传图片2:<input type="file" name="images"><br>
        上传图片3:<input type="file" name="images"><br>
                <input type="submit" value="上传">
    </form>
</div>
<h2>点击图片完成下载</h2>
<div>
    <a href="${pageContext.request.contextPath}/user/load/blossom1.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom1.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load/blossom2.jpg">
        <img src="${pageContext.request.contextPath}/images/blossom2.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load/菊花.jpg">
        <img src="${pageContext.request.contextPath}/images/菊花.jpg" width="300px">
    </a>
    <a href="${pageContext.request.contextPath}/user/load/Desert.jpg">
        <img src="${pageContext.request.contextPath}/images/Desert.jpg" width="300px">
    </a>
</div>
</body>
</html>
