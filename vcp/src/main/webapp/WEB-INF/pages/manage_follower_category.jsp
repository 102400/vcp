<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>manage_follower_category</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>

<form action="/ajax/followerCategory/addCategory" class="form-inline">
	<div class="form-group">
		<input type="text" id="categoryName" class="form-control" placeholder="分类name">
		<input type="text" id="categoryDescription" class="form-control" placeholder="分类description">
		<button type="button" id="addCategoryButton" class="btn btn-default">增加分类</button>
	</div>
</form>

<table class="table table-hover" id="categoryTable">
	<tr>
		<th>categoryId</th>
		<th>name</th>
		<th>description</th>
	</tr>
	<c:forEach items="${followerCategoryList}" var="followerCategory">
		<tr>
			<td>${followerCategory.categoryId}</td>
			<td>${followerCategory.name}</td>
			<td>${followerCategory.description}</td>
		</tr>
	</c:forEach>
</table>

<form action="/ajax/followerCategory/addUserToCategory" class="form-inline">
	<div class="form-group">
		<input type="text" id="categoryId" class="form-control" placeholder="分类id">
		<input type="text" id="peopleId" class="form-control" placeholder="用户id">
		<button type="button" id="addUserToCategoryButton" class="btn btn-default">增加</button>
		<span id="addUserToCategoryMessage" style="color: red;"></span>
	</div>
</form>

manage_follower_category.jsp

<script type="text/javascript">
var userId = ${userId};
$(function () {
	
    $("#addCategoryButton").click(function() {
    	var categoryName = $("#categoryName").val();
    	var categoryDescription = $("#categoryDescription").val();
    	var html = $(this).html();
    	
        if(categoryName!=null&&categoryName!="") {
        	var sendData = {"userId":userId, "categoryName":categoryName, "categoryDescription":categoryDescription};  //服务端需验证usedId是否与cookie中的相同
        	
        	$.ajax({
        		type: "POST",
        		url: "/ajax/followerCategory/addCategory",
        		dataType: "json",
        		contentType: "application/json",               
                data: JSON.stringify(sendData),
        		success : function(data) {
        			var json = JSON.parse(JSON.stringify(data));
        			if(json.isSuccess) {
        				$("#categoryTable").append(
        						"<tr>"
        					+ "<td>" + json.info + "</td>"  // json.info = categoryId
        					+ "<td>" + categoryName + "</td>"
        					+ "<td>" + categoryDescription + "</td>"
        					+ "</tr>"
        				);
        				$("#categoryName").val("");
        				$("#categoryDescription").val("");
        			}
        		}
        	});
        }
        else {
            return;
        }
    });
    
    $("#addUserToCategoryButton").click(function() {
    	var categoryId = $("#categoryId").val();
    	var peopleId = $("#peopleId").val();
    	var html = $(this).html();
    	
        if(categoryName!=null&&categoryName!="") {
        	var sendData = {"userId":userId, "categoryId":categoryId, "peopleId":peopleId};  //服务端需验证usedId是否与cookie中的相同
        	
        	$.ajax({
        		type: "POST",
        		url: "/ajax/followerCategory/addUserToCategory",
        		dataType: "json",
        		contentType: "application/json",
                data: JSON.stringify(sendData),
        		success : function(data) {
        			var json = JSON.parse(JSON.stringify(data));
        			if(json.isSuccess) {
        				$("#addUserToCategoryMessage").html("更改peopleId: " + peopleId + " 到categoryId: " + categoryId + "成功");
        				$("#categoryId").val("");
        				$("#peopleId").val("");
        			}
        			else {
        				$("#addUserToCategoryMessage").html("操作失败");
        			}
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