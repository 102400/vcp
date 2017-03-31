<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>picture</title>
<style type="text/css">
</style>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<div>
	<h3>${picture.name}</h3>
	所属用户: <a href="/people/${picture.userId}">${picture.userId}</a> <br />
	所属专辑: <a href="/album/${picture.albumId}">${picture.albumId}</a> <br />
	<p>描述: ${picture.description}</p>
</div>
<div>
	<img src="/pic/${picture.uuid}${picture.suffix}"
					alt="${picture.name}"
					class="img_default">
</div>
picture.jsp
</body>
</html>