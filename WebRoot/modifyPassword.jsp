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
    <title>修改密码</title>
	
  </head>
  
  <body>
  <CENTER>
	<BR>输入您的密码：
	<FORM action="helpModifyPassword" Method="post">
	<BR>当前密码:<Input type=text name="oldPassword">
	<BR>新密码: <Input type=password name="newPassword">
	<BR><Input type=submit name="g" value="提交">
	</Form>
</CENTER>
  </body>
</html>
