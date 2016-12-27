<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mybean.data.Login"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="login" type="com.mybean.data.Login" scope="session"/>
<html>
  <head>
  	<%@ include file="main.html"%>
    <base href="<%=basePath%>">
    <title>登录成功后的显示页面</title>
  </head>
  
  <body>
  	<CENTER>
  		<jsp:getProperty name="login" property="backNews"/>
  		<%if(login.getSuccess()==true){ %>
  			<br>登录会员名称：<jsp:getProperty name="login" property="logname"/>
  		<%}else{%>
  			<br>登录会员名称：<jsp:getProperty name="login" property="logname"/>
  			<br>登录会员密码：<jsp:getProperty name="login" property="password"/>
  		<%}%> 
  	</CENTER>
  </body>
</html>
