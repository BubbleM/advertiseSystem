<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mybean.data.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="register" type="com.mybean.data.Register" scope="request" />
<html>
  <head>
  	<%@ include file="main.html"%>
    <base href="<%=basePath%>">
    <title>显示注册信息</title>
  </head>
  
  <body>
    <CENTER>
    	<font size=4 color=blue>
    		<br><jsp:getProperty name="register" property="backNews"/>
    	</font>
    	<table>
    		<tr>
    			<td>注册的会员名称：</td>
    			<td>
    				<jsp:getProperty name="register" property="logname"/>
    			</td>
    		</tr>
    		<tr>
    			<td>注册的广告标题:</td>
    			<td>
    				<jsp:getProperty name="register" property="advertiseTitle"/>
    			</td>
    		</tr>
    		<tr>
    			<td>注册的电子邮件：</td>
    			<td>
    				<jsp:getProperty name="register" property="email"/>
    			</td>
    		</tr>
    		<tr>
    			<td>注册的联系电话：</td>
    			<td>
    				<jsp:getProperty name="register" property="phone"/>
    			</td>
    		</tr>
    	</table>
    	<table>
    		<tr>
    			<td>您的广告词:</td>
    		</tr>
    		<tr>
    			<td>
    				<textarea rows="6" cols="30" name="message">
    					<jsp:getProperty property="message" name="register"/>
    				</textarea>
    			</td>
    		</tr>
    	</table>
    </CENTER>
  </body>
</html>	