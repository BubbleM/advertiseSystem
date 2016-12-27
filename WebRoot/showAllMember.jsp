<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.mybean.data.ShowByPage" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:useBean id="show" type="com.mybean.data.ShowByPage" scope="session"/>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'showAIIMember.jsp' starting page</title>
  </head>
  
  <body>
  	<center>
		<P>显示会员信息。
		<br>每页最多显示<jsp:getProperty name= "show" property="pageSize" />条信息。
		<br>当前显示第<Font color=blue>
 			<jsp:getProperty name= "show" property="showPage" />
 		</Font>页,共有
 		<Font color=blue><jsp:getProperty name= "show" property="pageAllCount" />
 		</Font>页。
		<BR>当前显示的内容是：
 		<table border=2>
			<tr>
			<th>会员名</th><th>广告标题</th><th>电话</th>
			<th>email</th><th>广告词</th><th>广告照片</th>
			</tr>
 			<jsp:getProperty name= "show" property="presentPageResult" />
 		</table>
		<BR>单击“前一页”或“后一页”按钮查看信息
		 <Table>
		 	<tr><td><FORM action="helpShowMember" method=post>
		 	<Input type=hidden name="showPage" value="<%=show.getShowPage()-1 %>" >
		 	<Input type=submit name="g" value="前一页">
		 	</FORM>
		 	</td>
		 <td><FORM action="helpShowMember" method=post>
		 <Input type=hidden name="showPage" value="<%=show.getShowPage()+1 %>" >
		 <Input type=submit name="g" value="后一页">
		 </Form>
		 </td>
		 <td> <FORM action="helpShowMember" method=post>
		 输入页码：<Input type=text name="showPage" size=5 >
		 <Input type=submit name="g" value="提交">
		 </FORM>
		 </td>
		 </tr>
		 </Table>
</center>

  </body>
</html>
