<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mybean.data.MemberInform" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="main.html" %>
    <base href="<%=basePath%>">
    <title>My JSP 'showLookedMember.jsp' starting page</title>
  </head>
<jsp:useBean id="inform" type="com.mybean.data.MemberInform" scope="request" />  
  <body>
  	<Center>
<table border=2>
 <tr>
 <th>会员名</th><th>广告标题</th><th>电话</th>
 <th>email</th><th>广告词</th><th>广告照片</th>
</tr>
<tr>
 <td><jsp:getProperty name= "inform" property="logname" /></td>
 <td><jsp:getProperty name= "inform" property="advertiseTitle" /></td>
 <td><jsp:getProperty name= "inform" property="phone" /></td>
 <td><jsp:getProperty name= "inform" property="email" /></td>
 <td>a<jsp:getProperty name= "inform" property="message" /></td>
 <td><img src=img/<jsp:getProperty name= "inform" property="pic" /> width=50 height=50 >
 </img></td> 
</table>
</Center>
  </body>
</html>
