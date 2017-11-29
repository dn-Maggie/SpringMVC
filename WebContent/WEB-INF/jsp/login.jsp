<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
      <title>Spring论坛登录</title>
    </head>
    <body>
    	 <c:if test="${!empty error}">
            <font color="red"><c:out value="${error}"></c:out></font>
        </c:if>
        <form action="<c:url value="/loginCheck.do"/>" method="post">
            用户名：
            <input type="text" name="userName">
            <br>
            密码：
            <input type="password" name="password">
            <br>
            <input type="submit" value="登录" />
            <input type="reset" value="重置" />
        </form>
    </body>
</html>