<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@ include file="main.html" %>
    <title>广告系统首页面</title>
  </head>
  
  <body>
	<CENTER> 
		<h1 style="margin-bottom:25PX;"><Font Size=5 color=red>欢迎您来这里做广告<br></font></h1>
		<img src="img/01.jpg" width=250px height=250px ></img>
		<img src="img/01.jpg" width=250px height=250px ></img>
		<img src="img/01.jpg" width=250px height=250px ></img>
		<footer style="margin-top:40PX;">
			<author>author:BubbleM</author>
			<address>address:西安邮电大学---电子商务1401---02142027</address>
		</footer>
	</CENTER>
  </body>
</html>
