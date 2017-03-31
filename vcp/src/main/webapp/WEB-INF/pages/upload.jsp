<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>

<form method="post" enctype="multipart/form-data">
	<input name="file" type="file" multiple>(支持 .jpg .png)
	<input type="submit" value="上传">(总和不得大于256mb)
</form>

upload.jsp
</body>
</html>