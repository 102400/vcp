<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>followers</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<h3>${people.nickname} 的关注者</h3>
<c:if test="${isLogin&&people.userId==userId}">
	<a href="/manage/follower/category" target="blank"><button type="button" class="btn btn-default" >关注者分类管理</button></a>
</c:if>
<table class="table table-hover">
<tr>
	<th>userId</th>
	<th>nickname</th>
</tr>
<c:forEach items="${followerList}" var="user">
	<tr>
		<td><a href="/people/${user.userId}" target="blank">${user.userId}</a></td>
		<td><a href="/people/${user.userId}" target="blank">${user.nickname}</a></td>
	</tr>
</c:forEach>
</table>
followers.jsp
</body>
</html>