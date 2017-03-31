<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>people</title>
</head>
<body>
<jsp:include page="include/head.jsp"></jsp:include>
<h3>${people.nickname}</h3>
关注了:<span id="following">${people.following}</span>
关注者:<span id="followers">${people.followers}</span>  <br />
<c:if test="${userId!=people.userId&&isLogin}">
	<!-- 已关注 -->
	<!-- 
	<button type="button" class="btn btn-default" id=“follow” value=“true”>已关注</button>
	 -->
	<c:choose>
		<c:when test="${isFollow}">
			<button type="button" class="btn btn-success" id="follow" value="true">已关注</button>
		</c:when>
		<c:when test="${!isFollow}">
			<button type="button" class="btn btn-default" id="follow" value="false">关注</button>
		</c:when>
	</c:choose>
</c:if>
<hr>
people.jsp

<script type="text/javascript">
var peopleId = ${people.userId};
$(function () {
	
    $("#follow").click(function() {
    	var val = $(this).val();
    	var html = $(this).html();
    	
        if(val=="true" || val=="false") {
        	var sendData = {"isFollow":val, "peopleId":peopleId};
        	
        	$.ajax({
        		type: "POST",
        		url: "/ajax/follow",
        		dataType: "json",
        		contentType: "application/json",               
                data: JSON.stringify(sendData),
        		success : function(data) {
        			var json = JSON.parse(JSON.stringify(data));
        			if(json.isSuccess) {
        				if(html=="关注") {
	                    	$("#follow").val("true");
	                    	$("#follow").html("已关注");
	                    	$("#follow").attr("class","btn btn-success");
	                    	var va = parseInt($("#followers").html());
	                    	$("#followers").html(++va);
	                    }
	                    else if(html=="已关注") {
	                    	$("#follow").val("false");
	                    	$("#follow").html("关注");
	                    	$("#follow").attr("class","btn btn-default");
	                    	var va = parseInt($("#followers").html());
	                    	$("#followers").html(--va);
	                    }
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