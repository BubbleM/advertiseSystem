<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
	<%@include file="main.html" %>
  </head>
  
  <body>
  <CENTER>
		<FORM action="helpRegister" name=form>
			<table>
			 输入您的信息，会员名字必须由字母和数字组成，带*号项必须填写。
				 <tr><td><br>会员名称:</td><td><br><Input type=text name="logname" >*</td></tr>
				 <tr><td><br>设置密码:</td><td><br><Input type=password name="password">*</td></tr>
				 <tr><td><br>广告标题:</td><td><br><Input type=text name="advertiseTitle">* </td></tr>
				 <tr><td><br>电子邮件:</td><td><br><Input type=text name="email"></td></tr>
				 <tr><td><br>联系电话:</td><td><br><Input type=text name="phone"></td></tr>
			 </table>
			 <table>
				 <tr><td><br>输入您的广告词：</td></tr>
				 <tr>
				 	<td><TextArea name="message" Rows="6" Cols="30"></TextArea></td>
				 </tr>
				 <tr><td><br><Input type=submit name="g" value="提交"></td> </tr>
			</table>
		</Form>
	</CENTER> 
  </body>
</html>
