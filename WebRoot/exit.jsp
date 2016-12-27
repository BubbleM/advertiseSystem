<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@include file="main.html" %>
    <title>退出登录页面</title>
  </head>
  
  <body>
    <Font >
	  <CENTER>
		<br>你确定要退出吗？
		<form action="helpExitLogin" method="post"><br>
			 <input type="submit" name ="g" value="确认">
			 <input type=submit name="fileName" value="取消">
		 </form>
		</CENTER>
	</font> 
  </body>
</html>
