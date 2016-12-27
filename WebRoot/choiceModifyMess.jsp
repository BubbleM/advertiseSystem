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
    <title>修改注册信息</title>
  </head>
  
  <body>
    <CENTER>
    	<a href="register.jsp">
    		<button class="btn btn-Danger" id="change">我要修改注册信息</button><hr>
    	</a>
    	<button class="btn btn-Info">我不想修改了</button>
    </CENTER>
  </body>
  <script type="text/javascript">
  	
  </script>
</html>
