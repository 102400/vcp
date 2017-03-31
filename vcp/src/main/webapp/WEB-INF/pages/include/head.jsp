<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//修改
	//<li class="active">
	String active_home = "";
	String active_explore = "";
	String active_random = "";
	String active_register = "";
	String active_login = "";
	String active_upload = "";
	
	String RequestURI = request.getRequestURI();
	//System.out.println(RequestURI);
	switch(RequestURI) {
		case "/WEB-INF/pages/index.jsp":
			active_home = "active";
			break;
		case "/WEB-INF/pages/explore.jsp":
			active_explore = "active";
			break;
		case "/WEB-INF/pages/random.jsp":
			active_random = "active";
			break;
		case "/WEB-INF/pages/upload.jsp":
			active_upload = "active";
			break;
		case "/WEB-INF/pages/register.jsp":
			active_register = "active";
			break;
		case "/WEB-INF/pages/login.jsp":
			active_login = "active";
			break;
	}
	
	%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/general.css" rel="stylesheet" type="text/css"/>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style>
        body{
            padding-top: 0px;
        }
        .starter{
            padding: 40px 15px;
            text-align: center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">VCP</a>
        </div>
        
        <!-- 搜索框 -->
        <form action="/search" method="get" class="navbar-form navbar-left" role="search">  <!-- 更改  -->
	        <div class="form-group">
	        	<input type="hidden" name="type" value="bookname">
	        	<input type="text"  name="q" class="form-control" placeholder="Search">
	        </div>
	        <button type="submit" class="btn btn-default">O</button>
      	</form>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="<%=active_home %>"><a href="/">首页</a></li>
				<li class="<%=active_explore %>"><a href="/explore">探索</a></li>
				<li class="<%=active_random %>"><a href="/random">随机</a></li>
	            <c:if test="${isLogin}">
	            	<li class="<%=active_upload %>"><a href="/upload">上传</a></li>
                </c:if>
             </ul>
             <ul class="nav navbar-nav navbar-right">
             <c:choose>
                 <c:when test="${!isLogin}">
	                <li class="<%=active_register %>"><a href="/register" >注册</a></li>
	                <li class="<%=active_login %>"><a href="/login" >登陆</a></li>
                </c:when>
              	<c:otherwise>
	            	<li class="dropdown">
	          		<a href="#" class="dropdown-toggle" data-toggle="dropdown">${nickname}<span class="caret"></span></a>
		          		<ul class="dropdown-menu" role="menu">
		            	<li><a href="/people/${userId}">我的主页</a></li>
		            	<li><a href="/message">消息</a></li>
		            	<li><a href="/settings">设置</a></li>
		            	<li class="divider"></li>
		            	<li><a href="/logout">退出</a></li>
		          		</ul>
	       	 		</li>
            	</c:otherwise>
            </c:choose>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>