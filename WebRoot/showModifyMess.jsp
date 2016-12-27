<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mybean.data.ModifyMessage"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="modify" type="com.mybean.data.ModifyMessage" scope="request" />
<html>
  <head>
  	<%@ include file="main.html"%>
    <base href="<%=basePath%>">
    <title>My JSP 'showModifyMess.jsp' starting page</title>
  </head>
  
  <body>
    <CENTER>
<jsp:getProperty name="modify" property="backNews" />
<BR>您修改信息如下：
 <BR>新广告标题:<jsp:getProperty name="modify" property="newAdvertiseTitle" />
 <BR>新电话:<jsp:getProperty name="modify" property="newPhone" />
 <BR>新email:<jsp:getProperty name="modify" property="newEmail" />
 <BR>新广告词:<jsp:getProperty name="modify" property="newMessage" />
</CENTER>
  </body>
</html>
