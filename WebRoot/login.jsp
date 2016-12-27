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
    <title>登录页面</title>
  </head>
  
  <body>
    <CENTER>
		<FORM action="helpLogin" Method="post">
			<table border=2>
				<thead>
					<tr><th>请您登录</th></tr>
				</thead>
				<tr><td>登录名称:<Input type=text name="logname"></td></tr>
				<tr><td>输入密码:<Input type=password name="password"></td></tr>
				<tr><td><Input type=submit name="g" value="提交"></td><tr>
			</table>
		</Form>
	</CENTER>
  </body>
</html>
