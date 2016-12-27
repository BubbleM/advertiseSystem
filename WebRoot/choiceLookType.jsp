<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="main.html" %>
    <base href="<%=basePath%>">
    <title></title>
  </head>
  
  <body>
  	<center>
  		<form action="helpShowMember" method="post" name="form">
  			分页显示全体会员
		 	<input type="hidden" value="1" name="showPage" size=5>
		 	<input type="submit" value="显示" name="submit">
		 </form>
 		<form action="helpShowMember" method="get" name="form">
 			输入要查找的会员名：
			<input type="text" name="logname" size=5>
			<input type="submit" value="显示" name="submit">
  		</form>
  	</center>
  </body>
</html>
