<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mybean.data.Password"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="password" type="com.mybean.data.Password" scope="request" />
<html>
  <head>
  	<%@ include file="main.html" %>
    <base href="<%=basePath%>">
    <title>My JSP 'showNewPasswor.jsp' starting page</title>
  </head>
  
  <body>
  <CENTER>
<jsp:getProperty name="password" property="backNews" />
<BR>您的新密码：<jsp:getProperty name="password" property="newPassword" />
<BR>您的旧密码：<jsp:getProperty name="password" property="oldPassword" />
</CENTER>
  </body>
</html>
