<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

<c:if test="${not empty message}">
    <div style="color:#ff4444">${message}</div>
</c:if>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="name"><br>
        <input type="file" name="photo">
        <button>开始上传</button>
    </form>
    <img src="/static/img/1.jpg" alt="">
</body>
</html>