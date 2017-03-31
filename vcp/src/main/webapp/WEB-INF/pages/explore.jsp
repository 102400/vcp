<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/general.css" rel="stylesheet" type="text/css"/>
<title>explore</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<form class="float_left">
<input type="hidden" name="page" value="${previous}" />
<button class="btn btn-default btn-lg btn-block" type="submit" >上一页</button>
</form>
<form class="float_right">
<input type="hidden" name="page" value="${next}" />
<button class="btn btn-default btn-lg btn-block" type="submit" >下一页</button>
</form>
<hr class="float_clear_both">
<c:forEach items="${allPictureList}" var="picture">
	<!--  
	uuid: ${picture.uuid} <br />
	user_id: ${picture.userId} <br />
	name: ${picture.name} <br />
	-->
	<div>
		<a href="/picture/${picture.uuid}" target="blank">
			<img src="/pic/${picture.uuid}${picture.suffix}"
				alt="${picture.name}"
				class="float_right"
				width="19.5%">
		</a>
	</div>
			
</c:forEach>
<hr class="float_clear_both">
<hr>
<form class="float_left">
<input type="hidden" name="page" value="${previous}" />
<button class="btn btn-default btn-lg btn-block" type="submit" >上一页</button>
</form>
<form class="float_right">
<input type="hidden" name="page" value="${next}" />
<button class="btn btn-default btn-lg btn-block" type="submit" >下一页</button>
</form>

explore.jsp

</body>
</html>