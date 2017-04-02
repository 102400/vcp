<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:if test="${canManage}">
		<form action="/ajax/pictureAuthorize/changeAuthorize" class="form-inline">
			<select class="form-control" id="choose">
				<c:choose>
					<c:when test="${picture.authorize==1}">
						<option value="public">public</option>
						<option value="private" selected="selected">当前:private</option>
						<option value="protected">protected</option>
					</c:when>
					<c:when test="${picture.authorize==2}">
						<option value="public" selected="selected">当前:public</option>
						<option value="private">private</option>
						<option value="protected">protected</option>
					</c:when>
					<c:when test="${picture.authorize==3}">
						<option value="public">public</option>
						<option value="private">private</option>
						<option value="protected" selected="selected">当前:protected</option>
					</c:when>
				</c:choose>
			</select>
			<c:choose>
				<c:when test="${picture.authorize==3}">
					<input type="text" id="categoryId" class="form-control" placeholder="当前分类id: ${categoryId}"/><!-- 修改 -->
				</c:when>
				<c:otherwise>
					<input type="text" id="categoryId" class="form-control" placeholder="分类id(选protected才有效)"/>
				</c:otherwise>
			</c:choose>
			<button type="button" id="changePictureAuthorizeButton" class="btn btn-default">更改图片浏览权限</button>
			<a href="/manage/follower/category" target="blank"><button type="button" class="btn btn-default" >关注者分类管理</button></a>
		</form>
	</c:if>
</div>
<div>
	<img src="/pic/${picture.uuid}${picture.suffix}"
					alt="${picture.name}"
					class="img_default">
</div>
picture.jsp
<script type="text/javascript">
var userId = ${userId};
var pictureId = ${picture.pictureId};

$(function () {
	
    $("#changePictureAuthorizeButton").click(function() {
    	var choose = $("#choose").val();
    	var categoryId = $("#categoryId").val();
    	var html = $(this).html();
    	
        if(choose=="public"||choose=="private"||choose=="protected") {
        	var sendData = {"userId":userId, "pictureId":pictureId, "choose":choose, "categoryId":categoryId};  //服务端需验证usedId是否与cookie中的相同
        	
        	$.ajax({
        		type: "POST",
        		url: "/ajax/pictureAuthorize/changeAuthorizey",
        		dataType: "json",
        		contentType: "application/json",               
                data: JSON.stringify(sendData),
        		success : function(data) {
        			var json = JSON.parse(JSON.stringify(data));
        			if(json.isSuccess) {
        				alert("success!");
        			}
        			else {
        				alert("fail!");
        			}
        			location.reload();
        		}
        	});
        }
        else {
            return;
        }
    });
});
</script>
</body>
</html>